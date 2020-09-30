package com.centene.exception;

/**
 * 
 * @author Larry T. Phan
 *
 */
public class CustomGenericException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errCode;
	private String errMsg;
	private int statusCode;

	
	public CustomGenericException(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	

	public CustomGenericException(int statusCode, String errMsg) {
		super();
		this.errMsg = errMsg;
		this.statusCode = statusCode;
	}



	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}


	public int getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	

}
