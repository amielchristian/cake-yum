/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.apache.commons.codec.binary.Base64;

// this class is for retrieving product information, represented by Product objects
public class ProductGetter {
    private Connection conn;
    
    public ProductGetter(String driver, String username, String password, String url)    {
        try {
            Class.forName(driver);
            
            conn = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    // for general use
    public ArrayList<Product> getProducts()    {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String query = "SELECT * FROM PRODUCTS";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next())   {
                int id = rs.getInt("PRODUCT_ID");
                String name = rs.getString("PRODUCT_NAME");
                double price = rs.getDouble("PRODUCT_PRICE");
                Blob image = rs.getBlob("PRODUCT_IMAGE");
                String description = rs.getString("PRODUCT_DESCRIPTION");
                String altName = rs.getString("PRODUCT_ALTNAME");
                
                byte[] imageBytes = image.getBytes(1, (int)image.length());
                image.free();
                String imageString = Base64.encodeBase64String(imageBytes);
                
                products.add(new Product(id,name,price,imageString,description,altName));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    // for specific product, when altname is given
    public Product getProduct(String selection)    {
        Product product = null;
        try {
            String query = "SELECT * FROM PRODUCTS WHERE PRODUCT_ALTNAME=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, selection);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next())   {
                int id = rs.getInt("PRODUCT_ID");
                String name = rs.getString("PRODUCT_NAME");
                double price = rs.getDouble("PRODUCT_PRICE");
                Blob image = rs.getBlob("PRODUCT_IMAGE");
                String description = rs.getString("PRODUCT_DESCRIPTION");
                String altName = rs.getString("PRODUCT_ALTNAME");
                
                byte[] imageBytes = image.getBytes(1, (int)image.length());
                image.free();
                String imageString = Base64.encodeBase64String(imageBytes);
                
                product = new Product(id,name,price,imageString,description,altName);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }
    // for specific product, when ID is given
    public Product getProduct(int productID)   {
        Product product = null;
        try {
            String query = "SELECT * FROM PRODUCTS WHERE PRODUCT_ID=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, productID);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next())   {
                int id = rs.getInt("PRODUCT_ID");
                String name = rs.getString("PRODUCT_NAME");
                double price = rs.getDouble("PRODUCT_PRICE");
                Blob image = rs.getBlob("PRODUCT_IMAGE");
                String description = rs.getString("PRODUCT_DESCRIPTION");
                String altName = rs.getString("PRODUCT_ALTNAME");
                
                byte[] imageBytes = image.getBytes(1, (int)image.length());
                image.free();
                String imageString = Base64.encodeBase64String(imageBytes);
                
                product = new Product(id,name,price,imageString,description,altName);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }
}
