package com.example.lautaro.turnos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Lautaro on 28/9/2017.
 */

public class Main extends AppCompatActivity {

    Button gestionFarmacia;
    Button gestionTurnos;
    Button reproducir;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        gestionFarmacia = (Button) findViewById(R.id.gestionFarmacias);
        gestionTurnos = (Button) findViewById(R.id.gestionTurnos);
        reproducir = (Button) findViewById(R.id.reproducir);
        gestionFarmacia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String action = "android.intent.action.ALTAFARMACIA";
                Intent intent = new Intent(action);
                startActivity(intent);
            }
        });
        gestionTurnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String action = "android.intent.action.GESTIONTURNOS";
                Intent intent = new Intent(action);
                startActivity(intent);
            }
        });
        reproducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String action = "android.intent.action.PANTALLA1";
                Intent intent = new Intent(action);
                startActivity(intent);
            }
        });



    }



}
