package com.example.BookShoop.Spring.repositories.imp;

import com.example.BookShoop.Spring.models.User;
import com.example.BookShoop.Spring.repositories.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryIMP extends AbstractRepository {


    public User addUser(User user) {
        Connection connection;
        connection = openDataBase();
        PreparedStatement preparedStatement;

        try {

            preparedStatement = connection.prepareStatement("insert into users(name) values (?) returning user_id;");
            preparedStatement.setString(1, user.getName());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            user.setId(resultSet.getInt(1));


            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            System.err.println("Can't add user \n" + ex);
        }
        return user;
    }

    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();

        Connection connection;
        connection = openDataBase();
        Statement statement;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users;");

            while (resultSet.next()) {
                userList.add(new User(resultSet.getInt("user_id"), resultSet.getString("name")));

            }

            statement.close();
            connection.close();
        } catch (SQLException ex) {
            System.err.println("SQL Exception" + ex);
        }

        return userList;

    }

    public User getUser(int id) {
        User user = null;
        Connection connection;
        connection = openDataBase();
        Statement statement;

        try {

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users where user_id =" + id + ";");
           if (resultSet.next()) {
               user = new User(resultSet.getInt("user_id"), resultSet.getString("name"));

           }else {
               throw new IllegalArgumentException("Can't find object id: " + id + " in repository");
           }
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            System.err.println("Can't read user \n" + ex);
        }
        return user;
    }

    public boolean deleteUser(int id) {
        Connection connection;
        connection = openDataBase();
        Statement statement;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select exists(select 1 from users where user_id=" + id + ");");
            resultSet.next();
            if (resultSet.getBoolean(1)) {

                statement.executeQuery("delete  from users where user_id =" + id + ";");
                statement.close();
                connection.close();
            } else {

                throw new IllegalArgumentException("Can't find object id: " + id + " in repository");

            }
        } catch (SQLException ex) {
            System.err.println("Can't read user \n" + ex);
        }
        return true;
    }


}
