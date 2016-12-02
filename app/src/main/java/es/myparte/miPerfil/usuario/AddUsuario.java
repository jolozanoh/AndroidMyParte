package es.myparte.miPerfil.usuario;

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
import es.myparte.XMLficheros.AseguradoA;
import es.myparte.XMLficheros.AseguradoB;
import es.myparte.XMLficheros.ConductorA;
import es.myparte.XMLficheros.ConductorB;
import es.myparte.XMLficheros.ControlListas;
import es.myparte.clases.Usuario;
import es.myparte.database.UsuarioDbAdapter;
import es.myparte.miPerfil.MiPerfil;
import es.myparte.parte.MenuDatos;
import es.myparte.utilidades.Validaciones;

public class AddUsuario extends AppCompatActivity implements View.OnClickListener {

    private int modo;

    private Usuario usuario = new Usuario(this);

    private long id;
    private EditText usuari;
    private EditText email;
    private EditText telefono;
    private Button btUsuario;
    private boolean isMenuDatos = false;
    private String nombre;
    private String correo;
    private String tlf;

    private String nombreC;
    private String correoC;
    private String tlfC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            Intent intent = getIntent();
            Bundle extra = intent.getExtras();

            if (extra == null) return;

            usuari = (EditText) findViewById(R.id.usuario);
            email = (EditText) findViewById(R.id.email);
            telefono = (EditText) findViewById(R.id.telefono);
            btUsuario = (Button) findViewById(R.id.btDatosUsuario);



            if (extra.containsKey(UsuarioDbAdapter.C_COLUMNA_ID)) {
                id = extra.getLong(UsuarioDbAdapter.C_COLUMNA_ID);
                consultar(id);
            }

