package com.planisa.repository;

import com.planisa.model.Benchmark;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BenchmarkRepository extends JpaRepository<Benchmark, Long> {
    Optional<Benchmark> findByName(String name);
}