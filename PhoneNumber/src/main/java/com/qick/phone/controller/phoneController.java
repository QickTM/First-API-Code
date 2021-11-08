package com.qick.phone.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qick.phone.model.ContactInfo;
import com.qick.phone.model.Response;
import com.qick.phone.service.ContactInfoService;
import com.qick.phone.service.ResponseService;

@RestController
@RequestMapping("/Contacts")
public class phoneController {
	
	@Autowired
	ContactInfoService contactInfoService;
	
	//resultResponse function 
	public Response resultResponse(boolean result, String successful) {
		String notExist = "This contact does not exist";
		
		Response response = new Response();
		if (result == false) {
			response.setCode(ResponseService.INVALID_ARG);
			response.setData(notExist);
		}
		else {
			response.setCode(ResponseService.OK);
			response.setData(result + successful);
		}
		return response;
	}
	
	
	public Response resultException(Exception e) {
		//Return error code 500 if there is an error
		e.printStackTrace();
		Response response = new Response();
		response.setCode(ResponseService.SERVER_ERROR);
		response.setData(null);
		return response;
	}
	//To store log messages
	private Logger log = LoggerFactory.getLogger(phoneController.class);
	
	@RequestMapping(method=RequestMethod.GET) //To have a GET button in the Swagger 2 UI
	public @ResponseBody Response getALLConactInfo() {
		
		//Declare a list to store data
		List<ContactInfo> cInfomationList = null;
		try {
			//Store all data into cInfomationList via getALLConactaInfo()
			cInfomationList = contactInfoService.getALLContactInfo();
		}
		catch(Exception e) {
			//Return error code "500" if there is an error
			resultException(e);
		}
		
		//Print out response in Swagger2 web-page
		Response response = new Response();
		response.setCode(ResponseService.OK);
		response.setData(cInfomationList);
		return response;
	}
	
	//Note: The "value" string must be the same as @PathVariable
	@RequestMapping(value="/getname/{cName}", method=RequestMethod.GET) 
	public @ResponseBody Response getContactInfo(@PathVariable("cName") String cName) {
		
		//Declare variable to store data
		ContactInfo result = null;
		try {
			//Get data from the DataBase
			result = contactInfoService.getContactInfo(cName);
			
			//If cName value does not exist, do this
			if(result == null) {
				Response response = new Response();
				response.setCode(ResponseService.INVALID_ARG);
				response.setData("This contact does not exist");
				return response;
			}
		}
		catch(Exception e) {
			//Return error code 500 if there is an error
			resultException(e);
		}
		
		//Print out response in Swagger2 web-page
		Response response = new Response();
		response.setCode(ResponseService.OK);
		response.setData(result);
		return response;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody Response insertContact(@RequestBody ContactInfo cInsert) {
		
		//Successful message string and boolean result
		String successful = " (Successfully inserted new contact)";
		boolean result = false;
		
		try {
			//Insert new contact into database
			result = contactInfoService.insertContact(cInsert);
		}
		catch(Exception e) {
			//Return error code 500 if there is an error
			resultException(e);
		}
		
		//Print out response in Swagger2 web-page
		Response response = new Response();
		response.setCode(ResponseService.OK);
		response.setData(result + successful);
		return response;
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public @ResponseBody Response updateContact(@RequestBody ContactInfo cUpdate) {
		
		//Successful message string and boolean result
		String successful = " (Successfully updated contact)";
		boolean result = false;
		
		try {
			//Update existing contact in database
			result = contactInfoService.updateContact(cUpdate);
		}
		catch(Exception e) {
			//Return error code 500 if there is an error
			resultException(e);
		}
		
		//Print out response in Swagger2 web-page
		return resultResponse(result, successful);
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	public @ResponseBody Response deleteContact(@PathVariable ("id") String id) {
		
		//Successful message string and boolean result
		String successful = " (Successfully deleted contact)";
		boolean result = false;
		
		try {
			//Delete existing user via name search
			result = contactInfoService.deleteContact(id);
		}
		catch(Exception e) {
			//Return error code 500 if there is an error
			resultException(e);
		}
		
		//Print out response in Swagger2 web-page
		return resultResponse(result, successful);		
	}
	
	//list insert (one whole batch)
	@RequestMapping(value="/batchInsert", method=RequestMethod.POST)
	public @ResponseBody Response batchInsetContact(@RequestBody List<ContactInfo> contactList) {
		String successful = " (Successfully inserted batch of contacts)";
		boolean result = false;
		
		try {
			//return true result if manage to batch insert
			result = contactInfoService.batchInsertContact(contactList);
		}
		catch(Exception e) {
			//Return error code 500 if there is an error
			resultException(e);
		}
		//Print out response in Swagger2 web-page
		Response response = new Response();
		response.setCode(ResponseService.OK);
		response.setData(result + successful);
		return response;		
	}
	
	//id get (get data using id)
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody Response getContactId(@PathVariable ("id") String id) {
		ContactInfo result = null;
		
		try {
			result = contactInfoService.getContactId(id);
		}
		catch(Exception e) {
			//Return error code 500 if there is an error
			resultException(e);
		}
		//Print out response in Swagger2 web-page
		Response response = new Response();
		response.setCode(ResponseService.OK);
		response.setData(result);
		return response;
	}
	
	//batch delete (delete using list)
	@RequestMapping(value="/batchDelete", method=RequestMethod.DELETE)
	public @ResponseBody Response batchDeleteContact(@RequestBody ArrayList<Integer> ids) {
		String successful = " (Successfully deleted batch of contacts)";
		boolean result = false;
		
		try {
			result = contactInfoService.batchDeleteContact(ids);
		}
		catch(Exception e) {
			resultException(e);
		}
		//Print out response in Swagger2 web-page
		return resultResponse(result, successful);
	}
	
	//batch update (update via list)	
	@RequestMapping(value="/batchUpdate", method=RequestMethod.PUT)
	public @ResponseBody Response batchUpdateContact(@RequestBody List<ContactInfo> contactList) {
		String successful = " (Successfully updated batch of contacts)";
		boolean result = false;
		
		try {
			result = contactInfoService.batchUpdateContact(contactList);
		}
		catch(Exception e) {
			resultException(e);
		}
		//Print out response in Swagger2 web-page
		return resultResponse(result, successful);
	}
}
