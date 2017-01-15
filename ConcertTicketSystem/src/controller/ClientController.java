/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.Concert;
import model.ConcertsTableModel;
import service.ConcertService;
import view.ClientView;

/**
 *
 * @author Cristina
 */
public class ClientController {
    private ClientView clientView;
    private ConcertService concertService;

    public ClientController(ClientView clientView) {
        this.clientView = clientView;
        this.concertService = new ConcertService();
    }
    
    public void setUp() {
        //initializeActionListeners();
        displayClientView();
    }

    private void initializeActionListeners() {
    }

    private void displayClientView() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                List<Concert> concerts = getConcertService().getAllConcerts();
                ConcertsTableModel concertsTableModel = new ConcertsTableModel(concerts);
                getClientView().setTableModel(concertsTableModel);
                getClientView().setVisible(true);
            }
        });}

    public ClientView getClientView() {
        return clientView;
    }

    public ConcertService getConcertService() {
        return concertService;
    }
    
}
