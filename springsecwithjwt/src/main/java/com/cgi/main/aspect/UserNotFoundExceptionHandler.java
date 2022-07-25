package com.cgi.main.aspect;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserNotFoundExceptionHandler {

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(code=HttpStatus.UNAUTHORIZED)
	public Map<String, String> handleUserNotFoundException(com.cgi.main.aspect.UserNotFoundException e) {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("errorMessage", e.getMessage());
		return map;
	}
}
