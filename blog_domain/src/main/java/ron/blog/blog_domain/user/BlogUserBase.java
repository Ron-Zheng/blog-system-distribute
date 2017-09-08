package ron.blog.blog_domain.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class BlogUserBase {
	private Integer id;
	
	@NotBlank(message="common.validate.notblank")
	private String userLoginName;
	
	@Length(min=6,max=12,message="common.validate.psw.length")
	private String userLoginPassword;
	
	@NotBlank(message="common.validate.notblank")
	@Email(message="common.validate.email.format.error")
	private String userEmail;
	
	public String getUserLoginName() {
		return userLoginName;
	}
	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}
	public String getUserLoginPassword() {
		return userLoginPassword;
	}
	public void setUserLoginPassword(String userLoginPassword) {
		this.userLoginPassword = userLoginPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
