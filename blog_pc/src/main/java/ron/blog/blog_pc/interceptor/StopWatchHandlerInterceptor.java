package ron.blog.blog_pc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Comment 性能监控拦截器
 * @Author Ron
 * @Date 2017年9月20日 上午11:09:04
 * @return
 */
public class StopWatchHandlerInterceptor implements HandlerInterceptor {

	private Logger logger = LogManager.getLogger(StopWatchHandlerInterceptor.class);

	//Spring提供的一个命名的ThreadLocal实现
	private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");

	/**
	 * @Comment 预处理回调方法，实现处理器的预处理
	 * @Author Ron
	 * @Date 2017年9月20日 上午11:10:26
	 * @return
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long beginTime = System.currentTimeMillis();// 1、开始时间
		
		logger.info("开始时间："+beginTime);
		
		startTimeThreadLocal.set(beginTime);// 线程绑定变量（该数据只有当前请求的线程可见）

		return true;
	}

	/**
	 * @Comment 后处理回调方法
	 * @Author Ron
	 * @Date 2017年9月20日 上午11:11:00
	 * @return
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	}

	/**
	 * @Comment 整个请求处理完毕回调方法
	 * @Author Ron
	 * @Date 2017年9月20日 上午11:11:12
	 * @return
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long endTime = System.currentTimeMillis();//2、结束时间 
		long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
		long consumeTime = endTime - beginTime;//3、消耗的时间  
		if(consumeTime > 500) {//此处认为处理时间超过500毫秒的请求为慢请求  
			//记录到日志文件
			logger.warn(String.format("%s 花费时间 %d 毫秒", request.getRequestURI(), consumeTime));
		}else{
			logger.info(String.format("%s 花费时间 %d 毫秒", request.getRequestURI(), consumeTime));
		}
	}
}
