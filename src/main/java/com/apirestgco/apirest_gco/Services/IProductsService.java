package com.apirestgco.apirest_gco.Services;

import java.util.List;

import com.apirestgco.apirest_gco.Models.ProductsModel;

public interface IProductsService {
    List<ProductsModel> findAllProduct();
    ProductsModel findById(Long id);
    ProductsModel saveProducts(ProductsModel products);
    ProductsModel updateProducts(Long id,ProductsModel products);
    void deleteProducts(Long id);
}