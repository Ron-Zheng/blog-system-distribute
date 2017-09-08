package ron.blog.blog_pc.controller.user;

import javax.servlet.http.HttpServletRequest;
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

import ron.blog.blog_common.resp.ResCode;
import ron.blog.blog_common.resp.Resp;
import ron.blog.blog_domain.user.BlogUserBase;
import ron.blog.blog_facade.user.UserBaseFacade;
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
				if(!userBaseService.sendVerifyCode(email)){
					resCode = ResCode.SYS_ERROR;
				}
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
			//注册入库
			return new Resp(ResCode.SUCCESS, "");
		}
	}
}
