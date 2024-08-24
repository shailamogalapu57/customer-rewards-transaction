package com.customer.rewardsprogram.service;

import java.time.Month;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.customer.rewardsprogram.model.RewardsTransaction;
import com.customer.rewardsprogram.utils.RewardsCalculator;

@Service
public class RewardsService {

	public Map<String, Object> getRewardPoints(Map<String, List<RewardsTransaction>> customerTransactions) {
		return RewardsCalculator.calculateRewardsTotalMonthly(customerTransactions);
	}
	
	public Map<Month, Integer> getMonthlyRewardPoints(List<RewardsTransaction> customerTransactions) {
		return RewardsCalculator.calculateMonthlyPoints(customerTransactions);
	}
	
	public int getTotalRewardPoints(Map<Month, Integer> monthlyPoints) {
		return RewardsCalculator.calculateTotalPoints(monthlyPoints);
	}
}
