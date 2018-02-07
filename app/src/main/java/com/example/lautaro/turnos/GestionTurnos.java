package com.example.lautaro.turnos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonWriter;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import adaptador.AdaptadorFarmacia;

/**
 * Created by Lautaro on 9/10/2017.
 */

public class GestionTurnos extends AppCompatActivity {
    CalendarView calendario;
    Calendar fecha = Calendar.getInstance();
    Spinner comboBox;
    TextView farmaciaDeTurno; //
    TextView diaTurno; //
    Turno turno;
    Button asignarTurno;
    ArrayList<Farmacia> farmacias;
    DatePicker calendario2;
    DatePicker calendario3;
    DatePicker calendario4;

    Spinner comboBox2;
    Spinner comboBox3;
    Button agregarTurno;
    ListView turnosMasiva;
    Button quitarTurno;
    Button confirmaCargaMasiva;
    Button confirmaVaca;
    ArrayList<Turno> arrayTurnosMasiva;
    ArrayList<String> turnos;
    @Override
    protected  void  onCreate(Bundle savedInstanceState){


        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_turnos);

        calendario = (CalendarView) findViewById(R.id.calendario);
        asignarTurno = (Button) findViewById(R.id.asignarTurno);
        comboBox = (Spinner) findViewById(R.id.comboBox);
        farmaciaDeTurno = (TextView) findViewById(R.id.farmaciadeturno);
        diaTurno = (TextView) findViewById(R.id.diaturno);
        //Agrego carga masiva
        calendario2 = (DatePicker) findViewById(R.id.calendario2);
        calendario3 = (DatePicker) findViewById(R.id.calendario3);
        calendario4 = (DatePicker) findViewById(R.id.calendario4);

        comboBox2 = (Spinner) findViewById(R.id.comboBox2);
        comboBox3 = (Spinner) findViewById(R.id.comboBox3);

        agregarTurno = (Button) findViewById(R.id.agregarTurno);
        turnosMasiva = (ListView) findViewById(R.id.listaMasiva);
        farmacias = new FarmaciaDAO(getApplicationContext()).listaFarmacias();
        comboBox.setAdapter(new ArrayAdapter<Farmacia>(this,R.layout.farmaciaspinner,farmacias));
        comboBox2.setAdapter(new ArrayAdapter<Farmacia>(this,R.layout.farmaciaspinner2,farmacias));
        comboBox3.setAdapter(new ArrayAdapter<Farmacia>(this,R.layout.farmaciaspinner2,farmacias));

        calendario2.setCalendarViewShown(false);
        calendario3.setCalendarViewShown(false);
        calendario3.setCalendarViewShown(false);
        calendario4.setCalendarViewShown(false);

