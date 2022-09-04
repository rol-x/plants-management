package com.codeshop.plants.service;

import java.time.Duration;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.codeshop.plants.dto.RepottingPlanDTO;
import com.codeshop.plants.model.Plant;

@Service
public class RepottingPlanService {

    public static final RepottingPlanDTO getRepottingPlan(Plant plant) {
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

    private RepottingPlanService() {
    }

}
