package com.thoughtworks.quiz.bean;

public class Item {
    private String name;
    private int price;
    private String picture;

    public Item() {
    }

    public Item(String name, int price, String picture) {
        this.name = name;
        this.price = price;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
