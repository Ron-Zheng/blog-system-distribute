package ron.blog.blog_common.security;

import java.security.MessageDigest;

public class SecurityMD5Utils {
	/**
	 * @Comment MD5编码（生成32位的MD5码）
	 * @Author Ron
	 * @Date 2017年9月13日 下午3:18:45
	 * @return
	 */
	public static String md5Encode(String inStr) throws Exception {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}

		byte[] byteArray = inStr.getBytes("UTF-8");
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
	
	public static void main(String args[]) throws Exception {  
        String str = new String("amigoxiexiexingxing");  
        System.out.println("原始：" + str);  
        System.out.println("MD5后：" + md5Encode(str));  
    }  
}
