package es.myparte.miPerfil;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import es.myparte.Main;
import es.myparte.R;
import es.myparte.clases.Usuario;
import es.myparte.database.UsuarioDbAdapter;
import es.myparte.miPerfil.aseguradora.AseguradorasActivity;
import es.myparte.miPerfil.usuario.AddUsuario;
import es.myparte.miPerfil.vehiculo.VehiculosActivity;

public class MiPerfil extends AppCompatActivity implements View.OnClickListener {

    private Button btUsuario;
    private Button btVehiculo;
    private Button btAseguradoras;


    public static final String C_MODO  = "modo" ;
    public static final int C_VISUALIZAR = 551 ;
    public static final int C_CREAR = 552 ;
    public static final int C_EDITAR = 553 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btUsuario = (Button) findViewById(R.id.btUsurario);
        btUsuario.setOnClickListener(this);
        btVehiculo = (Button) findViewById(R.id.btVehiculo);
        btVehiculo.setOnClickListener(this);
        btAseguradoras = (Button) findViewById(R.id.btAseguradoras);
        btAseguradoras.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_atras, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent i;
        if (id == R.id.atras) {
            i = new Intent(MiPerfil.this, Main.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        if (v == btUsuario) {
            //MIRAR BASE DE DATOS.
            Usuario user = Usuario.find(this, 0);

            if (user==null){
                i = new Intent(MiPerfil.this, AddUsuario.class);
                i.putExtra(C_MODO, C_CREAR);
                startActivityForResult(i, C_CREAR);
            }else{
                i = new Intent(MiPerfil.this, AddUsuario.class);
                i.putExtra(C_MODO, C_EDITAR);
                i.putExtra(UsuarioDbAdapter.C_COLUMNA_ID, user.getId());
                startActivityForResult(i, C_EDITAR);
            }
        }
        if (v == btVehiculo){
            i = new Intent(this,VehiculosActivity.class);
            startActivity(i);
        }
        if (v == btAseguradoras){
            i = new Intent(this,AseguradorasActivity.class);
            startActivity(i);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch(requestCode)
        {
            case C_CREAR:
                if (resultCode == RESULT_OK)
                    Toast.makeText(MiPerfil.this, "Usuario dado de alta correctamente.", Toast.LENGTH_SHORT).show();

            case C_VISUALIZAR:
                if (resultCode == RESULT_OK)
                    return;
            case C_EDITAR:
                if (resultCode == RESULT_OK)
                    Toast.makeText(MiPerfil.this, "Usuario modificado correctamente.", Toast.LENGTH_SHORT).show();


            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
