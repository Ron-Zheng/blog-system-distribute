package ron.blog.blog_facade.messages;

import ron.blog.blog_domain.messages.BlogUserMessages;

/**
 * @Comment 博客用户留言接口
 * @Author Ron
 * @Date 2017年9月14日 下午5:06:47
 * @return
 */
public interface BlogUserMessagesFacade {
	/**
	 * @Comment 添加信息接口
	 * @Author Ron
	 * @Date 2017年9月14日 下午5:14:16
	 * @return
	 */
	int insertMsg(BlogUserMessages messages);
}
