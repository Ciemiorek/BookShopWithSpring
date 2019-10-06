package com.example.BookShoop.Spring.services;

import com.example.BookShoop.Spring.models.Order;
import com.example.BookShoop.Spring.repositories.imp.OrderRepositoryIMP;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepositoryIMP orderRepositoryIMP;

    public OrderService(OrderRepositoryIMP orderRepositoryIMP) {
        this.orderRepositoryIMP = orderRepositoryIMP;
    }

    public List<Order> getOrders() {
        return  orderRepositoryIMP.getOrders();

    }

    public Order addOrder(Order order) {
        return orderRepositoryIMP.addOrder(order);
    }

    public Order getOrder(long id) {
        return orderRepositoryIMP.getOrder(id);
    }

}
