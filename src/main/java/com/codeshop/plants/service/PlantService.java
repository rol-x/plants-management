package com.codeshop.plants.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codeshop.plants.dto.PlantDTO;
import com.codeshop.plants.dto.RepottingPlanDTO;
import com.codeshop.plants.exception.EntityNotFoundException;
import com.codeshop.plants.model.Plant;

@Service
public interface PlantService {

    public Plant getPlant(Long id) throws EntityNotFoundException;

    public Plant getPlant(String name) throws EntityNotFoundException;

    public List<Plant> getAllPlants();

    public Plant addPlant(PlantDTO plantDto);

    public Plant updatePlant(Long id, PlantDTO plantDto);

    public Plant partiallyUpdatePlant(Long id, PlantDTO plantDto);

    public void removePlant(Long id);

    public RepottingPlanDTO getRepottingPlan(Long id) throws EntityNotFoundException;
}