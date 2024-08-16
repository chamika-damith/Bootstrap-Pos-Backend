package com.example.bootstrapposbackend.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Customer {
    @Id
    String id;
    String name;
    String address;
    double salary;
}
