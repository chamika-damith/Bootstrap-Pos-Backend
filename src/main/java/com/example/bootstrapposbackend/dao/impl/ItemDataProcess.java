package com.example.bootstrapposbackend.dao.impl;

import com.example.bootstrapposbackend.dao.ItemData;
import com.example.bootstrapposbackend.dto.CustomerDTO;
import com.example.bootstrapposbackend.dto.ItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ItemDataProcess implements ItemData {

    static String SAVE_ITEM = "INSERT INTO item (id,name,price,qty) VALUES (?,?,?,?)";
    static String GET_ITEM = "SELECT * FROM item WHERE id=?";



    @Override
    public ItemDTO getItem(String itemId, Connection connection) throws SQLException {
        var itemDTO = new ItemDTO();
        try {
            var ps = connection.prepareStatement(GET_ITEM);
            ps.setString(1, itemId);
            var resultSet = ps.executeQuery();
            while (resultSet.next()) {
                itemDTO.setId(resultSet.getInt("id"));
                itemDTO.setName(resultSet.getString("name"));
                itemDTO.setPrice(resultSet.getDouble("price"));
                itemDTO.setQty(resultSet.getInt("qty"));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return itemDTO;
    }

    @Override
    public String saveItem(ItemDTO itemDTO, Connection connection) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(SAVE_ITEM);
            preparedStatement.setInt(1, itemDTO.getId());
            preparedStatement.setString(2,itemDTO.getName());
            preparedStatement.setDouble(3,itemDTO.getPrice());
            preparedStatement.setInt(4,itemDTO.getQty());

            if (preparedStatement.executeUpdate() != 0) {
                return "success save customer";
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return "error save customer";
    }

    @Override
    public boolean deleteItem(String itemId, Connection connection) {
        return false;
    }

    @Override
    public boolean updateItem(String itemId, ItemDTO itemDTO, Connection connection) {
        return false;
    }

    @Override
    public List<ItemDTO> getAllItem(Connection connection) {
        return null;
    }
}
