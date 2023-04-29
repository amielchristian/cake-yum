/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class CartGetter {
    HashMap<Integer,Integer> map;
    Connection conn;
    
    public CartGetter(String driver, String username, String password, String url)    {
        try {
            Class.forName(driver);
            
            conn = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    // this returns a map containing the products in a specific user's cart and how much they have of that product in their cart
        // the key represents the ID of a product
        // the value represents the quantity of the product in the cart
    public HashMap<Integer,Integer> getCart(int userID)    {
        HashMap<Integer,Integer> cartContents = new HashMap<>();
        try {
            String query = "SELECT * FROM CART WHERE USER_ID=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while(rs.next())    {
                int productID = rs.getInt("PRODUCT_ID");
                int quantity = rs.getInt("QUANTITY");
                cartContents.put(productID, quantity);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return cartContents;
    }
}