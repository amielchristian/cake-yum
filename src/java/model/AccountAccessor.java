/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.Security;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author chris
 */
public class AccountAccessor {
    private Connection conn;
    
    public AccountAccessor (String driver, String username, String password, String url)    {
        try {
            Class.forName(driver);
            
            conn = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public LinkedList<String> login (String username) throws SQLException    {
        LinkedList<String> userInfo = new LinkedList();
        
        String query = "SELECT * FROM USERS WHERE USER_UNAME=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            userInfo.add(rs.getString("USER_UNAME"));
            userInfo.add(rs.getString("USER_PASSWORD"));

            userInfo.add(rs.getString("USER_GIVEN_NAME"));
            userInfo.add(rs.getString("USER_ADDRESS"));
            userInfo.add(rs.getString("USER_CONTACT_NUM"));
        }
        
        return userInfo;
    }
    
    public void delete (int userID) throws SQLException   {
        // create DELETE queries
        List<String> queries = new ArrayList<>();
        queries.add("DELETE FROM ORDER_PRODUCTS WHERE ORDER_ID IN (SELECT ORDER_ID FROM ORDERS WHERE USER_ID=?)");
        queries.add("DELETE FROM ORDERS WHERE USER_ID=?");
        queries.add("DELETE FROM CART WHERE USER_ID=?");
        queries.add("DELETE FROM USERS WHERE USER_ID=?");

        // execute DELETE queries
        for (String query : queries)    {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userID);
            ps.execute();
        }
    }
    
    public void signup (String username, String password, String fullName, String address, String celNum) throws SQLException   {
        String query = "INSERT INTO USERS(USER_UNAME, USER_PASSWORD, USER_GIVEN_NAME, USER_ADDRESS, USER_CONTACT_NUM) VALUES (?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, password);
        ps.setString(3, fullName);
        ps.setString(4, address);
        ps.setString(5, celNum);
        ps.execute();
    }
}
