package com.example.bootstrapposbackend.controller;

import com.example.bootstrapposbackend.dao.custom.OrderData;
import com.example.bootstrapposbackend.dao.custom.impl.OrderDataProcess;
import com.example.bootstrapposbackend.dto.OrderDTO;
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

@WebServlet("/order")
public class OrderController extends HttpServlet {
    Connection connection;

    OrderData orderData= new OrderDataProcess();

    @Override
    public void init() throws ServletException {
        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/pos");
            this.connection = pool.getConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (!"application/json".equalsIgnoreCase(req.getContentType())) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Expected content type: application/json");
                return;
            }
            Jsonb jsonb = JsonbBuilder.create();
            OrderDTO orderDTO = jsonb.fromJson(req.getReader(), OrderDTO.class);
            String saveCustomer = orderData.saveOrder(orderDTO,connection);
            System.out.println(orderDTO.getOrderId());
            if (saveCustomer.isEmpty()) {
                resp.getWriter().write("order not saved");
            }else {
                resp.getWriter().write("order saved successfully");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
