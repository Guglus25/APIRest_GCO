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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Gestión de productos", description = "Controlador de Productos")
public class ProductsController {
    @Autowired
    ProductsService productsService;

    @Operation(summary = "Consultar todos los productos", description = "Este endpoint devuelve todos los registros de los productos creados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos consultados", content = @Content(schema = @Schema(implementation = ProductsModel.class))),
            @ApiResponse(responseCode = "400", description = "Datos de solicitud no válidos", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<?> getfindProduct() {
        try {
            List<ProductsModel> Products = productsService.findAllProduct();
            return new ResponseEntity<List<ProductsModel>>(Products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Operation(summary = "Consultar los productos por id", description = "Este endpoint devuelve el registro especifico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto consultado", content = @Content(schema = @Schema(implementation = ProductsModel.class))),
            @ApiResponse(responseCode = "400", description = "Datos de solicitud no válidos", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
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

    @Operation(summary = "Creación de producto", description = "Este endpoint crea el producto en la base de datos, recibe el modelo de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado", content = @Content(schema = @Schema(implementation = ProductsModel.class), examples = @ExampleObject(name = "Respuesta exitosa", value = """
                    {
                        "nombre":"Producto ",
                        "descripcion":"Prueba para almacenar",
                        "precio":20000,
                        "stock":"6",
                        "categoria":"Pan",
                        "codigo":"C001",
                        "fechaCreacion":"2025-05-18"
                    }
                    """))),
            @ApiResponse(responseCode = "400", description = "Datos de solicitud no válidos", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
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

    @Operation(summary = "Actualizar el producto", description = "Este endpoint actualiza un producto por id y recibe el modelo de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto actualizado", content = @Content(schema = @Schema(implementation = ProductsModel.class), examples = @ExampleObject(name = "Respuesta exitosa", value = """
                    {
                        "nombre":"Producto ",
                        "descripcion":"Prueba para almacenar",
                        "precio":20000,
                        "stock":"6",
                        "categoria":"Pan",
                        "codigo":"C001",
                        "fechaCreacion":"2025-05-18"
                    }
                    """))),
            @ApiResponse(responseCode = "400", description = "Datos de solicitud no válidos", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
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

    @Operation(summary = "Eliminar producto por id", description = "Este endpoint elimina el registro de un producto en especifico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto eliminado", content = @Content(examples = @ExampleObject(name = "Eliminado"))),
            @ApiResponse(responseCode = "400", description = "Datos de solicitud no válidos", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        try {
            productsService.deleteProducts(id);
            return new ResponseEntity<String>("Eliminado", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
