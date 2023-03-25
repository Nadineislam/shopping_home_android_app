package com.example.mygroceryapp.Products;

public class RecommendedProducts {
    String name;
    String description;
    String image;

    public RecommendedProducts() {
    }

    public RecommendedProducts(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;

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


    public String getImage() {
        return image;
    }


}
