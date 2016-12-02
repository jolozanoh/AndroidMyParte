package es.myparte.clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import es.myparte.database.UsuarioDbAdapter;


/**
 * Created by oscarlozanohernaiz.
 */
public class Usuario {
    private Context context;

    private long id;
    private long idAnterior;
    private String usuario;
    private String email;
    private String telefono;

    public Usuario(Context context)
    {
        this.context = context;
    }

    public Usuario(long id , String usuario, String email, String telefono){
        this.id = id;
        this.usuario = usuario;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public long getIdAnterior() {
        return idAnterior;
    }

    public void setIdAnterior(long idAnterior) {
        this.idAnterior = idAnterior;
    }


    public static Usuario find(Context context, long id)
    {
        Usuario usuario = null;
        UsuarioDbAdapter dbAdapter = new UsuarioDbAdapter(context);

        Cursor c = dbAdapter.getRegistro(id);

        if (c.getCount() != 0 ){


            usuario = Usuario.cursorToUsuario(context, c);
        }


        c.close();

        return usuario;
    }

    public static Usuario cursorToUsuario(Context context, Cursor c)
    {
        Usuario usuario = null;

        if (c != null)
        {
            usuario = new Usuario(context);

            usuario.setId(c.getLong(c.getColumnIndex(UsuarioDbAdapter.C_COLUMNA_ID)));
            usuario.setUsuario(c.getString(c.getColumnIndex(UsuarioDbAdapter.C_COLUMNA_USUARIO)));
            usuario.setEmail(c.getString(c.getColumnIndex(UsuarioDbAdapter.C_COLUMNA_EMAIL)));
            usuario.setTelefono(c.getString(c.getColumnIndex(UsuarioDbAdapter.C_COLUMNA_TELEFONO)));

        }

        return usuario;
    }

    private ContentValues toContentValues()
    {
        ContentValues reg = new ContentValues();

        reg.put(UsuarioDbAdapter.C_COLUMNA_ID, this.getId());
        reg.put(UsuarioDbAdapter.C_COLUMNA_USUARIO, this.getUsuario());
        reg.put(UsuarioDbAdapter.C_COLUMNA_EMAIL, this.getEmail());
        reg.put(UsuarioDbAdapter.C_COLUMNA_TELEFONO, this.getTelefono());


        return reg;
    }

    public long save() {
        UsuarioDbAdapter dbAdapter = new UsuarioDbAdapter(this.getContext());

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
        this.setIdAnterior(this.getId());
        return this.getId();
    }

    public long update() {
        UsuarioDbAdapter dbAdapter = new UsuarioDbAdapter(this.getContext());

        if (dbAdapter.exists(this.getId())) {
            dbAdapter.update(this.toContentValues());
        }
        return this.getId();
    }

    public long delete()
    {
        // borramos el registro
        UsuarioDbAdapter dbAdapter = new UsuarioDbAdapter(this.getContext());

        return dbAdapter.delete(this.getId());
    }

}
