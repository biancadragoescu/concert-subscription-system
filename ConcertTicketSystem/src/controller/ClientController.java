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
import service.ClientService;
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
    private ClientService clientService;
    private PassService passService;
    
    public ClientController(ClientView clientView) {
        this.clientView = clientView;
        this.concertService = new ConcertService();
        this.clientService = new ClientService();
        this.passService = new PassService();
    }
    
    public void setUp(Client client) {
        initializeActionListeners(client);
        displayClientView(client);
    }

    private void initializeActionListeners(Client client) {
        getClientView().setBuyPassButtonActionListener(new BuyPassButtonActionListener(client));
    }

    private void displayClientView(final Client client) {
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
        private Client client;

        public BuyPassButtonActionListener(Client client) {
            this.client = client;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            buyPass();
        }    

        private void buyPass() {
            Pass pass = new Pass();
            pass.setPrice(new Double(2000));
            pass.setConcerts_available(10);
            getPassService().addPass(pass);
            getClientService().updateClientPass(getClient().getUsername(), getPassService().getLastPassId());
        }

        public Client getClient() {
            return client;
        }

        public void setClient(Client client) {
            this.client = client;
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

    public ClientService getClientService() {
        return clientService;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
    
}
