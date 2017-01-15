/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import dao.ConcertDao;
import java.util.List;
import model.Concert;

public class ConcertService {
    private ConcertDao concertDao;

    public ConcertService() {
        this.concertDao = new ConcertDao();
    }
    
    public List<Concert> getAllConcerts() {
         return getConcertDao().getAllConcerts();
    }

    public ConcertDao getConcertDao() {
        return concertDao;
    }
}
