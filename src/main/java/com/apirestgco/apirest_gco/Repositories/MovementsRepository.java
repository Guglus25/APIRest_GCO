package com.apirestgco.apirest_gco.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirestgco.apirest_gco.Models.MovementsModel;

public interface MovementsRepository extends JpaRepository<MovementsModel,Long>{
       List<MovementsModel> findByIdProducto(Long idProducto);
}
