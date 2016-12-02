package es.myparte.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;



import java.util.ArrayList;
import java.util.Iterator;

import es.myparte.clases.Aseguradora;

/**
 * Created by oscarlozanohernaiz.
 */
public class AseguradoraDbAdapter {

    /**
     * Definimos constante con el nombre de la tabla
     */
    public static final String C_TABLA = "ASEGURADORA" ;

    /**
     * Definimos constantes con el nombre de las columnas de la tabla
     */
    public static final String C_COLUMNA_ID   = "_id";
    public static final String C_COLUMNA_ASESOR = "ase_asesor";
    public static final String C_COLUMNA_EMAIL = "ase_email";
    public static final String C_COLUMNA_TELEFONO = "ase_telefono";


    private Context contexto;
    private AseguradoraDbHelper dbHelper;
    private SQLiteDatabase db;

    /**
     * Definimos lista de columnas de la tabla para utilizarla en las consultas a la base de datos
     */
    private String[] columnas = new String[]{ C_COLUMNA_ID, C_COLUMNA_ASESOR, C_COLUMNA_EMAIL, C_COLUMNA_TELEFONO} ;

    public AseguradoraDbAdapter(Context context)
    {
        this.contexto = context;
    }

    public AseguradoraDbAdapter abrir() throws SQLException
    {
        dbHelper = new AseguradoraDbHelper(contexto);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void cerrar()
    {
        dbHelper.close();
    }

    /**
     * Devuelve cursor con todos las columnas de la tabla
     */
    public Cursor getCursor() throws SQLException
    {
        Cursor c = db.query( true, C_TABLA, columnas, null, null, null, null, null, null);

        return c;
    }

    public Cursor getRegistro(long id) throws SQLException
    {
        if (db == null)
            abrir();
        Cursor c = db.query( true, C_TABLA, columnas, C_COLUMNA_ID + "=" + id, null, null, null, null, null);

        //Nos movemos al primer registro de la consulta
        if (c != null) {
            c.moveToFirst();


        }
        return c;
    }
    /**
     * Inserta los valores en un registro de la tabla
     */
    public long insert(ContentValues reg)
    {
        if (db == null)
            abrir();

        return db.insert(C_TABLA, null, reg);
    }

    /**
     * Eliminar el registro con el identificador indicado
     */
    public long delete(long id)
    {
        if (db == null)
            abrir();

        return db.delete(C_TABLA, "_id=" + id, null);
    }

    /**
     * Modificar el registro
     */
    public long update(ContentValues reg)
    {
        long result = 0;

        if (db == null)
            abrir();

        if (reg.containsKey(C_COLUMNA_ID))
        {
            long id = reg.getAsLong(C_COLUMNA_ID);

            //reg.remove(C_COLUMNA_ID);
            result = db.update(C_TABLA, reg, "_id=" + id, null);
        }
        return result;
    }

    /**
     * Comprueba si existe el registro
     */
    public boolean exists(long id) throws SQLException
    {
        boolean exists ;

        if (db == null)
            abrir();

        Cursor c = db.query( true, C_TABLA, columnas, C_COLUMNA_ID + "=" + id, null, null, null, null, null);

        exists = (c.getCount() > 0);

        c.close();

        return exists;
    }

    public ArrayList<Aseguradora> getAseguradoras(String filtro)
    {
        ArrayList<Aseguradora> aseguradorass = new ArrayList<Aseguradora>();

        if (db == null)
            abrir();

        Cursor c = db.query(true, C_TABLA, columnas, filtro, null, null, null, null, null);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
        {
            aseguradorass.add(Aseguradora.cursorToAseguradora(contexto, c));
        }

        c.close();

        return aseguradorass;
    }

    public long registros() throws SQLException
    {
        long i = 0;
        ArrayList<Aseguradora> aseguradorass = new ArrayList<Aseguradora>();

        if (db == null)
            abrir();

        Cursor c = db.query( true, C_TABLA, columnas, null, null, null, null, null, null);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
        {
            aseguradorass.add(Aseguradora.cursorToAseguradora(contexto, c));
        }

        Iterator<Aseguradora> iteratorAseguradoras = aseguradorass.iterator();
        while(iteratorAseguradoras.hasNext()){
            Aseguradora elemento = iteratorAseguradoras.next();
            i = elemento.getId();
        }

        return i;
    }



}

