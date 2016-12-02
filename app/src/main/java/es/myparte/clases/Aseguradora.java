package es.myparte.clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import es.myparte.database.AseguradoraDbAdapter;


/**
 * Created by oscarlozanohernaiz.
 */
public class Aseguradora {
    private Context context;

    private long id;
    private String asesor;
    private String email;
    private String telefono;

    public Aseguradora(Context context)
    {
        this.context = context;
    }

    public Aseguradora(long id , String asesor, String email, String telefono){
        this.id = id;
        this.asesor = asesor;
        this.email = email;
        this.telefono = telefono;
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

    public String getAsesor() {
        return asesor;
    }

    public void setAsesor(String asesor) {
        this.asesor = asesor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public static Aseguradora find(Context context, long id)
    {
        AseguradoraDbAdapter dbAdapter = new AseguradoraDbAdapter(context);

        Cursor c = dbAdapter.getRegistro(id);

        Aseguradora aseguradora = Aseguradora.cursorToAseguradora(context, c);

        c.close();

        return aseguradora;
    }

    public static Aseguradora cursorToAseguradora(Context context, Cursor c)
    {
        Aseguradora aseguradora = null;

        if (c != null)
        {
            aseguradora = new Aseguradora(context);

            aseguradora.setId(c.getLong(c.getColumnIndex(AseguradoraDbAdapter.C_COLUMNA_ID)));
            aseguradora.setAsesor(c.getString(c.getColumnIndex(AseguradoraDbAdapter.C_COLUMNA_ASESOR)));
            aseguradora.setEmail(c.getString(c.getColumnIndex(AseguradoraDbAdapter.C_COLUMNA_EMAIL)));
            aseguradora.setTelefono(c.getString(c.getColumnIndex(AseguradoraDbAdapter.C_COLUMNA_TELEFONO)));

        }

        return aseguradora;
    }

    private ContentValues toContentValues()
    {
        ContentValues reg = new ContentValues();

        reg.put(AseguradoraDbAdapter.C_COLUMNA_ID, this.getId());
        reg.put(AseguradoraDbAdapter.C_COLUMNA_ASESOR, this.getAsesor());
        reg.put(AseguradoraDbAdapter.C_COLUMNA_EMAIL, this.getEmail());
        reg.put(AseguradoraDbAdapter.C_COLUMNA_TELEFONO, this.getTelefono());


        return reg;
    }

    public long save() {
        AseguradoraDbAdapter dbAdapter = new AseguradoraDbAdapter(this.getContext());

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
        AseguradoraDbAdapter dbAdapter = new AseguradoraDbAdapter(this.getContext());

        if (dbAdapter.exists(this.getId())) {
            dbAdapter.update(this.toContentValues());
        }
        return this.getId();
    }

    public long delete()
    {
        // borramos el registro
        AseguradoraDbAdapter dbAdapter = new AseguradoraDbAdapter(this.getContext());

        return dbAdapter.delete(this.getId());
    }


}

