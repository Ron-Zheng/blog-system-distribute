package ron.blog.blog_service.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ron.blog.blog_common.resp.ResCode;
import ron.blog.blog_common.resp.Resp;
import ron.blog.blog_common.security.SecurityUtils;
import ron.blog.blog_common.utils.IdGenerator;
import ron.blog.blog_dao.dao.user.BlogUserBaseDao;
import ron.blog.blog_domain.user.BlogUserBase;
import ron.blog.blog_facade.user.UserBaseFacade;

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
	 * @Comment 用户注册
	 * @Author Ron
	 * @Date 2017年9月11日 下午5:43:23
	 * @return
	 */
	@Override
	public Resp insertUser(BlogUserBase user) {
		//生成Uid
		try {
			String uid = IdGenerator.genUUID();
			user.setUid(uid);
			
			user.setUserLoginPassword(SecurityUtils.encodePassword(user.getUserLoginPassword(), user.getUserEmail()));
			
			blogUserBaseDao.insert(user);
			return new Resp(ResCode.SUCCESS, "");
		} catch (Exception e) {
			logger.error("用户注册失败，用户邮箱{}",user.getUserEmail());
			return new Resp(ResCode.SYS_ERROR, "");
		}
	}
}
