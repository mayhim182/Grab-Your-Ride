package com.vehicleRental.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vehicleRental.model.ErrorMessage;


@ControllerAdvice
public class GlobalExceptionHandler {


	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(UserDoesNotExistException.class)
	public ResponseEntity<Object> checkInvalidLogin(UserDoesNotExistException e)
	{
		ErrorMessage responseError=new ErrorMessage(LocalDateTime.now(), e.getMessage());
		logger.error("User Does Not Exist");
		ResponseEntity< Object> res=new ResponseEntity<Object>(responseError, HttpStatus.BAD_REQUEST);
		return res;
		
	}
	
	
	
	
	
	
	
		 
	}
