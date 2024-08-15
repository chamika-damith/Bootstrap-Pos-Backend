package com.example.bootstrapposbackend.bo.custom;

import com.example.bootstrapposbackend.bo.SuperBO;
import com.example.bootstrapposbackend.dto.CustomerDTO;
import com.example.bootstrapposbackend.dto.OrderDTO;

import java.sql.Connection;
import java.util.List;

public interface OrderBO extends SuperBO {
    boolean saveOrder(OrderDTO orderDTO, Connection connection);
}
