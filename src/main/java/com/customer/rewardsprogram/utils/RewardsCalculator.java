package com.customer.rewardsprogram.utils;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.customer.rewardsprogram.model.RewardsResponse;
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

	public static Map<Month, Integer> calculateMonthlyPoints(List<RewardsTransaction> transactions) {
		Map<Month, Integer> monthlyPoints = new HashMap<>();
		for (RewardsTransaction transaction : transactions) {
			Month month = transaction.getDate().getMonth();
			int points = calculatePoints(transaction.getAmount());
			monthlyPoints.put(month, monthlyPoints.getOrDefault(month, 0) + points);
		}
		return monthlyPoints;
	}

	public static int calculateTotalPoints(Map<Month, Integer> monthlyPoints) {
		return monthlyPoints.values().stream().mapToInt(Integer::intValue).sum();
	}

	public static Map<String, Object> calculateRewardsTotalMonthly(
			Map<String, List<RewardsTransaction>> customerTransactions) {
		List<RewardsResponse> customers = new ArrayList<>();

		for (Map.Entry<String, List<RewardsTransaction>> entry : customerTransactions.entrySet()) {
			String customerId = entry.getKey();
			List<RewardsTransaction> transactions = entry.getValue();

			Map<Month, Integer> monthlyPoints = calculateMonthlyPoints(transactions);
			int totalPoints = calculateTotalPoints(monthlyPoints);

			// Convert Month to String for the JSON response
			Map<String, Integer> monthlyRewards = new HashMap<>();
			for (Map.Entry<Month, Integer> monthlyEntry : monthlyPoints.entrySet()) {
				monthlyRewards.put(monthlyEntry.getKey().name(), monthlyEntry.getValue());
			}

			// Create CustomerRewards object
			RewardsResponse customerRewards = new RewardsResponse(customerId, monthlyRewards, totalPoints);
			customers.add(customerRewards);

		}
		// Create the response map
		Map<String, Object> response = new HashMap<>();
		response.put("customers", customers);

		return response;
	}

}
