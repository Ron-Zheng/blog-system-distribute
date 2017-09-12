package ron.blog.blog_common.constant;

/**
 * @Comment 验证码相关枚举类
 * @Author Ron
 * @Date 2017年9月11日 下午4:49:37
 * @return
 */
public interface VerifyCodeEnum {
	/**
	 * @Comment 验证码类别
	 * @Author Ron
	 * @Date 2017年9月11日 下午4:49:30
	 * @return
	 */
	enum type{
		/*注册*/REGISTER(0, "注册验证码"),
		/*修改密码*/CHANGE_PSW(1, "修改密码验证码");
		private int value;
		private String remarks;
		
		private type(int value, String remarks) {   
	        this.value=value;   
	        this.remarks=remarks; 
	    }

		public String getRemarks() {
			return remarks;
		}
		public int getValue() {
			return value;
		}
	};
	
	/**
	 * @Comment 验证码状态
	 * @Author Ron
	 * @Date 2017年9月11日 下午4:49:59
	 * @return
	 */
	enum status{
		/*已发送*/SEND(0),
		/*已验证*/VERIFIED(1);
		private int value;
		
		private status(int value) {   
	        this.value=value;  
	    }
		public int getValue() {
			return value;
		}
	};
}
