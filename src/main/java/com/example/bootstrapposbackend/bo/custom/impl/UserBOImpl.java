package com.example.bootstrapposbackend.bo.custom.impl;

import com.example.bootstrapposbackend.bo.custom.UserBO;
import com.example.bootstrapposbackend.dao.DAOFactory;
import com.example.bootstrapposbackend.dao.custom.UserData;
import com.example.bootstrapposbackend.dto.UserDTO;
import com.example.bootstrapposbackend.entity.User;

import java.sql.Connection;
import java.util.UUID;

public class UserBOImpl implements UserBO {

    UserData userData= (UserData) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOType.USER);

    @Override
    public boolean saveUser(UserDTO userDTO, Connection connection) {
        String id= UUID.randomUUID().toString();
        return userData.save(new User(id,userDTO.getEmail(),userDTO.getUsername(),userDTO.getPassword()),connection);
    }

    @Override
    public boolean isExistsUser(String email, String password, Connection connection) {
        return userData.isExistsUser(email, password, connection);
    }
}
