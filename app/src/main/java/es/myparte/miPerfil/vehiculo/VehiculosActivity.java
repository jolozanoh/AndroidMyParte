package es.myparte.miPerfil.vehiculo;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import es.myparte.R;
import es.myparte.adaptadores.VehiculosAdapter;
import es.myparte.database.VehiculoDbAdapter;

public class VehiculosActivity extends ListActivity implements View.OnClickListener{
    public static final String C_MODO = "modo";
    public static final int C_VISUALIZAR = 551;
    public static final int C_CREAR = 552;
    public static final int C_EDITAR = 553;
    public static final int C_ELIMINAR = 554;
    public static final int C_SELECIONAR_A = 103;
    public static final int C_SELECIONAR_B = 109;

    private int modo;
    private VehiculoDbAdapter dbAdapter;
    private VehiculosAdapter vehiculosAdapter;
    private ListView lista;

    private String filtro;

    private Button btCancelar;
    private Button btNuevo;

    private boolean isSeleccionar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculos);

        btCancelar = (Button) findViewById(R.id.btCancelar);
        btNuevo = (Button) findViewById(R.id.btNuevo);
        btNuevo.setOnClickListener(this);
        btCancelar.setOnClickListener(this);

        lista = (ListView) findViewById(android.R.id.list);

        dbAdapter = new VehiculoDbAdapter(this);
        dbAdapter.abrir();

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        if ( extra != null ) {
            establecerModo(extra.getInt(AddVehiculo.C_MODO));
            btNuevo.setVisibility(View.INVISIBLE);
            lista.setClickable(true);
        }
        consultar();
        registerForContextMenu(getListView());
    }

    private void establecerModo(int m) {
        this.modo = m;
        if ((modo == AddVehiculo.C_SELECCIONAR_A)||(modo == AddVehiculo.C_SELECCIONAR_B)) {
            isSeleccionar = true;
        }
    }

    /////////////////////////////////////
    private void consultar() {
        vehiculosAdapter = new VehiculosAdapter(this, dbAdapter.getVehiculos(filtro));
        lista.setAdapter(vehiculosAdapter);
    }

    /////////////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_atras_add, menu);
        if ((modo == AddVehiculo.C_SELECCIONAR_A)||(modo == AddVehiculo.C_SELECCIONAR_B)){
            getMenuInflater().inflate(R.menu.menu_atras, menu);
        }
        return true;
    }

    ////////////////////////////////////////////
    private void visualizar(long id) {
        Intent i = new Intent(VehiculosActivity.this, AddVehiculo.class);
        i.putExtra(C_MODO, C_VISUALIZAR);
        i.putExtra(VehiculoDbAdapter.C_COLUMNA_ID, id);
        startActivityForResult(i, C_VISUALIZAR);
    }
////////////////////////////////////////////////
    //@Override
    /*protected void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);

        visualizar(id);
    }*/

    ///////////////////////////////////////////
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent i;

        if (id == R.id.atras) {
            return true;
        }
        if (id == R.id.add) {
            i = new Intent(VehiculosActivity.this, AddVehiculo.class);
            i.putExtra(C_MODO, C_CREAR);
            startActivityForResult(i, C_CREAR);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //////////////////////////////////////////////
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //
        // Nos aseguramos que es la petición que hemos realizado
        //
        switch (requestCode) {
            case C_CREAR:
                if (resultCode == RESULT_OK)
                    consultar();

            case C_VISUALIZAR:
                if (resultCode == RESULT_OK)
                    consultar();

            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    //////////////////////////////////////
    private void borrar(final long id) {
        //
        // Borramos el registro y refrescamos la lista
        //
        AlertDialog.Builder dialogEliminar = new AlertDialog.Builder(this);


        dialogEliminar.setIcon(android.R.drawable.ic_dialog_alert);
        dialogEliminar.setTitle(getResources().getString(R.string.vehiculo_eliminar_titulo));
        dialogEliminar.setMessage(getResources().getString(R.string.vehiculo_eliminar_mensaje));
        dialogEliminar.setCancelable(false);


        dialogEliminar.setPositiveButton(getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int boton) {
                dbAdapter.delete(id);
                Toast.makeText(VehiculosActivity.this, R.string.vehiculo_eliminar_confirmacion, Toast.LENGTH_SHORT).show();
                consultar();
            }
        });

        dialogEliminar.setNegativeButton(android.R.string.no, null);

        dialogEliminar.show();
    }

    /////////////////////////////////////////////////
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (!isSeleccionar) {
            menu.setHeaderTitle(vehiculosAdapter.getItem(((AdapterView.AdapterContextMenuInfo) menuInfo).position).getMatricula());
            menu.add(Menu.NONE, C_VISUALIZAR, Menu.NONE, R.string.menu_visualizar);
            menu.add(Menu.NONE, C_EDITAR, Menu.NONE, R.string.menu_editar);
            menu.add(Menu.NONE, C_ELIMINAR, Menu.NONE, R.string.menu_eliminar);
        }else{
            if (modo == AddVehiculo.C_SELECCIONAR_A) {
                menu.add(Menu.NONE, C_SELECIONAR_A, Menu.NONE, R.string.menu_seleccionar);
            }else if ( modo == AddVehiculo.C_SELECCIONAR_B){
                menu.add(Menu.NONE, C_SELECIONAR_B, Menu.NONE, R.string.menu_seleccionar);
            }

        }
    }

    //////////////////////////////////////////////////////////
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Intent i;
        if (!isSeleccionar) {
            switch (item.getItemId()) {
                case C_ELIMINAR:
                    borrar(info.id);
                    return true;

                case C_VISUALIZAR:
                    visualizar(info.id);
                    return true;

                case C_EDITAR:
                    i = new Intent(VehiculosActivity.this, AddVehiculo.class);
                    i.putExtra(C_MODO, C_EDITAR);
                    i.putExtra(VehiculoDbAdapter.C_COLUMNA_ID, info.id);

                    startActivityForResult(i, C_EDITAR);
                    return true;
            }
        }else{
            if (item.getItemId() == C_SELECIONAR_A){
                i = new Intent(VehiculosActivity.this, AddVehiculo.class);
                i.putExtra(C_MODO, C_SELECIONAR_A);
                i.putExtra(VehiculoDbAdapter.C_COLUMNA_ID, info.id);
                startActivityForResult(i, C_SELECIONAR_A);
                return true;
            }else if (item.getItemId() == C_SELECIONAR_B) {
                i = new Intent(VehiculosActivity.this, AddVehiculo.class);
                i.putExtra(C_MODO, C_SELECIONAR_B);
                i.putExtra(VehiculoDbAdapter.C_COLUMNA_ID, info.id);
                startActivityForResult(i, C_SELECIONAR_B);
                return true;
            }
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        if (v == btNuevo) {
            i = new Intent(VehiculosActivity.this, AddVehiculo.class);
            i.putExtra(C_MODO, C_CREAR);
            startActivityForResult(i, C_CREAR);

        }
        if (v == btCancelar) {
            setResult(RESULT_CANCELED, null);
            finish();
        }
    }



}

