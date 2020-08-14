package com.thoughtworks.quiz.controller;

import com.thoughtworks.quiz.dto.ItemDto;
import com.thoughtworks.quiz.dto.OrderListItemDto;
import com.thoughtworks.quiz.repository.ItemRepository;
import com.thoughtworks.quiz.repository.OrderListItemDtoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class OrderListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderListItemDtoRepository orderListItemDtoRepository;

    @BeforeEach
    public void setUp() {
        ItemDto itemTestData1 = new ItemDto("可乐", 3, "url1", "瓶");
        ItemDto itemTestData2 = new ItemDto("雪碧", 5, "url2", "瓶");
        ItemDto itemTestData3 = new ItemDto("方便面", 7, "url3", "桶");
        ItemDto itemTestData4 = new ItemDto("火腿肠", 6, "url4", "根");
        itemRepository.save(itemTestData1);
        itemRepository.save(itemTestData2);
        itemRepository.save(itemTestData3);
        itemRepository.save(itemTestData4);

        OrderListItemDto orderListItemTestData1 = new OrderListItemDto("可乐", 2, "瓶");
        OrderListItemDto orderListItemTestData2 = new OrderListItemDto("雪碧", 3, "瓶");
        OrderListItemDto orderListItemTestData3 = new OrderListItemDto("方便面", 1, "桶");
        orderListItemDtoRepository.save(orderListItemTestData1);
        orderListItemDtoRepository.save(orderListItemTestData2);
        orderListItemDtoRepository.save(orderListItemTestData3);
    }


    @Test
    public void should_return_entire_order_list() throws Exception {
        mockMvc.perform(get("/get-order-list"))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].itemName", is("可乐")))
                .andExpect(jsonPath("$[0].quantity", is(2)))
                .andExpect(jsonPath("$[0].unit", is("瓶")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].itemName", is("雪碧")))
                .andExpect(jsonPath("$[1].quantity", is(3)))
                .andExpect(jsonPath("$[1].unit", is("瓶")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].itemName", is("方便面")))
                .andExpect(jsonPath("$[2].quantity", is(1)))
                .andExpect(jsonPath("$[2].unit", is("桶")))
                .andExpect(status().isOk());
    }

    @Test
    public void should_increase_item_quantity_when_item_exist_in_order_list() throws Exception {
        mockMvc.perform(post("/add-item-into-order-list").param("id", "2"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/get-order-list"))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].itemName", is("可乐")))
                .andExpect(jsonPath("$[0].quantity", is(2)))
                .andExpect(jsonPath("$[0].unit", is("瓶")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].itemName", is("雪碧")))
                .andExpect(jsonPath("$[1].quantity", is(4)))
                .andExpect(jsonPath("$[1].unit", is("瓶")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].itemName", is("方便面")))
                .andExpect(jsonPath("$[2].quantity", is(1)))
                .andExpect(jsonPath("$[2].unit", is("桶")))
                .andExpect(status().isOk());
    }

    @Test
    public void should_add_item_into_order_list_when_item_not_exist_in_it() throws Exception {
        mockMvc.perform(post("/add-item-into-order-list").param("id", "4"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/get-order-list"))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].itemName", is("可乐")))
                .andExpect(jsonPath("$[0].quantity", is(2)))
                .andExpect(jsonPath("$[0].unit", is("瓶")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].itemName", is("雪碧")))
                .andExpect(jsonPath("$[1].quantity", is(3)))
                .andExpect(jsonPath("$[1].unit", is("瓶")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].itemName", is("方便面")))
                .andExpect(jsonPath("$[2].quantity", is(1)))
                .andExpect(jsonPath("$[2].unit", is("桶")))
                .andExpect(jsonPath("$[3].id", is(4)))
                .andExpect(jsonPath("$[3].itemName", is("火腿肠")))
                .andExpect(jsonPath("$[3].quantity", is(1)))
                .andExpect(jsonPath("$[3].unit", is("根")))
                .andExpect(status().isOk());
    }

    @Test
    public void should_delete_item_from_order_list_by_the_given_id() throws Exception {
        mockMvc.perform(delete("/delete-item").param("id", "1"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/get-order-list"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].itemName", is("雪碧")))
                .andExpect(jsonPath("$[0].quantity", is(3)))
                .andExpect(jsonPath("$[0].unit", is("瓶")))
                .andExpect(jsonPath("$[1].id", is(3)))
                .andExpect(jsonPath("$[1].itemName", is("方便面")))
                .andExpect(jsonPath("$[1].quantity", is(1)))
                .andExpect(jsonPath("$[1].unit", is("桶")))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/delete-item").param("id", "3"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/get-order-list"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].itemName", is("雪碧")))
                .andExpect(jsonPath("$[0].quantity", is(3)))
                .andExpect(jsonPath("$[0].unit", is("瓶")))
                .andExpect(status().isOk());
    }
}
