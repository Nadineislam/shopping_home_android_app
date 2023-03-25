package com.example.mygroceryapp.Products;

import java.io.Serializable;

public class CategoryDetailsProducts implements Serializable {
    String name;
    String image;
    String type;
    int price;
    int count;

    public CategoryDetailsProducts() {
    }

    public CategoryDetailsProducts(String name, String image, String type, int price, int count) {
        this.name = name;
        this.image = image;
        this.type = type;
        this.price = price;
        this.count = count;
    }

    public CategoryDetailsProducts(String name, String image, String type, int price) {
        this.name = name;
        this.image = image;
        this.type = type;
        this.price = price;
    }


    public void setImage(String image) {
        this.image = image;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

}
