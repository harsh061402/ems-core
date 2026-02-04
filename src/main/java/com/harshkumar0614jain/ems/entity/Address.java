package com.harshkumar0614jain.ems.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "address")
@Data
@AllArgsConstructor
public class Address {
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;

}
