package ron.blog.blog_facade.user;

import ron.blog.blog_common.resp.Resp;
import ron.blog.blog_domain.user.BlogUserBase;

public interface UserBaseFacade {
	
	/**
	 * @Comment 用户登录接口
	 * @Author Ron
	 * @Date 2017年8月22日 下午2:55:22
	 * @return
	 */
	Resp login(BlogUserBase user);
	
	/**
	 * @Comment 检查邮箱是否存在
	 * @Author Ron
	 * @Date 2017年8月31日 下午5:18:09
	 * @return
	 */
	boolean checkEmail(String email);
}
