package com.example.lautaro.turnos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Lautaro on 1/11/2017.
 */

public class FarmaciaDAO {
    SQLOpenHelper controlador;

    public FarmaciaDAO(Context context){
        controlador =SQLOpenHelper.getInstance(context);
    }


    public void agregarFarmacia(Farmacia farmacia) {
        ContentValues contenedor = new ContentValues(4);
        contenedor.put("nombre" , farmacia.getNombre().trim());
        contenedor.put("calle" , farmacia.getCalle().trim());
        contenedor.put("numero" , farmacia.getNumero());
        contenedor.put("telefono" , farmacia.getTelefono().trim());
        SQLiteDatabase query = controlador.getWritableDatabase();
        query.insert( "farmacia" , null, contenedor);
        query.close();
    }


    public void eliminarFarmacia(Farmacia farmacia) {
        SQLiteDatabase query = controlador.getWritableDatabase();
        query.delete("farmacia" ,"id_farmacia="+Integer.toString(farmacia.getIdFarmacia()).trim() , null);

    }
    public void eliminarFarmaciaS() {
        SQLiteDatabase query = controlador.getWritableDatabase();
        query.delete("farmacia" ,null , null);

    }
    public  void actualizaFarmacia(Farmacia farmacia) {
        ContentValues contenedor = new ContentValues(6);
        contenedor.put("nombre" , farmacia.getNombre().trim());
        contenedor.put("calle" , farmacia.getCalle().trim());
        contenedor.put("numero" , farmacia.getNumero());
        contenedor.put("telefono" , farmacia.getTelefono().trim());
        SQLiteDatabase query = controlador.getWritableDatabase();
        query.update( "farmacia" ,contenedor , "id_farmacia="+Integer.toString(farmacia.getIdFarmacia()).trim() , null);
        query.close();
    }
    public ArrayList<Farmacia> listaFarmacias(){
        ArrayList<Farmacia> farmacias = new ArrayList<>();
        Cursor result = controlador.getReadableDatabase().query("farmacia", null, null, null, null, null, null);
        Farmacia farmacia = null;
        while (result.moveToNext()){
            farmacia = new Farmacia();
            farmacia.setIdFarmacia(result.getInt(0));
            farmacia.setNombre(result.getString(1));
            farmacia.setCalle(result.getString(2));
            farmacia.setNumero(result.getString(3));
            farmacia.setTelefono(result.getString(4));
            farmacias.add(farmacia);
        }

        return farmacias;
        }
        public Farmacia obtenerFarmacia(int idFarmacia){
            Cursor result = controlador.getReadableDatabase().query("farmacia",null,"id_farmacia="+Integer.toString(idFarmacia).trim(),null,null,null,null);
            Farmacia farmacia = null;
            if(result.moveToFirst()){
                farmacia = new Farmacia();
                farmacia.setIdFarmacia(result.getInt(0));
                farmacia.setNombre(result.getString(1));
                farmacia.setCalle(result.getString(2));
                farmacia.setNumero(result.getString(3));
                farmacia.setTelefono(result.getString(4));

            }
            return farmacia;
        }




}
