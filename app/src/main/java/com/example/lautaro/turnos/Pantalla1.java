package com.example.lautaro.turnos;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Lautaro on 14/11/2017.
 */

public class Pantalla1 extends AppCompatActivity {
    TextView fechadehoy;
    TextView farmaciaDeTurno;
    TextView calleNumero;
    TextView telefono;
    Turno turno;
    ImageButton menuPrincipal;
    boolean salir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla1);
        salir = true;
        fechadehoy = (TextView) findViewById(R.id.fechadehoy);
        farmaciaDeTurno = (TextView) findViewById(R.id.farmaciadeldia);
        calleNumero = (TextView) findViewById(R.id.callenumero);
        telefono = (TextView) findViewById(R.id.telefonopantalla);
        menuPrincipal = (ImageButton) findViewById(R.id.imageButton);
        Date fecha = Calendar.getInstance().getTime();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String diaS = formato.format(fecha);
        fechadehoy.setText(diaS);
        turno = new TurnoDAO(getApplicationContext()).obtenerTurnoRepro(fecha);
        if (turno != null) {
            Farmacia farmacia = turno.getFarmacia();
            farmaciaDeTurno.setText(farmacia.getNombre());
            calleNumero.setText(farmacia.getCalle() + " " + farmacia.getNumero());
            telefono.setText(farmacia.getTelefono());
        }
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        esperarYCerrar(5000);
        menuPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salir = false;
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

            }
        });


    }

    public void esperarYCerrar(int milisegundos) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if(salir){
                System.out.println("Cambio de pantalla");
                String action = "android.intent.action.PANTALLA2";
                Intent intent = new Intent(action);
                startActivity(intent);
                finish();}
            }
        }, milisegundos);
    }



}
