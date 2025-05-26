package com.apirestgco.apirest_gco.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirestgco.apirest_gco.Models.MovementsModel;
import com.apirestgco.apirest_gco.Models.ProductsModel;
import com.apirestgco.apirest_gco.Services.MovementsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/movement")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Gestión de movimientos", description = "Controlador de Movimientos")
public class MovementsController {
    @Autowired
    MovementsService movementService;

    @Operation(summary = "Consultar los movimientos del producto por id", description = "Este endpoint devuelve los registro de movimiento de un producto en especifico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimiento consultado", content = @Content(schema = @Schema(implementation = ProductsModel.class))),
            @ApiResponse(responseCode = "400", description = "Datos de solicitud no válidos", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping(value = "/product/{idproducto}")
    public ResponseEntity<?> getfindMovements(@PathVariable("idproducto")Long idproducto) {
        try {
            List<MovementsModel> movements = movementService.findAllMovements(idproducto);
            return new ResponseEntity<List<MovementsModel>>(movements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Operation(summary = "Consultar los movimientos por id", description = "Este endpoint devuelve el registro de movimiento en especifico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimiento consultado", content = @Content(schema = @Schema(implementation = ProductsModel.class))),
            @ApiResponse(responseCode = "400", description = "Datos de solicitud no válidos", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getfindMovementsByid(@PathVariable("id") Long id) {
        try {
            MovementsModel movement = movementService.findById(id);
            if (movement != null) {
                return new ResponseEntity<MovementsModel>(movement, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Movimiento no encontrado", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @Operation(summary = "Creación del movimiento", description = "Este endpoint crea el movimiento de un producto en la base de datos, recibe el modelo de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movimiento creado", content = @Content(schema = @Schema(implementation = MovementsModel.class), examples = @ExampleObject(name = "Respuesta exitosa", value = """
                    {
                        "idProducto":1,
                        "tipo":"Entrada",
                        "cantidad":4,
                        "fecha":"2025-05-18",
                        "descripcion":"nuevo Producto"
                    }
                    """))),
            @ApiResponse(responseCode = "400", description = "Datos de solicitud no válidos", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<?> postMovements(@RequestBody MovementsModel entity) {
        try {
            movementService.saveMovements(entity);
            return new ResponseEntity<MovementsModel>(entity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(),
                       HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

     @Operation(summary = "Actualizar el movimiento", description = "Este endpoint actualiza un movimiento por id y recibe el modelo de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "movimiento actualizado", content = @Content(schema = @Schema(implementation = MovementsModel.class), examples = @ExampleObject(name = "Respuesta exitosa", value = """
                    {
                        "idProducto":1,
                        "tipo":"Entrada",
                        "cantidad":4,
                        "fecha":"2025-05-18",
                        "descripcion":"nuevo Producto"
                    }
                    """))),
            @ApiResponse(responseCode = "400", description = "Datos de solicitud no válidos", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> putMovements(@PathVariable("id") Long id, @RequestBody MovementsModel entity) {
        try {
            MovementsModel movement = movementService.updateMovements(id, entity);
            return new ResponseEntity<MovementsModel>(movement, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Eliminar movimiento por id", description = "Este endpoint elimina el registro de un movimiento en especifico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimiento eliminado", content = @Content(examples = @ExampleObject(name = "Fue eliminado"))),
            @ApiResponse(responseCode = "400", description = "Datos de solicitud no válidos", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteMovements(@PathVariable("id") Long id) {
        try {
            movementService.deleteMovements(id);
            return new ResponseEntity<String>("Fue eliminado", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
