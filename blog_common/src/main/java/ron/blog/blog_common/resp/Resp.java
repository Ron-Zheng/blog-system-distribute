package ron.blog.blog_common.resp;

import java.io.Serializable;

public class Resp implements Serializable {

	/**
	 * @Comment 
	 * @Author Ron
	 * @Date 2017年8月22日 下午2:52:12
	 */
	private static final long serialVersionUID = 1L;
	
	protected String resCode;
	protected String resMsg;
	protected Object data;
	
	public Resp(ResCode resCode, Object data){
		this.resCode = resCode.getCode();
		this.resMsg = resCode.getText();
		this.data = data;
	}
	
	public Resp(String resCode, String resMsg){
		this.resCode = resCode;
		this.resMsg = resMsg;
	}
	
	public Resp(String resCode, String resMsg, Object data){
		this.resCode = resCode;
		this.resMsg = resMsg;
		this.data = data;
	}
	
	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
