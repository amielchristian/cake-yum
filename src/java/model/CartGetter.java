/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

// this class is for accessing a user's cart, for both adding and removing
public class CartGetter {
    private Connection conn;
    
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
    
    public void addToCart(Map<Integer,Integer> cartContents, int productID, int quantity, int userID) throws SQLException {
        // if there is an entry in the database that contains both the user ID and the product ID, then quantity is updated
        if (cartContents.containsKey(productID))    {
            System.out.println("crash");
            String query = "UPDATE CART SET QUANTITY=? WHERE USER_ID=? AND PRODUCT_ID=?";
            PreparedStatement ps = conn.prepareCall(query);
            ps.setInt(1, quantity);
            ps.setInt(2, userID);
            ps.setInt(3, productID);
            ps.executeUpdate();
        }
        // if there is no such entry, then an entry is added to the database
        else    {
            System.out.println("boogsh");
            String query = "INSERT INTO CART(USER_ID, PRODUCT_ID, QUANTITY) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareCall(query);
            ps.setInt(1, userID);
            ps.setInt(2,productID);
            ps.setInt(3, quantity);
            ps.executeUpdate();
        }
    }
    
    public void removeFromCart(int productID, int userID) throws SQLException  {
        String query = "DELETE FROM CART WHERE USER_ID=? AND PRODUCT_ID=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, userID);
        ps.setInt(2, productID);
        ps.executeUpdate();
    }
}