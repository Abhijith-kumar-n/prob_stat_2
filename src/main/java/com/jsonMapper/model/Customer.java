package com.jsonMapper.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity

public class Customer {

    @Id
    private int customerId;


    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private int phoneNumber;

    private int pincode;

    private String address;

    @OneToMany(mappedBy = "customerId")
    @JsonIgnore
    private List<Orders> orders;
}
