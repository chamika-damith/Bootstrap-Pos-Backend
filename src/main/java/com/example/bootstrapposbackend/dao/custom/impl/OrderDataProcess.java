package com.example.bootstrapposbackend.dao.custom.impl;

import com.example.bootstrapposbackend.dao.custom.OrderData;
import com.example.bootstrapposbackend.dto.OrderDTO;
import com.example.bootstrapposbackend.entity.Order;
import com.example.bootstrapposbackend.entity.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDataProcess implements OrderData {
    public boolean save(Order order, Connection connection) {
        String SAVE_ORDER = "INSERT INTO `order` (order_id, order_date, cus_id,item_id,order_qty, total, cash, discount) VALUES (?, ?, ?, ?, ?, ?,?,?)";
        String SAVE_ORDER_DETAIL = "INSERT INTO `order_detail` (order_id, item_id, order_qty, item_price) VALUES (?, ?, ?, ?)";

        PreparedStatement orderStmt = null;
        PreparedStatement orderDetailStmt = null;

        try {
            connection.setAutoCommit(false);  // Begin transaction

            // Save Order
            orderStmt = connection.prepareStatement(SAVE_ORDER);
            orderStmt.setString(1, order.getOrderId());
            orderStmt.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            orderStmt.setString(3, order.getCusIdOption());
            orderStmt.setString(4, order.getItemIdOption());
            orderStmt.setInt(5, order.getOrderQty());
            orderStmt.setDouble(6, order.getTotal());
            orderStmt.setDouble(7, order.getTxtCash());
            orderStmt.setDouble(8, order.getTxtDiscount());

            if (orderStmt.executeUpdate() == 0) {
                connection.rollback();  // Rollback transaction if order save fails
                return false;
            }

            // Save Order Details
            orderDetailStmt = connection.prepareStatement(SAVE_ORDER_DETAIL);
            for (OrderDetail orderDetail : order.getOrderDetails()) {
                orderDetailStmt.setString(1, order.getOrderId());
                orderDetailStmt.setString(2, orderDetail.getItem().getId());
                orderDetailStmt.setInt(3, orderDetail.getOrderQty());
                orderDetailStmt.setDouble(4, orderDetail.getItemPrice());

                if (orderDetailStmt.executeUpdate() == 0) {
                    connection.rollback();  // Rollback transaction if order detail save fails
                    return false;
                }
            }

            connection.commit();  // Commit transaction if everything was successful
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();  // Rollback transaction on exception
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (orderStmt != null) orderStmt.close();
                if (orderDetailStmt != null) orderDetailStmt.close();
                if (connection != null) connection.setAutoCommit(true);  // Reset auto-commit mode
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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
