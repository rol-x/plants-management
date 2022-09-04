package com.codeshop.plants.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeshop.plants.dto.PlantDTO;
import com.codeshop.plants.dto.RepottingPlanDTO;
import com.codeshop.plants.exception.EntityNotFoundException;
import com.codeshop.plants.exception.IncompletePlantException;
import com.codeshop.plants.model.Plant;
import com.codeshop.plants.repository.PlantRepository;

@Service
public class PlantServiceImpl implements PlantService {

    @Autowired
    PlantRepository plantRepository;

    @Autowired
    RequestValidationService requestValidationService;

    @Override
    public Plant getPlant(Long plantId) throws EntityNotFoundException {
        return plantRepository.findById(plantId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Plant getPlant(String name) throws EntityNotFoundException {
        return plantRepository.findByName(name).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    @Override
    public Plant addPlant(PlantDTO plantDto) {
        validateRequestFormat(plantDto);
        return plantRepository.save(new Plant(plantDto));
    }

    @Override
    public Plant updatePlant(Long plantId, PlantDTO plantDto) throws EntityNotFoundException {
        validateRequestFormat(plantDto);

        Plant plant = getPlant(plantId);
        plant.setName(plantDto.getName());
        // ... etc
        // TODO: Move all these updates to Plant class?

        return plantRepository.save(plant);
    }

    @Override
    public Plant partiallyUpdatePlant(Long plantId, PlantDTO plantDto) throws EntityNotFoundException {
        Plant plant = getPlant(plantId);

        // Update only the attributes present in the request
        if (plantDto.getName() != null)
            plant.setName(plantDto.getName());
        // ... etc
        // TODO: Move all these updates to Plant as well?

        return plantRepository.save(plant);
    }

    @Override
    public void deletePlant(Long plantId) throws EntityNotFoundException {
        if (plantRepository.findById(plantId).isEmpty())
            throw new EntityNotFoundException();

        plantRepository.deleteById(plantId);
    }

    // TODO: Move this to another class
    @Override
    public RepottingPlanDTO getRepottingPlan(Long plantId) throws EntityNotFoundException {
        Plant plant = getPlant(plantId);
        return RepottingPlanService.getRepottingPlan(plant);
    }

    private void validateRequestFormat(PlantDTO plantDto) {
        if (requestValidationService.plantDtoIncomplete(plantDto))
            throw new IncompletePlantException();
    }
}
