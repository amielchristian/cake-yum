/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Blob;

/**
 *
 * @author chris
 */
public class Product {
    private int id;
    private String name;
    private double price;
    private String imageString;
    private String description;
    private String altName;
    
    public Product(int id, String name, double price, String imageString, String description, String altName)  {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageString = imageString;
        this.description = description;
        this.altName = altName;
    }
    // getters
    public int getID()   { return id; }
    public String getName()  { return name; }
    public double getPrice()  { return price; }
    public String getImageString()  { return imageString; }
    public String getDescription()  { return description; }
    public String getAltName()  { return altName; }
}
