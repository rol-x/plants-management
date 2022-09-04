package com.codeshop.plants.plant;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codeshop.plants.exception.EntityNotFoundException;
import com.codeshop.plants.repottingplan.RepottingPlanDTO;

@Service
public interface PlantService {

    public Plant getPlant(Long plantId) throws EntityNotFoundException;

    public Plant getPlant(String name) throws EntityNotFoundException;

    public List<Plant> getAllPlants();

    public Plant addPlant(PlantDTO plantDto);

    public Plant updatePlant(Long plantId, PlantDTO plantDto) throws EntityNotFoundException;

    public Plant partiallyUpdatePlant(Long plantId, PlantDTO plantDto) throws EntityNotFoundException;

    public void deletePlant(Long plantId) throws EntityNotFoundException;

    public RepottingPlanDTO getRepottingPlan(Long plantId) throws EntityNotFoundException;
}
