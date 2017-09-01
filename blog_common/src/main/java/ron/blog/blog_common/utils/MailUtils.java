package ron.blog.blog_common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ron.blog.blog_common.config.SysConfig;

public class MailUtils {
	private static Logger logger = LogManager.getLogger(MailUtils.class);
	private static int CONNECTION_TIMEOUT = 300000;//5分钟
	private static int TIMEOUT = 300000;//5分钟
	
	/**
	 * @Comment 发送简单邮件
	 * @Author Ron
	 * @Date 2017年9月1日 下午4:30:29
	 * @return
	 */
	public static String sendSimpleEmail(String fromName, String to, String subject, String content) throws EmailException{
		
		String res = null;
		SimpleEmail simpleEmail = new SimpleEmail();
		simpleEmail.setSocketConnectionTimeout(CONNECTION_TIMEOUT);
		simpleEmail.setSocketTimeout(TIMEOUT);
		simpleEmail.setHostName(SysConfig.getMail_host());
		simpleEmail.setAuthentication(SysConfig.getMail_address(), SysConfig.getMail_passowrd());
		simpleEmail.setFrom(SysConfig.getMail_address(), fromName);
		simpleEmail.setSmtpPort(Integer.parseInt(SysConfig.getMail_port()));
		if(isMailAddrPattern(to)){
			simpleEmail.addTo(to);
		}
		else{
			logger.error("不正确的邮箱格式{}", to);
			throw new EmailException("不正确的邮箱格式");
		}
		
		simpleEmail.setSubject(subject);
		simpleEmail.setMsg(content);
		try {
			res = simpleEmail.send();
		} catch (Exception e) {
			logger.error("向【{}】发送“{}”邮件失败！", to, subject, e);
		}
		return res;
	}
	
	/**
	 * @Comment 发送Html邮件
	 * @Author Ron
	 * @Date 2017年9月1日 下午4:31:04
	 * @return
	 */
	public static String sendHtmlEmail(String fromName, String to, String subject, String content) throws EmailException{
		String res = null;
		HtmlEmail htmlEmail = new HtmlEmail();
		htmlEmail.setSocketConnectionTimeout(CONNECTION_TIMEOUT);
		htmlEmail.setSocketTimeout(TIMEOUT);
		htmlEmail.setHostName(SysConfig.getMail_host());
		htmlEmail.setAuthentication(SysConfig.getMail_address(), SysConfig.getMail_passowrd());
		htmlEmail.setFrom(SysConfig.getMail_address(), fromName);
		htmlEmail.setSmtpPort(Integer.parseInt(SysConfig.getMail_port()));
		if(isMailAddrPattern(to)){
			htmlEmail.addTo(to);
		}
		else{
			logger.error("不正确的邮箱格式{}", to);
			throw new EmailException("不正确的邮箱格式");
		}
		
		htmlEmail.setSubject(subject);
		htmlEmail.setMsg(content);  
		htmlEmail.setCharset("utf-8");
		try {
			res = htmlEmail.send();
		} catch (Exception e) {
			logger.error("向【{}】发送“{}”邮件失败！", to, subject, e);
		}
		return res;
	}
	
	/**
	 * @Comment 验证邮箱格式
	 * @Author Ron
	 * @Date 2017年9月1日 下午4:33:16
	 * @return
	 */
	public static boolean isMailAddrPattern(String addr){
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher matcher = pattern.matcher(addr);
		return matcher.matches();
	}
}
