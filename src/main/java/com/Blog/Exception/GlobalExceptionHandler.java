package com.Blog.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{
  @ExceptionHandler(ResourceNotFound.class)
  public ResponseEntity<ApiResponse> ResourceNotFoundHandler(ResourceNotFound ex){
	ApiResponse response = new ApiResponse();
	response.setCode(HttpStatus.NOT_FOUND.toString());
	response.setMessage(ex.getMessage());
	
	return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
  }
}
