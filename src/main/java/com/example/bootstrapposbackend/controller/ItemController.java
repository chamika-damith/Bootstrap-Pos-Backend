package com.example.bootstrapposbackend.controller;

import com.example.bootstrapposbackend.dao.ItemData;
import com.example.bootstrapposbackend.dao.impl.ItemDataProcess;
import com.example.bootstrapposbackend.dto.CustomerDTO;
import com.example.bootstrapposbackend.dto.ItemDTO;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/item")
public class ItemController extends HttpServlet {
    Connection connection;

    ItemData itemData=new ItemDataProcess();

    @Override
    public void init() throws ServletException {
        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/customers");
            this.connection = pool.getConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (!"application/json".equalsIgnoreCase(req.getContentType())) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Expected content type: application/json");
                return;
            }
            Jsonb jsonb = JsonbBuilder.create();
            ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);
            String saveCustomer = itemData.saveItem(itemDTO,connection);
            if (saveCustomer.isEmpty()) {
                resp.getWriter().write("Item not saved");
            }else {
                resp.getWriter().write("Item saved successfully");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
