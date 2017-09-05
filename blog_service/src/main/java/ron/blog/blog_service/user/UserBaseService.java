package ron.blog.blog_service.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ron.blog.blog_common.resp.ResCode;
import ron.blog.blog_common.resp.Resp;
import ron.blog.blog_common.utils.IdGenerator;
import ron.blog.blog_dao.dao.user.BlogUserBaseDao;
import ron.blog.blog_domain.user.BlogUserBase;
import ron.blog.blog_facade.user.UserBaseFacade;
import ron.blog.blog_service.utils.MailSender;

/**
 * @Comment 用户服务
 * @Author Ron
 * @Date 2017年8月22日 下午5:12:42
 * @return
 */
@Component
public class UserBaseService implements UserBaseFacade {
	
	private Logger logger=LogManager.getLogger(this.getClass());
	
	@Autowired
	BlogUserBaseDao blogUserBaseDao;

	/**
	 * @Comment 用户登录实现
	 * @Author Ron
	 * @Date 2017年8月22日 下午2:55:37
	 * @return
	 */
	@Override
	public Resp login(BlogUserBase user) {
		user = blogUserBaseDao.login(user.getUserLoginName(), user.getUserLoginPassword());
		return new Resp(ResCode.SUCCESS,"Hello"+user.getUserEmail());
	}
	
	/**
	 * @Comment 检查邮箱是否存在
	 * @Author Ron
	 * @Date 2017年8月31日 下午5:18:28
	 * @return
	 */
	@Override
	public boolean checkEmail(String email) {
		if(blogUserBaseDao.checkEmail(email) > 0){
			return true;
		}
		return false;
	}

	/**
	 * @Comment 发送验证码
	 * @Author Ron
	 * @Date 2017年9月1日 下午4:58:39
	 * @return
	 */
	@Override
	public boolean sendVerifyCode(String email) {
		String verifyCode = IdGenerator.getRandomNum(4);
		logger.info("验证码为："+verifyCode);
		MailSender.send(email, "Ron博客验证码", "您的验证码为"+verifyCode);
		return true;
	}
}
