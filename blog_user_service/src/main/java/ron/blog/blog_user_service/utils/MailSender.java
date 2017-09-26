package ron.blog.blog_user_service.utils;

import org.apache.commons.mail.EmailException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ron.blog.blog_common.utils.MailUtils;

/**
 * @Comment 邮件发送
 * @Author Ron
 * @Date 2017年9月1日 下午5:47:45
 * @return
 */
public class MailSender extends Thread {
	private Logger logger=LogManager.getLogger(this.getClass());
	
	protected static final String FROM_NAME = "Ron博客邮件通知";
	protected String toEmail;
	protected String title;
	protected String content;
	
	public MailSender(String toEmail, String title, String content) {
		this.title=title;
		this.toEmail=toEmail;
		this.content=content;
	}

	@Override
	public void run() {
		try {
			MailUtils.sendSimpleEmail(FROM_NAME, toEmail, title, content);
		} catch (EmailException e) {
			logger.error("向【{}】发送“{}”邮件失败！", toEmail, title, e);
		}
	}
	
	public static void send(String toEmail,String title,String content){
		new MailSender(toEmail,title,content).run();
	}
}
