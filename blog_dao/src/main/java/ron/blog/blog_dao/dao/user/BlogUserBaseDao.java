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
	BlogUserBase login(@Param("userEmail")String userEmail,@Param("userLoginPassword")String userLoginPassword);
	
	/**
	 * @Comment 检查邮箱是否存在
	 * @Author Ron
	 * @Date 2017年8月31日 下午5:16:42
	 * @return
	 */
	int checkEmail(String email);
	
	/**
	 * @Comment 插入用户
	 * @Author Ron
	 * @Date 2017年9月11日 下午5:53:52
	 * @return
	 */
	int insert(BlogUserBase userBase);
}
