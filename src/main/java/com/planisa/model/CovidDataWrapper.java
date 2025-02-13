package com.planisa.model;

import java.util.Map;

public class CovidDataWrapper {
    private String country;
    private String region;
    private Map<String, DailyCases> cases;

    // Getters e Setters
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Map<String, DailyCases> getCases() {
        return cases;
    }

    public void setCases(Map<String, DailyCases> cases) {
        this.cases = cases;
    }

    public static class DailyCases {
        private int total;
        private int newCases;

        // Getters e Setters
        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getNewCases() {
            return newCases;
        }

        public void setNewCases(int newCases) {
            this.newCases = newCases;
        }
    }
}