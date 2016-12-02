package es.myparte.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;



import java.util.ArrayList;
import java.util.Iterator;

import es.myparte.clases.Parte;

/**
 * Created by oscarlozanohernaiz.
 */
public class ParteDbAdapter {

    /**
     * Definimos constante con el nombre de la tabla
     */
    public static final String C_TABLA = "PARTE" ;

    /**
     * Definimos constantes con el nombre de las columnas de la tabla
     */
    public static final String C_COLUMNA_ID   = "_id";
    public static final String C_COLUMNA_FECHA = "par_fecha";
    public static final String C_COLUMNA_DIRECCION = "par_direccion";
    public static final String C_COLUMNA_CIUDAD = "par_ciudad";
    public static final String C_COLUMNA_CODIGOPOSTAL = "par_codigoPostal";
    public static final String C_COLUMNA_PAIS = "par_pais";
    public static final String C_COLUMNA_ATESTADO = "par_atestado";
    public static final String C_COLUMNA_HERIDO = "par_herido";
    public static final String C_COLUMNA_DISTINTOSAB = "par_distintosAB";
    public static final String C_COLUMNA_OBJETOSA = "par_objetosA";
    public static final String C_COLUMNA_TESTIGOS = "par_testigos";
    public static final String C_COLUMNA_NOMBRE_TESTIGO = "par_nombreTestigo";
    public static final String C_COLUMNA_TELEFONO_TESTIGO = "par_telefonoTestigo";
    public static final String C_COLUMNA_LATITUD = "par_latitud";
    public static final String C_COLUMNA_LONGITUD = "par_longitud";

    public static final String C_COLUMNA_FOTO1 = "par_foto1";
    public static final String C_COLUMNA_FOTO2 = "par_foto2";
    public static final String C_COLUMNA_FOTO3 = "par_foto3";

    public static final String C_COLUMNA_ASENOMBRE_A = "par_aseNombreA";
    public static final String C_COLUMNA_ASEEMAIL_A = "par_aseEmailA";
    public static final String C_COLUMNA_ASETELEFONO_A = "par_aseTelefonoA";
    public static final String C_COLUMNA_VEHMATRICULA_A = "par_vehMatriculaA";
    public static final String C_COLUMNA_VEHMARCA_A = "par_vehMarcaA";
    public static final String C_COLUMNA_VEHMODELO_A = "par_vehModeloA";
    public static final String C_COLUMNA_VEHREMOLQUE_A = "par_vehRemolqueA";
    public static final String C_COLUMNA_VEHMATREMOL_A = "par_vehMatRemolA";
    public static final String C_COLUMNA_SEGNOMBRE_A = "par_segNombreA";
    public static final String C_COLUMNA_SEGEMAIL_A = "par_segEmailA";
    public static final String C_COLUMNA_SEGTELEFONO_A = "par_segTelefonoA";
    public static final String C_COLUMNA_CONNOMBRE_A = "par_conNombreA";
    public static final String C_COLUMNA_CONEMAIL_A = "par_conEmailA";
    public static final String C_COLUMNA_TELEFONO_A = "par_conTelefonoA";
    public static final String C_COLUMNA_DANOS_A = "par_danosA";
    public static final String C_COLUMNA_CIRCUNSTANCIAS_A = "par_circunstanciasA";

    public static final String C_COLUMNA_ASENOMBRE_B = "par_aseNombreB";
    public static final String C_COLUMNA_ASEEMAIL_B = "par_aseEmailB";
    public static final String C_COLUMNA_ASETELEFONO_B = "par_aseTelefonoB";
    public static final String C_COLUMNA_VEHMATRICULA_B = "par_vehMatriculaB";
    public static final String C_COLUMNA_VEHMARCA_B = "par_vehMarcaB";
    public static final String C_COLUMNA_VEHMODELO_B = "par_vehModeloB";
    public static final String C_COLUMNA_VEHREMOLQUE_B = "par_vehRemolqueB";
    public static final String C_COLUMNA_VEHMATREMOL_B = "par_vehMatRemolB";
    public static final String C_COLUMNA_SEGNOMBRE_B = "par_segNombreB";
    public static final String C_COLUMNA_SEGEMAIL_B = "par_segEmailB";
    public static final String C_COLUMNA_SEGTELEFONO_B = "par_segTelefonoB";
    public static final String C_COLUMNA_CONNOMBRE_B = "par_conNombreB";
    public static final String C_COLUMNA_CONEMAIL_B = "par_conEmailB";
    public static final String C_COLUMNA_TELEFONO_B = "par_conTelefonoB";
    public static final String C_COLUMNA_DANOS_B = "par_danosB";
    public static final String C_COLUMNA_CIRCUNSTANCIAS_B = "par_circunstanciasB";


