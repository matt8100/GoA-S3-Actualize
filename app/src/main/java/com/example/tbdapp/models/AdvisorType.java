package com.example.tbdapp.models;

public enum AdvisorType {
    CAREER("Career Advisor"),
    FINANCIAL("Financial Advisor");

    private final String advisorType;

    AdvisorType(final String advisorType) {
        this.advisorType = advisorType;
    }

    @Override
    public String toString() {
        return advisorType;
    }
}
