package com.qick.phone.model;


public class ContactInfo {
	private Long id;
	private String phoneName;
	private String phoneCode;
	private String phoneNumber;
	
	public Long getID() {
		return id;
	}
	
	public void setID(Long pId) {
		this.id = pId;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String pNumber) {
		this.phoneNumber = pNumber;
	}
	
	public String getPhoneName() {
		return phoneName;
	}
	
	public void setPhoneName(String pName) {
		this.phoneName = pName;
	}
	
	public String getPhoneCode() {
		return phoneCode;
	}
	
	public void setPhoneCode(String pCode) {
		this.phoneCode = pCode;
	}
}
