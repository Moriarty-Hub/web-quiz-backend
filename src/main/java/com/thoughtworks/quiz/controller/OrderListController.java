package com.thoughtworks.quiz.controller;

import com.thoughtworks.quiz.bean.OrderListItem;
import com.thoughtworks.quiz.service.OrderListService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrderListController {

    private final OrderListService orderListService;

    public OrderListController(OrderListService orderListService) {
        this.orderListService = orderListService;
    }

    @GetMapping("/get-order-list")
    public ResponseEntity<List<OrderListItem>> getOrderList() {
        return ResponseEntity.ok().body(orderListService.getOrderList());
    }
}
