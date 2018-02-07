package com.example.lautaro.turnos;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CancellationException;

/**
 * Created by Lautaro on 1/11/2017.
 */

public class TurnoDAO {
    SQLOpenHelper controlador;
    Context context;
    TurnoDAO(Context context) {
        controlador = SQLOpenHelper.getInstance(context);
        this.context = context;
    }
    public void agregarTurno(Turno turno){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String diaS = formato.format(turno.getDia());
        Date dia = null;
        try {
             dia = formato.parse(diaS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ContentValues contenedor = new ContentValues(2);
        contenedor.put("fecha" , dia.getTime());
        contenedor.put("id_farmacia" , turno.getFarmacia().getIdFarmacia());
        SQLiteDatabase db = controlador.getWritableDatabase();
        db.insert("turno" ,null ,contenedor);
    }
    public void modificarTurno(Turno turno){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String diaS = formato.format(turno.getDia());
        Date dia = null;
        try {
            dia = formato.parse(diaS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ContentValues contenedor = new ContentValues(2);
        contenedor.put("fecha" , dia.getTime());
        contenedor.put("id_farmacia" , turno.getFarmacia().getIdFarmacia());
        SQLiteDatabase db = controlador.getWritableDatabase();
        db.update("turno",contenedor,"id_turno="+Integer.toString(turno.getIdTurno()).trim(),null);
        }
    public void eliminarTurno(Turno turno){
        SQLiteDatabase db = controlador.getWritableDatabase();
        db.delete("turno","id_turno="+Integer.toString(turno.getIdTurno()).trim(),null);
    }
    public void eliminarTurnoS(){
        SQLiteDatabase db = controlador.getWritableDatabase();
        db.delete("turno",null,null);
    }
    public ArrayList<Turno> listarTurnos() {
        ArrayList<Turno> turnos = new ArrayList<>();
        Cursor result = controlador.getReadableDatabase().query("turno", null, null, null, null, null, null);
        Turno turno = null ;
        while (result.moveToNext()){
            turno = new Turno();
            turno.setIdTurno(result.getInt(0));
            turno.setDia(new Date(result.getLong(1)));//no lo se rick...
            Farmacia farmacia = new FarmaciaDAO(context).obtenerFarmacia(result.getInt(2));
            turno.setFarmacia(farmacia);
            turnos.add(turno);
                    }
        return turnos;
    }
    public ArrayList<Turno> listarSemana(Date fecha) {
        Date primerDia;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        int dias = -1*calendar.get(Calendar.DAY_OF_WEEK)+2;
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
        primerDia = calendar.getTime();
        ArrayList<Turno> turnos = new ArrayList<>();
        Turno turno = null;
        for(int i=0; i<7;i++){
            System.out.println(calendar.getTime().toString());
            turno = obtenerTurno(calendar.getTime());
            calendar.add(Calendar.DAY_OF_YEAR,1);
            turnos.add(turno);
        }

        return turnos;
    }

    public Turno obtenerTurnoRepro(Date fecha){
        SimpleDateFormat formatoH = new SimpleDateFormat("HH");
        String hora = formatoH.format(fecha);
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        int h = Integer.parseInt(hora);
        if(h<8){
           calendario.add(Calendar.DAY_OF_YEAR, -1);
        }
        fecha.setTime(calendario.getTimeInMillis());
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String diaS = formato.format(fecha);
        Date dia = null;
        try {
            dia = formato.parse(diaS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SQLiteDatabase db = controlador.getReadableDatabase();
        Cursor result =  db.query("turno",null,"fecha="+Long.toString(dia.getTime()).trim(),null,null , null,null);
        Turno turno = null;
        if(result.moveToFirst()){
            turno = new Turno();
            turno.setIdTurno(result.getInt(0));
            turno.setDia(new Date(result.getLong(1)));//no lo se rick...
            Farmacia farmacia = new FarmaciaDAO(context).obtenerFarmacia(result.getInt(2));
            turno.setFarmacia(farmacia);
        }
        return turno;
    }
    public Turno obtenerTurno(Date fecha){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String diaS = formato.format(fecha);
        Date dia = null;
        try {
            dia = formato.parse(diaS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SQLiteDatabase db = controlador.getReadableDatabase();
        Cursor result =  db.query("turno",null,"fecha="+Long.toString(dia.getTime()).trim(),null,null , null,null);
        Turno turno = null;
        if(result.moveToFirst()){
            turno = new Turno();
            turno.setIdTurno(result.getInt(0));
            turno.setDia(new Date(result.getLong(1)));//no lo se rick...
            Farmacia farmacia = new FarmaciaDAO(context).obtenerFarmacia(result.getInt(2));
            turno.setFarmacia(farmacia);
        }
        return turno;
    }

     public void cargaMasiva(ArrayList<Turno> turnos){
         eliminarTurnoS();
         Calendar calendar = Calendar.getInstance();
         ArrayList<Farmacia> secuencia = new ArrayList<>();
         for (Turno turno : turnos) {
             this.agregarTurno(turno);
             secuencia.add(turno.getFarmacia());
             calendar.setTime(turno.getDia());
         }
         calendar.add(Calendar.DAY_OF_YEAR,1);
         int index=0;
         int cantf = secuencia.size();
         for(int i=0; i<190; i++){
             Turno turno = new Turno();
             if(index==cantf) index=0;
             turno.setDia(calendar.getTime());
             turno.setFarmacia(secuencia.get(index));
             agregarTurno(turno);
             index++;
             calendar.add(Calendar.DAY_OF_YEAR,1);
         }

     }
     public void sacudir(Date inicio ){
         Calendar cal = Calendar.getInstance();
         int f = 185;
         cal.setTime(inicio);
         int i = cal.get(Calendar.DAY_OF_YEAR) + 1;
         for(int r=i; r<f ; r++){
             Turno hoy = this.obtenerTurno(cal.getTime());
             System.out.println(hoy.getFarmacia() + " este es hoy rick " + Integer.toString(r) );
             cal.add(Calendar.DAY_OF_YEAR,1);
             Turno mania = this.obtenerTurno(cal.getTime());
             hoy.setFarmacia(mania.getFarmacia());
             this.modificarTurno(hoy);

         }



     }
     public void vacaciones(Farmacia farmacia , Date inicio , Date fin){
         Calendar calendario = Calendar.getInstance();
         calendario.setTime(fin);
         int f = calendario.get(Calendar.DAY_OF_YEAR);
         calendario.setTime(inicio);
         int i = calendario.get(Calendar.DAY_OF_YEAR);
         for(int r=i; r<f; r++){
             Turno turno = this.obtenerTurno(calendario.getTime());
             calendario.add(Calendar.DAY_OF_YEAR,1);
             Turno turnoM = this.obtenerTurno(calendario.getTime());
             if (turno.getFarmacia().getIdFarmacia() == farmacia.getIdFarmacia()){
                turno.setFarmacia(turnoM.getFarmacia());
                 this.modificarTurno(turno);
                 System.out.println("VACACIONES");
                 sacudir(turnoM.getDia());
             }
             System.out.println("PASO");

         }



     }

}
