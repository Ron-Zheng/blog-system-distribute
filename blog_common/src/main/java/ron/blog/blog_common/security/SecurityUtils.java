package ron.blog.blog_common.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;

public class SecurityUtils {
	
	/**
	 * @Comment SHA1加密密码
	 * @Author Ron
	 * @Date 2017年9月12日 下午2:46:31
	 * @return
	 */
	public static String encodePassword(String psw,String salt) {
		if(StringUtils.isEmpty(psw) || StringUtils.isEmpty(salt)){
			return null;
		}else{
			return DigestUtils.sha1Hex(psw+"@ron"+salt);
		}
	}
	
	/**
	 * @Comment MD5加密密码
	 * @Author Ron
	 * @Date 2017年9月13日 下午3:09:09
	 * @return
	 */
	public static String encodePasswordMD5(String psw,String salt) {
		if(StringUtils.isEmpty(psw) || StringUtils.isEmpty(salt)){
			return null;
		}else{
			return DigestUtils.md5Hex(psw+"@ron"+salt);
		}
	}
}
