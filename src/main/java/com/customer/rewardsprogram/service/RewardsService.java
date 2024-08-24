package com.customer.rewardsprogram.service;

import java.time.Month;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.customer.rewardsprogram.model.RewardsTransaction;
import com.customer.rewardsprogram.utils.RewardsCalculator;

@Service
public class RewardsService {

	public Map<String, Map<Month, Integer>> getRewardPoints(List<RewardsTransaction> transactions) {
		return RewardsCalculator.calculateRewards(transactions);
	}
}
