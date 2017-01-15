/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Client;
import model.Concert;
import model.ConcertsTableModel;
import service.ClientService;
import service.ConcertService;
import view.AdminView;

public class AdminViewController {
    private AdminView adminView;
    private ClientService clientService;
    private ConcertService concertService;
    
    public AdminViewController(AdminView adminView) {
        this.adminView = adminView;
        clientService = new ClientService();
        concertService = new ConcertService();
    }
    
    public void setUp() {
        initializeActionListeners();
        displayAdminView();
    }
    
    protected void initializeActionListeners() {
        getAdminView().setAddClientButtonActionListener(new AddClientButtonActionListener());
        getAdminView().setAddConcertButtonActionListener(new AddConcertButtonActionListener());
        getAdminView().setUpdateConcertButtonActionListener(new UpdateConcertButtonActionListener());
        getAdminView().setConcertsTableActionListener(new ConcertTableSelectionListener());
    }
    
    protected void displayAdminView() {
        List<Concert> concerts = getConcertService().getAllConcerts();
        getAdminView().setConcertsTableModel(new ConcertsTableModel(concerts));
        getAdminView().setVisible(true);
    }
    
    private class AddClientButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = getAdminView().getClientUsername();
            String password = getAdminView().getClientPassword();
            String fullName = getAdminView().getClientFullname();

            Client client = getClientService().getClientByUsername(username);
            if(client != null) {
                JOptionPane.showMessageDialog(getAdminView(), "The username wanted is already used", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                getClientService().addClient(username, password, fullName);
                JOptionPane.showMessageDialog(getAdminView(), "The client was successfully added", "INFO", JOptionPane.INFORMATION_MESSAGE);
                getAdminView().clearClientInputs();
            }    
        }
    }
    
    private class AddConcertButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String concertName = getAdminView().getConcertName();
            Date concertDate = getAdminView().getConcertDate();
            double concertPrice = getAdminView().getConcertPrice();
            String concertGenre = getAdminView().getConcertGenre();
            String concertArtists = getAdminView().getConcertArtists();
            int availableSeats = getAdminView().getConcertAvailableSeats();
            int initialSeats = getAdminView().getConcertInitialSeats();
            
            getConcertService().addConcert(concertName, concertPrice, concertDate, concertGenre, concertArtists, availableSeats, initialSeats);
            JOptionPane.showMessageDialog(getAdminView(), "Concert successfully added", "Info", JOptionPane.INFORMATION_MESSAGE);
            getAdminView().clearConcertInputs();
            
            List<Concert> concerts = getConcertService().getAllConcerts();
            getAdminView().setConcertsTableModel(new ConcertsTableModel(concerts));
        }        
    }
    
    private class UpdateConcertButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = getAdminView().getConcertName();
            Date date = getAdminView().getConcertDate();
            double price = getAdminView().getConcertPrice();
            String genre = getAdminView().getConcertGenre();
            String artists = getAdminView().getConcertArtists();
            int availableSeats = getAdminView().getConcertAvailableSeats();
            int initialSeats = getAdminView().getConcertInitialSeats();
            
            Concert concert = getAdminView().getSelectedConcert();
            if(concert != null) {
                boolean success = getConcertService().updateConcert(concert.getId(), name, price, date, genre, artists, availableSeats, initialSeats);
                if(success) {
                    List<Concert> concerts = getConcertService().getAllConcerts();
                    getAdminView().setConcertsTableModel(new ConcertsTableModel(concerts));
                    JOptionPane.showMessageDialog(getAdminView(), "Concert successfully updated", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(getAdminView(), "Something went wrong while updating the concert", "Warn", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(getAdminView(), "Please select a concert from the table in order to update it", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }        
    }
    
    private class ConcertTableSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            Concert concert = getAdminView().getSelectedConcert();
            if(concert != null) {
                getAdminView().setConcertNameText(concert.getName());
                getAdminView().setConcertArtists(concert.getArtists());
                getAdminView().setConcertDateText(concert.getDate().toString());
                getAdminView().setConcertGenre(concert.getGenre());
                getAdminView().setConcertPrice(String.valueOf(concert.getPrice()));
                getAdminView().setConcertAvailableSeats(String.valueOf(concert.getAvailable_seats()));
                getAdminView().setConcertInitialSeats(String.valueOf(concert.getInitial_available_seats()));
            }
        }
        
    }
    
    public AdminView getAdminView() {
        return adminView;
    }
    
    public ClientService getClientService() {
        return clientService;
    }

    public ConcertService getConcertService() {
        return concertService;
    }

    public void setConcertService(ConcertService concertService) {
        this.concertService = concertService;
    }
    
    
}
