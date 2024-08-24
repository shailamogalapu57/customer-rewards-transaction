Customer Rewards transaction

Overview

This project implements a rewards program for a retailer, where customers earn points based on their purchase amounts. The points are awarded according to the following rules:

	•	2 points for every dollar spent over $100 in each transaction.
	•	1 point for every dollar spent between $50 and $100 in each transaction.

For example:

	•	A $120 purchase would earn 90 points:
	•	2 points for each of the 20 dollars over $100 = 40 points
	•	1 point for each of the 50 dollars between $50 and $100 = 50 points

Problem Statement

Given a record of transactions over a three-month period, the goal is to calculate the reward points earned for each customer per month, as well as their total points across all three months.

Features

	•	Spring Boot application to handle the reward calculation.
	•	RESTful API endpoint to calculate and retrieve reward points.
	•	Sample dataset included to demonstrate the solution.

Technologies Used

	•	Java 8
	•	Spring Boot
	•	Maven for dependency management
	•	JUnit for testing
Example request:

curl -X GET http://localhost:8080/api/rewards

Example response:

{
  "customers": [
    {
      "customerId": "C001",
      "monthlyRewards": {
        "January": 150,
        "February": 220,
        "March": 300
      },
      "totalRewards": 670
    },
    {
      "customerId": "C002",
      "monthlyRewards": {
        "January": 100,
        "February": 180,
        "March": 240
      },
      "totalRewards": 520
    }
  ]
}


