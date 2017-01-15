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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Concert;

public class ConcertDao {
    
    public List<Concert> getAllConcerts() {
        String stringStatement = "SELECT * FROM concerts";
        List<Concert> concerts = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
            PreparedStatement preparedStmt = connection.prepareStatement(stringStatement);
            ResultSet results = preparedStmt.executeQuery();
            
            while(results.next()) {
                Concert concert = new Concert();
                concert.setId(results.getInt(1));
                concert.setName(results.getString(2));
                concert.setPrice(results.getDouble(3));
                concert.setDate(results.getDate(4));
                concert.setGenre(results.getString(5));
                concert.setArtists(results.getString(6));
                concert.setInitial_available_seats(results.getInt(7));
                concert.setAvailable_seats(results.getInt(8));
                
                concerts.add(concert);
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
  
        return concerts;
    }
    
    public void addConcert(String name, double price, java.sql.Date date, String genre, String artists, int availableSeats, int initialSeats) {
        String insertUserStatement = "INSERT INTO concerts(name, price, date, musicGenre, artists, initialNumberOfSeats, numberOfAvailableSeats) values(?,?,?,?,?,?,?)";
        
        Connection con = null;
        try {
            con = DBUtils.getConnection();
            PreparedStatement insertUserPrepare = con.prepareStatement(insertUserStatement);
            insertUserPrepare.setString(1, name);
            insertUserPrepare.setDouble(2, price);
            insertUserPrepare.setDate(3, date);
            insertUserPrepare.setString(4, genre);
            insertUserPrepare.setString(5, artists);
            insertUserPrepare.setInt(6, availableSeats);
            insertUserPrepare.setInt(7, initialSeats);
            insertUserPrepare.execute();
            
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean updateConcert(int concertId, String name, double price, java.sql.Date date, String genre, String artists, int availableSeats, int initialSeats) {
        String statementAsString = "UPDATE CONCERTS SET name=?, date=?, price=?, musicgenre=?, artists=?, initialNumberOfSeats=?, numberOfAvailableSeats=? WHERE id=?";
        Connection con = null;
        boolean success = true;
        
        try {
            con = DBUtils.getConnection();
            PreparedStatement statement = con.prepareStatement(statementAsString);
            statement.setString(1, name);
            statement.setDate(2, date);
            statement.setDouble(3, price);
            statement.setString(4, genre);
            statement.setString(5, artists);
            statement.setInt(6, initialSeats);
            statement.setInt(7, availableSeats);
            statement.setInt(8, concertId);
            statement.executeUpdate();
            
            con.close();
        } catch (SQLException ex) {
            success = false;
            Logger.getLogger(ConcertDao.class.getName()).log(Level.SEVERE, null, ex);
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException ex1) {
                    Logger.getLogger(ConcertDao.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
         
        return success;
    }
    
    public Concert getConcertById(int id)
    {
        return null;
    }
}
