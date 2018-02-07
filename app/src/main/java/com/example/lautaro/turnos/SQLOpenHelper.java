package com.example.lautaro.turnos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lautaro on 10/11/2017.
 */

public class SQLOpenHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE_CATEGORIA = "CREATE TABLE farmacia (" +
            "id_farmacia INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombre TEXT," +
            "calle TEXT," +
            "numero TEXT," +
            "telefono TEXT)";
    private static final String SQL_CREATE_TRABAJO = "CREATE TABLE turno (" +
            "id_turno INTEGER PRIMARY KEY AUTOINCREMENT," +
            "fecha NUMERIC ," +
            "id_farmacia INTEGER ," +
            "FOREIGN KEY (id_farmacia) REFERENCES farmacia(id_farmacia))";
    private static SQLOpenHelper _INSTANCE;

    private SQLOpenHelper(Context ctx){
        super(ctx,"WORK_FROM_HOME",null,1);
    }

    public static SQLOpenHelper getInstance(Context ctx){
        if(_INSTANCE==null) _INSTANCE = new SQLOpenHelper(ctx);
        return _INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_CATEGORIA);
        sqLiteDatabase.execSQL(SQL_CREATE_TRABAJO);
    }
        @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
