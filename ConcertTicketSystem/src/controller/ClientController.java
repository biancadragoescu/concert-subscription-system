/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.Client;
import model.Concert;
import model.ConcertsTableModel;
import model.Pass;
import service.ConcertService;
import service.PassService;
import view.ClientView;

/**
 *
 * @author Cristina
 */
public class ClientController {
    private ClientView clientView;
    private ConcertService concertService;
    private PassService passService;
    
    public ClientController(ClientView clientView) {
        this.clientView = clientView;
        this.concertService = new ConcertService();
        this.passService = new PassService();
    }
    
    public void setUp(Client client) {
        initializeActionListeners();
        displayClientView(client);
    }

    private void initializeActionListeners() {
        getClientView().setBuyPassButtonActionListener(new BuyPassButtonActionListener());
    }

    private void displayClientView(Client client) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                List<Concert> concerts = getConcertService().getAllConcerts();
                ConcertsTableModel concertsTableModel = new ConcertsTableModel(concerts);
                getClientView().setTableModel(concertsTableModel);
                getClientView().setUserInfo(client);
                getClientView().setVisible(true);
            }
        });}

    private class BuyPassButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            buyPass();
        }    

        private void buyPass() {
            Pass pass = new Pass();
            pass.setPrice(new Double(2000));
            pass.setConcerts_available(10);
            getPassService().addPass(pass);
        }
    }
    
    public ClientView getClientView() {
        return clientView;
    }

    public ConcertService getConcertService() {
        return concertService;
    }

    public PassService getPassService() {
        return passService;
    }

    public void setPassService(PassService passService) {
        this.passService = passService;
    }
    
}
