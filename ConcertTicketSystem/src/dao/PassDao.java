/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

public class PassDao {
    private int id;
    private int number_concerts_available;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber_concerts_available() {
        return number_concerts_available;
    }

    public void setNumber_concerts_available(int number_concerts_available) {
        this.number_concerts_available = number_concerts_available;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
