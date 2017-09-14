package ron.blog.blog_pc.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringEscapeUtils;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	/**
	 * 定义一个表，指定以那些字符开头的参数不需要转义，实现一些特殊需求
	 * 如：使用富文本编辑器时，提交过来的数据已经经过转义，所以就不用再转义
	 */
	private static String[] DONTCLEAR_TABLE_PRIFIX={"layedit_"};
	
	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	/**
	 * @Comment 重写getParameterValues方法，获取参数值转义后返回
	 * @Author Ron
	 * @Date 2017年9月14日 下午4:46:10
	 * @return
	 */
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if(isEscapeHtml(parameter)){
			return values;
		}
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
	}

	/**
	 * @Comment 重写getParameter方法，获取参数值转义后返回
	 * @Author Ron
	 * @Date 2017年9月14日 下午4:45:29
	 * @return
	 */
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		if(isEscapeHtml(parameter)){
			return value;
		}
		if (value == null) {
			return null;
		}
		return cleanXSS(value);
	}

	/**
	 * @Comment 获取请求Header的内容，转化之后返回
	 * @Author Ron
	 * @Date 2017年9月14日 下午4:39:30
	 * @return
	 */
	public String getHeader(String name) {
		String value = super.getHeader(name);
		if (value == null)
			return null;
		return cleanXSS(value);
	}
	
	/**
	 * @Comment 是否转义
	 * @Author Ron
	 * @Date 2017年9月14日 下午4:58:38
	 * @return
	 */
	private boolean isEscapeHtml(String parameter) {
		for(int i = 0 ; i < DONTCLEAR_TABLE_PRIFIX.length; i++){
			String prefix = DONTCLEAR_TABLE_PRIFIX[i];
			if(parameter.startsWith(prefix)){
				return true;
			}
		}
		return false;
	}

	/**
	 * @Comment 标签转化
	 * @Author Ron
	 * @Date 2017年9月14日 下午4:32:32
	 * @return
	 */
	private String cleanXSS(String value) {
		value = value != null ? StringEscapeUtils.escapeHtml4(value.trim()) : null;
		return value;
	}
}
