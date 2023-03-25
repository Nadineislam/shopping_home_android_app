package com.example.mygroceryapp.Products;

public class PopularProducts {
    String image;
    String name;
    String description;
    String discount;
    String type;

    public PopularProducts() {
    }

    public PopularProducts(String image, String name, String description, String discount, String type) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.discount = discount;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }


    public String getDiscount() {
        return discount;
    }

}
