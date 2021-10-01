package com.qick.phone.service;

import java.util.List;

import com.qick.phone.model.ContactInfo;

public interface ContactInfoService {
	
	public List<ContactInfo> getALLContactInfo();
	
	public ContactInfo getContactInfo(String cName);
	
	public boolean updateContact(ContactInfo cUpdate);

	public boolean insertContact(ContactInfo cInsert);
	
	public boolean deleteContact(String cDelete);
}
