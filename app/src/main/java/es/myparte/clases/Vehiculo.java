package es.myparte.clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import es.myparte.database.VehiculoDbAdapter;


/**
 * Created by oscarlozanohernaiz.
 */
public class Vehiculo {
    private Context context;

    private long id;
    private String matricula;
    private String marca;
    private String modelo;

    public Vehiculo(Context context)
    {
        this.context = context;
    }

    public Vehiculo(long id , String matricula, String marca, String modelo){
        this.id = id;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public static Vehiculo find(Context context, long id)
    {
        VehiculoDbAdapter dbAdapter = new VehiculoDbAdapter(context);

        Cursor c = dbAdapter.getRegistro(id);

        Vehiculo vehiculo = Vehiculo.cursorToVehiculo(context, c);

        c.close();

        return vehiculo;
    }

    public static Vehiculo cursorToVehiculo(Context context, Cursor c)
    {
        Vehiculo vehiculo = null;

        if (c != null)
        {
            vehiculo = new Vehiculo(context);

            vehiculo.setId(c.getLong(c.getColumnIndex(VehiculoDbAdapter.C_COLUMNA_ID)));
            vehiculo.setMatricula(c.getString(c.getColumnIndex(VehiculoDbAdapter.C_COLUMNA_MATRICULA)));
            vehiculo.setMarca(c.getString(c.getColumnIndex(VehiculoDbAdapter.C_COLUMNA_MARCA)));
            vehiculo.setModelo(c.getString(c.getColumnIndex(VehiculoDbAdapter.C_COLUMNA_MODELO)));

        }

        return vehiculo;
    }

    private ContentValues toContentValues()
    {
        ContentValues reg = new ContentValues();

        reg.put(VehiculoDbAdapter.C_COLUMNA_ID, this.getId());
        reg.put(VehiculoDbAdapter.C_COLUMNA_MATRICULA, this.getMatricula());
        reg.put(VehiculoDbAdapter.C_COLUMNA_MARCA, this.getMarca());
        reg.put(VehiculoDbAdapter.C_COLUMNA_MODELO, this.getModelo());


        return reg;
    }

    public long save() {
        VehiculoDbAdapter dbAdapter = new VehiculoDbAdapter(this.getContext());

        if (dbAdapter.exists(this.getId())) {
            long i = dbAdapter.registros();
            i++;
            this.setId(i);
            dbAdapter.insert(this.toContentValues());
        } else{
            if (id == 0) {
                dbAdapter.insert(this.toContentValues());
            }
        }
        return this.getId();
    }

    public long update() {
        VehiculoDbAdapter dbAdapter = new VehiculoDbAdapter(this.getContext());

        if (dbAdapter.exists(this.getId())) {
            dbAdapter.update(this.toContentValues());
        }
        return this.getId();
    }

    public long delete()
    {
        // borramos el registro
        VehiculoDbAdapter dbAdapter = new VehiculoDbAdapter(this.getContext());

        return dbAdapter.delete(this.getId());
    }


}

