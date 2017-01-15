/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import dao.PassDao;
import model.Pass;


public class PassService {
    private PassDao passDao;

    public PassService() {
        this.passDao = new PassDao();
    }
    
    public void addPass(Pass pass) {
        getPassDao().addPass(pass.getPrice(), pass.getConcerts_available());
    }

    public PassDao getPassDao() {
        return passDao;
    }

    public void setPassDao(PassDao passDao) {
        this.passDao = passDao;
    }
    
}
