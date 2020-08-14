package com.thoughtworks.quiz.service;

import com.thoughtworks.quiz.bean.Item;
import com.thoughtworks.quiz.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getItemList() {
        return itemRepository.findAll().stream().map(itemDto ->
                new Item(itemDto.getName(), itemDto.getPrice(), itemDto.getPicture())).collect(Collectors.toList());
    }
}
