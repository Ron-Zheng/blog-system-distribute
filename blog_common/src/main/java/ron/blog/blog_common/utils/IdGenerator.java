package ron.blog.blog_common.utils;

import java.util.Random;
import java.util.UUID;

public class IdGenerator {
	private static final char[] NUM_ARR = {'0','1','2','3','4','5','6','7','8','9'};
	private static final char[] NUM_ARR_NO_ZERO = {'1','2','3','4','5','6','7','8','9'};
	private static final char[] NUM_CHAR_ARR={
			'0','1','2','3','4','5','6','7','8','9',
			'A','B','C','D','E','F','G','H','I','J',
			'K','L','M','N','O','P','Q','R','S','T',
			'U','V','W','X','Y','Z','a','b','c','d',
			'e','f','g','h','i','j','k','l','m','n',
			'o','p','q','r','s','t','u','v','w','x',
			'y','z'
		};
	
	private static final Random random = new Random(); 
	
	private static String getRandom(char[] charArray, int length){
		StringBuilder sb = new StringBuilder(); 
		int range = charArray.length; 
	    for (int i = 0; i < length; i++) {
			sb.append(charArray[random.nextInt(range)]);
		}
		return sb.toString();
	}
	
	/**
	 * 生成UUID
	 */
	public static String genUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * @Comment 获取随机数字（不包括0）
	 * @Author Ron
	 * @Date 2017年9月15日 下午4:04:39
	 * @return
	 */
	public static String getRandomNumNoZero(int length){
		return getRandom(NUM_ARR_NO_ZERO, length);
	}
	
	/**
	 * @Comment 获取随机数字（包括0）
	 * @Author Ron
	 * @Date 2017年9月15日 下午4:05:00
	 * @return
	 */
	public static String getRandomNum(int length){
		return getRandom(NUM_ARR, length);
	}
	
	/**
	 * @Comment 获取随机字符（包括数字和字符）
	 * @Author Ron
	 * @Date 2017年9月15日 下午4:05:54
	 * @return
	 */
	public static String getRandomChar(int length) {
		return getRandom(NUM_CHAR_ARR, length);
	}
	
	/**
	 * @Comment 获取微毫秒串
	 * @Author Ron
	 * @Date 2017年9月25日 上午11:01:27
	 * @return
	 */
	public static String getMsCode() {
		return System.currentTimeMillis()+"";
	}
}
