package com.example.mygroceryapp.Products;

public class HomeProducts {
    String name;
    String image;
    String type;

    public HomeProducts(String name, String image, String type) {
        this.name = name;
        this.image = image;
        this.type = type;
    }

    public HomeProducts() {
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
}
