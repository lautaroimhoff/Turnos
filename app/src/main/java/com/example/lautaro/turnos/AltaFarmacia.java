package com.example.lautaro.turnos;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.Duration;

import adaptador.AdaptadorFarmacia;

public class AltaFarmacia extends AppCompatActivity {

    EditText nombre;
    EditText calle ;
    EditText nro ;
    EditText telefono ;
    EditText email ;
    Button agregar;
    Button modificar;
    ListView listaFarmacia;
    AdaptadorFarmacia adapter;
    ArrayList<Farmacia> farmacias;
    Farmacia farmacia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta_farmacia);
        nombre = (EditText) findViewById(R.id.tNombre);
        calle = (EditText) findViewById(R.id.tCalle);
        nro = (EditText) findViewById(R.id.tNumeroCalle);
        telefono = (EditText) findViewById(R.id.tTelefono);
        agregar = (Button) findViewById(R.id.Agregar);
        modificar = (Button) findViewById(R.id.Modficar);

        farmacias = new FarmaciaDAO(getApplicationContext()).listaFarmacias();
        listaFarmacia = (ListView) findViewById(R.id.listaFarmacias);
        adapter = new AdaptadorFarmacia(getApplicationContext(),farmacias);
        listaFarmacia.setAdapter(adapter);
        registerForContextMenu(listaFarmacia);
        modificar.setEnabled(false);
        agregar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            Farmacia farmacia = new Farmacia();
                            farmacia.setNombre(nombre.getText().toString());
                            farmacia.setCalle(calle.getText().toString());
                            farmacia.setNumero(nro.getText().toString());
                            farmacia.setTelefono(telefono.getText().toString());
                            new FarmaciaDAO(getApplicationContext()).agregarFarmacia(farmacia);
                            farmacias = new FarmaciaDAO(getApplicationContext()).listaFarmacias();
                            adapter = new AdaptadorFarmacia(getApplicationContext(), farmacias);
                            listaFarmacia.setAdapter(adapter);
                            nombre.setText("");
                            calle.setText("");
                            nro.setText("");
                            telefono.setText("");
                    }
                }

        );

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                farmacia.setNombre(nombre.getText().toString());
                farmacia.setCalle(calle.getText().toString());
                farmacia.setNumero(nro.getText().toString());
                farmacia.setTelefono(telefono.getText().toString());
                new FarmaciaDAO(getApplicationContext()).actualizaFarmacia(farmacia);
                farmacias = new FarmaciaDAO(getApplicationContext()).listaFarmacias();
                adapter = new AdaptadorFarmacia(getApplicationContext(), farmacias);
                listaFarmacia.setAdapter(adapter);
                modificar.setEnabled(false);
                agregar.setEnabled(true);
                nombre.setText("");
                calle.setText("");
                nro.setText("");
                telefono.setText("");


            }
        });

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.modificar_borrar, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.modificarFarmacia:
                AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                farmacia = (Farmacia) listaFarmacia.getItemAtPosition(info.position);
                nombre.setText(farmacia.getNombre());
                calle.setText(farmacia.getCalle());
                nro.setText(farmacia.getNumero());
                telefono.setText(farmacia.getTelefono());
                agregar.setEnabled(false);
                modificar.setEnabled(true);
                return true;
            case R.id.borrarFarmacia:
                AdapterView.AdapterContextMenuInfo info2= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                farmacia = (Farmacia) listaFarmacia.getItemAtPosition(info2.position);
                new FarmaciaDAO(getApplicationContext()).eliminarFarmacia(farmacia);
                farmacias = new FarmaciaDAO(getApplicationContext()).listaFarmacias();
                adapter = new AdaptadorFarmacia(getApplicationContext(), farmacias);
                listaFarmacia.setAdapter(adapter);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }




}
