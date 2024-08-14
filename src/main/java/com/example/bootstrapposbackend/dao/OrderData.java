package com.example.bootstrapposbackend.dao;

import com.example.bootstrapposbackend.dto.CustomerDTO;
import com.example.bootstrapposbackend.dto.OrderDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderData {
        String saveOrder(OrderDTO orderDTO,Connection connection);
}
