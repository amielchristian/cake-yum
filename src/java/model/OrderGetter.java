/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;

public class OrderGetter {
    HashMap<Integer,Integer> map;
    Connection conn;
    
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
                    Date dateTime = new Date(rs2.getTimestamp("ORDER_TIME").getTime());
                
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
}