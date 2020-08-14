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
        ItemDto itemTestData1 = new ItemDto("可乐", 3, "url1");
        ItemDto itemTestData2 = new ItemDto("雪碧", 5, "url2");
        ItemDto itemTestData3 = new ItemDto("方便面", 7, "url3");
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
                .andExpect(jsonPath("$[0].name", is("可乐")))
                .andExpect(jsonPath("$[0].price", is(3)))
                .andExpect(jsonPath("$[0].picture", is("url1")))
                .andExpect(jsonPath("$[1].name", is("雪碧")))
                .andExpect(jsonPath("$[1].price", is(5)))
                .andExpect(jsonPath("$[1].picture", is("url2")))
                .andExpect(jsonPath("$[2].name", is("方便面")))
                .andExpect(jsonPath("$[2].price", is(7)))
                .andExpect(jsonPath("$[2].picture", is("url3")))
                .andExpect(status().isOk());
    }

}
