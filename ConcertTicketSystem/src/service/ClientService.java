/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import dao.UserDao;
import model.Client;
import model.Pass;
import model.User;

public class ClientService {
    private UserDao userDao;
    
    public ClientService() {
        userDao = new UserDao();
    }
    
    public User getUserByUsernameAndPassword(String username, String password) {
        return getUserDao().getUserByUsernameAndPassword(username, password);
    }
    
    public Client getClientByUsername(String username) {
        return getUserDao().getClientByUsername(username);
    }
    
    public void addClient(String username, String password, String fullname) {
        getUserDao().addClient(username, password, fullname);
    }
    
    public boolean updateClientPass(String username, int passId) {
        return getUserDao().updateClientPass(username, passId);
    }
    
    protected UserDao getUserDao() {
        return userDao;
    }
}
