package com.codeshop.plants.plant;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeshop.plants.exception.EntityNotFoundException;
import com.codeshop.plants.repottingplan.RepottingPlanDTO;

@RestController
@RequestMapping("plants")
public class PlantController {

    @Autowired
    private PlantService plantService;

    @GetMapping("{id}")
    public Plant getPlant(@PathVariable("id") Long id) throws EntityNotFoundException {
        return plantService.getPlant(id);
    }

    @GetMapping("/search")
    public Plant getPlantByName(@RequestParam("name") String name) throws EntityNotFoundException {
        return plantService.getPlant(name);
    }

    @GetMapping("")
    public List<Plant> getAllPlants() {
        return plantService.getAllPlants();
    }

    @PostMapping("")
    public ResponseEntity<Plant> addPlant(@RequestBody PlantDTO plantDto) {
        Plant savedPlant = plantService.addPlant(plantDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Content-Location", "/plants/" + savedPlant.getId().toString())
                .body(savedPlant);
    }

    // TODO: Make sure only complete requests are fulfilled
    @PutMapping("/{id}")
    public Plant updatePlant(@PathVariable("id") Long id, @RequestBody PlantDTO plantDto)
            throws EntityNotFoundException {
        return plantService.updatePlant(id, plantDto);
    }

    @PatchMapping("/{id}")
    public Plant partiallyUpdatePlant(@PathVariable("id") Long id, @RequestBody PlantDTO plantDto)
            throws EntityNotFoundException {
        return plantService.partiallyUpdatePlant(id, plantDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Plant> deletePlant(@PathVariable("id") Long id) throws EntityNotFoundException {
        plantService.deletePlant(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/repotting-plan/{id}")
    public RepottingPlanDTO getRepottingPlan(@PathVariable("id") Long plantId)
            throws EntityNotFoundException {
        return plantService.getRepottingPlan(plantId);
    }

}
