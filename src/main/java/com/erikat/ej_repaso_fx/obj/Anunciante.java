package com.erikat.ej_repaso_fx.obj;

public class Anunciante extends Cliente {
    private int numAds;

    public Anunciante(String id, String passwd, double discount, int numAds) {
        super(id, passwd, discount);
        this.numAds = numAds;
    }

    public int getNumAds() {
        return numAds;
    }

    public void setNumAds(int numAds) {
        this.numAds = numAds;
    }
}
