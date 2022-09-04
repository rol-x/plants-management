package com.codeshop.plants.model;

import javax.persistence.CascadeType;
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

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "id")
    private Address address;

    public User(UserDTO user, Address address) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = address;
    }

    public User(UserDTO user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = null;
    }
}
