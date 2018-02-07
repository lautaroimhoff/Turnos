package com.example.lautaro.turnos;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Lautaro on 17/11/2017.
 */

public class Pantalla2 extends AppCompatActivity {

    TextView nomb1;
    TextView nomb2;
    TextView nomb3;
    TextView nomb4;
    TextView nomb5;
    TextView nomb6;
    TextView nomb7;
    TextView dir1;
    TextView dir2;
    TextView dir3;
    TextView dir4;
    TextView dir5;
    TextView dir6;
    TextView dir7;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla2);
        nomb1 = (TextView) findViewById(R.id.nomb1);
        nomb2 = (TextView) findViewById(R.id.nomb2);
        nomb3 = (TextView) findViewById(R.id.nomb3);
        nomb4 = (TextView) findViewById(R.id.nomb4);
        nomb5 = (TextView) findViewById(R.id.nomb5);
        nomb6 = (TextView) findViewById(R.id.nomb6);
        nomb7 = (TextView) findViewById(R.id.nomb7);

        dir1 = (TextView) findViewById(R.id.dire1);
        dir2 = (TextView) findViewById(R.id.dire2);
        dir3 = (TextView) findViewById(R.id.dire3);
        dir4 = (TextView) findViewById(R.id.dire4);
        dir5 = (TextView) findViewById(R.id.dire5);
        dir6 = (TextView) findViewById(R.id.dire6);
        dir7 = (TextView) findViewById(R.id.dire7);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        esperarYCerrar(10000);
        Date fecha = Calendar.getInstance().getTime();
        ArrayList<Turno> turnos = new TurnoDAO(getApplicationContext()).listarSemana(fecha);
        getSupportActionBar().hide();
        Turno turno;
        turno = turnos.get(0);
        if(turno !=null){
            nomb1.setText("FARMACIA "+turno.getFarmacia().getNombre());
            dir1.setText(turno.getFarmacia().getCalle() + " " + turno.getFarmacia().getNumero());
        }
        turno = turnos.get(1);
        if(turno !=null){
            nomb2.setText("FARMACIA "+turno.getFarmacia().getNombre());
            dir2.setText(turno.getFarmacia().getCalle() + " " + turno.getFarmacia().getNumero());
        }
        turno = turnos.get(2);
        if(turno !=null){
            nomb3.setText("FARMACIA "+turno.getFarmacia().getNombre());
            dir3.setText(turno.getFarmacia().getCalle() + " " + turno.getFarmacia().getNumero());
        }
        turno = turnos.get(3);
        if(turno !=null){
            nomb4.setText("FARMACIA "+turno.getFarmacia().getNombre());
            dir4.setText(turno.getFarmacia().getCalle() + " " + turno.getFarmacia().getNumero());
        }
        turno = turnos.get(4);
        if(turno !=null){
            nomb5.setText("FARMACIA "+turno.getFarmacia().getNombre());
            dir5.setText(turno.getFarmacia().getCalle() + " " + turno.getFarmacia().getNumero());
        }
        turno = turnos.get(5);
        if(turno !=null){
            nomb6.setText("FARMACIA "+turno.getFarmacia().getNombre());
            dir6.setText(turno.getFarmacia().getCalle() + " " + turno.getFarmacia().getNumero());
        }
        turno = turnos.get(6);
        if(turno !=null){
            nomb7.setText("FARMACIA "+turno.getFarmacia().getNombre());
            dir7.setText(turno.getFarmacia().getCalle() + " " + turno.getFarmacia().getNumero());
        }
    }
    public void esperarYCerrar(int milisegundos) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                System.out.println("Cambio de pantalla");
                String action = "android.intent.action.PANTALLA1";
                Intent intent = new Intent(action);
                startActivity(intent);
                finish();

            }
        }, milisegundos);
    }
}
