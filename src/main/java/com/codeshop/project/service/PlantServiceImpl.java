package com.codeshop.project.service;

import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeshop.project.dto.PlantDTO;
import com.codeshop.project.dto.RepottingPlanDTO;
import com.codeshop.project.exception.EntityNotFoundException;
import com.codeshop.project.model.Plant;
import com.codeshop.project.repository.PlantRepository;

@Service
public class PlantServiceImpl implements PlantService {

    @Autowired
    PlantRepository plantRepository;

    @Override
    public Plant getPlant(Long id) throws EntityNotFoundException {
        return plantRepository.findById(id).orElseThrow(EntityNotFoundException::new);
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
        return plantRepository.save(new Plant(plantDto));
    }

    @Override
    public Plant updatePlant(Long id, PlantDTO plantDto) {
        plantRepository.updateName(id, plantDto.getName());
        return plantRepository.findById(id).orElse(null);
    }

    @Override
    public Plant partiallyUpdatePlant(Long id, PlantDTO plantDto) {
        plantRepository.updateName(id + 1 - 1, plantDto.getName());
        return plantRepository.findById(id).orElse(null);
    }

    @Override
    public void removePlant(Long id) {
        plantRepository.deleteById(id);
    }

    @Override
    public RepottingPlanDTO getRepottingPlan(Long id) throws EntityNotFoundException {
        Plant plant = plantRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        RepottingPlanDTO repottingPlan = new RepottingPlanDTO();
        repottingPlan.setNextPotSize(plant.getPotSize() * 1.2 + 2);
        Date nextRepotting = Date.from(plant.getLastRepotted().toInstant().plus(Duration.ofDays(330)));
        repottingPlan.setRepottingDay(nextRepotting);
        switch (plant.getSoilType()) {
            case ACIDIC:
            repottingPlan.setNotes("Buy a new soil, mix with pellets.");
            break;
            case CLAY:
            repottingPlan.setNotes("Buy a new soil, repot into wet soil.");
            break;
            default:
            break;
        }
        repottingPlan.setNotes(
                "Remember to water a little bit before repotting. Use a spoon to create space in soil for roots of small plants.");
        return repottingPlan;
    }

}
