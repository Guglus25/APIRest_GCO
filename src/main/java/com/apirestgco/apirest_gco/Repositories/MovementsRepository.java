package com.apirestgco.apirest_gco.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirestgco.apirest_gco.Models.MovementsModel;

public interface MovementsRepository extends JpaRepository<MovementsModel,Long>{
    
}
