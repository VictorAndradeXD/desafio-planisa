package com.planisa.repository;

import com.planisa.model.CovidData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CovidDataRepository extends JpaRepository<CovidData, Long> {
    List<CovidData> findByCountryAndDateBetween(String country, LocalDate startDate, LocalDate endDate);
}