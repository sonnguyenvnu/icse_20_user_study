package com.jpay.vo;

import java.io.Serializable;

public class AjaxResult implements Serializable{

	private static final long serialVersionUID = 6439646269084700779L;

	private int code = 0;

	// 返回的中文消�?�
	private String message;

	// �?功时�?�带的数�?�
	private Object data;
	
	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	// 校验错误
	public boolean hasError() {
		return this.code != 0;
	}

	// 添加错误，用于alertError
	public AjaxResult addError(String message) {
		this.message = message;
		this.code = 1;
		return this;
	}

	/**
	 * 用于Confirm的错误信�?�
	 * @param message
	 * @return {AjaxResult}
	 */
	public AjaxResult addConfirmError(String message) {
		this.message = message;
		this.code = 2;
		return this;
	}

	/**
	 * �?装�?功时的数�?�
	 * @param data
	 * @return {AjaxResult}
	 */
	public AjaxResult success(Object data) {
		this.data = data;
		this.code = 0;
		return this;
	}

}
