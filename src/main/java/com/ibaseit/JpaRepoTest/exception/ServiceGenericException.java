package com.ibaseit.JpaRepoTest.exception;

public class ServiceGenericException extends Exception{
	private static final long serialVersionUID = 1L;

	private String errCode;
	private String errMsg;

	public ServiceGenericException(String errMsg) {
		super(errMsg);
		this.errMsg = errMsg;
	}

	public ServiceGenericException(String errCode, String errMsg) {
		super(errMsg);
		this.errCode = errCode;
		this.errMsg = errMsg;
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
}
