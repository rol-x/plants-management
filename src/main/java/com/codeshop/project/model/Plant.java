package com.codeshop.project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.codeshop.project.dto.PlantDTO;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Table(name = "PLANT")
@NoArgsConstructor
public class Plant {

    public enum SoilType {
        SANDY,
        CLAY,
        SILTY,
        PEATY,
        CHALKY,
        LOAMY,
        ACIDIC,
        BASE,
        NEUTRAL,
        MIX
    }

    public enum Intensity {
        NONE,
        LOW,
        MEDIUM,
        HIGH
    }

    public enum Frequency {
        WEEKLY,
        BIWEEKLY,
        DAILY,
        BIMONTHLY,
        MONTHLY
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "NAME", nullable = false)
    public String name;

    @Column(name = "POT_SIZE")
    public Double potSize;

    @Enumerated(EnumType.STRING)
    @Column(name = "SOIL_TYPE")
    public SoilType soilType;

    @Column(name = "LAST_REPOTTED")
    public Date lastRepotted;

    @Enumerated(EnumType.STRING)
    @Column(name = "WATERING_IN", nullable = false)
    public Frequency wateringInSeason;

    @Enumerated(EnumType.STRING)
    @Column(name = "WATERING_OUT", nullable = false)
    public Frequency wateringOutOfSeason;

    @Enumerated(EnumType.STRING)
    @Column(name = "SUNLIGHT", nullable = false)
    public Intensity sunlightNeeds;

    @Enumerated(EnumType.STRING)
    @Column(name = "MOISTURE", nullable = false)
    public Intensity moistureNeeds;

    @Column(name = "NOTES")
    public String notes;

    public Plant(PlantDTO plantDto) {
        this.name = plantDto.getName();
        this.potSize = plantDto.getPotSize();
        this.soilType = SoilType.values()[plantDto.getSoilType()];
        this.lastRepotted = plantDto.getLastRepotted();
        this.wateringInSeason = Frequency.values()[plantDto.getWateringInSeason()];
        this.wateringOutOfSeason = Frequency.values()[plantDto.getWateringOutOfSeason()];
        this.sunlightNeeds = Intensity.values()[plantDto.getSunlightNeeds()];
        this.moistureNeeds = Intensity.values()[plantDto.getMoistureNeeds()];
        this.notes = plantDto.getNotes();
    }

    public static final class Builder {
        private String name;
        private Double potSize;
        private SoilType soilType;
        private Date lastRepotted;
        private Frequency wateringInSeason;
        private Frequency wateringOutOfSeason;
        private Intensity sunlightNeeds;
        private Intensity moistureNeeds;
        private String notes;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withPotSize(Double potSize) {
            this.potSize = potSize;
            return this;
        }

        public Builder withSoilType(SoilType soilType) {
            this.soilType = soilType;
            return this;
        }

        public Builder lastRepottedOn(Date lastRepotted) {
            this.lastRepotted = lastRepotted;
            return this;
        }

        public Builder withWateringInSeason(Frequency frequency) {
            this.wateringInSeason = frequency;
            return this;
        }

        public Builder withWateringOutOfSeason(Frequency frequency) {
            this.wateringOutOfSeason = frequency;
            return this;
        }

        public Builder withSunlightNeeds(Intensity intensity) {
            this.sunlightNeeds = intensity;
            return this;
        }

        public Builder withMoistureNeeds(Intensity intensity) {
            this.moistureNeeds = intensity;
            return this;
        }

        public Builder withNotes(String notes) {
            this.notes = notes;
            return this;
        }

        public Plant build() {
            return new Plant(this);
        }
    }

    private Plant(Builder builder) {
        this.name = builder.name;
        this.potSize = builder.potSize;
        this.soilType = builder.soilType;
        this.lastRepotted = builder.lastRepotted;
        this.wateringInSeason = builder.wateringInSeason;
        this.wateringOutOfSeason = builder.wateringOutOfSeason;
        this.sunlightNeeds = builder.sunlightNeeds;
        this.moistureNeeds = builder.moistureNeeds;
        this.notes = builder.notes;
    }

}
