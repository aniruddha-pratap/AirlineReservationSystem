package com.sjsu.airline.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptioController {

	/*@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> generalException(Exception e)throws Exception{
		ExceptionResponse exResp = new ExceptionResponse();
		exResp.setErrorCode(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()));
		exResp.setErrorMessage(exResp.getErrorMessage());
		return new ResponseEntity<ExceptionResponse>(exResp , HttpStatus.INTERNAL_SERVER_ERROR);
	}*/

	@ExceptionHandler(SpecialException.class)
	public ResponseEntity<ExceptionResponse> customException(SpecialException e)throws Exception{
		ExceptionResponse exResp = new ExceptionResponse();
		exResp.setCode(Integer.toString(e.getCode()));
		exResp.setMsg(e.getMessage());
		return new ResponseEntity<ExceptionResponse>(exResp , HttpStatus.BAD_REQUEST);
	}
	
}
