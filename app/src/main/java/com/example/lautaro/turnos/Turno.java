package com.example.lautaro.turnos;

/**
 * Created by Lautaro on 28/9/2017.
 */
import java.util.Date;
public class Turno {
     int idTurno;
     Date dia;
     Farmacia farmacia;
    public Turno(){};
    public Turno(int id ,Date dia, Farmacia farmacia) {
        this.idTurno = id;
        this.dia = dia;
        this.farmacia = farmacia;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }
    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Farmacia getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(Farmacia farmacia) {
        this.farmacia = farmacia;
    }
}
