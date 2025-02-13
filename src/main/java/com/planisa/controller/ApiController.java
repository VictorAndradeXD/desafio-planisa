package com.planisa.controller;

import com.planisa.model.CovidDataWrapper;
import com.planisa.service.ApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

//    @GetMapping("/buscar-dados")
//    public CovidDataWrapper buscarDados(@RequestParam(required = false) String data, @RequestParam(required = false) String tipo, @RequestParam(required = false) String pais, @RequestParam(required = false) String regiao) {
//        return apiService.buscarEGuardarDados(data, tipo, pais, regiao);
//    }

    @GetMapping
    public void buscarDados() {
        System.out.println("funcionou");
    }
}
