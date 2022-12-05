/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author chris
 */
public class Product {
    private String name;
    private double price;
    private int quantity;
    
    public Product(String name, double price, int quantity)  {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    // getters
    public String getName()  {
        return name;
    }
    public double getPrice()  {
        return price;
    }
    public int getQuantity()  {
        return quantity;
    }
    
    @Override
    public String toString()  {
        return name;
    }
}
