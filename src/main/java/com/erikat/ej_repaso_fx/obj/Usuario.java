package com.erikat.ej_repaso_fx.obj;

public class Usuario extends Cliente{

    private boolean premium;

    public Usuario(String id, String passwd, double discount, boolean premium) {
        super(id, passwd, discount);
        this.premium = premium;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    @Override
    public String toString() {
        return super.toString() + "Usuario " + (this.premium ? "" : "no ") + "premium (tarifa de " + (this.premium ? "35.5 " : "25.5 ") + "euros)."; //Primer ternario: Añade "no" si el usuario es no premium. Segundo ternario: Pone 35.5 si es premium y 25.5 si es no premium
    }
}
