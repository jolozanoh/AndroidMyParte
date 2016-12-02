package es.myparte.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by oscarlozanohernaiz.
 */
public class VehiculoDbHelper extends SQLiteOpenHelper {

    private static int version = 1;
    private static String name = "VehiculoDb" ;
    private static SQLiteDatabase.CursorFactory factory = null;

    public VehiculoDbHelper(Context context)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.i(this.getClass().toString(), "Creando base de datos");

        db.execSQL( "CREATE TABLE VEHICULO(" +
                " _id INTEGER PRIMARY KEY," +
                " veh_matricula TEXT NOT NULL, " +
                " veh_marca TEXT, " +
                " veh_modelo TEXT)" );

        db.execSQL( "CREATE UNIQUE INDEX veh_matricula ON VEHICULO(veh_matricula ASC)" );

        Log.i(this.getClass().toString(), "Tabla VEHICULO creada");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}

