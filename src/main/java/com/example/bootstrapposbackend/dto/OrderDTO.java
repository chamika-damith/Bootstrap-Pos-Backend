package com.example.bootstrapposbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO {
    int orderId;
    Date orderDate;
    int cusIdOption;
    int itemIdOption;
    int orderQty;
    double total;
    double txtCash;
    double txtDiscount;
}
