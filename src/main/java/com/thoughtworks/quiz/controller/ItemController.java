package com.thoughtworks.quiz.controller;

import com.thoughtworks.quiz.bean.Item;
import com.thoughtworks.quiz.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/get-item-list")
    public ResponseEntity<List<Item>> getItemList() {
        return ResponseEntity.ok().body(itemService.getItemList());
    }
}
