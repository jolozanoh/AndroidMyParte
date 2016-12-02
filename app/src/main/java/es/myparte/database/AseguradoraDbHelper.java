package es.myparte.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by oscarlozanohernaiz.
 */
public class AseguradoraDbHelper extends SQLiteOpenHelper {

    private static int version = 1;
    private static String name = "AseguradoraDb" ;
    private static SQLiteDatabase.CursorFactory factory = null;

    public AseguradoraDbHelper(Context context)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.i(this.getClass().toString(), "Creando base de datos");

        db.execSQL( "CREATE TABLE ASEGURADORA(" +
                " _id INTEGER PRIMARY KEY," +
                " ase_asesor TEXT NOT NULL, " +
                " ase_email TEXT, " +
                " ase_telefono TEXT)" );

        db.execSQL( "CREATE UNIQUE INDEX ase_asesor ON ASEGURADORA(ase_asesor ASC)" );

        Log.i(this.getClass().toString(), "TABLA ASEGURADORA CREADA");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
