package ron.blog.blog_common.security;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

public class SecurityRSAUtils {
	public static final String KEY_ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	// 公钥
	private static final String PUBLIC_KEY = "RSAPublicKey";
	// 私钥
	private static final String PRIVATE_KEY = "RSAPrivateKey";

	/**
	 * @Comment Base64解码
	 * @Author Ron
	 * @Date 2017年9月12日 下午4:45:19
	 * @return
	 */
	public static byte[] decryptBASE64(String key) {
		return Base64.decodeBase64(key);
	}

	/**
	 * @Comment Base64编码
	 * @Author Ron
	 * @Date 2017年9月12日 下午4:47:55
	 * @return
	 */
	public static String encryptBASE64(byte[] bytes) {
		return Base64.encodeBase64String(bytes);
	}

	/**
	 * @Comment 用私钥对内容进行签名
	 * @Author Ron
	 * @Date 2017年9月12日 下午4:58:10
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] data, String privateKey) throws Exception {
		// 解密由Base64编码的私钥
		byte[] keyBytes = decryptBASE64(privateKey);

		// 构造PKCS8EncodedKeySpec对象
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		// KEY_ALGORITHM 指定的加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

		// 取私钥对象
		PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 用私钥对信息生成数字签名
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(priKey);
		signature.update(data);
		return encryptBASE64(signature.sign());
	}

	/**
	 * @Comment 公钥验证数字签名
	 * @Author Ron
	 * @Date 2017年9月12日 下午5:09:03
	 * @return
	 */
	public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
		// 解密Base64编码的公钥
		byte[] keyBytes = decryptBASE64(publicKey);
		// 构造X509EncodedKeySpec对象
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

		// KEY_ALGORITHM 指定的加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

		// 获取公钥对象
		PublicKey pubKey = keyFactory.generatePublic(keySpec);

		// 用公钥验证数字签名
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(pubKey);
		signature.update(data);

		// 校验签名是否正常
		return signature.verify(decryptBASE64(sign));
	}

	/**
	 * @Comment 通过私钥解密数据
	 * @Author Ron
	 * @Date 2017年9月12日 下午5:16:09
	 * @return
	 */
	public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception {
		// 对密钥解密
		byte[] keyBytes = decryptBASE64(key);
		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/**
	 * @Comment 通过私钥解密数据
	 * @Author Ron
	 * @Date 2017年9月12日 下午5:19:39
	 * @return
	 */
	public static byte[] decryptByPrivateKey(String data, String key) throws Exception {
		return decryptByPrivateKey(decryptBASE64(data), key);
	}

	/**
	 * @Comment 通过公钥对数据进行解密
	 * @Author Ron
	 * @Date 2017年9月12日 下午5:20:27
	 * @return
	 */
	public static byte[] decryptByPublicKey(byte[] data, String key) throws Exception {
		// 对密钥解密
		byte[] keyBytes = decryptBASE64(key);

		// 获得公钥
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key pubKey = keyFactory.generatePublic(keySpec);

		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, pubKey);
		return cipher.doFinal(data);
	}

	/**
	 * @Comment 通过公钥对数据进行解密
	 * @Author Ron
	 * @Date 2017年9月12日 下午5:26:16
	 * @return
	 */
	public static byte[] decryptByPublicKey(String data, String key) throws Exception {
		return decryptByPublicKey(decryptBASE64(data), key);
	}

	/**
	 * @Comment 用公钥进行加密
	 * @Author Ron
	 * @Date 2017年9月12日 下午5:29:31
	 * @return
	 */
	public static byte[] encryptByPublicKey(String data, String key) throws Exception {
		// 对公钥解密
		byte[] keyBytes = decryptBASE64(key);
		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data.getBytes());
	}

	/**
	 * @Comment 用私钥进行加密
	 * @Author Ron
	 * @Date 2017年9月12日 下午5:34:36
	 * @return
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String key) throws Exception {
		// 对密钥解密
		byte[] keyBytes = decryptBASE64(key);
		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/**
	 * @Comment 获取私钥
	 * @Author Ron
	 * @Date 2017年9月12日 下午5:35:16
	 * @return
	 */
	public static String getPrivateKey(Map<String, Key> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return encryptBASE64(key.getEncoded());
	}

	/**
	 * @Comment 获取公钥
	 * @Author Ron
	 * @Date 2017年9月12日 下午5:35:40
	 * @return
	 */
	public static String getPublicKey(Map<String, Key> keyMap) throws Exception {
		Key key = keyMap.get(PUBLIC_KEY);
		return encryptBASE64(key.getEncoded());
	}

	/**
	 * @Comment 初始化密钥对
	 * @Author Ron
	 * @Date 2017年9月12日 下午5:36:29
	 * @return
	 */
	public static Map<String, Key> initKey() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		Map<String, Key> keyMap = new HashMap(2);
		keyMap.put(PUBLIC_KEY, keyPair.getPublic());// 公钥
		keyMap.put(PRIVATE_KEY, keyPair.getPrivate());// 私钥
		return keyMap;
	}

	public static void main(String[] args) throws Exception {
		Map<String, Key> keyMap = initKey();
		String publicKey = getPublicKey(keyMap);
		String privateKey = getPrivateKey(keyMap);
		
		System.out.println("--------------------------------------PublicKey------------------------------------------------");
        System.out.println(publicKey);
        System.out.println("--------------------------------------PrivateKey-----------------------------------------------");
        System.out.println(privateKey);
        
        System.out.println("--------------------------------------私钥加密-------------------------------------------------");
        byte[] encryptByPrivateKey = encryptByPrivateKey("Ron,您好，私钥加密".getBytes(),privateKey);
        System.out.println(encryptBASE64(encryptByPrivateKey));
        
        System.out.println("--------------------------------------公钥加密-------------------------------------------------");
        byte[] encryptByPublicKey = encryptByPublicKey("Ron,您好，公钥加密",publicKey);
        System.out.println(encryptBASE64(encryptByPublicKey));
        
        System.out.println("--------------------------------------私钥签名------------------------------------------------");
        String sign = sign(encryptByPrivateKey,privateKey);
        System.out.println(sign);
        
        System.out.println("--------------------------------------公钥验证------------------------------------------------");
        boolean verify = verify(encryptByPrivateKey,publicKey,sign);
        System.out.println(verify);
        
        System.out.println("--------------------------------------公钥解密------------------------------------------------");
        byte[] decryptByPublicKey = decryptByPublicKey(encryptByPrivateKey,publicKey);
        System.out.println(new String(decryptByPublicKey));
        System.out.println("--------------------------------------私钥解密------------------------------------------------");
        byte[] decryptByPrivateKey = decryptByPrivateKey(encryptByPublicKey,privateKey);
        System.out.println(new String(decryptByPrivateKey));
	}
}
