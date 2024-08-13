package com.example.bootstrapposbackend.controller;

import com.example.bootstrapposbackend.dao.CustomerData;
import com.example.bootstrapposbackend.dao.impl.CustomerDataProcess;
import com.example.bootstrapposbackend.dto.CustomerDTO;
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

@WebServlet("/customer")
public class CustomerController extends HttpServlet {
    Connection connection;

    CustomerData customerData= new CustomerDataProcess();

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (!"application/json".equalsIgnoreCase(req.getContentType())) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Expected content type: application/json");
                return;
            }
            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
            String saveCustomer = customerData.saveCustomer(customerDTO, connection);
            if (saveCustomer.isEmpty()) {
                resp.getWriter().write("Customer not saved");
            }else {
                resp.getWriter().write("Customer saved successfully");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
