package com.customer.rewardsprogram.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RewardsTransaction {

	private String customerId;
	private double amount;
	private LocalDate date;

}
