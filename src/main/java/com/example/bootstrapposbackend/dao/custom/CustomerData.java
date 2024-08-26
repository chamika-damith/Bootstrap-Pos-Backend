package com.example.bootstrapposbackend.dao.custom;

import com.example.bootstrapposbackend.dao.CrudDAO;
import com.example.bootstrapposbackend.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CustomerData extends CrudDAO<Customer>  {
}
