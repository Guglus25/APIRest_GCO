package com.apirestgco.apirest_gco.Controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apirestgco.apirest_gco.Models.ProductsModel;

@RestController
public class ProductsController {
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/product")
    public ProductsModel getMethodName(@RequestParam(value = "nombre", defaultValue = "Cristian") String nombre) {
        return new ProductsModel(counter.incrementAndGet(), nombre);
    }
}
