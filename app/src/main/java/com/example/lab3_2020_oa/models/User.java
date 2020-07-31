package com.example.lab3_2020_oa.models;
public class User {
    private String mail;
    private String pass;

    public User(String mail, String pass) {
        this.mail = mail;
        this.pass = pass;
    }

    public User() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}