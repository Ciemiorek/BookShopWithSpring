package com.example.BookShoop.Spring.repositories;

import com.example.BookShoop.Spring.models.AbstractModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;

public abstract class AbstractRepository<M extends AbstractModel> {


    protected static String JBDC_URL = "jdbc:postgresql://localhost:5432/bookshop";
    protected static String USER = "postgres";
    protected static String PASSWORD = "password";


    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error class \'org.postgresql.Driver\' NOT FOUND");
        }
    }


    protected Connection openDataBase() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(JBDC_URL, USER, PASSWORD);
        }catch (SQLException ex){
            System.err.println("Can't open database \n"+ex);
            return null;
        }
        return connection;
    }


}
