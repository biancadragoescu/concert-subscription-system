/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Date;

public class Concert {
    private int id;
    private String name;
    private Date date;
    private Double price;
    private String genre;
    private String artists;
    private int available_seats;
    private int initial_available_seats;

    public Concert(int id, String name, Date date, Double price, String genre, String artists, int available_seats, int initial_available_seats) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.price = price;
        this.genre = genre;
        this.artists = artists;
        this.available_seats = available_seats;
        this.initial_available_seats = initial_available_seats;
    }

    public Concert() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public int getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(int available_seats) {
        this.available_seats = available_seats;
    }

    public int getInitial_available_seats() {
        return initial_available_seats;
    }

    public void setInitial_available_seats(int initial_available_seats) {
        this.initial_available_seats = initial_available_seats;
    }
}
