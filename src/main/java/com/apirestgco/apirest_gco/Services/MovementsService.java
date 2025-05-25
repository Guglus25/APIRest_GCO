package com.apirestgco.apirest_gco.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirestgco.apirest_gco.Models.MovementsModel;
import com.apirestgco.apirest_gco.Models.ProductsModel;
import com.apirestgco.apirest_gco.Repositories.MovementsRepository;
import com.apirestgco.apirest_gco.Repositories.ProductsRepository;

@Service
public class MovementsService implements IMovementsService {
    @Autowired
    MovementsRepository movementsRepository;
    @Autowired
    ProductsRepository productsRepository;

    @Override
    public List<MovementsModel> findAllMovements(Long idProducto) {
        return movementsRepository.findByIdProducto(idProducto);
    }

    @Override
    public MovementsModel findById(Long id) {
        return movementsRepository.findById(id).get();
    }

    @Override
    public MovementsModel saveMovements(MovementsModel movements) {

        ProductsModel dataProduct = productsRepository.findById(movements.getIdProducto()).get();
        if (movements.getTipo().equals("Entrada"))
            dataProduct.setStock(dataProduct.getStock() + movements.getCantidad());
        else
            dataProduct.setStock(dataProduct.getStock() - movements.getCantidad());

        productsRepository.save(dataProduct);

        return movementsRepository.save(movements);
    }

    @Override
    public MovementsModel updateMovements(Long id, MovementsModel movements) {
        // MovementsModel movement = movementsRepository.findById(id).get();
        return movementsRepository.save(movements);
    }

    @Override
    public void deleteMovements(Long id) {
        movementsRepository.deleteById(id);
    }

}
