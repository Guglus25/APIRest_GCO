package com.apirestgco.apirest_gco.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.apirestgco.apirest_gco.Models.ProductsModel;

public interface ProductsRepository extends JpaRepository<ProductsModel,Long> {
    
} 
