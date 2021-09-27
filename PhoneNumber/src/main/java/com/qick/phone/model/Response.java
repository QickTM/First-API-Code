package com.qick.phone.model;

public class Response {
	
	private String code;
	private Object data;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public Response setData(Object data) {
		this.data = data;
		return this;
	}
	@Override
	public String toString() {
		return "Response [code=" + code + ", data=" + data + "]";
	}
	
}
