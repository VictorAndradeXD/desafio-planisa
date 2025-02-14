package com.planisa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Benchmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String country1;
    private String country2;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(mappedBy = "benchmark", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CovidData> covidData;
}