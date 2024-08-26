package com.customer.rewardsprogram.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
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

	private static final Logger logger = LoggerFactory.getLogger(RewardsController.class);

	public RewardsController(RewardsService rewardsService) {
		this.rewardsService = rewardsService;
	}

	@PostMapping("/calculate")
	public ResponseEntity<Map<String, Object>> calculateRewards(
			@RequestBody Map<String, List<RewardsTransaction>> customerTransactions) {
		logger.info("Starting reward calculation for {} transactions", customerTransactions.size());
		return rewardsService.getRewardPoints(customerTransactions);
	}

	@Async
	@GetMapping("/calculate")
	public CompletableFuture<ResponseEntity<Map<String, Object>>> calculateRewardsForDataset() {
		Map<String, List<RewardsTransaction>> customerTransactions = new HashMap<>();

		customerTransactions.put("C1",
				Arrays.asList(new RewardsTransaction("C1", 120.0, LocalDate.of(2024, 6, 15)),
						new RewardsTransaction("C1", 75.0, LocalDate.of(2024, 7, 10)),
						new RewardsTransaction("C1", 50.0, LocalDate.of(2024, 8, 5)),
						new RewardsTransaction("C1", 110.0, LocalDate.of(2024, 6, 20))));

		customerTransactions.put("C2",
				Arrays.asList(new RewardsTransaction("C2", 100.0, LocalDate.of(2024, 6, 10)),
						new RewardsTransaction("C2", 85.0, LocalDate.of(2024, 7, 12)),
						new RewardsTransaction("C2", 130.0, LocalDate.of(2024, 8, 15))));
		logger.info("Starting reward calculation for {} transactions", customerTransactions.size());

		return CompletableFuture.completedFuture(rewardsService.getRewardPoints(customerTransactions));
	}

}
