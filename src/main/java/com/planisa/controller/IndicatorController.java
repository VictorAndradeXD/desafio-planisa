package com.planisa.controller;

import com.planisa.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/indicators")
public class IndicatorController {

    @Autowired
    private IndicatorService indicatorService;

    @GetMapping("/cases-difference")
    public Map<String, Integer> getCasesDifference(
            @RequestParam String country1,
            @RequestParam String country2,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        return indicatorService.calculateCasesDifference(country1, country2, startDate, endDate);
    }

    @GetMapping("/deaths-difference")
    public Map<String, Integer> getDeathsDifference(
            @RequestParam String country1,
            @RequestParam String country2,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        return indicatorService.calculateDeathsDifference(country1, country2, startDate, endDate);
    }

    @GetMapping("/fatality-rate")
    public Map<String, Double> getFatalityRate(
            @RequestParam String country1,
            @RequestParam String country2,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        return indicatorService.calculateFatalityRate(country1, country2, startDate, endDate);
    }
}