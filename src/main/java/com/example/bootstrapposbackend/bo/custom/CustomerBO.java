package com.example.bootstrapposbackend.bo.custom;

import com.example.bootstrapposbackend.bo.SuperBO;
import com.example.bootstrapposbackend.dto.CustomerDTO;

import java.sql.Connection;
import java.util.List;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(CustomerDTO customerDTO, Connection connection);
    List<CustomerDTO> getAllCustomer(Connection connection);
    boolean updateCustomer(CustomerDTO customerDTO, String Id, Connection connection);
    boolean isExistsCustomer(String id,Connection connection);
    boolean deleteCustomer(String id,Connection connection);
    CustomerDTO get(String id,Connection connection);
}
