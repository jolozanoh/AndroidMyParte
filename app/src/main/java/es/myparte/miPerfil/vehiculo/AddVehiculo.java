package es.myparte.miPerfil.vehiculo;

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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;

import es.myparte.R;
import es.myparte.XMLficheros.ControlListas;
import es.myparte.XMLficheros.VehiculoA;
import es.myparte.XMLficheros.VehiculoB;
import es.myparte.adaptadores.VehiculosAdapter;
import es.myparte.clases.Vehiculo;
import es.myparte.database.VehiculoDbAdapter;
import es.myparte.parte.MenuDatos;
import es.myparte.utilidades.Validaciones;

public class AddVehiculo extends AppCompatActivity implements View.OnClickListener {
    public static final String C_MODO = "modo";
    public static final int C_SELECCIONAR_A = 103;
    public static final int C_SELECCIONAR_B = 109;

    private int modo;

    private Vehiculo vehiculo = new Vehiculo(this);


    String matriculaA ;
    String marcaA ;
    String modeloA;
    String matriRemolqueA;
    String remolqueA ;


    private EditText matricula;
    private EditText marca;
    private EditText modelo;

    private Button btSelecionarVE;
    private LinearLayout llTrailer;
    private LinearLayout llTrailerMatricula;
    private Switch swTrailer;

    private EditText etTrailerMatricula;

    private boolean swChequeado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehiculo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        if (extra == null) return;

        matricula = (EditText) findViewById(R.id.etAseguradoNombre);
        matricula.requestFocus();
        marca = (EditText) findViewById(R.id.etAseguradoApellidos);
        modelo = (EditText) findViewById(R.id.modelo);
        btSelecionarVE = (Button) findViewById(R.id.btDatosVehiculo);
        llTrailer = (LinearLayout) findViewById(R.id.llRemolque);
        llTrailerMatricula = (LinearLayout)findViewById(R.id.llMatriculaRemolque);
        swTrailer = (Switch) findViewById(R.id.swRemolque);
        etTrailerMatricula = (EditText) findViewById(R.id.etMatriculaRemolque);

        btSelecionarVE.setOnClickListener(this);

