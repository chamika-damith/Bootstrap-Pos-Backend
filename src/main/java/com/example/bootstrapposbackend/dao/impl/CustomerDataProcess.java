package com.example.bootstrapposbackend.dao.impl;

import com.example.bootstrapposbackend.dao.CustomerData;
import com.example.bootstrapposbackend.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerDataProcess implements CustomerData {
    @Override
    public CustomerDTO getCustomer(String cusId, Connection connection) throws SQLException {
        return null;
    }

    @Override
    public String saveCustomer(CustomerDTO customerDTO, Connection connection) {
        return null;
    }

    @Override
    public boolean deleteCustomer(String cusId, Connection connection) {
        return false;
    }

    @Override
    public boolean updateCustomer(String cusId, CustomerDTO customer, Connection connection) {
        return false;
    }
}
