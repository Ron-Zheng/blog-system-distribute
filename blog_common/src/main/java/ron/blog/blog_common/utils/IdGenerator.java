package ron.blog.blog_common.utils;

import java.util.Random;
import java.util.UUID;

public class IdGenerator {
	private static final char[] numArr = {'0','1','2','3','4','5','6','7','8','9'};
	private static final char[] numArrNoZero = {'1','2','3','4','5','6','7','8','9'};
	
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
	
	public static String getRandomNumNoZero(int length){
		return getRandom(numArrNoZero, length);
	}
	
	public static String getRandomNum(int length){
		return getRandom(numArr, length);
	}
}
