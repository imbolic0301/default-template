package com.example.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.web.dto.ExceptionDTO;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@ResponseBody
@Slf4j
public class CustomExceptionHandler {
	
	@ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	 protected Object handleException(Exception e,  HttpServletRequest request) {
		
		log.error("[" + request.getMethod() + "]" + request.getRequestURI(), e);
        
		ExceptionDTO exceptionDTO = ExceptionDTO.builder()
	                .status(HttpStatus.BAD_REQUEST.value())
	                .message(e.getMessage())
	                .build();
		
		return exceptionDTO;
    }
	
	
	@ExceptionHandler(GlobalException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	 protected Object handleGlobalException(GlobalException e,  HttpServletRequest request) {
		
		log.error("[" + request.getMethod() + "]" + request.getRequestURI(), e);
		
		ExceptionDTO exceptionDTO = ExceptionDTO.builder()
	                .status(HttpStatus.BAD_REQUEST.value())
	                .message(e.getMessage())
	                .build();
		
		return exceptionDTO;
    }
	
	@ExceptionHandler({DataAccessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	 protected Object handleDataAccessException(DataAccessException e,  HttpServletRequest request) {
		
		log.error("[" + request.getMethod() + "]" + request.getRequestURI(), e);
		
		ExceptionDTO exceptionDTO = ExceptionDTO.builder()
	                .status(HttpStatus.BAD_REQUEST.value())
	                .message("Please contact the developer")
	                .build();
		
		return exceptionDTO;
    }
}