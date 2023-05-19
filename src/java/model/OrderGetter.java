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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

// this class is for retrieving a user's previously placed orders
public class OrderGetter {
    private Connection conn;
    
    public OrderGetter(String driver, String username, String password, String url)    {
        try {
            Class.forName(driver);
            
            conn = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    // this returns all the IDs of orders made by a user in the past
    public ArrayList<Integer> getOrderIDs(int userID)    {
        ArrayList<Integer> orderIDs = new ArrayList<>();
        try {
            String query = "SELECT * FROM ORDERS WHERE USER_ID=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next())    {
                orderIDs.add(rs.getInt("ORDER_ID"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return orderIDs;
    }
    // this returns all the products associated with an order ID, along with the metadata
    public ArrayList<Order> getOrderInfo(int orderID)   {
        ArrayList<Order> orderInfo = new ArrayList<>();
        try {
            String q1 = "SELECT * FROM ORDER_PRODUCTS WHERE ORDER_ID=?";
            PreparedStatement ps1 = conn.prepareStatement(q1);
            ps1.setInt(1, orderID);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next())   {
                int productID = rs1.getInt("PRODUCT_ID");
                int quantity = rs1.getInt("QUANTITY");
                double price = rs1.getDouble("COST");
                
                // additional call for dateTime
                String q2 = "SELECT ORDER_TIME FROM ORDERS WHERE ORDER_ID=?";
                PreparedStatement ps2 = conn.prepareStatement(q2);
                ps2.setInt(1, orderID);
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next())  {
                    LocalDateTime dateTime = rs2.getTimestamp("ORDER_TIME").toLocalDateTime();
                
                    Order order = new Order(orderID, productID, quantity, price, dateTime);
                    orderInfo.add(order);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return orderInfo;
    }
    
    public void placeOrder(Map<Integer,Integer> cart, int userID) throws SQLException {
        // add an entry to the ORDERS table
        String q1 = "INSERT INTO ORDERS(USER_ID,ORDER_TIME) VALUES (?,CURRENT_TIMESTAMP)";
        PreparedStatement ps1 = conn.prepareStatement(q1);
        ps1.setInt(1, userID);
        ps1.execute();

        for (Map.Entry<Integer,Integer> entry : cart.entrySet())   {
            Integer productID = entry.getKey();
            Integer quantity = entry.getValue();

            String query = "SELECT * FROM PRODUCTS WHERE PRODUCT_ID=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, productID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            double price = rs.getDouble("PRODUCT_PRICE");

            // add entries to ORDER_PRODUCTS table
            String q2 = "INSERT INTO ORDER_PRODUCTS(ORDER_ID,PRODUCT_ID,QUANTITY,COST) VALUES (IDENTITY_VAL_LOCAL(),?,?,?)";
            PreparedStatement ps2 = conn.prepareStatement(q2);
            ps2.setInt(1, productID);
            ps2.setInt(2, quantity);
            ps2.setDouble(3, (price * quantity));
            ps2.execute();

            // remove entries from CART table
            String q3 = "DELETE FROM CART WHERE USER_ID=? AND PRODUCT_ID=?";
            PreparedStatement ps3 = conn.prepareStatement(q3);
            ps3.setInt(1, userID);
            ps3.setInt(2, productID);
            ps3.execute();
        }
    }
}