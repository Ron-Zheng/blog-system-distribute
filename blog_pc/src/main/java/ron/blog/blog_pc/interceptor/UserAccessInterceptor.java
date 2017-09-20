package ron.blog.blog_pc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @Comment 用户登录检测拦截器
 * @Author Ron
 * @Date 2017年9月20日 上午9:13:05
 * @return
 */
public class UserAccessInterceptor extends HandlerInterceptorAdapter {
	
	/**
	 * 登录链接
	 */
	private final String loginUrl="/login";
	
	private Logger logger=LogManager.getLogger(UserAccessInterceptor.class);
	
	/**
	 * @Comment 预处理回调方法，实现处理器的预处理（如登录检查），第三个参数为响应的处理器
	 * @Author Ron
	 * @Date 2017年9月20日 上午10:24:16
	 * @return
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		logger.info("开始检测是否登录");
		
		//如果用户已经登录，放行
		if (request.getSession().getAttribute("userInfo") != null) {
			return true;
		}

		logger.info("用户尚未登录");
		//非法请求，跳转到登录页面
		response.sendRedirect(request.getContextPath() + loginUrl);  
		return false;
	}
}
