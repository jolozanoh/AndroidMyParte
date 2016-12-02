package es.myparte.pdf;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import es.myparte.R;
import es.myparte.adaptadores.PartesAdapter;
import es.myparte.database.ParteDbAdapter;

public class ListPartesPdf extends ListActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    public static final String C_MODO = "modo";
    public static final int C_GENERAR_PDF = 551;

    private ParteDbAdapter dbAdapter;
    private ListView lista;
    private Button btCancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_partes_pdf);

        btCancelar = (Button) findViewById(R.id.btCancelar);
        btCancelar.setOnClickListener(this);

        lista = (ListView) findViewById(android.R.id.list);

        dbAdapter = new ParteDbAdapter(this);
        dbAdapter.abrir();

        consultar();
        lista.setOnItemClickListener(this);
    }

    private void consultar() {
        PartesAdapter partesAdapter = new PartesAdapter(this, dbAdapter.getPartes(null));
        lista.setAdapter(partesAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_atras_add, menu);
        return true;
    }


    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent i;
        if (id == R.id.atras) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(ListPartesPdf.this, GenerarPDF.class);
        intent.putExtra(C_MODO, C_GENERAR_PDF);
        intent.putExtra(ParteDbAdapter.C_COLUMNA_ID, l);
        startActivityForResult(intent, C_GENERAR_PDF);
    }

    @Override
    public void onClick(View view) {
        if(view == btCancelar){
            setResult(RESULT_CANCELED, null);
            finish();
        }
    }
}
