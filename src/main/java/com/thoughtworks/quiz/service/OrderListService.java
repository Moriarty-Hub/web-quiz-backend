package com.thoughtworks.quiz.service;

import com.thoughtworks.quiz.bean.OrderListItem;
import com.thoughtworks.quiz.dto.ItemDto;
import com.thoughtworks.quiz.dto.OrderListItemDto;
import com.thoughtworks.quiz.repository.ItemRepository;
import com.thoughtworks.quiz.repository.OrderListItemDtoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderListService {

    private final int INITIAL_QUANTITY_OF_NEW_ADDED_ITEM = 1;

    private final ItemRepository itemRepository;
    private final OrderListItemDtoRepository orderListItemDtoRepository;

    public OrderListService(ItemRepository itemRepository, OrderListItemDtoRepository orderListItemDtoRepository) {
        this.itemRepository = itemRepository;
        this.orderListItemDtoRepository = orderListItemDtoRepository;
    }

    public List<OrderListItem> getOrderList() {
        return orderListItemDtoRepository.findAll().stream().map(orderListItemDto ->
                new OrderListItem(orderListItemDto.getId(), orderListItemDto.getItemName(), orderListItemDto.getQuantity(),
                        orderListItemDto.getUnit())).collect(Collectors.toList());
    }

    public void addNewItemIntoOrderList(int id) {
        Optional<ItemDto> optionalItemDto = itemRepository.findById(id);
        if (!optionalItemDto.isPresent()) {
            return;
        }
        ItemDto itemDto = optionalItemDto.get();
        Optional<OrderListItemDto> optionalOrderListItemDto = orderListItemDtoRepository
                .findOrderListItemDtoByItemName(itemDto.getName());
        if (optionalOrderListItemDto.isPresent()) {
            OrderListItemDto newAddedOrderListItemDto = optionalOrderListItemDto.get();
            orderListItemDtoRepository.updateQuantityById(newAddedOrderListItemDto.getId(),
                    newAddedOrderListItemDto.getQuantity() + 1);
        } else {
            OrderListItemDto orderListItemDto = new OrderListItemDto(itemDto.getName(),
                    INITIAL_QUANTITY_OF_NEW_ADDED_ITEM, itemDto.getUnit());
            orderListItemDtoRepository.save(orderListItemDto);
        }
    }

    public void deleteItemFromOrderList(int id) {
        orderListItemDtoRepository.deleteById(id);
    }
}
