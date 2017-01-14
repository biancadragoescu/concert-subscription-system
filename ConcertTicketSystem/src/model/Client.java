/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

public class Client extends User {
    private String name;
    private Pass pass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pass getPass() {
        return pass;
    }

    public void setPass(Pass pass) {
        this.pass = pass;
    }
}
