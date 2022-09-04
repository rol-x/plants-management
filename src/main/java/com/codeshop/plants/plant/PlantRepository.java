package com.codeshop.plants.plant;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

    Optional<Plant> findByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE Plant SET name = :name WHERE id = :id")
    void updateName(Long id, String name);

}
