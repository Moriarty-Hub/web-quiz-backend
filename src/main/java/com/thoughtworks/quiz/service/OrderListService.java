package com.thoughtworks.quiz.service;

import com.thoughtworks.quiz.bean.OrderListItem;
import com.thoughtworks.quiz.repository.OrderListItemDtoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderListService {

    private final OrderListItemDtoRepository orderListItemDtoRepository;

    public OrderListService(OrderListItemDtoRepository orderListItemDtoRepository) {
        this.orderListItemDtoRepository = orderListItemDtoRepository;
    }

    public List<OrderListItem> getOrderList() {
        return orderListItemDtoRepository.findAll().stream().map(orderListItemDto ->
                new OrderListItem(orderListItemDto.getId(), orderListItemDto.getItemName(), orderListItemDto.getQuantity(),
                        orderListItemDto.getUnit())).collect(Collectors.toList());
    }
}
