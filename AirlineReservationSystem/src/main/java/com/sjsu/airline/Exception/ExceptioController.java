package com.sjsu.airline.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptioController {

	/*@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> generalException(Exception e)throws Exception{
		Response exResp = new Response();
		exResp.setErrorCode(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()));
		exResp.setErrorMessage(exResp.getErrorMessage());
		return new ResponseEntity<Response>(exResp , HttpStatus.INTERNAL_SERVER_ERROR);
	}*/

	@ExceptionHandler(SpecialException.class)
	public ResponseEntity customException(SpecialException e)throws Exception{
		Response exResp = new Response();
		exResp.setCode(Integer.toString(e.getCode()));
		exResp.setMsg(e.getMessage());
		if(e.isXml()){
			System.out.println("Inside xml");
			return new ResponseEntity(exResp , HttpStatus.BAD_REQUEST);
		}
		if(!e.isXml()){
			System.out.println("Inside json");
			ModelMap m =new ModelMap();
			m.addAttribute("Bad Request", exResp);
			return new ResponseEntity(m , HttpStatus.BAD_REQUEST);
		}
		return null;
	}
	
}
