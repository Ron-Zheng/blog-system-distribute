package ron.blog.blog_dao.dao.user;

import org.apache.ibatis.annotations.Param;

import ron.blog.blog_domain.user.BlogUserBase;

public interface BlogUserBaseDao {
	/**
	 * @Comment 登录
	 * @Author Ron
	 * @Date 2017年8月23日 下午5:34:56
	 * @return
	 */
	BlogUserBase login(@Param("userLoginName")String userLoginName,@Param("userLoginPassword")String userLoginPassword);
}
