package com.customer.rewardsprogram.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.rewardsprogram.model.RewardsTransaction;
import com.customer.rewardsprogram.service.RewardsService;

@RestController
@RequestMapping("/api/rewards")
public class RewardsController {

	private final RewardsService rewardsService;

	public RewardsController(RewardsService rewardsService) {
		this.rewardsService = rewardsService;
	}

	@PostMapping("/calculate")
	public Map<String,Object> calculateRewards(@RequestBody Map<String, List<RewardsTransaction>> customerTransactions) {
		 
		return rewardsService.getRewardPoints(customerTransactions);
	}
}
