package com.example.bootstrapposbackend.bo.custom.impl;

import com.example.bootstrapposbackend.bo.custom.OrderBO;
import com.example.bootstrapposbackend.dao.DAOFactory;
import com.example.bootstrapposbackend.dao.custom.CustomerData;
import com.example.bootstrapposbackend.dao.custom.OrderData;
import com.example.bootstrapposbackend.dto.OrderDTO;
import com.example.bootstrapposbackend.entity.Order;

import java.sql.Connection;

public class OrderBOImpl implements OrderBO {

    OrderData orderData= (OrderData) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOType.ORDER);

    @Override
    public boolean saveOrder(OrderDTO orderDTO, Connection connection) {
        return orderData.save(new Order(orderDTO.getOrderId(),orderDTO.getOrderDate(),orderDTO.getCusIdOption(),orderDTO.getItemIdOption(),orderDTO.getOrderQty(),orderDTO.getTotal(),orderDTO.getTxtCash(),orderDTO.getTxtDiscount()),connection);
    }
}
