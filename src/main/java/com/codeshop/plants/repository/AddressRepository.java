package com.codeshop.plants.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeshop.plants.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
