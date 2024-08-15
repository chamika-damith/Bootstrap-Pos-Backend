package com.example.bootstrapposbackend.dao.custom.impl;

import com.example.bootstrapposbackend.dao.custom.OrderData;
import com.example.bootstrapposbackend.dto.OrderDTO;
import com.example.bootstrapposbackend.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class OrderDataProcess implements OrderData {
    static String SAVE_ORDER = "INSERT INTO `order` (order_id,order_date,cus_id,item_id,order_qty,total,cash,discount) VALUES (?,?,?,?,?,?,?,?)";


    @Override
    public boolean save(Order entity, Connection connection) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(SAVE_ORDER);
            preparedStatement.setInt(1,entity.getOrderId());
            preparedStatement.setDate(2, entity.getOrderDate());
            preparedStatement.setInt(3, entity.getCusIdOption());
            preparedStatement.setInt(4, entity.getItemIdOption());
            preparedStatement.setInt(5, entity.getOrderQty());
            preparedStatement.setDouble(6, entity.getTotal());
            preparedStatement.setDouble(7, entity.getTxtCash());
            preparedStatement.setDouble(8, entity.getTxtDiscount());

            if (preparedStatement.executeUpdate() != 0) {
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Order> getAll(Connection connection) {
        return null;
    }

    @Override
    public boolean update(Order entity, String Id, Connection connection) {
        return false;
    }

    @Override
    public boolean isExists(String id, Connection connection) {
        return false;
    }

    @Override
    public boolean delete(String id, Connection connection) {
        return false;
    }

    @Override
    public Order get(String id, Connection connection) {
        return null;
    }
}
