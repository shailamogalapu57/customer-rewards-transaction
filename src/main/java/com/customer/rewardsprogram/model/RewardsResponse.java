package com.customer.rewardsprogram.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RewardsResponse {

	private String customerId;
	private Map<String, Integer> monthlyRewards;
	private int totalRewards;

}
