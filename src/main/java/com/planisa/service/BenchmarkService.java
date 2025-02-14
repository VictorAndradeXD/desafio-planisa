package com.planisa.service;

import com.planisa.model.Benchmark;
import com.planisa.model.CovidData;
import com.planisa.repository.BenchmarkRepository;
import com.planisa.repository.CovidDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BenchmarkService {

    @Autowired
    private BenchmarkRepository benchmarkRepository;

    @Autowired
    private CovidDataRepository covidDataRepository;

    @Autowired
    private ApiService apiService;

    public Benchmark saveBenchmark(Benchmark benchmark) {
        try {
            // Salva o benchmark no banco de dados
            Benchmark savedBenchmark = benchmarkRepository.save(benchmark);
            System.out.println("Benchmark salvo: " + savedBenchmark.getId());

            // Busca os dados da API para os países e período especificados
            List<CovidData> covidDataList = apiService.fetchCovidData(
                    benchmark.getCountry1(),
                    benchmark.getCountry2(),
                    benchmark.getStartDate().toString(),
                    benchmark.getEndDate().toString()
            );
            System.out.println("Total de dados de COVID-19: " + covidDataList.size());

            // Associa os dados de COVID-19 ao benchmark e salva no banco
            covidDataList.forEach(data -> data.setBenchmark(savedBenchmark));
            covidDataRepository.saveAll(covidDataList);
            System.out.println("Dados de COVID-19 salvos no banco.");

            return savedBenchmark;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar o benchmark: " + e.getMessage());
        }
    }

    public List<Benchmark> findAllBenchmarks() {
        return benchmarkRepository.findAll();
    }

    public Optional<Benchmark> findBenchmarkById(Long id) {
        return benchmarkRepository.findById(id);
    }
}