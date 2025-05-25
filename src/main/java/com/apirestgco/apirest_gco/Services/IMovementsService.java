package com.apirestgco.apirest_gco.Services;

import java.util.List;

import com.apirestgco.apirest_gco.Models.MovementsModel;

public interface IMovementsService {
    List<MovementsModel> findAllMovements(Long idProducto);
    MovementsModel findById(Long id);
    MovementsModel saveMovements(MovementsModel movements);
    MovementsModel updateMovements(Long id,MovementsModel movements);
    void deleteMovements(Long id);
}
