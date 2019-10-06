package com.example.BookShoop.Spring.models;

public class OrderedBook extends AbstractModel {

    private int orderedBookId;
    private long bookID;
    private long orderID;
    private int quantity;

    public OrderedBook() {
    }

    public OrderedBook(long bookID, long orderID, int quantity) {
        this.bookID = bookID;
        this.orderID = orderID;
        this.quantity = quantity;
    }

    public long getBookID() {
        return bookID;
    }

    public void setBookID(long bookID) {
        this.bookID = bookID;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
