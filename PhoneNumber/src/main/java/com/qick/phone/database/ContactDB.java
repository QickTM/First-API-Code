package com.qick.phone.database;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.qick.phone.model.ContactInfo;

@Mapper
public interface ContactDB {
	
	//Query our the data from "contacts" table
	@Select({"select id, contact_name phoneName, contact_code phoneCode, contact_number phoneNumber from contacts"})
	public List<ContactInfo> getALLContactInfo();

}
