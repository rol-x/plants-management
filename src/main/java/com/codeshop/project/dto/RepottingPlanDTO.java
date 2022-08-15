package com.codeshop.project.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class RepottingPlanDTO {

    private Date repottingDay;
    private Double nextPotSize;
    private String notes;
}
