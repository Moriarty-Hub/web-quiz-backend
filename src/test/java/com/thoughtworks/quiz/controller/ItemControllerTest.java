package com.thoughtworks.quiz.controller;

import com.thoughtworks.quiz.dto.ItemDto;
import com.thoughtworks.quiz.repository.ItemRepository;
import org.junit.jupiter.api.AfterEach;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    public void setUp() {
        ItemDto itemTestData1 = new ItemDto("可乐", 3, "url1", "瓶");
        ItemDto itemTestData2 = new ItemDto("雪碧", 5, "url2", "瓶");
        ItemDto itemTestData3 = new ItemDto("方便面", 7, "url3", "桶");
        itemRepository.save(itemTestData1);
        itemRepository.save(itemTestData2);
        itemRepository.save(itemTestData3);
    }

    @AfterEach
    public void tearDown() {
        itemRepository.deleteAll();
    }

    @Test
    public void should_return_entire_item_list() throws Exception {
        mockMvc.perform(get("/get-item-list"))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("可乐")))
                .andExpect(jsonPath("$[0].price", is(3)))
                .andExpect(jsonPath("$[0].picture", is("url1")))
                .andExpect(jsonPath("$[0].unit", is("瓶")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("雪碧")))
                .andExpect(jsonPath("$[1].price", is(5)))
                .andExpect(jsonPath("$[1].picture", is("url2")))
                .andExpect(jsonPath("$[1].unit", is("瓶")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].name", is("方便面")))
                .andExpect(jsonPath("$[2].price", is(7)))
                .andExpect(jsonPath("$[2].picture", is("url3")))
                .andExpect(jsonPath("$[2].unit", is("桶")))
                .andExpect(status().isOk());
    }

    @Test
    public void should_add_new_item_when_all_parameters_are_valid() throws Exception {
        mockMvc.perform(post("/add-new-item")
                .param("name", "香蕉")
                .param("price", "20")
                .param("picture", "urlOfBanana")
                .param("unit", "斤")).andExpect(status().isOk());

        mockMvc.perform(get("/get-item-list"))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("可乐")))
                .andExpect(jsonPath("$[0].price", is(3)))
                .andExpect(jsonPath("$[0].picture", is("url1")))
                .andExpect(jsonPath("$[0].unit", is("瓶")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("雪碧")))
                .andExpect(jsonPath("$[1].price", is(5)))
                .andExpect(jsonPath("$[1].picture", is("url2")))
                .andExpect(jsonPath("$[1].unit", is("瓶")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].name", is("方便面")))
                .andExpect(jsonPath("$[2].price", is(7)))
                .andExpect(jsonPath("$[2].picture", is("url3")))
                .andExpect(jsonPath("$[2].unit", is("桶")))
                .andExpect(jsonPath("$[3].id", is(4)))
                .andExpect(jsonPath("$[3].name", is("香蕉")))
                .andExpect(jsonPath("$[3].price", is(20)))
                .andExpect(jsonPath("$[3].picture", is("urlOfBanana")))
                .andExpect(jsonPath("$[3].unit", is("斤")))
                .andExpect(status().isOk());
    }

    @Test
    public void should_throw_null_parameter_exception_when_parameter_is_null() throws Exception {
        mockMvc.perform(post("/add-new-item")
                .param("price", "20")
                .param("picture", "urlOfBanana")
                .param("unit", "斤"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("The parameter cannot be null")));

        mockMvc.perform(post("/add-new-item")
                .param("name", "香蕉")
                .param("picture", "urlOfBanana")
                .param("unit", "斤"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("The parameter cannot be null")));

        mockMvc.perform(post("/add-new-item")
                .param("name", "香蕉")
                .param("price", "20")
                .param("unit", "斤"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("The parameter cannot be null")));

        mockMvc.perform(post("/add-new-item")
                .param("name", "香蕉")
                .param("price", "20")
                .param("picture", "urlOfBanana"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("The parameter cannot be null")));
    }

    @Test
    public void should_throw_name_already_exist_exception_when_name_is_repeated() throws Exception {
        mockMvc.perform(post("/add-new-item")
                .param("name", "可乐")
                .param("price", "20")
                .param("picture", "urlOfCola")
                .param("unit", "瓶"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("The item name has already exist")));
    }

}
