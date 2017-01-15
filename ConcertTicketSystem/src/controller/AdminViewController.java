/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Client;
import service.ClientService;
import view.AdminView;

public class AdminViewController {
    private AdminView adminView;
    private ClientService clientService;
    
    public AdminViewController(AdminView adminView) {
        this.adminView = adminView;
        clientService = new ClientService();
    }
    
    public void setUp() {
        initializeActionListeners();
        displayAdminView();
    }
    
    protected void initializeActionListeners() {
        getAdminView().setAddClientButtonActionListener(new AddClientButtonActionListener());
    }
    
    protected void displayAdminView() {
        getAdminView().setVisible(true);
    }
    
    private class AddClientButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = getAdminView().getUsername();
            String password = getAdminView().getPassword();
            String fullName = getAdminView().getFullname();

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
    
    public AdminView getAdminView() {
        return adminView;
    }
    
    public ClientService getClientService() {
        return clientService;
    }
}
