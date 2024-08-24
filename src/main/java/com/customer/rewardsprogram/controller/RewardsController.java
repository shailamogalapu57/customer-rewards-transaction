package com.customer.rewardsprogram.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
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
	public Map<String, Object> calculateRewards(
			@RequestBody Map<String, List<RewardsTransaction>> customerTransactions) {
		return rewardsService.getRewardPoints(customerTransactions);
	}

	@Async
	@GetMapping("/calculate")
	public CompletableFuture<Map<String, Object>> calculateRewardsForDataset() {
		 Map<String, List<RewardsTransaction>> customerTransactions = new HashMap<>();

	        customerTransactions.put("C1", Arrays.asList(
	                new RewardsTransaction("C1", 120.0, LocalDate.of(2024, 6, 15)),
	                new RewardsTransaction("C1", 75.0, LocalDate.of(2024, 7, 10)),
	                new RewardsTransaction("C1", 50.0, LocalDate.of(2024, 8, 5)),
	                new RewardsTransaction("C1", 110.0, LocalDate.of(2024, 6, 20))
	        ));

	        customerTransactions.put("C2", Arrays.asList(
	                new RewardsTransaction("C2", 100.0, LocalDate.of(2024, 6, 10)),
	                new RewardsTransaction("C2", 85.0, LocalDate.of(2024, 7, 12)),
	                new RewardsTransaction("C2", 130.0, LocalDate.of(2024, 8, 15))
	        ));
	        
	     return CompletableFuture.completedFuture(rewardsService.getRewardPoints(customerTransactions));
	}
	
}
