/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.JOptionPane;
import model.Client;
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
    }
    
    protected void displayAdminView() {
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
            int seats = getAdminView().getAvailableSeats();
            
            getConcertService().addConcert(concertName, concertPrice, concertDate, concertGenre, concertArtists, seats);
            JOptionPane.showMessageDialog(getAdminView(), "Concert successfully added", "Info", JOptionPane.INFORMATION_MESSAGE);
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
