package com.techelevator;

import jdk.dynalink.NamedOperation;

public class Product{

    private String name;
    private double price;
    private int quantity;
    private String type;
    public Product (String name, double price,  String type) {
        this.name = name;
        this.price = price;
        this.quantity = 1;
        this.type = type;
    }


    public Product (String name, double price,  int quantity, String type) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.type = type;

    }
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }
    public void increaseQuantity (int amount){
          quantity = quantity + amount;
    }
    public void decreaseQuantity(int amount){
        if (quantity > amount){
            quantity = quantity - amount;
        }
    }
    public boolean isSoldOut(){
        return quantity == 0;
    }
}
