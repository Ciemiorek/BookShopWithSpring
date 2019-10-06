package com.example.BookShoop.Spring.repositories.imp;

import com.example.BookShoop.Spring.models.Book;
import com.example.BookShoop.Spring.repositories.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepositoryIMP extends AbstractRepository {




    public Book addBook(Book book) {
        Connection connection = openDataBase() ;
        PreparedStatement preparedStatement;

        try {
            preparedStatement=connection.prepareStatement("insert into books(name,author,pagessum) values(?,?,?) returning book_id;");
            preparedStatement.setString(1,book.getName());
            preparedStatement.setString(2,book.getAuthor());
            preparedStatement.setInt(3,book.getPagesSum());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            book.setBookID(resultSet.getInt("book_id"));

            preparedStatement.close();
            connection.close();

        }catch (SQLException ex){
            System.err.println("Can't add book \n" + ex);
        }

        return book;
    }

    public List<Book> getBooks() {
        List<Book> bookList = new ArrayList<>();
        Connection connection = openDataBase();
        Statement statement;

        try {
            statement=connection.createStatement();
            ResultSet resultSet =statement.executeQuery("select * from books;");
            resultSet.next();
            while (resultSet.next()){
                int book_id = resultSet.getInt("book_id");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                int pagesSum = resultSet.getInt("pagessum");

                bookList.add(new Book(book_id,name,author,pagesSum));

            }
            statement.close();
            connection.close();
        }catch (SQLException ex){
            System.err.println("Can't open database");
        }



        return bookList;
    }

    public Book getBook(int id) {
        Book book = new Book();

        Connection connection =openDataBase();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from books where book_id ="+id+";");

            if (resultSet.next()){
            book.setBookID(resultSet.getInt("book_id"));
            book.setName(resultSet.getString("name"));
            book.setAuthor(resultSet.getString("author"));
            book.setPagesSum(resultSet.getInt("pagessum"));

            }else {
               throw new IllegalArgumentException("Can't find object id: " + id + " in repository");
            }
            statement.close();
            connection.close();
        }catch (SQLException ex){
            System.err.println("Can't do this \n" +ex);
        }

        return book;
    }

    public boolean deleteBook(int id){
        Book book = new Book();

        Connection connection =openDataBase();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet =statement.executeQuery("select exists(select 1 from books where book_id=" + id +");");
            resultSet.next();
            if (resultSet.getBoolean(1)){
                statement.execute("delete from books where book_id ="+ id+";");
            }else {
                throw new IllegalArgumentException("Can't find object id: " + id + " in repository");
            }

            statement.close();
            connection.close();
        }catch (SQLException ex){
            System.err.println("Can't do this \n" +ex);
        }

        return true;
    }
}
