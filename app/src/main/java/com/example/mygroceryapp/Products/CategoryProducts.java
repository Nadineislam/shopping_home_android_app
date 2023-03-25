package com.example.mygroceryapp.Products;

public class CategoryProducts {
    String name;
    String image;
    String description;
    String discount;
    String type;

    public CategoryProducts() {
    }

    public CategoryProducts(String name, String image, String description, String discount, String type) {
        this.name = name;
        this.image = image;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }


    public String getDescription() {
        return description;
    }


    public String getDiscount() {
        return discount;
    }

}
