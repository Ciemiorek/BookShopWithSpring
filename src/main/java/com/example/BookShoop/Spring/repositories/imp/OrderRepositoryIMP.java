package com.example.BookShoop.Spring.repositories.imp;

import com.example.BookShoop.Spring.models.Order;
import com.example.BookShoop.Spring.repositories.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepositoryIMP extends AbstractRepository {


    public List<Order> getOrders() {
        List<Order> orderList = new ArrayList<>();
        Connection connection = openDataBase();
        Statement statement;
        Statement statementBooks;
        try {
            statement = connection.createStatement();
            statementBooks = connection.createStatement();

            ResultSet resultSetBooks;
            ResultSet resultSet = statement.executeQuery("select * from orders;");
            resultSet.next();

            while (resultSet.next()) {
                resultSetBooks = statementBooks.executeQuery("select * from ordered_books where order_id=" + resultSet.getLong("order_id") + ";");
                long orderId = resultSet.getLong("order_id");
                long userId = resultSet.getLong("user_id");

                Order order = new Order(orderId, userId);

                Date date = resultSet.getDate("date");

                order.setDate(date);

                //resultSetBooks.next();
                while (resultSetBooks.next()) {
                    Integer intToInt = Integer.valueOf(resultSetBooks.getInt("quantity"));
                    Long longToLong = Long.valueOf(resultSetBooks.getLong("book_id"));

                    order.getBooksIdList().put(longToLong, intToInt);
                }
                orderList.add(order);
            }
        } catch (SQLException ex) {
            System.err.println("Can't do this \n" + ex);
        }


        return orderList;
    }

    public Order addOrder(Order order) {
        Connection connection = openDataBase();
        PreparedStatement statement;

        try {
            statement = connection.prepareStatement("insert into orders(user_id,date) values(?,now()) returning order_id , date;");
            statement.setLong(1, order.getUserId());
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            order.setOrderId(resultSet.getLong("order_id"));
            order.setDate(resultSet.getDate("date"));

            statement = connection.prepareStatement("insert into ordered_books(order_id,book_id,quantity) values(?,?,?);");
            statement.setLong(1, order.getOrderId());

            for (Map.Entry<Long, Integer> book : order.getBooksIdList().entrySet()) {
                statement.setLong(2, book.getKey());
                statement.setInt(3, book.getValue());
                statement.execute();
            }

            statement.close();
            connection.close();


        } catch (SQLException ex) {
            System.err.println("Can't do this \n" + ex);
        }


        return order;
    }

    public Order getOrder(long id) {
        Order order = new Order();
        Connection connection = openDataBase();
        Statement statement;
        Statement statement1Books;

        try {
            statement1Books = connection.createStatement();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from orders where order_id =" + id + ";");
            ResultSet resultSetBooks;
            if (resultSet.next()) {
                order.setDate(resultSet.getDate("date"));
                order.setOrderId(resultSet.getLong("order_id"));
                order.setUserId(resultSet.getLong("user_id"));
                resultSetBooks = statement1Books.executeQuery("select * from ordered_books where order_id =" + id + ";");

                while (resultSetBooks.next()){

                    Integer intToInt = Integer.valueOf(resultSetBooks.getInt("quantity"));
                    Long longToLong = Long.valueOf(resultSetBooks.getLong("book_id"));

                    order.getBooksIdList().put(longToLong, intToInt);
                }

            }else {
                throw new IllegalArgumentException("Can't find object id: " + id + " in repository");
            }

            statement1Books.close();
            statement.close();
            connection.close();


        } catch (SQLException ex) {
            System.err.println("Can't do this \n" + ex);
        }


        return order;
    }

    public void deleteOrder(long id) {

        Connection connection = openDataBase();
        Statement statement;
        Statement statement1Books;

        try {
            statement1Books = connection.createStatement();
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from orders where order_id =" + id + ";");
            ResultSet resultSetBooks;
            if (resultSet.next()) {

               statement1Books.execute("delete  from ordered_books where order_id =" + id + ";");
               statement.execute("delete  from orders where order_id =" + id + ";");


            }else {
                throw new IllegalArgumentException("Can't find object id: " + id + " in repository");
            }

            statement1Books.close();
            statement.close();
            connection.close();


        } catch (SQLException ex) {
            System.err.println("Can't do this \n" + ex);
        }

    }
}
