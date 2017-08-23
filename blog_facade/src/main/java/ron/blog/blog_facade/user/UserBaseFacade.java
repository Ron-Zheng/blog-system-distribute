package ron.blog.blog_facade.user;

import ron.blog.blog_common.resp.Resp;
import ron.blog.blog_domain.user.UserLogin;

public interface UserBaseFacade {
	
	/**
	 * @Comment 用户登录接口
	 * @Author Ron
	 * @Date 2017年8月22日 下午2:55:22
	 * @return
	 */
	Resp login(UserLogin user);
}
