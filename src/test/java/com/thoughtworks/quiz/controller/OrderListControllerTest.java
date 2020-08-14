package com.thoughtworks.quiz.controller;

import com.thoughtworks.quiz.dto.OrderListItemDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class OrderListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderListItemDtoRepository orderListItemDtoRepository;

    @BeforeEach
    public void setUp() {
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
}