    private Context contexto;
    private ParteDbHelper dbHelper;
    private SQLiteDatabase db;

    /**
     * Definimos lista de columnas de la tabla para utilizarla en las consultas a la base de datos
     */
    private String[] columnas = new String[]{ C_COLUMNA_ID,C_COLUMNA_FECHA,C_COLUMNA_DIRECCION,C_COLUMNA_CIUDAD,C_COLUMNA_CODIGOPOSTAL,
                                C_COLUMNA_PAIS,C_COLUMNA_ATESTADO,C_COLUMNA_HERIDO,C_COLUMNA_DISTINTOSAB,C_COLUMNA_OBJETOSA,C_COLUMNA_TESTIGOS,
                                C_COLUMNA_NOMBRE_TESTIGO,C_COLUMNA_TELEFONO_TESTIGO,C_COLUMNA_LATITUD,C_COLUMNA_LONGITUD,C_COLUMNA_FOTO1,
                                C_COLUMNA_FOTO2,C_COLUMNA_FOTO3,C_COLUMNA_ASENOMBRE_A,C_COLUMNA_ASEEMAIL_A,C_COLUMNA_ASETELEFONO_A,
                                C_COLUMNA_VEHMATRICULA_A,C_COLUMNA_VEHMARCA_A,C_COLUMNA_VEHMODELO_A,C_COLUMNA_VEHREMOLQUE_A,C_COLUMNA_VEHMATREMOL_A,
                                C_COLUMNA_SEGNOMBRE_A,C_COLUMNA_SEGEMAIL_A,C_COLUMNA_SEGTELEFONO_A,C_COLUMNA_CONNOMBRE_A,C_COLUMNA_CONEMAIL_A,
                                C_COLUMNA_TELEFONO_A,C_COLUMNA_DANOS_A,C_COLUMNA_CIRCUNSTANCIAS_A,C_COLUMNA_ASENOMBRE_B,C_COLUMNA_ASEEMAIL_B,
                                C_COLUMNA_ASETELEFONO_B,C_COLUMNA_VEHMATRICULA_B,C_COLUMNA_VEHMARCA_B,C_COLUMNA_VEHMODELO_B,C_COLUMNA_VEHREMOLQUE_B,
                                C_COLUMNA_VEHMATREMOL_B,C_COLUMNA_SEGNOMBRE_B,C_COLUMNA_SEGEMAIL_B,C_COLUMNA_SEGTELEFONO_B,C_COLUMNA_CONNOMBRE_B,
                                C_COLUMNA_CONEMAIL_B,C_COLUMNA_TELEFONO_B,C_COLUMNA_DANOS_B,C_COLUMNA_CIRCUNSTANCIAS_B};


    public ParteDbAdapter(Context context)
    {
        this.contexto = context;
    }

    public ParteDbAdapter abrir() throws SQLException
    {
        dbHelper = new ParteDbHelper(contexto);
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

    public ArrayList<Parte> getPartes(String filtro)
    {
        ArrayList<Parte> partess = new ArrayList<Parte>();

        if (db == null)
            abrir();

        Cursor c = db.query(true, C_TABLA, columnas, filtro, null, null, null, null, null);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
        {
            partess.add(Parte.cursorToParte(contexto, c));
        }

        c.close();

        return partess;
    }

    public long registros() throws SQLException
    {
        long i = 0;
        ArrayList<Parte> partess = new ArrayList<Parte>();

        if (db == null)
            abrir();

        Cursor c = db.query( true, C_TABLA, columnas, null, null, null, null, null, null);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
        {
            partess.add(Parte.cursorToParte(contexto, c));
        }

        Iterator<Parte> iteratorPartes = partess.iterator();
        while(iteratorPartes.hasNext()){
            Parte elemento = iteratorPartes.next();
            i = elemento.getId();
        }

        return i;
    }



}


