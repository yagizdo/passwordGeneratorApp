package com.example.passwordgeneratorapp;

public class Sifreler {
    private int sifre_id;
    private String sifre;

    public Sifreler() {
    }

    public Sifreler(int sifre_id, String sifre) {
        this.sifre_id = sifre_id;
        this.sifre = sifre;
    }

    public int getSifre_id() {
        return sifre_id;
    }

    public void setSifre_id(int sifre_id) {
        this.sifre_id = sifre_id;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
}
