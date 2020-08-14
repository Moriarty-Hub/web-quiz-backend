package com.thoughtworks.quiz.repository;

import com.thoughtworks.quiz.dto.ItemDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<ItemDto, Integer> {

    @Override
    List<ItemDto> findAll();
}