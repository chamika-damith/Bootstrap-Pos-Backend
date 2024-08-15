package com.example.bootstrapposbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor@Data
@Entity
public class Item {
    @Id
    int id;
    String name;
    double price;
    int qty;
}
