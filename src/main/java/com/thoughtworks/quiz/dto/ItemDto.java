package com.thoughtworks.quiz.dto;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class ItemDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer price;
    private String picture;

    public ItemDto() {
    }

    public ItemDto(String name, Integer price, String picture) {
        this.name = name;
        this.price = price;
        this.picture = picture;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
