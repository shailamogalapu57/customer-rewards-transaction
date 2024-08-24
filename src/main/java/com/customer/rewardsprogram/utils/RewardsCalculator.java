package com.customer.rewardsprogram.utils;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.customer.rewardsprogram.model.RewardsTransaction;

public class RewardsCalculator {

	public static int calculatePoints(double amount) {
		int points = 0;
		if (amount > 50 && amount <= 100) {
			points = (int) (amount - 50);
		} else if (amount > 100) {
			points = (int) ((amount - 100) * 2 + 50);
		}
		return points;
	}

	public static Map<String, Map<Month, Integer>> calculateRewards(List<RewardsTransaction> transactions) {
		Map<String, Map<Month, Integer>> rewardsPerCustomer = new HashMap<>();
		for (RewardsTransaction transaction : transactions) {
			String customerId = transaction.getCustomerId();
			Month transactionMonth = transaction.getDate().getMonth();
			int points = calculatePoints(transaction.getAmount());
			rewardsPerCustomer.computeIfAbsent(customerId, k -> new HashMap<>()).merge(transactionMonth, points,
					Integer::sum);
		}
		return rewardsPerCustomer.entrySet().stream()
				.collect(Collectors.toMap(entry -> "customer -" + entry.getKey(), Map.Entry::getValue));
	}
}
