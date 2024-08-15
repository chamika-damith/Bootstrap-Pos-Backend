package com.example.bootstrapposbackend.dao.custom;

import com.example.bootstrapposbackend.dao.CrudDAO;
import com.example.bootstrapposbackend.dto.CustomerDTO;
import com.example.bootstrapposbackend.dto.OrderDTO;
import com.example.bootstrapposbackend.entity.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderData extends CrudDAO<Order> {

}
