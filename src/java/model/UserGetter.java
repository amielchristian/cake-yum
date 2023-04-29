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

public class UserGetter {
    HashMap<Integer,Integer> map;
    Connection conn;
    
    public UserGetter(String driver, String username, String password, String url)    {
        try {
            Class.forName(driver);
            
            conn = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public int getUserID(String username)    {
        int userID = 0;
        try {
            String userIDQuery = "SELECT USER_ID FROM USERS WHERE USER_UNAME=?";
            PreparedStatement ps1 = conn.prepareStatement(userIDQuery);
            ps1.setString(1, username);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next())  {
                userID = rs1.getInt("USER_ID");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return userID;
    }
}