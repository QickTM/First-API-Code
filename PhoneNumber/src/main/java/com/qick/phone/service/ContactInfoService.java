package com.qick.phone.service;

import java.util.List;

import com.qick.phone.model.ContactInfo;

public interface ContactInfoService {
	
	public List<ContactInfo> getALLContactInfo();
	
	public ContactInfo getContactInfo(String cName);
	
	public ContactInfo getContactId(String id);
	
	public boolean updateContact(ContactInfo cUpdate);

	public boolean insertContact(ContactInfo cInsert);
	
	public boolean deleteContact(String id);
	
	public boolean batchInsertContact(List<ContactInfo> cBatch);
	
	public boolean batchDeleteContact(List<Integer> cBatch);
	
	public boolean batchUpdateContact(List<ContactInfo> cBatch);
}
