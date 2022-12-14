package com.codeshop.plants.validation;

import org.springframework.stereotype.Service;

import com.codeshop.plants.plant.PlantDTO;
import com.codeshop.plants.plant.Plant.Frequency;
import com.codeshop.plants.plant.Plant.Intensity;
import com.codeshop.plants.user.UserDTO;

@Service
public class RequestValidationService {

    public boolean userDtoIncomplete(UserDTO userDto) {
        return (userDto.getFirstName() == null ||
                userDto.getFirstName().isEmpty() ||
                userDto.getLastName() == null ||
                userDto.getLastName().isEmpty() ||
                userDto.getStreet() == null ||
                userDto.getCity() == null ||
                userDto.getPostalCode() == null);
    }

    public boolean plantDtoIncomplete(PlantDTO plantDto) {
        return (plantDto.getName() == null ||
                plantDto.getName().isEmpty() ||
                plantDto.getWateringInSeason() < Frequency.MONTHLY.ordinal() ||
                plantDto.getWateringInSeason() > Frequency.DAILY.ordinal() ||
                plantDto.getWateringOutOfSeason() < Frequency.MONTHLY.ordinal() ||
                plantDto.getWateringOutOfSeason() > Frequency.DAILY.ordinal() ||
                plantDto.getSunlightNeeds() < Intensity.NONE.ordinal() ||
                plantDto.getSunlightNeeds() > Intensity.HIGH.ordinal() ||
                plantDto.getMoistureNeeds() < Intensity.NONE.ordinal() ||
                plantDto.getMoistureNeeds() > Intensity.HIGH.ordinal());
    }
}
