package ron.blog.blog_service.comment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import ron.blog.blog_domain.comment.BlogComment;
import ron.blog.blog_domain.comment.BlogCommentReply;
import ron.blog.blog_facade.comment.BlogCommetFacade;

@Component
public class BlogCommetService implements BlogCommetFacade {

	/**
	 * @Comment 查询博客评论
	 * @Author Ron
	 * @Date 2017年9月5日 下午4:11:09
	 * @return
	 */
	@Override
	public List<BlogComment> queryBlogComment(String blogCode) {
		List<BlogComment> list=new ArrayList<BlogComment>();
		
		for(int i = 0 ; i < 5 ; i++){
			BlogComment blogComment = new BlogComment();
			
			blogComment.setComContent("抢沙发00"+i+",沙发今日不抢何时抢？不抢是傻逼。");
			blogComment.setComDate(new Date());
			blogComment.setComUserName("我不是陈冠希00"+i);
			
			list.add(blogComment);
		}
		
		return list;
	}

	/**
	 * @Comment 查询评论回复
	 * @Author Ron
	 * @Date 2017年9月5日 下午4:11:24
	 * @return
	 */
	@Override
	public List<BlogCommentReply> queryBlogCommentReply(String commentCode) {
		List<BlogCommentReply> list = new ArrayList<BlogCommentReply>();
		
		for(int i = 0 ; i < 5 ; i++){
			BlogCommentReply blogCommentReply=new BlogCommentReply();
			
			blogCommentReply.setChildren(1);
			blogCommentReply.setId(i+1);
			blogCommentReply.setPid(0);
			blogCommentReply.setReplyContent("你这个傻逼，博客写成这样还敢发？");
			blogCommentReply.setReplyUserName("汤姆克鲁斯00"+i);
			blogCommentReply.setRepliedUserName("我不是陈冠希00"+i);
			blogCommentReply.setReplyDate(new Date());
			
			list.add(blogCommentReply);
		}
		
		for(int i = 0 ; i < 5 ; i++){
			BlogCommentReply blogCommentReply=new BlogCommentReply();
			
			blogCommentReply.setChildren(1);
			blogCommentReply.setId(6+i);
			blogCommentReply.setPid(i+1);
			blogCommentReply.setReplyContent("你这个傻逼，说你你能咋地？");
			blogCommentReply.setReplyUserName("我不是陈冠希00"+i);
			blogCommentReply.setRepliedUserName("汤姆克鲁斯00"+i);
			blogCommentReply.setReplyDate(new Date());
			
			BlogCommentReply blogCommentReply2=new BlogCommentReply();
			blogCommentReply2.setChildren(0);
			blogCommentReply2.setId(11+i);
			blogCommentReply2.setPid(i+1);
			blogCommentReply2.setReplyContent("你这个傻逼，说你你能咋地？");
			blogCommentReply2.setReplyUserName("我不是陈冠希00"+i);
			blogCommentReply2.setRepliedUserName("汤姆克鲁斯00"+i);
			blogCommentReply2.setReplyDate(new Date());
			
			list.add(blogCommentReply);
			list.add(blogCommentReply2);
		}
		
		for(int i = 0 ; i < 5 ; i++){
			BlogCommentReply blogCommentReply=new BlogCommentReply();
			
			blogCommentReply.setChildren(0);
			blogCommentReply.setId(16+i);
			blogCommentReply.setPid(6+i);
			blogCommentReply.setReplyContent("你这个傻逼，说你你还不服，艹？");
			blogCommentReply.setReplyUserName("汤姆克鲁斯00"+i);
			blogCommentReply.setRepliedUserName("我不是陈冠希00"+i);
			blogCommentReply.setReplyDate(new Date());
			
			list.add(blogCommentReply);
		}
		
		return list;
	}
}