            establecerModoMiPerfil(extra.getInt(MiPerfil.C_MODO));


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        btUsuario.setOnClickListener(this);
    }

    private void establecerModoMiPerfil(int m) throws ParserConfigurationException, SAXException, IOException {
        this.modo = m;

        if (modo == MiPerfil.C_VISUALIZAR) {
            this.setTitle(usuari.getText().toString());
            this.setEdicion(false);
            btUsuario.setVisibility(View.INVISIBLE);
        } else if (modo == MiPerfil.C_CREAR) {
            this.setTitle(R.string.usuario_crear_titulo);
            this.setEdicion(true);
            btUsuario.setVisibility(View.INVISIBLE);
        } else if (modo == MiPerfil.C_EDITAR) {
            this.setTitle(R.string.usuario_editar_titulo);
            this.setEdicion(true);
            btUsuario.setVisibility(View.INVISIBLE);
        }else if ((modo == MenuDatos.C_ASEGURADOA) || (modo == MenuDatos.C_ASEGURADO_B)){
            this.setTitle(R.string.asegurado);
            this.setEdicion(true);
            if (modo == MenuDatos.C_ASEGURADOA){

                leerAseguradoAXML();
                btUsuario.setVisibility(View.VISIBLE);
            }
            if (modo == MenuDatos.C_ASEGURADO_B){
                leerAseguradoBXML();
                btUsuario.setVisibility(View.INVISIBLE);
            }
            if (!nombre.equalsIgnoreCase("null")){
                usuari.setText(nombre);
                email.setText(correo);
                telefono.setText(tlf);
            }

            isMenuDatos = true;
        }else if ((modo == MenuDatos.C_CONDUCTOR_A)||(modo == MenuDatos.C_CONDUCTOR_B)){
            this.setTitle(R.string.conductor);
            if (modo == MenuDatos.C_CONDUCTOR_A){
                leerConductorAXML();
                btUsuario.setVisibility(View.VISIBLE);
            }
            if (modo == MenuDatos.C_CONDUCTOR_B){
                leerConductorBXML();
                btUsuario.setVisibility(View.INVISIBLE);
            }
            if (!nombreC.equalsIgnoreCase("null")){
                usuari.setText(nombreC);
                email.setText(correoC);
                telefono.setText(tlfC);
            }
            this.setEdicion(true);

            isMenuDatos = true;
        }
    }



    private void consultar(long id) {
        usuario = Usuario.find(this, id);
        if (usuario != null) {
            usuari.setText(usuario.getUsuario());
            email.setText(usuario.getEmail());
            telefono.setText(usuario.getTelefono());
        }else{
            usuari.setText("");
            email.setText("");
            telefono.setText("");
            Toast.makeText(AddUsuario.this, "No hay usuario que mostrar.", Toast.LENGTH_SHORT).show();
        }
    }


    private void setEdicion(boolean opcion) {
        usuari.setEnabled(opcion);
        email.setEnabled(opcion);
        telefono.setEnabled(opcion);
    }

    private void guardar() {

        if (Validaciones.camposBlanco(usuari.getText().toString())) {
            Toast.makeText(AddUsuario.this, R.string.valida_nombre, Toast.LENGTH_SHORT).show();
            usuari.requestFocus();
        }else{
            usuario.setUsuario(usuari.getText().toString());
            if (Validaciones.camposBlanco(email.getText().toString())){
                Toast.makeText(AddUsuario.this, R.string.valida_email, Toast.LENGTH_SHORT).show();
                email.requestFocus();
            }else {
                boolean validoEmail = Validaciones.validateEmail(email.getText().toString());
                if (!validoEmail){
                    Toast.makeText(AddUsuario.this, R.string.valida_email_correcto, Toast.LENGTH_SHORT).show();
                    email.requestFocus();
                }else {
                    usuario.setEmail(email.getText().toString());
                    if (Validaciones.camposBlanco(telefono.getText().toString())) {
                        Toast.makeText(AddUsuario.this, R.string.valida_telefono, Toast.LENGTH_SHORT).show();
                        telefono.requestFocus();
                    } else {
                        usuario.setTelefono(telefono.getText().toString());

                        if (modo == MiPerfil.C_CREAR) {
                            usuario.save();
                            Toast.makeText(AddUsuario.this, R.string.usuario_crear_confirmacion, Toast.LENGTH_SHORT).show();
                        } else if (modo == MiPerfil.C_EDITAR) {
                            usuario.update();
                            Toast.makeText(AddUsuario.this, R.string.aseguradora_editar_confirmacion, Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(this, MiPerfil.class);
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
        if ((modo == MiPerfil.C_CREAR) || (modo == MenuDatos.C_ASEGURADOA) || (modo == MenuDatos.C_CONDUCTOR_A)
                ||(modo == MenuDatos.C_ASEGURADO_B) || (modo == MenuDatos.C_CONDUCTOR_B)){
            getMenuInflater().inflate(R.menu.menu_atras_save, menu);
        } else if (modo == MiPerfil.C_EDITAR) {
            getMenuInflater().inflate(R.menu.menu_atras_update, menu);
        } else if (modo == MiPerfil.C_VISUALIZAR) {
            getMenuInflater().inflate(R.menu.menu_atras, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean isOk = false;
        if (id == R.id.atras) {
            cancelar();
            return true;
        }
        if (id == R.id.save) {
            if(!isMenuDatos) {
                guardar();
            }else {
                try {
                    ControlListas cl = new ControlListas(this);
                    if ((modo == MenuDatos.C_ASEGURADOA)|| (modo == MenuDatos.C_ASEGURADO_B)) {
                        if (Validaciones.camposBlanco(usuari.getText().toString())) {
                            Toast.makeText(AddUsuario.this, R.string.valida_nombre, Toast.LENGTH_SHORT).show();
                            usuari.requestFocus();
                        } else {
                            nombre = usuari.getText().toString();
                            if (Validaciones.camposBlanco(email.getText().toString())) {
                                Toast.makeText(AddUsuario.this, R.string.valida_email, Toast.LENGTH_SHORT).show();
                                email.requestFocus();
                            } else {
                                boolean validoEmail = Validaciones.validateEmail(email.getText().toString());
                                if (!validoEmail) {
                                    Toast.makeText(AddUsuario.this, R.string.valida_email_correcto, Toast.LENGTH_SHORT).show();
                                    email.requestFocus();
                                } else {
                                    correo = email.getText().toString();
                                    if (Validaciones.camposBlanco(telefono.getText().toString())) {
                                        Toast.makeText(AddUsuario.this, R.string.valida_telefono, Toast.LENGTH_SHORT).show();
                                        telefono.requestFocus();
                                    } else {
                                        tlf = telefono.getText().toString();
                                        isOk = true;
                                        if (modo == MenuDatos.C_ASEGURADOA) {
                                            AseguradoA datosXML = new AseguradoA(this);
                                            datosXML.aseguradoAXML(nombre, correo, tlf);

                                        } else if (modo == MenuDatos.C_ASEGURADO_B) {
                                            AseguradoB datosXML = new AseguradoB(this);
                                            datosXML.aseguradoBXML(nombre, correo, tlf);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if ((modo == MenuDatos.C_CONDUCTOR_A) || (modo == MenuDatos.C_CONDUCTOR_B)) {
                        if (Validaciones.camposBlanco(usuari.getText().toString())) {
                            Toast.makeText(AddUsuario.this, R.string.valida_nombre, Toast.LENGTH_SHORT).show();
                            usuari.requestFocus();
                        } else {
                            nombreC = usuari.getText().toString();
                            if (Validaciones.camposBlanco(email.getText().toString())) {
                                Toast.makeText(AddUsuario.this, R.string.valida_email, Toast.LENGTH_SHORT).show();
                                email.requestFocus();
                            } else {
                                boolean isValEmail = Validaciones.validateEmail(email.getText().toString());
                                if (!isValEmail) {
                                    Toast.makeText(AddUsuario.this, R.string.valida_email_correcto, Toast.LENGTH_SHORT).show();
                                    email.requestFocus();
                                } else {
                                    correoC = email.getText().toString();
                                    if (Validaciones.camposBlanco(telefono.getText().toString())) {
                                        Toast.makeText(AddUsuario.this, R.string.valida_telefono, Toast.LENGTH_SHORT).show();
                                        telefono.requestFocus();
                                    } else {
                                        tlfC = telefono.getText().toString();
                                        isOk = true;
                                        if (modo == MenuDatos.C_CONDUCTOR_A) {
                                            ConductorA datosXML = new ConductorA(this);
                                            datosXML.conductorAXML(nombreC, correoC, tlfC);
                                        } else if (modo == MenuDatos.C_CONDUCTOR_B) {
                                            ConductorB conductorB = new ConductorB(this);
                                            conductorB.conductorBXML(nombreC, correoC, tlfC);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (isOk) {
                        String tipo = cl.listaActiva();
                        switch (tipo) {
                            case ("aseguradoA"):
                                cl.activarVehiculoAXML();
                                break;
                            case ("conductorA"):
                                cl.activarImpactosAXML();
                                break;
                            case ("aseguradoB"):
                                cl.activarVehiculoBXML();
                                break;
                            case ("conductorB"):
                                cl.activarImpactosBXML();
                                break;
                        }

                        Intent intent = new Intent(AddUsuario.this, MenuDatos.class);
                        startActivity(intent);
                    }


                } catch (IOException | SAXException | ParserConfigurationException | TransformerConfigurationException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        consultar(id);
    }



    public void leerAseguradoAXML() throws IOException, ParserConfigurationException, SAXException {
        //Obtenemos la referencia al fichero XML de entrada
        FileInputStream fil = openFileInput("aseguradoA.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document dom = builder.parse(fil);

        Element raiz = dom.getDocumentElement();

        NodeList items = raiz.getElementsByTagName("aseguradoA");

        for (int i = 0; i < items.getLength(); i++) {
            Node nodoAseguradora = items.item(i);
            for (int j = 0; j < nodoAseguradora.getChildNodes().getLength(); j++) {
                Node nodoDetalles = nodoAseguradora.getChildNodes().item(j);
                switch (nodoDetalles.getNodeName()) {
                    case "nombre":
                        nombre = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "email":
                        correo = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "telefono":
                        tlf = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;

                }
            }
        }
    }

    public void leerAseguradoBXML() throws IOException, ParserConfigurationException, SAXException {
        //Obtenemos la referencia al fichero XML de entrada
        FileInputStream fil = openFileInput("aseguradoB.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document dom = builder.parse(fil);

        Element raiz = dom.getDocumentElement();

        NodeList items = raiz.getElementsByTagName("aseguradoB");

        for (int i = 0; i < items.getLength(); i++) {
            Node nodoAseguradora = items.item(i);
            for (int j = 0; j < nodoAseguradora.getChildNodes().getLength(); j++) {
                Node nodoDetalles = nodoAseguradora.getChildNodes().item(j);
                switch (nodoDetalles.getNodeName()) {
                    case "nombre":
                        nombre = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "email":
                        correo = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "telefono":
                        tlf = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;

                }
            }
        }
    }

    public void leerConductorAXML() throws IOException, ParserConfigurationException, SAXException {
        //Obtenemos la referencia al fichero XML de entrada
        FileInputStream fil = openFileInput("conductorA.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document dom = builder.parse(fil);

        Element raiz = dom.getDocumentElement();

        NodeList items = raiz.getElementsByTagName("conductorA");

        for (int i = 0; i < items.getLength(); i++) {
            Node nodoAseguradora = items.item(i);
            for (int j = 0; j < nodoAseguradora.getChildNodes().getLength(); j++) { //cabezera, vehiculaA, VehiculoB
                Node nodoDetalles = nodoAseguradora.getChildNodes().item(j);
                switch (nodoDetalles.getNodeName()) {
                    case "nombre":
                        nombreC = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "email":
                        correoC = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "telefono":
                        tlfC = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;

                }
            }
        }
    }

    public void leerConductorBXML() throws IOException, ParserConfigurationException, SAXException {
        //Obtenemos la referencia al fichero XML de entrada
        FileInputStream fil = openFileInput("conductorB.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document dom = builder.parse(fil);

        Element raiz = dom.getDocumentElement();

        NodeList items = raiz.getElementsByTagName("conductorB");

        for (int i = 0; i < items.getLength(); i++) {
            Node nodoAseguradora = items.item(i);
            for (int j = 0; j < nodoAseguradora.getChildNodes().getLength(); j++) { //cabezera, vehiculaA, VehiculoB
                Node nodoDetalles = nodoAseguradora.getChildNodes().item(j);
                switch (nodoDetalles.getNodeName()) {
                    case "nombre":
                        nombreC = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "email":
                        correoC = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "telefono":
                        tlfC = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;

                }
            }
        }
    }



}
