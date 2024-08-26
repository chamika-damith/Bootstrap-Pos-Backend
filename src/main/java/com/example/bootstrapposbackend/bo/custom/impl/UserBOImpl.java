package com.example.bootstrapposbackend.bo.custom.impl;

import com.example.bootstrapposbackend.bo.custom.UserBO;
import com.example.bootstrapposbackend.dao.DAOFactory;
import com.example.bootstrapposbackend.dao.custom.UserData;
import com.example.bootstrapposbackend.dto.UserDTO;
import com.example.bootstrapposbackend.entity.User;
import com.example.bootstrapposbackend.util.UtilProcess;

import java.sql.Connection;

public class UserBOImpl implements UserBO {

    UserData userData= (UserData) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOType.USER);

    @Override
    public boolean saveUser(UserDTO userDTO, Connection connection) {
        String id= UtilProcess.generateId();
        return userData.save(new User(id,userDTO.getUsername(),userDTO.getEmail(),userDTO.getPassword()),connection);
    }

    @Override
    public boolean isExistsUser(String email, String password, Connection connection) {
        return userData.isExistsUser(email, password, connection);
    }
}
