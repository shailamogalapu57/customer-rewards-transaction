package com.customer.rewardsprogram.exceptionhandling;

public class RewardCalculationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public RewardCalculationException(String message) {
		super(message);
	}

	public RewardCalculationException(String message, Throwable cause) {
		super(message, cause);
	}

}
