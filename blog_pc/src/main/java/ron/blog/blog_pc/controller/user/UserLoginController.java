package ron.blog.blog_pc.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ron.blog.blog_common.resp.ResCode;
import ron.blog.blog_common.resp.Resp;
import ron.blog.blog_common.utils.IdGenerator;
import ron.blog.blog_common.utils.ImageVerifyCodeUtils;
import ron.blog.blog_domain.user.BlogUserBase;
import ron.blog.blog_facade.user.UserBaseFacade;
import ron.blog.blog_facade.user.VerifyCodeFacade;
import ron.blog.blog_pc.controller.base.BaseController;

/**
 * @Comment 用户登录控制器 
 * @Author Ron
 * @Date 2017年9月20日 上午9:19:09
 * @return
 */
@RequestMapping("/login")
@Controller
public class UserLoginController extends BaseController {
	private Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	UserBaseFacade userBaseService;
	@Autowired
	VerifyCodeFacade verifyCodeService;
	
	/**
	 * @Comment 登录页面
	 * @Author Ron
	 * @Date 2017年9月13日 下午5:06:29
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET)
	public String login(HttpServletRequest request){
		logger.info("用户进入登录页面");
		return "user/login";
	}
	
	/**
	 * @Comment 获取图片验证码
	 * @Author Ron
	 * @Date 2017年9月15日 下午6:08:42
	 * @return
	 */
	@RequestMapping("/getImageVerifyCode")
    @ResponseBody
	public String getImageVerifyCode(HttpServletResponse response,HttpServletRequest request) {
		response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Set-Cookie", "name=value; HttpOnly");//设置HttpOnly属性,防止Xss攻击
        response.setDateHeader("Expire", 0);
        
        try {
        	String verifyCode = IdGenerator.getRandomChar(4);
        	ImageVerifyCodeUtils.getRandcode(request, response, verifyCode);
        	
        	//验证码存储
        	setSession("loginVerifyCode", verifyCode, request);
        }catch (Exception e) {
            logger.error("生成验证码失败");
        }
        
        return "";
	}
	
	/**
	 * @Comment 登录提交操作
	 * @Author Ron
	 * @Date 2017年9月16日 下午4:30:53
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/loginSubmit",method=RequestMethod.POST)
	public Resp loginSubmit(HttpServletRequest request){
		try {
			String email = request.getParameter("userEmail");
			String psw = request.getParameter("userLoginPassword");
			String verifyCode = request.getParameter("verifyCode");
			
			//验证验证码是否正确
			String storeVerifyCode = (String) getSession("loginVerifyCode", request);
			if(!storeVerifyCode.toLowerCase().equals(verifyCode.toLowerCase())){
				return new Resp(ResCode.VERIFY_CODE_ERROR,"");
			}else{
				BlogUserBase user=new BlogUserBase();
				user.setUserEmail(email);
				user.setUserLoginPassword(psw);
				Resp resp = userBaseService.login(user);
				
				//存储用户登录信息
				setUserInfoSession(resp.getData(),request);
				
				return resp;
			}
		} catch (Exception e) {
			logger.info("登录时报异常",e);
			return new Resp(ResCode.SYS_ERROR, "");
		}
	}
}
