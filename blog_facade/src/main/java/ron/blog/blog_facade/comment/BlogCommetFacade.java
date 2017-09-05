package ron.blog.blog_facade.comment;

import java.util.List;

import ron.blog.blog_domain.comment.BlogComment;
import ron.blog.blog_domain.comment.BlogCommentReply;

public interface BlogCommetFacade {
	/**
	 * @Comment 查询博客评论
	 * @Author Ron
	 * @Date 2017年9月5日 下午4:08:05
	 * @return
	 */
	List<BlogComment> queryBlogComment(String blogCode);
	
	/**
	 * @Comment 查询评论回复
	 * @Author Ron
	 * @Date 2017年9月5日 下午4:09:04
	 * @return
	 */
	List<BlogCommentReply> queryBlogCommentReply(String commentCode);
}
