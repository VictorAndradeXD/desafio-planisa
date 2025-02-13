package com.planisa.service;

import com.planisa.model.CovidDataWrapper;
import com.planisa.repository.DadosApiRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ApiService {

    private final DadosApiRepository repository;
    private final RestTemplate restTemplate;

    public ApiService(DadosApiRepository repository) {
        this.repository = repository;
        this.restTemplate = new RestTemplate();
    }

    public CovidDataWrapper buscarEGuardarDados(String data, String tipo, String pais, String regiao) {
        // Construir URL com query params opcionais
        String url = UriComponentsBuilder.fromHttpUrl("https://api.api-ninjas.com/v1/covid19")
                .queryParamIfPresent("date", Optional.ofNullable(data))
                .queryParamIfPresent("country", Optional.ofNullable(pais))
                .queryParamIfPresent("region", Optional.ofNullable(regiao))
                .queryParamIfPresent("type", Optional.ofNullable(tipo))
                .toUriString();

        ResponseEntity<CovidDataWrapper> response = restTemplate.exchange(url, HttpMethod.GET, null, CovidDataWrapper.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return repository.save(response.getBody());
        }
        return null;
    }

}
