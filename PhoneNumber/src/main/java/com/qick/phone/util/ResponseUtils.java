package com.qick.phone.util;

import com.qick.phone.model.Response;
import com.qick.phone.service.ResponseService;

public class ResponseUtils {

	private ResponseUtils(){}
	
	/**
	 * return response with OK code
	 * @return
	 */
	public static Response createOKResponse(){
		Response response = new Response();
		response.setCode(ResponseService.OK);
		return response;
	}
	
	/**
	 *return response with error code 
	 * @return
	 */
	public static Response createErrorResponse(String code){
		Response response = new Response();
		response.setCode(code);
		return response;
	}
	
}
