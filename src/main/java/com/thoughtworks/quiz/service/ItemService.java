package com.thoughtworks.quiz.service;

import com.thoughtworks.quiz.bean.Item;
import com.thoughtworks.quiz.dto.ItemDto;
import com.thoughtworks.quiz.exception.NameAlreadyExistException;
import com.thoughtworks.quiz.exception.NullParameterException;
import com.thoughtworks.quiz.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getItemList() {
        return itemRepository.findAll().stream().map(itemDto ->
                new Item(itemDto.getId(), itemDto.getName(), itemDto.getPrice(), itemDto.getPicture(), itemDto.getUnit())).collect(Collectors.toList());
    }

    public void addNewItem(String name, Integer price, String picture, String unit) {
        if (name == null || price == null || picture == null || unit == null) {
            throw new NullParameterException("The parameter cannot be null");
        }
        if (isNameExist(name)) {
            throw new NameAlreadyExistException("The item name has already exist");
        }
    }

    public boolean isNameExist(String name) {
        Optional<ItemDto> optionalItemDto = itemRepository.findItemDtoByName(name);
        return optionalItemDto.isPresent();
    }
}
