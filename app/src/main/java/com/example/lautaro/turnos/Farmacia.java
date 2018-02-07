package com.example.lautaro.turnos;
import java.util.Date;

/**
 * Created by Lautaro on 26/9/2017.
 */
public class Farmacia {
    private int  idFarmacia;
    private String nombre;
    private String calle;
    private String numero;
    private String telefono;



    public Farmacia() {
    }

    public Farmacia(int idFarmacia, String nombre, String calle, String numero, String telefono) {
        this.idFarmacia = idFarmacia;
        this.nombre = nombre;
        this.calle = calle;
        this.numero = numero;
        this.telefono = telefono;

    }

    public int getIdFarmacia() {
        return idFarmacia;
    }

    public void setIdFarmacia(int idFarmacia) {
        this.idFarmacia = idFarmacia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return nombre;
    }
}