package com.codeshop.plants.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.codeshop.plants.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "APP_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @OneToOne()
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "id")
    private Address address;

    public User(UserDTO user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = new Address(user.getStreet(), user.getCity(), user.getPostalCode());
    }

    public User(String firstName, String lastName, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public User(String name, Address address) {
        String[] names = name.split(" ");
        this.firstName = names[0];
        if (names.length > 1) {
            this.lastName = names[1];
        }
        this.address = address;
    }
}
