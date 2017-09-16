package ron.blog.blog_pc.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ron.blog.blog_common.constant.VerifyCodeEnum;
import ron.blog.blog_common.resp.ResCode;
import ron.blog.blog_common.resp.Resp;
import ron.blog.blog_common.utils.IdGenerator;
import ron.blog.blog_common.utils.ImageVerifyCodeUtils;
import ron.blog.blog_domain.user.BlogUserBase;
import ron.blog.blog_facade.user.UserBaseFacade;
import ron.blog.blog_facade.user.VerifyCodeFacade;
import ron.blog.blog_pc.controller.base.BaseController;

/**
 * @Comment 用户控制器
 * @Author Ron
 * @Date 2017年8月22日 下午3:20:12
 * @return
 */
@RequestMapping("/user")
@Controller
public class UserController extends BaseController {
	private Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	UserBaseFacade userBaseService;
	@Autowired
	VerifyCodeFacade verifyCodeService;
	
	/**
	 * @Comment 用户注册
	 * @Author Ron
	 * @Date 2017年8月28日 下午6:01:02
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String register(HttpServletRequest request){
		logger.info("用户进入注册页面");
		return "user/register";
	}
	
	/**
	 * @Comment 登录页面
	 * @Author Ron
	 * @Date 2017年9月13日 下午5:06:29
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(HttpServletRequest request){
		logger.info("用户进入登录页面");
		return "user/login";
	}
	
	/**
	 * @Comment 获取验证码
	 * @Author Ron
	 * @Date 2017年8月31日 下午5:23:25
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getVerifyCode",method=RequestMethod.POST)
	public Resp getVerifyCode(HttpServletRequest request) {
		ResCode resCode = ResCode.SUCCESS;
		String email = request.getParameter("email");
		if(!StringUtils.isEmpty(email)){
			boolean isExist = userBaseService.checkEmail(email);
			if(isExist){
				resCode = ResCode.DATA_EXIST;
			}else{
				//生成数据并发送邮件，邮件发送成功之后将验证码存入数据库
				logger.info("发送验证码邮件："+email);
				String uid = verifyCodeService.insertVerifyCode(email,VerifyCodeEnum.type.REGISTER);
				return new Resp(ResCode.SUCCESS, uid);
			}
		}else{
			resCode = ResCode.DATA_EMPTY;
		}
		return new Resp(resCode, "");
	}
	
	/**
	 * @Comment 注册信息提交
	 * @Author Ron
	 * @Date 2017年8月31日 下午4:07:47
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/submitRegister",method=RequestMethod.POST)
	public Resp submitRegister(@Valid BlogUserBase user,BindingResult result,HttpServletRequest request){
		if (result.hasErrors()) {
			return new Resp(ResCode.VALIDATE_FAILED, result.getFieldErrors());
		}else{
			//验证验证码
			String vuid=request.getParameter("verifyCodeUid");
			String vCode = request.getParameter("verifyCode");
			Resp vResp = verifyCodeService.checkVerifyCode(vuid, vCode);
			
			if(vResp.getResCode().equals(ResCode.SUCCESS.getCode())){
				//注册入库
				return userBaseService.insertUser(user);
			}else{
				return vResp;
			}
		}
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
