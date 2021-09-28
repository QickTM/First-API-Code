package com.qick.phone.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.qick.phone.util.StringUtil;

@RestController
@RequestMapping("/Contacts")
public class phoneController {
	
	@Autowired
	ContactInfoService contactInfoService;
	
	//To store log messages
	private Logger log = LoggerFactory.getLogger(phoneController.class);
	
	@RequestMapping(method=RequestMethod.GET) //To have a GET button in the Swagger 2 UI
	public @ResponseBody Response getALLConactInfo() {
		
		//Declare a list to store data
		List<ContactInfo> cInfomationList = null;
		try {
			//Store data into cInfomationList via getALLConactaInfo()
			cInfomationList = contactInfoService.getALLContactInfo();
		}
		catch (Exception e){
			//Return error code "500" if there is an error
			e.printStackTrace();
			Response response = new Response();
			response.setCode(ResponseService.SERVER_ERROR);
			response.setData(null);
			return response;
		}
		
		//print out response in Swagger2 web-page
		Response response = new Response();
		response.setCode(ResponseService.OK);
		response.setData(cInfomationList);
		return response;
	}
	
	//Note: The "value" string must be the same as @PathVariable
	@RequestMapping(value="/{cName}",method=RequestMethod.GET) 
	public @ResponseBody Response getContactInfo(@PathVariable("cName") String cName) {
		
		//Declare variable to store data
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			//Get data from the DataBase
			ContactInfo contactInfo = contactInfoService.getContactInfo(cName);
			
			//Put values into resultMap
			resultMap.put("phoneName", cName);
			resultMap.put("phoneCode", contactInfo.getPhoneCode());
			resultMap.put("phoneNumber", contactInfo.getPhoneNumber());
		}
		catch (Exception e) {
			//Return error code 500 if there is an error
			e.printStackTrace();
			Response response = new Response();
			response.setCode(ResponseService.SERVER_ERROR);
			response.setData(null);
			return response;
		}
		
		//print out response in Swagger2 web-page
		Response response = new Response();
		response.setCode(ResponseService.OK);
		response.setData(resultMap);
		return response;
	}
}
