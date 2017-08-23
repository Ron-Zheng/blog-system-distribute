package ron.blog.blog_service.user;

import org.springframework.stereotype.Component;

import ron.blog.blog_common.resp.ResCode;
import ron.blog.blog_common.resp.Resp;
import ron.blog.blog_domain.user.UserLogin;
import ron.blog.blog_facade.user.UserBaseFacade;

/**
 * @Comment 用户服务
 * @Author Ron
 * @Date 2017年8月22日 下午5:12:42
 * @return
 */
@Component
public class UserBaseService implements UserBaseFacade {

	/**
	 * @Comment 用户登录实现
	 * @Author Ron
	 * @Date 2017年8月22日 下午2:55:37
	 * @return
	 */
	public Resp login(UserLogin user) {
		return new Resp(ResCode.SUCCESS,"Hello"+user.getUsername());
	}
}
