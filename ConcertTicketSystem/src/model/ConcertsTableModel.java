/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Cristina
 */
public class ConcertsTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Name", "Price", "Date", "Music genre", "Artists", "Initial number of seats", "Number of available seats"};
    private List<Concert> concerts;

    public ConcertsTableModel(List<Concert> concerts) {
        this.concerts = concerts;
    }
    
    @Override
    public int getColumnCount() {
            return columnNames.length;
    }

    @Override
    public int getRowCount() {
        if (concerts != null) {
           return concerts.size(); 
        } else {
            return -1;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return concerts.get(rowIndex).getId();
            case 1:
                return concerts.get(rowIndex).getName();
            case 2:
                return concerts.get(rowIndex).getPrice();
            case 3:
                return concerts.get(rowIndex).getDate();
            case 4:
                return concerts.get(rowIndex).getGenre();
            case 5:
                return concerts.get(rowIndex).getArtists();
            case 6:
                return concerts.get(rowIndex).getInitial_available_seats();
            case 7:
                return concerts.get(rowIndex).getAvailable_seats();		
            default:
                break;
        }
        return null;
    }
    
    @Override
     public String getColumnName(int column) {
        return columnNames[column];
    }
     
    public Concert getConcertAtRow(int rowIndex){
        Concert concert = new Concert();
        concert.setId(concerts.get(rowIndex).getId());
        concert.setName(concerts.get(rowIndex).getName());
        concert.setPrice(concerts.get(rowIndex).getPrice());
        concert.setDate(concerts.get(rowIndex).getDate());
        concert.setGenre(concerts.get(rowIndex).getGenre());
        concert.setArtists(concerts.get(rowIndex).getArtists());
        concert.setInitial_available_seats(concerts.get(rowIndex).getInitial_available_seats());
        concert.setAvailable_seats(concerts.get(rowIndex).getAvailable_seats());
        
        return concert;	
    }

    public void setConcerts(List<Concert> concerts) {
        this.concerts = concerts;
    }
}   
