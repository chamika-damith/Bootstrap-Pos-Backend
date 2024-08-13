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
import java.io.PrintWriter;
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


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            if (!"application/json".equalsIgnoreCase(req.getContentType())) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Expected content type: application/json");
                return;
            }

            CustomerDTO customer = customerData.getCustomer(id, connection);
            PrintWriter writer = resp.getWriter();
            Jsonb jsonb = JsonbBuilder.create();
            jsonb.toJson(customer,writer);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (!"application/json".equalsIgnoreCase(req.getContentType())) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Expected content type: application/json");
                return;
            }
            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
            boolean updateCustomer = customerData.updateCustomer(String.valueOf(customerDTO.getId()), customerDTO, connection);
            if (updateCustomer) {
                resp.getWriter().write("Customer update saved");
            }else {
                resp.getWriter().write("Customer update successfully");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (!"application/json".equalsIgnoreCase(req.getContentType())) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Expected content type: application/json");
                return;
            }

            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
            if (customerData.deleteCustomer(String.valueOf(customerDTO.getId()), connection)) {
                resp.getWriter().write("Delete success");
            }else {
                resp.getWriter().write("Delete not success");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
