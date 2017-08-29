package ron.blog.blog_pc.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	/**
	 * @Comment 用户注册
	 * @Author Ron
	 * @Date 2017年8月28日 下午6:01:02
	 * @return
	 */
	@RequestMapping(value="/register")
	public String register(HttpServletRequest request){
		logger.info("hello");
		return "user/register";
	}
}
