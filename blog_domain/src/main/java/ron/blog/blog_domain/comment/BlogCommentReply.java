package ron.blog.blog_domain.comment;

import java.util.Date;

public class BlogCommentReply {
	private Integer id;
	private Integer pid;
	private Integer children;
	private String replyContent;
	private String replyUserName;
	private String repliedUserName;
	private Date replyDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getChildren() {
		return children;
	}
	public void setChildren(Integer children) {
		this.children = children;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyUserName() {
		return replyUserName;
	}
	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName;
	}
	public String getRepliedUserName() {
		return repliedUserName;
	}
	public void setRepliedUserName(String repliedUserName) {
		this.repliedUserName = repliedUserName;
	}
	public Date getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}
	
}
