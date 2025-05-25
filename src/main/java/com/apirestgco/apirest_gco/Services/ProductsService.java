package com.apirestgco.apirest_gco.Services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirestgco.apirest_gco.Models.MovementsModel;
import com.apirestgco.apirest_gco.Models.ProductsModel;
import com.apirestgco.apirest_gco.Repositories.MovementsRepository;
import com.apirestgco.apirest_gco.Repositories.ProductsRepository;

@Service
public class ProductsService implements IProductsService {

    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    MovementsRepository movementsRepository;

    @Override
    public List<ProductsModel> findAllProduct() {
        return productsRepository.findAll();
    }

    @Override
    public ProductsModel findById(Long id) {
        return productsRepository.findById(id).get();
    }

    @Override
    public void deleteProducts(Long id) {
        productsRepository.deleteById(id);
    }

    @Override
    public ProductsModel saveProducts(ProductsModel products) {
        ProductsModel dataProduc = productsRepository.save(products);
        if (dataProduc != null) {
            MovementsModel dataMovem = new MovementsModel();
            dataMovem.setIdProducto(products.getId());
            dataMovem.setDescripcion("Ingreso por primera vez");
            dataMovem.setCantidad(products.getStock());
            dataMovem.setTipo("Entrada");
            dataMovem.setFecha(new Date());

            movementsRepository.save(dataMovem);
        }        
        return dataProduc;
    }

    @Override
    public ProductsModel updateProducts(Long id, ProductsModel products) {
        ProductsModel product = productsRepository.findById(id).get();
        if (Objects.nonNull(products.getNombre()) && !"".equalsIgnoreCase(products.getNombre())) {
            product.setNombre(products.getNombre());
        }
        if (Objects.nonNull(products.getPrecio()) && products.getPrecio() != 0) {
            product.setPrecio(products.getPrecio());
        }
        if (Objects.nonNull(products.getCodigo()) && !"".equalsIgnoreCase(products.getCodigo())) {
            product.setCodigo(products.getCodigo());
        }
        return productsRepository.save(products);
    }

}
