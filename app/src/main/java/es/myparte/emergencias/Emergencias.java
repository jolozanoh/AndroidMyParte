package es.myparte.emergencias;

import android.Manifest;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import es.myparte.R;
import es.myparte.adaptadores.EmergenciasAdapter;
import es.myparte.clases.Aseguradora;
import es.myparte.database.AseguradoraDbAdapter;

public class Emergencias extends ListActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    public static final String C_MODO = "modo";
    public static final int C_VISUALIZAR = 551;
    public static final int C_CREAR = 552;
    public static final int C_EDITAR = 553;
    public static final int C_ELIMINAR = 554;

    private AseguradoraDbAdapter dbAdapter;
    private EmergenciasAdapter aseguradorasAdapter;
    private ListView lista;

    public String filtro;

    private Button btCancelar;
    private ImageButton bt112;

    private static final int USO_CAMARA = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencias);

        btCancelar = (Button) findViewById(R.id.btCancelar);
        btCancelar.setOnClickListener(this);
        bt112 = (ImageButton) findViewById(R.id.btEmergencias);

        bt112.setOnClickListener(this);

        lista = (ListView) findViewById(android.R.id.list);

        dbAdapter = new AseguradoraDbAdapter(this);
        dbAdapter.abrir();

        consultar();

        lista.setOnItemClickListener(this);


    }

    private void consultar() {
        aseguradorasAdapter = new EmergenciasAdapter(this, dbAdapter.getAseguradoras(filtro));
        lista.setAdapter(aseguradorasAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_atras, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent i;
        if (v == btCancelar) {
            setResult(RESULT_CANCELED, null);
            finish();
        }
        if (v == bt112) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:112"));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(intent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Aseguradora aseguradora = Aseguradora.find(this, i);
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + aseguradora.getTelefono()));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }
}

