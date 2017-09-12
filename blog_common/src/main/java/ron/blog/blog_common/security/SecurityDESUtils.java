package ron.blog.blog_common.security;

import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.Base64Utils;

public class SecurityDESUtils {
	private static Logger logger = LogManager.getLogger(SecurityDESUtils.class);
	
	private static final String ENCODEING = "UTF-8";
	private static final String ALGORITHM = "DES";//加密算法
	
	/**
	 * @Comment DES算法加密
	 * @param plaintext 需要加密的文本
	 * @param secureKey 长度不能小于8
	 * @Author Ron
	 * @Date 2017年9月12日 上午11:54:12
	 * @return
	 */
	public static String encrypt(String plaintext, String secureKey) {
		String encryptStr = "";
		try {
			byte[] datasource = plaintext.getBytes(ENCODEING);
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(secureKey.getBytes(ENCODEING));
			
			SecretKeyFactory keyFactory =  SecretKeyFactory.getInstance(ALGORITHM);
			SecretKey securekey = keyFactory.generateSecret(desKey);
			
			//Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			
			//用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			
			//现在，获取数据并加密
			//正式执行加密操作
			byte[] encryptSrc = cipher.doFinal(datasource);
			
			encryptStr = Base64Utils.encodeToString(encryptSrc);
		} catch (Exception e) {
			logger.error("DES加密报异常",e);
		}
		return encryptStr;
	}
	
	/**
	 * @Comment 解密
	 * @param encryptStr 已加密的字符串
	 * @param secureKey 长度不能小于8
	 * @Author Ron
	 * @Date 2017年9月12日 下午1:12:56
	 * @return
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	public static String decrypt(String encryptStr, String secureKey){
		String decryptStr = "";	
		try {
			byte[] src = Base64Utils.decodeFromString(encryptStr);
			// DES算法要求有一个可信任的随机数源
			SecureRandom random = new SecureRandom();
			// 创建一个DESKeySpec对象
			DESKeySpec desKey = new DESKeySpec(secureKey.getBytes(ENCODEING));
			
			// 创建一个密匙工厂
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
			
			// 将DESKeySpec对象转换成SecretKey对象
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, random);
			// 真正开始解密操作
			byte[] decryptSrc = cipher.doFinal(src);
			decryptStr = new String(decryptSrc,ENCODEING);
		} catch (Exception e) {
			logger.error("DES解密失败",e);
		}
		return decryptStr;
	}
	
	public static void main(String[] arg) throws Exception{
		String src="ninhao";
		String srKey="40c7f529c177487bb3b03cf16e962c82";
		String enString = encrypt(src,srKey);
		System.out.println("加密："+enString);
		
		System.out.println("解密："+decrypt(enString,srKey));
	}
}
