package es.myparte.miPerfil.aseguradora;

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
import android.widget.EditText;
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
import es.myparte.XMLficheros.SeguroA;
import es.myparte.XMLficheros.SeguroB;
import es.myparte.adaptadores.AseguradorasAdapter;
import es.myparte.clases.Aseguradora;
import es.myparte.database.AseguradoraDbAdapter;
import es.myparte.parte.MenuDatos;
import es.myparte.utilidades.Validaciones;

public class AddAseguradora extends AppCompatActivity implements View.OnClickListener {
    public static final String C_MODO = "modo";
    public static final int C_SELECCIONAR_A = 104;
    public static final int C_SELECCIONAR_B = 110;

    private int modo;

    private Aseguradora aseguradora = new Aseguradora(this);


    private EditText asesor;
    private EditText email;
    private EditText telefono;

    private Button btAsegurdora;

    String asesorA;
    String emailA;
    String telefonoA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aseguradora);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        if (extra == null) return;

        asesor = (EditText) findViewById(R.id.asesor);
        email = (EditText) findViewById(R.id.email);
        telefono = (EditText) findViewById(R.id.telefono);
        btAsegurdora = (Button) findViewById(R.id.btDatosAseguradora);


        btAsegurdora.setOnClickListener(this);




        try {
            establecerModo(extra.getInt(AseguradorasActivity.C_MODO));
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        if (extra.containsKey(AseguradoraDbAdapter.C_COLUMNA_ID)) {
            long id = extra.getLong(AseguradoraDbAdapter.C_COLUMNA_ID);
            consultar(id);
        }


    }

    private void establecerModo(int m) throws ParserConfigurationException, SAXException, IOException {


        this.modo = m;

        if (modo == MenuDatos.C_ASEGURADORA_A) {
            leerAseguradoraAXML();
            btAsegurdora.setVisibility(View.VISIBLE);
        }else if (modo == MenuDatos.C_ASEGURADORA_B){
            leerAseguradoraBXML();
            btAsegurdora.setVisibility(View.INVISIBLE);
        }

        if (modo == AseguradorasActivity.C_VISUALIZAR) {
            this.setTitle(asesor.getText().toString());
            this.setEdicion(false);
            btAsegurdora.setVisibility(View.INVISIBLE);
        } else if (modo == AseguradorasActivity.C_CREAR) {
            this.setTitle(R.string.aseguradora_crear_titulo);
            this.setEdicion(true);
            btAsegurdora.setVisibility(View.INVISIBLE);
        } else if (modo == AseguradorasActivity.C_EDITAR) {
            this.setTitle(R.string.aseguradora_editar_titulo);
            this.setEdicion(true);
            btAsegurdora.setVisibility(View.INVISIBLE);
        }else if ((modo == MenuDatos.C_ASEGURADORA_A)||(modo == MenuDatos.C_ASEGURADORA_B)){
            this.setTitle(R.string.aseguradora);
            if (!asesorA.equalsIgnoreCase("null")) {
                asesor.setText(asesorA);
                email.setText(emailA);
                telefono.setText(telefonoA);
            }
            this.setEdicion(true);

        }else if (modo == AseguradorasAdapter.NO_SELECTION){
            this.setTitle(R.string.aseguradora_editar_titulo);
            this.setEdicion(true);
            btAsegurdora.setVisibility(View.VISIBLE);
        }
    }

    private void consultar(long id) {
        aseguradora = Aseguradora.find(this, id);

        asesor.setText(aseguradora.getAsesor());
        email.setText(aseguradora.getEmail());
        telefono.setText(aseguradora.getTelefono());
    }

    private void setEdicion(boolean opcion) {
        asesor.setEnabled(opcion);
        email.setEnabled(opcion);
        telefono.setEnabled(opcion);
    }

    private void guardar() {

        if (Validaciones.camposBlanco(asesor.getText().toString())){
            Toast.makeText(AddAseguradora.this, R.string.valida_nombre, Toast.LENGTH_SHORT).show();
            asesor.requestFocus();
        }else {
            aseguradora.setAsesor(asesor.getText().toString());
            if (Validaciones.camposBlanco(email.getText().toString())){
                Toast.makeText(AddAseguradora.this, R.string.valida_email, Toast.LENGTH_SHORT).show();
                email.requestFocus();
            }else {
                boolean isValidoEmail = Validaciones.validateEmail(email.getText().toString());
                if (!isValidoEmail){
                    Toast.makeText(AddAseguradora.this, R.string.valida_email_correcto, Toast.LENGTH_SHORT).show();
                    email.requestFocus();
                }else {
                    aseguradora.setEmail(email.getText().toString());
                    if (Validaciones.camposBlanco(telefono.getText().toString())) {
                        Toast.makeText(AddAseguradora.this, R.string.valida_telefono, Toast.LENGTH_SHORT).show();
                        telefono.requestFocus();
                    } else {
                        aseguradora.setTelefono(telefono.getText().toString());
                        if (modo == AseguradorasActivity.C_CREAR) {
                            aseguradora.save();
                            Toast.makeText(AddAseguradora.this, R.string.vehiculo_crear_confirmacion, Toast.LENGTH_SHORT).show();
                        } else if (modo == AseguradorasActivity.C_EDITAR) {
                            aseguradora.update();
                            Toast.makeText(AddAseguradora.this, R.string.vehiculo_editar_confirmacion, Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(this, AseguradorasActivity.class);
                            startActivity(i);
                        }

                        setResult(RESULT_OK);
                        finish();
                    }
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
        if ((modo == AseguradorasActivity.C_CREAR) || (modo == AseguradorasActivity.C_SELECIONAR_A) || (modo == AseguradorasActivity.C_SELECIONAR_B)) {
            getMenuInflater().inflate(R.menu.menu_atras_save, menu);
        } else if (modo == AseguradorasActivity.C_EDITAR) {
            getMenuInflater().inflate(R.menu.menu_atras_update, menu);
        } else if ((modo == AseguradorasActivity.C_VISUALIZAR)  ){
            getMenuInflater().inflate(R.menu.menu_atras, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent i;
        if (id == R.id.atras) {
            if ((modo == AseguradorasActivity.C_SELECIONAR_A)||(modo == AseguradorasActivity.C_SELECIONAR_B)){
                i = new Intent(AddAseguradora.this, MenuDatos.class);
                startActivity(i);
            }else {
                cancelar();
            }
            return true;
        }
        if (id == R.id.save) {
            boolean isOk = false;
            if ((modo == MenuDatos.C_ASEGURADORA_A) || (modo == MenuDatos.C_ASEGURADORA_B)){
                try {
                    if(Validaciones.camposBlanco(asesor.getText().toString())){
                        Toast.makeText(AddAseguradora.this, R.string.valida_nombre, Toast.LENGTH_SHORT).show();
                        asesor.requestFocus();
                    }else{
                        asesorA = asesor.getText().toString();
                        if (Validaciones.camposBlanco(email.getText().toString())){
                            Toast.makeText(AddAseguradora.this, R.string.valida_email, Toast.LENGTH_SHORT).show();
                            email.requestFocus();
                        }else{
                            boolean isValEmail = Validaciones.validateEmail(email.getText().toString());
                            if(!isValEmail){
                                Toast.makeText(AddAseguradora.this, R.string.valida_email_correcto, Toast.LENGTH_SHORT).show();
                                email.requestFocus();
                            }else{
                                emailA = email.getText().toString();
                                if (Validaciones.camposBlanco(telefono.getText().toString())){
                                    Toast.makeText(AddAseguradora.this, R.string.valida_telefono, Toast.LENGTH_SHORT).show();
                                    telefono.requestFocus();
                                }else{
                                    telefonoA = telefono.getText().toString();
                                    isOk = true;
                                }
                            }
                        }
                    }

                    if (isOk) {
                        ControlListas cl = new ControlListas(this);
                        if (modo == MenuDatos.C_ASEGURADORA_A) {
                            if (cl.listaActiva().equalsIgnoreCase("aseguradoraA")) {
                                cl.activarConductorAXML();
                            }
                            SeguroA datosXML = new SeguroA(this);
                            datosXML.aseguradoraAXML(asesorA, emailA, telefonoA);
                        }
                        if (modo == MenuDatos.C_ASEGURADORA_B) {
                            if (cl.listaActiva().equalsIgnoreCase("aseguradoraB")) {
                                cl.activarConductorBXML();
                            }
                            SeguroB datosXML = new SeguroB(this);
                            datosXML.aseguradoraBXML(asesorA, emailA, telefonoA);
                        }

                        i = new Intent(AddAseguradora.this, MenuDatos.class);
                        startActivity(i);
                    }
                } catch (IOException | ParserConfigurationException | SAXException | TransformerConfigurationException e) {
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
        if (view == btAsegurdora){
            i = new Intent(AddAseguradora.this, AseguradorasActivity.class);
            if (modo == MenuDatos.C_ASEGURADORA_A){
                i.putExtra(C_MODO, C_SELECCIONAR_A);
                startActivityForResult(i, C_SELECCIONAR_A);
            }else if (modo == MenuDatos.C_ASEGURADORA_B) {
                i.putExtra(C_MODO, C_SELECCIONAR_B);
                startActivityForResult(i, C_SELECCIONAR_B);
            }
        }
    }

    public void leerAseguradoraAXML() throws IOException, ParserConfigurationException, SAXException {
        //Obtenemos la referencia al fichero XML de entrada
        FileInputStream fil = openFileInput("aseguradoraA.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document dom = builder.parse(fil);

        Element raiz = dom.getDocumentElement();

        NodeList items = raiz.getElementsByTagName("aseguradoraA");

        for (int i = 0; i < items.getLength(); i++) {
            Node nodoAseguradora = items.item(i);
            for (int j = 0; j < nodoAseguradora.getChildNodes().getLength(); j++) {
                Node nodoDetalles = nodoAseguradora.getChildNodes().item(j);
                switch (nodoDetalles.getNodeName()) {
                    case "nombre":
                        asesorA = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "email":
                        emailA = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "telefono":
                        telefonoA = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;

                }
            }
        }
    }

    public void leerAseguradoraBXML() throws IOException, ParserConfigurationException, SAXException {
        FileInputStream fil = openFileInput("aseguradoraB.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document dom = builder.parse(fil);

        Element raiz = dom.getDocumentElement();

        NodeList items = raiz.getElementsByTagName("aseguradoraB");

        for (int i = 0; i < items.getLength(); i++) {
            Node nodoAseguradora = items.item(i);
            for (int j = 0; j < nodoAseguradora.getChildNodes().getLength(); j++) {
                Node nodoDetalles = nodoAseguradora.getChildNodes().item(j);
                switch (nodoDetalles.getNodeName()) {
                    case "nombre":
                        asesorA = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "email":
                        emailA = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "telefono":
                        telefonoA = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;

                }
            }
        }
    }

}
