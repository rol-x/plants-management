package com.codeshop.plants.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeshop.plants.dto.PlantDTO;
import com.codeshop.plants.dto.RepottingPlanDTO;
import com.codeshop.plants.exception.EntityNotFoundException;
import com.codeshop.plants.model.Plant;
import com.codeshop.plants.service.PlantService;

@RestController
@RequestMapping("plants")
public class PlantController {

    @Autowired
    private PlantService plantService;

    @GetMapping("{id}")
    public Plant getPlantById(@PathVariable("id") Long customerId) throws EntityNotFoundException {
        return plantService.getPlant(customerId);
    }

    @GetMapping("/search")
    public Plant getPlantByName(@RequestAttribute("name") String name) throws EntityNotFoundException {
        return plantService.getPlant(name);
    }

    @GetMapping("")
    public List<Plant> getAllPlants() {
        return plantService.getAllPlants();
    }

    @PostMapping("")
    public ResponseEntity<Plant> addPlant(@RequestBody PlantDTO plantDto) {
        Plant savedPlant = plantService.addPlant(plantDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Content-Location", "/users/" + savedPlant.getId().toString()).body(savedPlant);
    }

    @PutMapping("/{id}")
    public Plant updatePlant(@PathVariable("id") Long plantId, @RequestBody PlantDTO plantDto) {
        return plantService.updatePlant(plantId, plantDto);
    }

    @PatchMapping("/{id}")
    public Plant partiallyUpdatePlant(@PathVariable("id") Long plantId, @RequestBody PlantDTO plantDto)
            throws EntityNotFoundException {
        return plantService.partiallyUpdatePlant(plantId, plantDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Plant> deletePlant(@PathVariable("id") Long plantId)
            throws EntityNotFoundException {
        plantService.removePlant(plantId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/repotting-plan/{id}")
    public RepottingPlanDTO getRepottingPlan(@PathVariable("id") Long plantId)
            throws EntityNotFoundException {
        return plantService.getRepottingPlan(plantId);
    }

}
