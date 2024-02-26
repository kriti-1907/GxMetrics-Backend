package com.galaxe.metrics.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.galaxe.metrics.Exception.ChangePasswordException;
import com.galaxe.metrics.Exception.ExceptionResponse;
import com.galaxe.metrics.Exception.UserAuthenticationException;
import com.galaxe.metrics.Exception.UserExistException;
import com.galaxe.metrics.Exception.UserNotFoundException;
import com.galaxe.metrics.Exception.UserRegistrationException;

@ControllerAdvice
public class GolbalHandler {
//	@ExceptionHandler({ UserNotFoundException.class, UserExistException.class, UserAuthenticationException.class,
//			UserRegistrationException.class, ChangePasswordException.class })

//	protected @ResponseBody ExceptionResponse handleException(Exception ex) {
//
////		ExceptionResponse exceptionResponse = ExceptionResponse.builder().errorMessage(ex.getMessage()).build();
//
//		return new ExceptionResponse(ex.getMessage());
//	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	protected @ResponseBody ExceptionResponse handleUserNotFoundException(UserNotFoundException ex) {
		return new ExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	@ExceptionHandler(UserExistException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	protected @ResponseBody ExceptionResponse handleUserExistException(UserExistException ex) {
		return new ExceptionResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
	}

	@ExceptionHandler(UserAuthenticationException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	protected @ResponseBody ExceptionResponse handleUserAuthenticationException(UserAuthenticationException ex) {
		return new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
	}

	@ExceptionHandler(UserRegistrationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected @ResponseBody ExceptionResponse handleUserRegistrationException(UserRegistrationException ex) {
		return new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}

	@ExceptionHandler(ChangePasswordException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected @ResponseBody ExceptionResponse handleChangePasswordException(ChangePasswordException ex) {
		return new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	}
}