        llTrailer.setVisibility(View.VISIBLE);
        llTrailerMatricula.setVisibility(View.INVISIBLE);
        if (swTrailer != null){
            swTrailer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        swChequeado = true;
                        llTrailerMatricula.setVisibility(View.VISIBLE);
                        llTrailerMatricula.requestFocus();
                    }else{
                        swChequeado = false;
                        etTrailerMatricula.setText("");
                        llTrailerMatricula.setVisibility(View.INVISIBLE);
                        matricula.requestFocus();

                    }
                }
            });
        }

        try {
            establecerModoVehiculosActivity(extra.getInt(VehiculosActivity.C_MODO));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        if (extra.containsKey(VehiculoDbAdapter.C_COLUMNA_ID)) {
            long id = extra.getLong(VehiculoDbAdapter.C_COLUMNA_ID);
            consultar(id);
        }

    }

    private void establecerModoVehiculosActivity(int m) throws ParserConfigurationException, SAXException, IOException {
        this.modo = m;

        if (modo == MenuDatos.C_VEHICULO_A) {
            leerVehiculoAXML();
            btSelecionarVE.setVisibility(View.VISIBLE);
        }else if (modo == MenuDatos.C_VEHICULO_B){
            leerVehiculoBXML();
            btSelecionarVE.setVisibility(View.INVISIBLE);
        }
        if (modo == VehiculosActivity.C_VISUALIZAR) {
            this.setTitle("Consultar Vehiculo");
            this.setEdicion(false);
            btSelecionarVE.setVisibility(View.INVISIBLE);
            llTrailer.setVisibility(View.INVISIBLE);
        } else if (modo == VehiculosActivity.C_CREAR) {
            this.setTitle(R.string.vehiculo_crear_titulo);
            this.setEdicion(true);
            btSelecionarVE.setVisibility(View.INVISIBLE);
            llTrailer.setVisibility(View.INVISIBLE);
        } else if (modo == VehiculosActivity.C_EDITAR) {
            this.setTitle(R.string.vehiculo_editar_titulo);
            this.setEdicion(true);
            btSelecionarVE.setVisibility(View.INVISIBLE);
            llTrailer.setVisibility(View.INVISIBLE);
        }else if (modo == VehiculosAdapter.NO_SELECTION){
            this.setTitle(R.string.vehiculo_editar_titulo);
            this.setEdicion(true);
            btSelecionarVE.setVisibility(View.VISIBLE);
            llTrailer.setVisibility(View.VISIBLE);
            llTrailerMatricula.setVisibility(View.INVISIBLE);
        }else if ((modo == MenuDatos.C_VEHICULO_A)||(modo == MenuDatos.C_VEHICULO_B)){
            this.setTitle(R.string.vehiculo);
            if (!matriculaA.equalsIgnoreCase("null")) {
                matricula.setText(matriculaA);
                marca.setText(marcaA);
                modelo.setText(modeloA);
                if (remolqueA.equalsIgnoreCase("true")) {
                    swTrailer.setChecked(true);
                    llTrailerMatricula.setVisibility(View.VISIBLE);
                    llTrailer.setVisibility(View.VISIBLE);

                    if (matriRemolqueA.equalsIgnoreCase("null")){
                        etTrailerMatricula.setText("");
                    }else{
                        etTrailerMatricula.setText(matriRemolqueA);
                    }
                }
            }
            this.setEdicion(true);
        }
    }

    private void consultar(long id) {
        vehiculo = Vehiculo.find(this, id);

        matricula.setText(vehiculo.getMatricula());
        marca.setText(vehiculo.getMarca());
        modelo.setText(vehiculo.getModelo());
    }

    private void setEdicion(boolean opcion) {
        matricula.setEnabled(opcion);
        marca.setEnabled(opcion);
        modelo.setEnabled(opcion);
    }

    private void guardar() {

        if (Validaciones.camposBlanco(matricula.getText().toString())){
            Toast.makeText(AddVehiculo.this, R.string.valida_matricula, Toast.LENGTH_SHORT).show();
            matricula.requestFocus();
        }else {
            vehiculo.setMatricula(matricula.getText().toString());
            if (Validaciones.camposBlanco(marca.getText().toString())){
                Toast.makeText(AddVehiculo.this, R.string.valida_marca, Toast.LENGTH_SHORT).show();
                marca.requestFocus();
            }else {
                vehiculo.setMarca(marca.getText().toString());
                if (Validaciones.camposBlanco(modelo.getText().toString())){
                    Toast.makeText(AddVehiculo.this, R.string.valida_marca, Toast.LENGTH_SHORT).show();
                    modelo.requestFocus();
                }else {
                    vehiculo.setModelo(modelo.getText().toString());
                    if (modo == VehiculosActivity.C_CREAR) {
                        vehiculo.save();
                        Toast.makeText(AddVehiculo.this, R.string.vehiculo_crear_confirmacion, Toast.LENGTH_SHORT).show();
                    } else if (modo == VehiculosActivity.C_EDITAR) {
                        vehiculo.update();
                        Toast.makeText(AddVehiculo.this, R.string.vehiculo_editar_confirmacion, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(this, VehiculosActivity.class);
                        startActivity(i);
                    }
                    setResult(RESULT_OK);
                    finish();
                }
            }
        }
    }

    private void cancelar() {
        setResult(RESULT_CANCELED, null);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if ((modo == VehiculosActivity.C_CREAR) || (modo == VehiculosActivity.C_SELECIONAR_A) || (modo == VehiculosActivity.C_SELECIONAR_B)) {
            getMenuInflater().inflate(R.menu.menu_atras_save, menu);
        } else if (modo == VehiculosActivity.C_EDITAR) {
            getMenuInflater().inflate(R.menu.menu_atras_update, menu);
        } else if ((modo == VehiculosActivity.C_VISUALIZAR)  ){
            getMenuInflater().inflate(R.menu.menu_atras, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent i;
        if (id == R.id.atras) {
            if ((modo == VehiculosActivity.C_SELECIONAR_A)||(modo == VehiculosActivity.C_SELECIONAR_B)){
                i = new Intent(AddVehiculo.this, MenuDatos.class);
                startActivity(i);
            }else {
                cancelar();
            }
            return true;
        }
        if (id == R.id.save) {
            boolean isOk = false;
            if ((modo == MenuDatos.C_VEHICULO_A) || (modo == MenuDatos.C_VEHICULO_B)){
                try {
                    if (Validaciones.camposBlanco(matricula.getText().toString())) {
                        Toast.makeText(AddVehiculo.this, R.string.valida_matricula, Toast.LENGTH_SHORT).show();
                        matricula.requestFocus();
                    }else{
                        matriculaA = matricula.getText().toString();
                        if (Validaciones.camposBlanco(marca.getText().toString())) {
                            Toast.makeText(AddVehiculo.this, R.string.valida_marca, Toast.LENGTH_SHORT).show();
                            marca.requestFocus();
                        }else{
                            marcaA = marca.getText().toString();
                            if (Validaciones.camposBlanco(modelo.getText().toString())){
                                Toast.makeText(AddVehiculo.this, R.string.valida_modelo, Toast.LENGTH_SHORT).show();
                                modelo.requestFocus();
                            }else {
                                modeloA = modelo.getText().toString();
                                isOk = true;
                                if (etTrailerMatricula.getText().toString().equalsIgnoreCase("")) {
                                    matriRemolqueA = "null";
                                } else {
                                    matriRemolqueA = etTrailerMatricula.getText().toString();
                                }
                            }
                        }
                    }
                    if (isOk) {
                        remolqueA = String.valueOf(swChequeado);
                        ControlListas cl = new ControlListas(this);
                        if (modo == MenuDatos.C_VEHICULO_A) {
                            if (cl.listaActiva().equalsIgnoreCase("vehiculoA")) {
                                cl.activarAseguradorasAXML();
                            }
                            VehiculoA datosXML = new VehiculoA(this);
                            datosXML.vehiculoAXML(matriculaA, marcaA, modeloA, remolqueA, matriRemolqueA);
                        }
                        if (modo == MenuDatos.C_VEHICULO_B) {
                            String activo = cl.listaActiva();
                            if (activo.equalsIgnoreCase("vehiculoB")) {
                                cl.activarAseguradoraBXML();
                            }
                            VehiculoB datosXML = new VehiculoB(this);
                            datosXML.vehiculoBXML(matriculaA, marcaA, modeloA, remolqueA, matriRemolqueA);
                        }

                        i = new Intent(AddVehiculo.this, MenuDatos.class);
                        startActivity(i);
                    }
                } catch (IOException | TransformerConfigurationException | SAXException | ParserConfigurationException e) {
                    e.printStackTrace();
                }
            }else{
                guardar();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        Intent i;
        if (view == btSelecionarVE){
            i = new Intent(AddVehiculo.this, VehiculosActivity.class);
            if (modo == MenuDatos.C_VEHICULO_A){
                i.putExtra(C_MODO, C_SELECCIONAR_A);
                startActivityForResult(i, C_SELECCIONAR_A);
            }else if (modo == MenuDatos.C_VEHICULO_B) {
                i.putExtra(C_MODO, C_SELECCIONAR_B);
                startActivityForResult(i, C_SELECCIONAR_B);
            }
        }
    }

    public void leerVehiculoAXML() throws IOException, ParserConfigurationException, SAXException {
        //Obtenemos la referencia al fichero XML de entrada
        FileInputStream fil = openFileInput("vehiculoA.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document dom = builder.parse(fil);

        Element raiz = dom.getDocumentElement();

        NodeList items = raiz.getElementsByTagName("vehiculoA");

        for (int i = 0; i < items.getLength(); i++) {
            Node nodoVehiculo = items.item(i);
            for (int j = 0; j < nodoVehiculo.getChildNodes().getLength(); j++) { //cabezera, vehiculaA, VehiculoB
                Node nodoDetalles = nodoVehiculo.getChildNodes().item(j);
                switch (nodoDetalles.getNodeName()) {
                    case "matricula":
                        matriculaA = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "marca":
                        marcaA = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "modelo":
                        modeloA = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "remolque":
                        remolqueA = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "matriculaRemolque":
                        matriRemolqueA = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;

                }
            }
        }
    }

    public void leerVehiculoBXML() throws IOException, ParserConfigurationException, SAXException {
        //Obtenemos la referencia al fichero XML de entrada
        FileInputStream fil = openFileInput("vehiculoB.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document dom = builder.parse(fil);

        Element raiz = dom.getDocumentElement();

        NodeList items = raiz.getElementsByTagName("vehiculoB");

        for (int i = 0; i < items.getLength(); i++) {
            Node nodoVehiculo = items.item(i);
            for (int j = 0; j < nodoVehiculo.getChildNodes().getLength(); j++) { //cabezera, vehiculaA, VehiculoB
                Node nodoDetalles = nodoVehiculo.getChildNodes().item(j);
                switch (nodoDetalles.getNodeName()) {
                    case "matricula":
                        matriculaA = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "marca":
                        marcaA = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "modelo":
                        modeloA = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "remolque":
                        remolqueA = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "matriculaRemolque":
                        matriRemolqueA = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;

                }
            }
        }
    }

}

