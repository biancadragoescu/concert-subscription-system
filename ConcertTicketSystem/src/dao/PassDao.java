/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
}
