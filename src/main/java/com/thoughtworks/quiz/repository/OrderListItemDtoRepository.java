package com.thoughtworks.quiz.repository;

import com.thoughtworks.quiz.dto.OrderListItemDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderListItemDtoRepository extends CrudRepository<OrderListItemDto, Integer> {

    @Override
    List<OrderListItemDto> findAll();
}
