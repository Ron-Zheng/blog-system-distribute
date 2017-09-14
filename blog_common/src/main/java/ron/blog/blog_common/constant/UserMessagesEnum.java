package ron.blog.blog_common.constant;

public interface UserMessagesEnum {
	
	/**
	 * @Comment 消息状态枚举
	 * @Author Ron
	 * @Date 2017年9月14日 下午5:21:15
	 * @return
	 */
	enum Status{
		/**未读**/NOT_READ(0),
		/**已读**/READED(1),
		/**已回复**/REPLAIED(2);
		private int value;
		private Status(int value) {
			this.value = value;
		}
		public int getValue() {
			return value;
		}
	}
}
