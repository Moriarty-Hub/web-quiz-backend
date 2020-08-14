package com.thoughtworks.quiz.bean;

public class Item {
    private int id;
    private String name;
    private int price;
    private String picture;
    private String unit;

    public Item() {
    }

    public Item(int id, String name, int price, String picture, String unit) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.picture = picture;
        this.unit = unit;
    }

    public int getId() {
        return id;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
