package ron.blog.blog_domain.comment;

import java.util.Date;

public class BlogComment {
	private String comContent;
	private Date comDate;
	private String comUserName;
	
	public String getComContent() {
		return comContent;
	}
	public void setComContent(String comContent) {
		this.comContent = comContent;
	}
	public Date getComDate() {
		return comDate;
	}
	public void setComDate(Date comDate) {
		this.comDate = comDate;
	}
	public String getComUserName() {
		return comUserName;
	}
	public void setComUserName(String comUserName) {
		this.comUserName = comUserName;
	}
	
}
