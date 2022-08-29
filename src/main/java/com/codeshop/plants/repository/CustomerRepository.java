package com.codeshop.plants.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codeshop.plants.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Customer SET firstName = :firstName WHERE id = :id")
    public void updateFirstName(Long id, String firstName);

    @Modifying
    @Transactional
    @Query("UPDATE Customer SET lastName = :lastName WHERE id = :id")
    public void updateLastName(Long id, String lastName);

    @Modifying
    @Transactional
    @Query("UPDATE Customer SET address = :address WHERE id = :id")
    public void updateAddress(Long id, String address);
}
