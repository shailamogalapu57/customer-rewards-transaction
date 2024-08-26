package com.customer.rewardsprogram.exceptionhandling;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(RewardCalculationException.class)
	public ResponseEntity<Map<String, Object>> handleRewardCalculationException(RewardCalculationException ex,
			WebRequest request) {

		logger.error("RewardCalculationException : {}", ex.getMessage(), ex);
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("timestamp", LocalDateTime.now().toString());
		responseBody.put("message", ex.getMessage());
		responseBody.put("details", request.getDescription(false));

		return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex, WebRequest request) {
		logger.error("Unhandled Exception : {}", ex.getMessage(), ex);
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("timestamp", LocalDateTime.now().toString());
		responseBody.put("message", "An unexpected error occurred");
		responseBody.put("details", request.getDescription(false));

		return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
