package ron.blog.blog_pc.controller.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import ron.blog.blog_domain.user.BlogUserBase;

/**
 * @Comment 
 * @Author Ron
 * @Date 2017年8月22日 下午3:22:18
 * @return
 */
@Controller
public class BaseController {
	
	/**
	 * @Comment Session设置
	 * @Author Ron
	 * @Date 2017年9月16日 下午4:36:15
	 * @return
	 */
	protected void setSession(String key,Object value,HttpServletRequest request) {
		request.getSession().setAttribute(key, value);
	}
	
	/**
	 * @Comment 获取Session
	 * @Author Ron
	 * @Date 2017年9月16日 下午4:36:53
	 * @return
	 */
	protected Object getSession(String key,HttpServletRequest request) {
		return request.getSession().getAttribute(key);
	}
	
	/**
	 * @Comment 设置用户Session信息
	 * @Author Ron
	 * @Date 2017年9月16日 下午4:53:44
	 * @return
	 */
	protected void setUserInfoSession(Object userBase,HttpServletRequest request) {
		setSession("userInfo",userBase,request);
	}
	
	/**
	 * @Comment 获取用户信息
	 * @Author Ron
	 * @Date 2017年9月16日 下午4:55:29
	 * @return
	 */
	protected BlogUserBase getUserInfo(HttpServletRequest request) {
		return (BlogUserBase) getSession("userInfo",request);
	}
}
