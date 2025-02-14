package com.planisa.service;

import com.planisa.model.CovidData;
import com.planisa.repository.CovidDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndicatorService {

    @Autowired
    private CovidDataRepository covidDataRepository;

    @Autowired
    private ApiService apiService;

    public Map<String, Integer> calculateCasesDifference(String country1, String country2, String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        List<CovidData> dataCountry1 = apiService.fetchDataForCountry(country1, startDate, endDate, "cases");
        List<CovidData> dataCountry2 = apiService.fetchDataForCountry(country2, startDate, endDate, "cases");

        int casesCountry1 = dataCountry1.stream().mapToInt(CovidData::getCases).sum();
        int casesCountry2 = dataCountry2.stream().mapToInt(CovidData::getCases).sum();

        Map<String, Integer> result = new HashMap<>();
        result.put("difference", Math.abs(casesCountry1 - casesCountry2));
        return result;
    }

    public Map<String, Integer> calculateDeathsDifference(String country1, String country2, String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        List<CovidData> dataCountry1 = apiService.fetchDataForCountry(country1, startDate, endDate, "deaths");
        List<CovidData> dataCountry2 = apiService.fetchDataForCountry(country2, startDate, endDate, "deaths");

        int deathsCountry1 = dataCountry1.stream().mapToInt(CovidData::getDeaths).sum();
        int deathsCountry2 = dataCountry2.stream().mapToInt(CovidData::getDeaths).sum();

        Map<String, Integer> result = new HashMap<>();
        result.put("difference", Math.abs(deathsCountry1 - deathsCountry2));
        return result;
    }

    public Map<String, Double> calculateFatalityRate(String country1, String country2, String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        List<CovidData> casesCountry1 = apiService.fetchDataForCountry(country1, startDate, endDate, "cases");
        List<CovidData> deathsCountry1 = apiService.fetchDataForCountry(country1, startDate, endDate, "deaths");
        List<CovidData> casesCountry2 = apiService.fetchDataForCountry(country2, startDate, endDate, "cases");
        List<CovidData> deathsCountry2 = apiService.fetchDataForCountry(country2, startDate, endDate, "deaths");

        int totalCasesCountry1 = casesCountry1.stream().mapToInt(CovidData::getCases).sum();
        int totalDeathsCountry1 = deathsCountry1.stream().mapToInt(CovidData::getDeaths).sum();
        double fatalityRateCountry1 = (totalDeathsCountry1 / (double) totalCasesCountry1) * 100;

        int totalCasesCountry2 = casesCountry2.stream().mapToInt(CovidData::getCases).sum();
        int totalDeathsCountry2 = deathsCountry2.stream().mapToInt(CovidData::getDeaths).sum();
        double fatalityRateCountry2 = (totalDeathsCountry2 / (double) totalCasesCountry2) * 100;

        Map<String, Double> result = new HashMap<>();
        result.put(country1, fatalityRateCountry1);
        result.put(country2, fatalityRateCountry2);
        return result;
    }
}