package com.planisa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class CovidData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private int cases;
    private int deaths;
    private String country; // Novo campo para armazenar o país

    @ManyToOne
    @JoinColumn(name = "benchmark_id")
    private Benchmark benchmark;
}