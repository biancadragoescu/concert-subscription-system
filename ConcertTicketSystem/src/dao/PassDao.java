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
import model.Pass;

public class PassDao {
    
    public void addPass(Double price, int remainingNumberOfConcerts) {
        String insertPassStatement = "INSERT INTO passes(price, remainingNumberOfConcerts) values (?,?)";
        
        Connection con = null;
        try {
            con = DBUtils.getConnection();
            PreparedStatement insertPassPrepare = con.prepareStatement(insertPassStatement);
            insertPassPrepare.setDouble(1, price);
            insertPassPrepare.setInt(2, remainingNumberOfConcerts);
            insertPassPrepare.execute();
          
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
    
    public int getLastPassId() {
        String stringStatement = "SELECT passes.id from passes order by passes.id desc limit 1";
        int id = 0;
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
            PreparedStatement preparedStmt = connection.prepareStatement(stringStatement);
            ResultSet results = preparedStmt.executeQuery();
            
            while(results.next()) {
                id = results.getInt(1);
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
        return id;
    }
    
    public Pass getPassById(int id) {
        String stringStatement = "SELECT * FROM passes WHERE id = ?";
        Pass pass = null;
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
            PreparedStatement preparedStmt = connection.prepareStatement(stringStatement);
            preparedStmt.setInt(1, id);
            ResultSet results = preparedStmt.executeQuery();
            
            while(results.next()) {
                pass = new Pass();
                pass.setId(id);
                pass.setPrice(results.getDouble(2));
                pass.setConcerts_available(results.getInt(3));
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
  
        return pass;
    }
}
