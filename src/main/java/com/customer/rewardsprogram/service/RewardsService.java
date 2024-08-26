package com.customer.rewardsprogram.service;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.customer.rewardsprogram.exceptionhandling.RewardCalculationException;
import com.customer.rewardsprogram.model.RewardsTransaction;
import com.customer.rewardsprogram.utils.RewardsCalculator;

@Service
public class RewardsService {

	private static final Logger logger = LoggerFactory.getLogger(RewardsService.class);

	public ResponseEntity<Map<String, Object>> getRewardPoints(Map<String, List<RewardsTransaction>> customerTransactions) {
		Map<String, Object> totalMonthlyrewards = new HashMap<>();
		if (customerTransactions == null || CollectionUtils.isEmpty(customerTransactions)) {
			logger.error("Customer list is null or empty");
			throw new RewardCalculationException("Customer List cannot be null or empty");
		}
		try {
			totalMonthlyrewards = RewardsCalculator.calculateRewardsTotalMonthly(customerTransactions);
			logger.info("Successfully calculated the rewards for transactions");
		} catch (Exception e) {
			logger.error("Error occured while calculating rewards",e.getMessage());
			throw new RewardCalculationException("Failed to calculate rewards");
		}
		return ResponseEntity.ok(totalMonthlyrewards);
	}

	public Map<Month, Integer> getMonthlyRewardPoints(List<RewardsTransaction> customerTransactions) {
		return RewardsCalculator.calculateMonthlyPoints(customerTransactions);
	}

	public int getTotalRewardPoints(Map<Month, Integer> monthlyPoints) {
		return RewardsCalculator.calculateTotalPoints(monthlyPoints);
	}
}
