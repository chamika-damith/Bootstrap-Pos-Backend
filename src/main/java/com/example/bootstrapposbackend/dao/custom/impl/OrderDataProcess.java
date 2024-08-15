package com.example.bootstrapposbackend.dao.custom.impl;

import com.example.bootstrapposbackend.dao.custom.OrderData;
import com.example.bootstrapposbackend.dto.OrderDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrderDataProcess implements OrderData {
    static String SAVE_ORDER = "INSERT INTO `order` (order_id,order_date,cus_id,item_id,order_qty,total,cash,discount) VALUES (?,?,?,?,?,?,?,?)";

    @Override
    public String saveOrder(OrderDTO orderDTO, Connection connection) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(SAVE_ORDER);
            preparedStatement.setInt(1,orderDTO.getOrderId());
            preparedStatement.setDate(2, orderDTO.getOrderDate());
            preparedStatement.setInt(3, orderDTO.getCusIdOption());
            preparedStatement.setInt(4, orderDTO.getItemIdOption());
            preparedStatement.setInt(5, orderDTO.getOrderQty());
            preparedStatement.setDouble(6, orderDTO.getTotal());
            preparedStatement.setDouble(7, orderDTO.getTxtCash());
            preparedStatement.setDouble(8, orderDTO.getTxtDiscount());

            if (preparedStatement.executeUpdate() != 0) {
                return "success save order";
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return "error save order";
    }
}
