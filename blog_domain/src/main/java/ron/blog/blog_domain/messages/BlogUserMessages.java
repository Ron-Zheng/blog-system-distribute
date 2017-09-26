package ron.blog.blog_domain.messages;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

public class BlogUserMessages implements Serializable {
	/**
	 * @Comment 
	 * @Author Ron
	 * @Date 2017年9月26日 下午4:34:32
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String uid;
    
    @NotBlank(message="common.validate.notblank")
    private String title;

    private String content;

    private Date crtTime;

    private String ip;

    private Integer status;
    
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
        this.uid = uid == null ? null : uid.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}