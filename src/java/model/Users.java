/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.*;
import java.util.*;

/**
 *
 * @author chris
 */
public class Users {
    // This method generates a hash map based on pairs of values in the file "login-credentials.txt"
    public Map<String, String> getLoginCredentials(File path) throws FileNotFoundException, IOException    {
        Map<String, String> loginCredentials = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        
        int lineNumber = 0;
        String line;
        String key = null;
        String value = null;
        while ((line = br.readLine())!= null)    {
            lineNumber++;
            
            if (lineNumber % 2 != 0)
                key = line;
            if (lineNumber % 2 == 0)
                value = line;
            
            if (value != null)  {
                loginCredentials.put(key, value);
                key = null;
                value = null;
            }
        }
        
        return loginCredentials;
    }
}