        Date fechaInicio = null;
        Date fechaFin = null;
        turno = null;
        arrayTurnosMasiva = new ArrayList<>();
        turnos = new ArrayList<String>();
        confirmaCargaMasiva = (Button)  findViewById(R.id.confimaCargaMasiva);
        confirmaVaca = (Button)  findViewById(R.id.confimaVaca);

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            fechaInicio = formato.parse("01/01/2017");
            fechaFin = formato.parse("01/01/2019");
            fecha.setTime(fechaInicio);
            calendario.setMinDate(fecha.getTimeInMillis());
            fecha.setTime(fechaFin);
            calendario.setMaxDate(fecha.getTimeInMillis());
        }catch (Exception e){
            e.printStackTrace();
        }
        Date fecha = new Date();
        fecha.setTime(calendario.getDate());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        diaTurno.setText(df.format(fecha).toString());
        TurnoDAO dao =  new TurnoDAO(getApplicationContext());
        turno = dao.obtenerTurno(fecha);
        Farmacia farmacia;
        if (turno!=null) {
            farmacia = turno.getFarmacia();
            farmaciaDeTurno.setText(farmacia.getNombre());

        }else {
            farmaciaDeTurno.setText("No asignado");
        }
        registerForContextMenu(turnosMasiva);




        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                Date fecha = new Date();
                fecha.setTime(calendario.getDate());
                System.out.println(fecha.toString());
                TurnoDAO dao =  new TurnoDAO(getApplicationContext());
                turno = dao.obtenerTurno(fecha);

                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                diaTurno.setText(df.format(fecha).toString());
                Farmacia farmacia;
                if (turno!=null) {
                    farmacia = turno.getFarmacia();
                    farmaciaDeTurno.setText(farmacia.getNombre());
                }else {
                    farmaciaDeTurno.setText("No asignado");
                }
            }
        });
        asignarTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Farmacia farmacia  = (Farmacia) comboBox.getSelectedItem();

                if (turno!=null) {
                    turno.setFarmacia(farmacia);
                    new TurnoDAO(getApplicationContext()).modificarTurno(turno);
                    farmaciaDeTurno.setText(farmacia.getNombre());
                }else {
                    turno = new Turno();
                    Date fecha = new Date();
                    fecha.setTime(calendario.getDate());
                    turno.setDia(fecha);
                    turno.setFarmacia(farmacia);
                    new TurnoDAO(getApplicationContext()).agregarTurno(turno);
                    farmaciaDeTurno.setText(farmacia.getNombre());

                }
            }
        });
        agregarTurno.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Farmacia farmacia = (Farmacia) comboBox2.getSelectedItem();
                Turno turno = new Turno();
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha = new Date();
                try {
                    fecha = df.parse(Integer.toString(calendario2.getDayOfMonth())+"/"+Integer.toString(calendario2.getMonth()+1)+"/"+Integer.toString(calendario2.getYear()));
                }catch (Exception e){e.printStackTrace();}
                System.out.println( fecha.toString() );
                turno.setFarmacia(farmacia);
                turno.setDia(fecha);
                String dia = new String();
                dia = df.format(turno.getDia());
                turnos.add( dia+"  "+turno.getFarmacia().getNombre());
                arrayTurnosMasiva.add(turno);
                ArrayAdapter<String> items = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_item ,turnos);
                turnosMasiva.setAdapter(items);


            }
        });

        confirmaCargaMasiva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                //VERIFICACIONES.
                boolean verifica = true;
                if(arrayTurnosMasiva.size() != farmacias.size()){
                    verifica = false;
                    Toast.makeText(getApplicationContext(),"Faltan asignar farmacias" , Toast.LENGTH_LONG).show();

                }
                if(verifica) {
                    Date primerdia = arrayTurnosMasiva.get(0).getDia();
                    calendar.setTime(primerdia);

                    for (Turno turno : arrayTurnosMasiva) {
                        Date dia = turno.getDia();
                        if (!dia.equals(calendar.getTime())) {
                            verifica = false;
                            Toast.makeText(getApplicationContext(), "No cumple el orden", Toast.LENGTH_LONG).show();
                        }
                        calendar.add(Calendar.DAY_OF_YEAR, 1);
                    }
                }

                //Mostrar un mensaje de confirmación antes de realizar el test
                if(verifica) {
                    new TurnoDAO(getApplicationContext()).cargaMasiva(arrayTurnosMasiva);
                    }

            }
        });
        confirmaVaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Farmacia farmacia = (Farmacia) comboBox3.getSelectedItem();
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date inicio = new Date();
                Date fin = new Date();
                try {
                    inicio = df.parse(Integer.toString(calendario3.getDayOfMonth())+"/"+Integer.toString(calendario3.getMonth()+1)+"/"+Integer.toString(calendario3.getYear()));
                    fin = df.parse(Integer.toString(calendario4.getDayOfMonth())+"/"+Integer.toString(calendario4.getMonth()+1)+"/"+Integer.toString(calendario4.getYear()));

                }catch (Exception e){e.printStackTrace();}
                new TurnoDAO(getApplicationContext()).vacaciones(farmacia,inicio,fin);

            }
        });


    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_turnos, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.quitarTurno :
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                arrayTurnosMasiva.remove(info.position);
                turnos.remove(info.position);
                ArrayAdapter<String> items = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_item ,turnos);
                turnosMasiva.setAdapter(items);

                break;
            default:
                return super.onContextItemSelected(item);

        }
        return super.onContextItemSelected(item);
    }
}
