package ron.blog.blog_facade.user;

import ron.blog.blog_common.constant.VerifyCodeEnum;
import ron.blog.blog_common.resp.Resp;

public interface VerifyCodeFacade {
	/**
	 * @Comment 发送验证码
	 * @Author Ron
	 * @Date 2017年9月1日 下午4:58:24
	 * @return
	 */
	String insertVerifyCode(String email,VerifyCodeEnum.type type);
	
	/**
	 * @Comment 
	 * @Author Ron
	 * @Date 2017年9月11日 下午5:17:51
	 * @return
	 */
	Resp checkVerifyCode(String uid,String vCode);
}
