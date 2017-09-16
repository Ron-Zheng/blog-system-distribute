package ron.blog.blog_common.resp;

public enum ResCode {
	SUCCESS("00","success"),
	SYS_ERROR("99","系统异常"),
	DATA_EMPTY("01","必填项为空"),
	DATA_EXIST("02","数据已经存在"),
	VALIDATE_FAILED("03","验证失败"),
	VERIFY_CODE_ERROR("04","验证码错误"),
	EXPIRED("05","超时"),
	USER_INFO_ERROR("06","用户不存在");
	
	private String code;
	private String text;
	
	private ResCode(String code, String text) {   
        this.setCode(code);   
        this.setText(text);
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
