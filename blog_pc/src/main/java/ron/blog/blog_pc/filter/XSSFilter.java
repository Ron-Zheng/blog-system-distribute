package ron.blog.blog_pc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @Comment XSS过滤器
 * @Author Ron
 * @Date 2017年9月14日 下午2:46:24
 * @return
 */
public class XSSFilter implements Filter {
	
	FilterConfig filterConfig = null;
	
	/**
	 * @Comment 
	 * @Author Ron
	 * @Date 2017年9月14日 下午3:34:39
	 * @return
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	/**
	 * @Comment 
	 * @Author Ron
	 * @Date 2017年9月14日 下午3:34:55
	 * @return
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);
	}

	/**
	 * @Comment
	 * @Author Ron
	 * @Date 2017年9月14日 下午3:35:24
	 * @return
	 */
	@Override
	public void destroy() {
		this.filterConfig=null;
	}
}
