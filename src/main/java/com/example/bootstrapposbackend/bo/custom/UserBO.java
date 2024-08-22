package com.example.bootstrapposbackend.bo.custom;

import com.example.bootstrapposbackend.bo.SuperBO;
import com.example.bootstrapposbackend.dto.UserDTO;

import java.sql.Connection;

public interface UserBO extends SuperBO {
    boolean saveUser(UserDTO userDTO, Connection connection);
    boolean isExistsUser(String id,String password,Connection connection);
}
