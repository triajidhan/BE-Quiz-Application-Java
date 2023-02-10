package com.triajiramadhan.quiz.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.triajiramadhan.quiz.dto.error.ErrorResDto;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResDto<List<String>>> handleValidation(final MethodArgumentNotValidException ex) {
		
		final ErrorResDto<List<String>> errorResDto = new ErrorResDto<>();
		final List<String> errors = new ArrayList<>();
		
		ex.getBindingResult().getAllErrors().forEach(e-> {
			errors.add(e.getDefaultMessage());
		});
		
		errorResDto.setMessage(errors);
		return new ResponseEntity<>(errorResDto, HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorResDto<String>> handleBadCredential(final BadCredentialsException ex) {
		final ErrorResDto<String> errorResDto = new ErrorResDto<>();
		errorResDto.setMessage("Bad Credentials");
		
		return new ResponseEntity<>(errorResDto, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(ObjectOptimisticLockingFailureException.class)
	public ResponseEntity<ErrorResDto<String>> handleObjectOptimistic(final ObjectOptimisticLockingFailureException ex) {
		final ErrorResDto<String> errorResDto = new ErrorResDto<>();
		errorResDto.setMessage("invalid version");
		
		return new ResponseEntity<>(errorResDto, HttpStatus.BAD_REQUEST);
	}
}
