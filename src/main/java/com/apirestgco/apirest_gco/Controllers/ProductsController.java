package com.apirestgco.apirest_gco.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.apirestgco.apirest_gco.Models.ProductsModel;
import com.apirestgco.apirest_gco.Services.ProductsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Product Management", description = "Controllers Product")
public class ProductsController {
    @Autowired
    ProductsService productsService;

    
    @GetMapping
    @Operation(summary = "", description = "Endpoint que trae los datos de la solicitud", tags = {
            "Product Management" })    
    public ResponseEntity<?> getfindProduct() {
        try {
            List<ProductsModel> Products = productsService.findAllProduct();
            return new ResponseEntity<List<ProductsModel>>(Products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findProductid(@PathVariable("id") Long id) {
        try {
            ProductsModel Products = productsService.findById(id);
            if (Products != null) {
                return new ResponseEntity<ProductsModel>(Products, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Producto no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> postProducts(@RequestBody ProductsModel entity) {
        try {
            productsService.saveProducts(entity);
            return new ResponseEntity<ProductsModel>(entity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable long id, @RequestBody ProductsModel Product) {
        try {
            ProductsModel ProductSave = productsService.updateProducts(id, Product);
            return new ResponseEntity<ProductsModel>(ProductSave, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        try {
            productsService.deleteProducts(id);
            return new ResponseEntity<String>( "Eliminado", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
