package com.apirestgco.apirest_gco.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirestgco.apirest_gco.Models.MovementsModel;
import com.apirestgco.apirest_gco.Services.MovementsService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/movement")
public class MovementsController {

    @Autowired
    MovementsService movementService;

    @GetMapping
    public ResponseEntity<?> getfindMovements() {
        try {
            List<MovementsModel> movements = movementService.findAllMovements();
            return new ResponseEntity<List<MovementsModel>>(movements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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
