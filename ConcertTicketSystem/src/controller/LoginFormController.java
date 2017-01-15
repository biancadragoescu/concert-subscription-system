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
import model.User;
import service.ClientService;
import view.AdminView;
import view.ClientView;
import view.LoginView;

public class LoginFormController {
    private ClientController clientController;
    ClientService clientService;
    private ClientView clientView;
    private LoginView loginView;
    private AdminViewController adminViewController;
    
    public LoginFormController(AdminView adminView, ClientView clientView) {
        this.adminViewController = new AdminViewController(adminView);
        this.clientView = clientView;
        this.loginView = new LoginView();
        clientService = new ClientService();
        this.clientController = new ClientController(clientView);
    }
    
    public void setUp() {
        initializeActionListeners();
        displayLoginView();
    }
    
    private void initializeActionListeners() {
        getLoginView().setLoginButtonActionListener(new LoginButtonActionListener());
    }
    
    protected void loginUser() {
        String username = getLoginView().getUsername();
        String password = getLoginView().getPassword();
        if((username != null && username.length() > 1) && (password != null && password.length() > 1)) {
            User user = getClientService().getUserByUsernameAndPassword(username, password);
            if(user != null) {
                if(user.isIsAdmin()) {
                    getAdminViewController().setUp();
                } else {
                    Client client = getClientService().getClientByUsername(username);
                    getClientController().setUp(client);
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(getLoginView(), "Please fill both username and password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    protected void displayLoginView() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                getLoginView().setVisible(true);
            }
        });
    }
    
    private class LoginButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            loginUser();
        }    
    }
    
    protected ClientService getClientService() {
        return clientService;
    }
    
    protected LoginView getLoginView() {
        return loginView;
    }

    public AdminViewController getAdminViewController() {
        return adminViewController;
    }

    public void setAdminViewController(AdminViewController adminViewController) {
        this.adminViewController = adminViewController;
    }

    public ClientView getClientView() {
        return clientView;
    }

    public void setClientView(ClientView clientView) {
        this.clientView = clientView;
    }

    public ClientController getClientController() {
        return clientController;
    }

    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }
}
