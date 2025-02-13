package com.planisa.repository;

import com.planisa.model.CovidDataWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadosApiRepository extends JpaRepository<CovidDataWrapper, Long> {
}
