package com.example.bootstrapposbackend.dao;

import com.example.bootstrapposbackend.dto.CustomerDTO;
import com.example.bootstrapposbackend.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemData {
    ItemDTO getItem(String itemId, Connection connection) throws SQLException;
    String saveItem(ItemDTO itemDTO,Connection connection);
    boolean deleteItem(String itemId,Connection connection);
    boolean updateItem(String itemId,ItemDTO itemDTO,Connection connection);
    List<ItemDTO> getAllItem(Connection connection);
}
