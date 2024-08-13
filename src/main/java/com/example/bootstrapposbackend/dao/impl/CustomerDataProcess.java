package com.example.bootstrapposbackend.dao.impl;

import com.example.bootstrapposbackend.dao.CustomerData;
import com.example.bootstrapposbackend.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDataProcess implements CustomerData {

    static String SAVE_CUSTOMER = "INSERT INTO customer (id,name,address,salary) VALUES (?,?,?,?)";
    @Override
    public CustomerDTO getCustomer(String cusId, Connection connection) throws SQLException {
        return null;
    }

    @Override
    public String saveCustomer(CustomerDTO customerDTO, Connection connection) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(SAVE_CUSTOMER);
            preparedStatement.setString(1, String.valueOf(customerDTO.getId()));
            preparedStatement.setString(2,customerDTO.getName());
            preparedStatement.setString(3,customerDTO.getAddress());
            preparedStatement.setString(4, String.valueOf(customerDTO.getSalary()));

            if (preparedStatement.executeUpdate() != 0) {
                return "success save customer";
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return "error save customer";
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
