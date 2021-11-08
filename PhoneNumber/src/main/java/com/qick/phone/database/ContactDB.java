package com.qick.phone.database;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import com.qick.phone.model.ContactInfo;

@Mapper
public interface ContactDB {
	
	//Query out all the data from "contacts" table
	@Select({"select id, contact_name contactName, contact_code contactCode, "
			+ "contact_number contactNumber from contacts"})
	public List<ContactInfo> getALLContactInfo();
	
	//Note: the search variable has to be the same name as the declared variable
	//To select a specific contact via name search
	@Select({"select id, contact_name contactName, contact_code contactCode, "
			+ "contact_number contactNumber from contacts where contact_name = #{cName}"})
	public ContactInfo getContactInfo(String cName);
	
	@Select({"select id, contact_name contactName, contact_code contactCode, "
			+ "contact_number contactNumber from contacts where id = #{id}"})
	public ContactInfo getContactId(String id);
	
	//Update an existing contact's values: i.e. phone number, name
	@Update({"update contacts set contact_number = #{contactNumber}, "
			+ "contact_name = #{contactName}, contact_code = #{contactCode}"
			+ "where id = #{id}"})
	public boolean updateContact(ContactInfo cUpdate);
	
	//To insert a new contact into the database
	@Insert({"insert into contacts (contact_name, contact_code, contact_number) "
			+ "values (#{contactName}, #{contactCode}, #{contactNumber})"})
	public boolean insertContact(ContactInfo cInsert);
	
	//Delete a specific existing contact
	@Delete({"delete from contacts where id = #{id}"})
	public boolean deleteContact(String id);
	
	//Batch insert into database
	//MyBatis keyword to loop a list
	@Insert({
		"<script>",
        "insert into contacts (contact_name, contact_number, contact_code)",
        "values ",
        "<foreach  collection='list' item='item' separator=','>", //for loop
        	"(#{item.contactName}, #{item.contactNumber}, #{item.contactCode})",
        "</foreach>",
        "</script>"
	})
	public boolean batchInsertContact(@Param("list") List<ContactInfo> contactInfoList);
	
	//Batch delete form MySQL
	@Delete({
		"<script>",
        "delete from contacts",
        "where id in ",
        "<foreach  collection='list' item='ids' open='(' separator=',' close=')'>", //for loop
        	"#{ids}",
        "</foreach>",
        "</script>"
	})
	public boolean batchDeleteContact(@Param("list") List<Integer> ids);
	
	//Batch update for MySQL => same as updateContact() just different name
	@Update({"update contacts set contact_number = #{contactNumber}, "
			+ "contact_name = #{contactName}, contact_code = #{contactCode}"
			+ "where id = #{id}"})
	public boolean batchUpdateContact(ContactInfo contactInfo);
}
