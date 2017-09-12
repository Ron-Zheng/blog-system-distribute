package ron.blog.blog_domain.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class BlogUserBase {
	private Integer id;
	
	private String uid;
	
	private String userRealyName;
	
	private String userPhone;
	
	private String userPosition;
	
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
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUserRealyName() {
		return userRealyName;
	}
	public void setUserRealyName(String userRealyName) {
		this.userRealyName = userRealyName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserPosition() {
		return userPosition;
	}
	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}
}
