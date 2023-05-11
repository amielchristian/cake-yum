/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

public class Order {
    private int orderID;
    private int productID;
    private int quantity;
    private double cost;
    private LocalDateTime dateTime;
    
    public Order(int orderID, int productID, int quantity, double cost, LocalDateTime dateTime) {
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.cost = cost;
        this.dateTime = dateTime;
    }
    
    // getters
    public int getOrderID() { return orderID; }
    public int getProductID() { return productID; }
    public int getQuantity() { return quantity; }
    public double getCost() { return cost; }
    public LocalDateTime getDateTime() { return dateTime; }
}
