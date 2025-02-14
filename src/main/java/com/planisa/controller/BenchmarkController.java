package com.planisa.controller;

import com.planisa.model.Benchmark;
import com.planisa.service.BenchmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/benchmarks")
public class BenchmarkController {

    @Autowired
    private BenchmarkService benchmarkService;

    @PostMapping
    public ResponseEntity<?> saveBenchmark(@RequestBody Benchmark benchmark) {
        try {
            Benchmark savedBenchmark = benchmarkService.saveBenchmark(benchmark);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBenchmark);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Benchmark>> findAllBenchmarks() {
        List<Benchmark> benchmarks = benchmarkService.findAllBenchmarks();
        return ResponseEntity.ok(benchmarks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findBenchmarkById(@PathVariable Long id) {
        Optional<Benchmark> benchmark = benchmarkService.findBenchmarkById(id);
        if (benchmark.isPresent()) {
            return ResponseEntity.ok(benchmark.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Benchmark não encontrado com o ID: " + id);
        }
    }
}