package com.apirestgco.apirest_gco.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirestgco.apirest_gco.Models.MovementsModel;
import com.apirestgco.apirest_gco.Repositories.MovementsRepository;

@Service
public class MovementsService implements IMovementsService {
    @Autowired
    MovementsRepository movementsRepository;

    @Override
    public List<MovementsModel> findAllMovements() {
        return movementsRepository.findAll();
    }

    @Override
    public MovementsModel findById(Long id) {
        return movementsRepository.findById(id).get();
    }

    @Override
    public MovementsModel saveMovements(MovementsModel movements) {
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
