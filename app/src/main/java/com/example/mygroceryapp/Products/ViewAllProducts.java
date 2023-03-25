package com.example.mygroceryapp.Products;


import java.io.Serializable;

public class ViewAllProducts implements Serializable {
    String name;
    String type;
    String description;
    String image;
    int price;

    public ViewAllProducts() {
    }

    public ViewAllProducts(String name, String type, String description, String image, int price) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }


    public String getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }

}
