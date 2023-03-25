package com.example.mygroceryapp.Products;

import java.io.Serializable;

public class CartProducts implements Serializable {
    String productName;
    String productPrice;
    String totalQuantity;
    int totalPrice;
    String documentId;

    public CartProducts() {
    }

    public CartProducts(String productName, String productPrice, String totalQuantity, int totalPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getProductName() {
        return productName;
    }


    public String getProductPrice() {
        return productPrice;
    }


    public String getTotalQuantity() {
        return totalQuantity;
    }


    public int getTotalPrice() {
        return totalPrice;
    }


}
