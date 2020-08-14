package com.thoughtworks.quiz.repository;

import com.thoughtworks.quiz.dto.OrderListItemDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface OrderListItemDtoRepository extends CrudRepository<OrderListItemDto, Integer> {

    @Override
    List<OrderListItemDto> findAll();

    @Override
    Optional<OrderListItemDto> findById(Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE OrderListItemDto item SET item.quantity = :quantity WHERE item.id = :id")
    void updateQuantityById(@Param("id") Integer id, @Param("quantity") Integer quantity);

    Optional<OrderListItemDto> findOrderListItemDtoByItemName(String itemName);

    @Override
    void deleteById(Integer integer);
}
