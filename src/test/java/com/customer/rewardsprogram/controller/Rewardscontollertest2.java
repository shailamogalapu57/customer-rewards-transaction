package com.customer.rewardsprogram.controller;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.customer.rewardsprogram.service.RewardsService;
import com.customer.rewardsprogram.utils.RewardsCalculator;

@RunWith(MockitoJUnitRunner.class)
public class Rewardscontollertest2 {
	private MockMvc mockMvc;

    @Mock
    private RewardsService rewardsService;

    @InjectMocks
    private RewardsController rewardsController;
    
    @Mock
    private RewardsCalculator calculator;

    @Before
    public void setUp() {
        // Initialize mocks
        MockitoAnnotations.initMocks(this);
        
        // Initialize MockMvc with the controller
        mockMvc = MockMvcBuilders.standaloneSetup(rewardsController).build();

        // Mocking the service layer
        Map<Month, Integer> customer1MonthlyRewards = new HashMap<>();
        customer1MonthlyRewards.put(Month.JUNE, 160);
        customer1MonthlyRewards.put(Month.JULY, 25);
        customer1MonthlyRewards.put(Month.AUGUST, 0);

        Map<Month, Integer> customer2MonthlyRewards = new HashMap<>();
        customer2MonthlyRewards.put(Month.JUNE, 50);
        customer2MonthlyRewards.put(Month.JULY, 35);
        customer2MonthlyRewards.put(Month.AUGUST, 110);

        when(rewardsService.getMonthlyRewardPoints(anyList()))
            .thenReturn(customer1MonthlyRewards)
            .thenReturn(customer2MonthlyRewards);

        when(rewardsService.getTotalRewardPoints(customer1MonthlyRewards)).thenReturn(185);
        when(rewardsService.getTotalRewardPoints(customer2MonthlyRewards)).thenReturn(195);
    }

    @Test
    public void testCalculateRewardsForPost() throws Exception {
        String requestBody = "{\n" +
                "    \"C1\": [\n" +
                "        {\"amount\": 120.0, \"date\": \"2024-06-15\"},\n" +
                "        {\"amount\": 75.0, \"date\": \"2024-07-10\"},\n" +
                "        {\"amount\": 50.0, \"date\": \"2024-08-05\"},\n" +
                "        {\"amount\": 110.0, \"date\": \"2024-06-20\"}\n" +
                "    ],\n" +
                "    \"C2\": [\n" +
                "        {\"amount\": 100.0, \"date\": \"2024-06-10\"},\n" +
                "        {\"amount\": 85.0, \"date\": \"2024-07-12\"},\n" +
                "        {\"amount\": 130.0, \"date\": \"2024-08-15\"}\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(post("/api/rewards/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers[0].customerId").value("C1"))
                .andExpect(jsonPath("$.customers[0].monthlyRewards.JUNE").value(160))
                .andExpect(jsonPath("$.customers[0].monthlyRewards.JULY").value(25))
                .andExpect(jsonPath("$.customers[0].monthlyRewards.AUGUST").value(0))
                .andExpect(jsonPath("$.customers[0].totalRewards").value(185))
                .andExpect(jsonPath("$.customers[1].customerId").value("C2"))
                .andExpect(jsonPath("$.customers[1].monthlyRewards.JUNE").value(50))
                .andExpect(jsonPath("$.customers[1].monthlyRewards.JULY").value(35))
                .andExpect(jsonPath("$.customers[1].monthlyRewards.AUGUST").value(110))
                .andExpect(jsonPath("$.customers[1].totalRewards").value(195));
    }
    
    @Test
    public void testCalculateRewardsForGet() throws Exception {
    	mockMvc.perform(get("/api/rewards/calculate"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.customers[0].customerId").value("C1"))
        .andExpect(jsonPath("$.customers[0].monthlyRewards.JUNE").value(160))
        .andExpect(jsonPath("$.customers[0].monthlyRewards.JULY").value(25))
        .andExpect(jsonPath("$.customers[0].monthlyRewards.AUGUST").value(0))
        .andExpect(jsonPath("$.customers[0].totalRewards").value(185))
        .andExpect(jsonPath("$.customers[1].customerId").value("C2"))
        .andExpect(jsonPath("$.customers[1].monthlyRewards.JUNE").value(50))
        .andExpect(jsonPath("$.customers[1].monthlyRewards.JULY").value(35))
        .andExpect(jsonPath("$.customers[1].monthlyRewards.AUGUST").value(110))
        .andExpect(jsonPath("$.customers[1].totalRewards").value(195));

    }


}
