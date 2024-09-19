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
        return "Usuario con correo " + this.id + "\n" +
                "Contraseña: " + this.passwd + "\n" +
                "Usuario " + ((this.premium) ? "":"no ") + "premium (tarifa de " + ((this.premium) ? "35.5" : "20.5") + " euros)\n" +
                "Descuento: " + this.discount;
    }
}
