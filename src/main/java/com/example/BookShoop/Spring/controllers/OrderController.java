package com.example.BookShoop.Spring.controllers;


import com.example.BookShoop.Spring.models.Order;
import com.example.BookShoop.Spring.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public List<Order> getOrders(){
        return orderService.getOrders();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Order addOrder(@RequestBody Order order){
        return orderService.addOrder(order);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable long id){
        return orderService.getOrder(id);
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable long id){
        orderService.deleteOrder(id);
        return "OK";
    }




}
