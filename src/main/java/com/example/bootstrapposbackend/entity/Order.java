package com.example.bootstrapposbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Order {
    @Id
    int orderId;
    Date orderDate;
    int cusIdOption;
    int itemIdOption;
    int orderQty;
    double total;
    double txtCash;
    double txtDiscount;
}
