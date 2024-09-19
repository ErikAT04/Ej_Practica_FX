package com.erikat.ej_repaso_fx.obj;

public abstract class Cliente {
    protected String id;
    protected String passwd;
    protected double discount;

    public Cliente(String id, String passwd, double discount) {
        this.id = id;
        this.passwd = passwd;
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
