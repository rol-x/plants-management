package com.codeshop.plants.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeshop.plants.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
