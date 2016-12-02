package es.myparte.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by oscarlozanohernaiz.
 */
public class ParteDbHelper extends SQLiteOpenHelper {

    private static int version = 1;
    private static String name = "ParteDb" ;
    private static SQLiteDatabase.CursorFactory factory = null;

    public ParteDbHelper(Context context)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.i(this.getClass().toString(), "Creando base de datos");

        db.execSQL( "CREATE TABLE PARTE(" +
                " _id INTEGER PRIMARY KEY," +
                " par_fecha TEX NOT NULL, " +
                " par_direccion TEXT, " +
                " par_ciudad TEXT, " +
                " par_codigoPostal TEXT, " +
                " par_pais TEXT, " +
                " par_atestado TEXT, " +
                " par_herido TEXT, " +
                " par_distintosAB TEXT, " +
                " par_objetosA TEXT, " +
                " par_testigos TEXT, " +
                " par_nombreTestigo TEXT, " +
                " par_telefonoTestigo TEXT, " +
                " par_latitud TEXT, " +
                " par_longitud TEXT, " +

                " par_foto1 TEXT, " +
                " par_foto2 TEXT, " +
                " par_foto3 TEXT, " +

                " par_aseNombreA TEXT, " +
                " par_aseEmailA TEXT, " +
                " par_aseTelefonoA TEXT, " +
                " par_vehMatriculaA TEXT, " +
                " par_vehMarcaA TEXT, " +
                " par_vehModeloA TEXT, " +
                " par_vehRemolqueA TEXT, " +
                " par_vehMatRemolA TEXT, " +
                " par_segNombreA TEXT, " +
                " par_segEmailA TEXT, " +
                " par_segTelefonoA TEXT, " +
                " par_conNombreA TEXT, " +
                " par_conEmailA TEXT, " +
                " par_conTelefonoA TEXT, " +
                " par_danosA TEXT, " +
                " par_circunstanciasA TEXT," +

                " par_aseNombreB TEXT, " +
                " par_aseEmailB TEXT, " +
                " par_aseTelefonoB TEXT, " +
                " par_vehMatriculaB TEXT, " +
                " par_vehMarcaB TEXT, " +
                " par_vehModeloB TEXT, " +
                " par_vehRemolqueB TEXT, " +
                " par_vehMatRemolB TEXT, " +
                " par_segNombreB TEXT, " +
                " par_segEmailB TEXT, " +
                " par_segTelefonoB TEXT, " +
                " par_conNombreB TEXT, " +
                " par_conEmailB TEXT, " +
                " par_conTelefonoB TEXT, " +
                " par_danosB TEXT, " +
                " par_circunstanciasB TEXT) ");

        db.execSQL( "CREATE UNIQUE INDEX par_fecha ON PARTE(par_fecha ASC)" );

        Log.i(this.getClass().toString(), "TABLA PARTE CREADA");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
