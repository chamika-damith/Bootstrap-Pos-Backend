package com.example.bootstrapposbackend.dao;

import com.example.bootstrapposbackend.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface CustomerData {
    CustomerDTO getCustomer(String cusId, Connection connection) throws SQLException;
    String saveCustomer(CustomerDTO customerDTO,Connection connection);
    boolean deleteCustomer(String cusId,Connection connection);
    boolean updateCustomer(String cusId,CustomerDTO customer,Connection connection);
}
