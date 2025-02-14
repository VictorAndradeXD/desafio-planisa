package com.planisa.service;

import com.planisa.model.CovidData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiService {

    @Value("${api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<CovidData> fetchCovidData(String country1, String country2, String startDate, String endDate) {
        List<CovidData> covidDataList = new ArrayList<>();

        covidDataList.addAll(fetchDataForCountry(country1, startDate, endDate, ""));
        covidDataList.addAll(fetchDataForCountry(country2, startDate, endDate, ""));

        return covidDataList;
    }

    public List<CovidData> fetchDataForCountry(String country, String startDate, String endDate, String type) {
        String url = "https://api.api-ninjas.com/v1/covid19?country=" + country + "&type=" + type;
        List<CovidData> covidDataList = new ArrayList<>();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Api-Key", apiKey);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            String jsonResponse = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            for (JsonNode node : rootNode) {
                String region = node.has("region") ? node.get("region").asText() : "N/A";
                JsonNode casesNode = node.get(type); // Usa o tipo de dados especificado (cases ou deaths)

                casesNode.fields().forEachRemaining(entry -> {
                    String date = entry.getKey();
                    if (isDateInRange(date, startDate, endDate)) {
                        JsonNode dataNode = entry.getValue();
                        CovidData covidData = new CovidData();
                        covidData.setDate(LocalDate.parse(date));
                        covidData.setCases(type.equals("cases") ? dataNode.get("total").asInt() : 0);
                        covidData.setDeaths(type.equals("deaths") ? dataNode.get("total").asInt() : 0);
                        covidData.setCountry(country);
                        covidDataList.add(covidData);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return covidDataList;
    }

    private boolean isDateInRange(String date, String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentDate = LocalDate.parse(date, formatter);
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        return !currentDate.isBefore(start) && !currentDate.isAfter(end);
    }
}