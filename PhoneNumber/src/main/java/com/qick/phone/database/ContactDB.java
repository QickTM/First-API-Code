package com.qick.phone.database;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.qick.phone.model.ContactInfo;

@Mapper
public interface ContactDB {
	
	//Query out all the data from "contacts" table
	@Select({"select id, contact_name phoneName, contact_code phoneCode, "
			+ "contact_number phoneNumber from contacts"})
	public List<ContactInfo> getALLContactInfo();
	
	//Note: the search variable has to be the same name as the declared variable
	//To select a specific contact via name search
	@Select({"select id, contact_Name phoneName, contact_code phoneCode, "
			+ "contact_number phoneNumber from contacts where contact_name = #{cName}"})
	public ContactInfo getContactInfo(String cName);
	
	//Update an existing contact's values: i.e. phone number, name
	@Update({"update contacts set contact_number = #{phoneNumber} "
			+ "where contact_name = #{phoneName}"})
	public boolean updateContact(ContactInfo cUpdate);
	
	//To insert a new contact into the database
	@Insert({"insert into contacts (contact_name, contact_code, contact_number) "
			+ "values (#{phoneName}, #{phoneCode}, #{phoneNumber})"})
	public boolean insertContact(ContactInfo cInsert);
	
	//Delete a specific existing contact
	@Delete({"delete from contacts where contact_name = #{phoneName}"})
	public boolean deleteContact(String cDelete);
}
