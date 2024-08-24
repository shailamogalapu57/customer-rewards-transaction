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

curl --location 'http://localhost:8080/api/rewards/calculate' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=719E2E7DE9619483827DE6CD27DEC420' \
--data '{
    "C001": [
        {
            "amount": 120,
            "date": "2024-01-15"
        },
        {
            "amount": 80,
            "date": "2024-02-15"
        },
        {
            "amount": 150,
            "date": "2024-03-15"
        }
    ],
    "C002": [
        {
            "amount": 100,
            "date": "2024-01-10"
        },
        {
            "amount": 150,
            "date": "2024-02-10"
        },
        {
            "amount": 120,
            "date": "2024-03-10"
        }
    ]
}'

Example response:
{
    "customers": [
        {
            "customerId": "C001",
            "monthlyRewards": {
                "JANUARY": 90,
                "MARCH": 150,
                "FEBRUARY": 30
            },
            "totalRewards": 270
        },
        {
            "customerId": "C002",
            "monthlyRewards": {
                "JANUARY": 50,
                "MARCH": 90,
                "FEBRUARY": 150
            },
            "totalRewards": 290
        }
    ]
}

curl --location 'http://localhost:8080/api/rewards/calculate' \
--header 'Cookie: JSESSIONID=719E2E7DE9619483827DE6CD27DEC420'

Expected response:
{
    "customers": [
        {
            "customerId": "C1",
            "monthlyRewards": {
                "JUNE": 160,
                "AUGUST": 0,
                "JULY": 25
            },
            "totalRewards": 185
        },
        {
            "customerId": "C2",
            "monthlyRewards": {
                "JUNE": 50,
                "AUGUST": 110,
                "JULY": 35
            },
            "totalRewards": 195
        }
    ]
}

