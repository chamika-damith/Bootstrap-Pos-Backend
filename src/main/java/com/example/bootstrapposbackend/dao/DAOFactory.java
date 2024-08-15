package com.example.bootstrapposbackend.dao;

import com.example.bootstrapposbackend.dao.custom.impl.CustomerDataProcess;
import com.example.bootstrapposbackend.dao.custom.impl.ItemDataProcess;
import com.example.bootstrapposbackend.dao.custom.impl.OrderDataProcess;

public class DAOFactory {
    public static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)? daoFactory=new DAOFactory() : daoFactory;
    }

    public enum DAOType {
        CUSTOMER,ITEM,ORDER;
    }

    public SuperDAO getDao(DAOType daoType){
        switch (daoType){
            case CUSTOMER:
                return new CustomerDataProcess();
            case ITEM:
                return new ItemDataProcess();
            case ORDER:
                return new OrderDataProcess();
            default:
                return null;
        }
    }
}
