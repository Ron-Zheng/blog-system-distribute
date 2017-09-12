package ron.blog.blog_dao.dao.user;

import org.apache.ibatis.annotations.Param;

import ron.blog.blog_domain.user.BlogVerifyCode;

public interface BlogVerifyCodeDao {
	/**
	 * @Comment 插入验证码记录
	 * @Author Ron
	 * @Date 2017年9月11日 下午4:17:32
	 * @return
	 */
	int insert(BlogVerifyCode record);
	
	/**
	 * @Comment 获取验证码
	 * @Author Ron
	 * @Date 2017年9月11日 下午4:17:46
	 * @return
	 */
	BlogVerifyCode selectByPrimaryKey(@Param("uid")String uid);
	
	/**
	 * @Comment 更新验证码状态
	 * @Author Ron
	 * @Date 2017年9月11日 下午5:31:43
	 * @return
	 */
	int updateStatusByPrimaryKey(@Param("uid")String uid,@Param("email")String email,@Param("status")int status);
}
