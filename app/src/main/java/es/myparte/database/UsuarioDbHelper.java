package es.myparte.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by oscarlozanohernaiz.
 */
public class UsuarioDbHelper extends SQLiteOpenHelper {

    private static int version = 1;
    private static String name = "UsuarioDb" ;
    private static SQLiteDatabase.CursorFactory factory = null;

    public UsuarioDbHelper(Context context)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.i(this.getClass().toString(), "Creando base de datos");

        db.execSQL( "CREATE TABLE USUARIO(" +
                " _id INTEGER PRIMARY KEY," +
                " usu_usuario TEXT NOT NULL, " +
                " usu_email TEXT, " +
                " usu_telefono TEXT)" );

        db.execSQL( "CREATE UNIQUE INDEX usu_usuario ON USUARIO(usu_usuario ASC)" );

        Log.i(this.getClass().toString(), "TABLA USUARIO CREADA");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
