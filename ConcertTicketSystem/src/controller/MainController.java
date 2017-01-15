/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import view.AdminView;
import view.ClientView;

public class MainController {
    
    public static void main(String[] args) {
        AdminView adminView = new AdminView();
        ClientView clientView = new ClientView();
        LoginFormController lfc = new LoginFormController(adminView, clientView);
        lfc.setUp();
    }
}
