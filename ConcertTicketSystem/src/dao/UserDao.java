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
        String stringStatement = "SELECT u.username, password, isAdmin, name FROM USERS as u LEFT JOIN USERSINFORMATION as ui on u.username = ui.username where u.username = ?";
        Client client = null;
        Connection connection = null;
        
        try {
            connection = DBUtils.getConnection();
            PreparedStatement getClientByUsername = connection.prepareStatement(stringStatement);
            getClientByUsername.setString(1, username);
            ResultSet results = getClientByUsername.executeQuery();
            if(results.first()) {
               String usernameResult = results.getString(1);
               String passwordResult = results.getString(2);
               boolean isAdminResult = results.getBoolean(3);
               String fullNameResult = results.getString(4);
               
               client = new Client();
               client.setUsername(usernameResult);
               client.setPassword(passwordResult);
               client.setIsAdmin(isAdminResult);
               client.setName(fullNameResult);
            }
            
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
     
        return client;
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
    
    public void addClient(String username, String password, String fullName) {
        String insertUserStatement = "INSERT INTO USERS(username, password, isAdmin) values (?,?,?)";
        String insertUserDetails = "INSERT INTO USERSINFORMATION(username, name) values	(?,?)";
        
        Connection con = null;
        try {
            con = DBUtils.getConnection();
            PreparedStatement addClientPrepare = con.prepareStatement(insertUserStatement);
            addClientPrepare.setString(1, username);
            addClientPrepare.setString(2, password);
            addClientPrepare.setBoolean(3, false);
            addClientPrepare.execute();
            
            PreparedStatement insertUserInfoPrepare = con.prepareStatement(insertUserDetails);
            insertUserInfoPrepare.setString(1, username);
            insertUserInfoPrepare.setString(2, fullName);
            insertUserInfoPrepare.execute();
          
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException ex1) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
    }
    
}
