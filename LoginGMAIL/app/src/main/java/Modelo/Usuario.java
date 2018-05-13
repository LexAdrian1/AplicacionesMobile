package Modelo;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombre;
    private String mail;
    private String pass;

    public Usuario(String nombre, String mail, String pass) {
        this.nombre = nombre;
        this.mail = mail;
        this.pass = pass;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return this.nombre + " " + this.mail;
    }
}
