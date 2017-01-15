/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import dao.UserDao;
import model.User;

public class ClientService {
    private UserDao userDao;
    
    public ClientService() {
        userDao = new UserDao();
    }
    
    
    public User getUserByUsernameAndPassword(String username, String password) {
        return getUserDao().getUserByUsernameAndPassword(username, password);
    }
    
    protected UserDao getUserDao() {
        return userDao;
    }
}
