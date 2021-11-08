package com.qick.phone.model;

public class ContactInfo {
	private Long id;
	private String contactName;
	private String contactCode;
	private String contactNumber;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	public String getContactCode() {
		return contactCode;
	}
	
	public void setContactCode(String contactCode) {
		this.contactCode = contactCode;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}
	
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}	
}
