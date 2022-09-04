package com.codeshop.plants.plant;

import java.util.Date;

import lombok.Data;

import org.springframework.stereotype.Component;

@Data
@Component
public class PlantDTO {

    private String name;
    private Double potSize;
    private int soilType;
    private Date lastRepotted;
    private int wateringInSeason;
    private int wateringOutOfSeason;
    private int sunlightNeeds;
    private int moistureNeeds;
    private String notes;

}
