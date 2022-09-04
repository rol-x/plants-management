package com.codeshop.plants.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.codeshop.plants.plant.Plant;
import com.codeshop.plants.plant.Plant.*;


@SpringBootTest
public class PlantBuilderTest {

    @Test
    public void testCreatingMinimalPlant() {
        Plant myFicus = new Plant.Builder()
        .withName("Ficus Benjamina")
        .withSunlightNeeds(Intensity.MEDIUM)
        .withMoistureNeeds(Intensity.LOW)
        .withWateringInSeason(Frequency.WEEKLY)
        .withWateringOutOfSeason(Frequency.BIMONTHLY)
        .build();

        assertEquals("Ficus Benjamina", myFicus.getName());
        assertEquals(Intensity.MEDIUM, myFicus.getSunlightNeeds());
        assertEquals(Intensity.LOW, myFicus.getMoistureNeeds());
        assertEquals(Frequency.WEEKLY, myFicus.getWateringInSeason());
        assertEquals(Frequency.BIMONTHLY, myFicus.getWateringOutOfSeason());

    }

    @Test
    public void testCreatingMaximalPlant() {
        Plant myRoses = new Plant.Builder()
        .withName("Red Roses")
        .withSunlightNeeds(Intensity.HIGH)
        .withMoistureNeeds(Intensity.MEDIUM)
        .withWateringInSeason(Frequency.BIWEEKLY)
        .withWateringOutOfSeason(Frequency.WEEKLY)
        .withPotSize(23.1)
        .withSoilType(SoilType.ACIDIC)
        .withNotes("Only fertilize in May!")
        .build();

        assertEquals("Red Roses", myRoses.getName());
        assertEquals(Intensity.HIGH, myRoses.getSunlightNeeds());
        assertEquals(Intensity.MEDIUM, myRoses.getMoistureNeeds());
        assertEquals(Frequency.BIWEEKLY, myRoses.getWateringInSeason());
        assertEquals(Frequency.WEEKLY, myRoses.getWateringOutOfSeason());
        assertEquals(23.1, myRoses.getPotSize());
        assertEquals(SoilType.ACIDIC, myRoses.getSoilType());
        assertEquals("Only fertilize in May!", myRoses.getNotes());

    }
}
