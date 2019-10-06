package com.example.BookShoop.Spring.models;

import java.util.*;

public class Order extends AbstractModel {

    private long orderId;
    private long userId;
    private Date date;
    private Map<Long,Integer> booksIdList;

    public Order() {
        this.booksIdList=new HashMap<>();
    }

    public Order(long order_id_pk, long user_id) {
        this.orderId = order_id_pk;
        this.userId = user_id;
        this.booksIdList=new HashMap<>();
    }


    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Map<Long, Integer> getBooksIdList() {
        return booksIdList;
    }

    public void setBooksIdList(Map<Long, Integer> booksIdList) {
        this.booksIdList = booksIdList;
    }
}
