/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Client;
import model.User;

public class UserDao {
    public Client getClientByUsername(String username) {
        return null;
    }
    
    public User getUserByUsernameAndPassword(String username, String password) {
        String stringStatement = "SELECT * FROM users WHERE username = ?";
        User user = null;
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
            PreparedStatement preparedStmt = connection.prepareStatement(stringStatement);
            preparedStmt.setString(1, username);
            ResultSet results = preparedStmt.executeQuery();
            
            while(results.next()) {
                String pass = results.getString(2);
                if(password.equals(pass)) {
                    user = new User();
                    user.setUsername(results.getString(1));
                    user.setPassword(results.getString(2));
                    user.setIsAdmin(results.getBoolean(3));
                }
            }
            
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex1) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
  
        return user;
    }
    
    public void createClient(String username, String password, String fullName) {
        
    }
}
