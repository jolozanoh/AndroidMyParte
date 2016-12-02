package es.myparte.parte.parte0;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

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
import es.myparte.XMLficheros.ImpactosA;
import es.myparte.XMLficheros.ImpactosB;
import es.myparte.parte.MenuDatos;

public class ParteImpactos extends AppCompatActivity {

    private String[] impactos = {"0",  "0",  "0",  "0",  "0",  "0",  "0",  "0",  "0",  "0",  "0",  "0"};


    private String impactosA;
    private int modo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parte_impactos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {

            Intent intent = getIntent();
            Bundle extra = intent.getExtras();
            modo = extra.getInt(MenuDatos.C_MODO_MENUDATOS);


            CheckBox cb01 = (CheckBox) findViewById(R.id.cb1);
            CheckBox cb02 = (CheckBox) findViewById(R.id.cb2);
            CheckBox cb03 = (CheckBox) findViewById(R.id.cb3);
            CheckBox cb04 = (CheckBox) findViewById(R.id.cb4);
            CheckBox cb05 = (CheckBox) findViewById(R.id.cb5);
            CheckBox cb06 = (CheckBox) findViewById(R.id.cb6);
            CheckBox cb07 = (CheckBox) findViewById(R.id.cb7);
            CheckBox cb08 = (CheckBox) findViewById(R.id.cb8);
            CheckBox cb09 = (CheckBox) findViewById(R.id.cb9);
            CheckBox cb10 = (CheckBox) findViewById(R.id.cb10);
            CheckBox cb11 = (CheckBox) findViewById(R.id.cb11);
            CheckBox cb12 = (CheckBox) findViewById(R.id.cb12);

            if (modo == MenuDatos.C_IMPACTOS_A) {
                leerImpactosAXML();
            }
            if (modo == MenuDatos.C_IMPACTOS_B){
                leerImpactosBXML();
            }

            if (!impactosA.equalsIgnoreCase("000000000000")){
                impactos = new String[12];
                for (int i = 0; i<=impactosA.length()-1; i++){
                    impactos[i]=String.valueOf(impactosA.charAt(i));
                    switch (i){
                        case 0:
                            if (impactos[i].equalsIgnoreCase("1")){
                                cb01.setChecked(true);
                            }
                            break;
                        case 1:
                            if (impactos[i].equalsIgnoreCase("1")){
                                cb02.setChecked(true);
                            }
                            break;
                        case 2:
                            if (impactos[i].equalsIgnoreCase("1")){
                                cb03.setChecked(true);
                            }
                            break;
                        case 3:
                            if (impactos[i].equalsIgnoreCase("1")){
                                cb04.setChecked(true);
                            }
                            break;
                        case 4:
                            if (impactos[i].equalsIgnoreCase("1")){
                                cb05.setChecked(true);
                            }
                            break;
                        case 5:
                            if (impactos[i].equalsIgnoreCase("1")){
                                cb06.setChecked(true);
                            }
                            break;
                        case 6:
                            if (impactos[i].equalsIgnoreCase("1")){
                                cb07.setChecked(true);
                            }
                            break;
                        case 7:
                            if (impactos[i].equalsIgnoreCase("1")){
                                cb08.setChecked(true);
                            }
                            break;
                        case 8:
                            if (impactos[i].equalsIgnoreCase("1")){
                                cb09.setChecked(true);
                            }
                            break;
                        case 9:
                            if (impactos[i].equalsIgnoreCase("1")){
                                cb10.setChecked(true);
                            }
                            break;
                        case 10:
                            if (impactos[i].equalsIgnoreCase("1")){
                                cb11.setChecked(true);
                            }
                            break;
                        case 11:
                            if (impactos[i].equalsIgnoreCase("1")){
                                cb12.setChecked(true);
                            }
                            break;


                    }
                }
            }


            if (cb01 != null){
                cb01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b){
                            impactos[0] = "1";
                        }else{
                            impactos[0] = "0";
                        }
                    }
                });
            }

            if (cb02 != null){
                cb02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b){
                            impactos[1] = "1";
                        }else{
                            impactos[1] = "0";
                        }
                    }
                });
            }
            if (cb03 != null){
                cb03.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b){
                            impactos[2] = "1";
                        }else{
                            impactos[2] = "0";
                        }
                    }
                });
            }
            if (cb04 != null){
                cb04.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b){
                            impactos[3] = "1";
                        }else{
                            impactos[3] = "0";
                        }
                    }
                });
            }
            if (cb05 != null){
                cb05.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b){
                            impactos[4] = "1";
                        }else{
                            impactos[4] = "0";
                        }
                    }
                });
            }
            if (cb06 != null){
                cb06.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b){
                            impactos[5] = "1";
                        }else{
                            impactos[5] = "0";
                        }
                    }
                });
            }
            if (cb07 != null){
                cb07.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b){
                            impactos[6] = "1";
                        }else{
                            impactos[6] = "0";
                        }
                    }
                });
            }
            if (cb08 != null){
                cb08.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b){
                            impactos[7] = "1";
                        }else{
                            impactos[7] = "0";
                        }
                    }
                });
            }
            if (cb09 != null){
                cb09.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b){
                            impactos[8] = "1";
                        }else{
                            impactos[8] = "0";
                        }
                    }
                });
            }
            if (cb10 != null){
                cb10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b){
                            impactos[9] = "1";
                        }else{
                            impactos[9] = "0";
                        }
                    }
                });
            }
            if (cb11 != null){
                cb11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b){
                            impactos[10] = "1";


                        }else{
                            impactos[10] = "0";
                        }
                    }
                });
            }
            if (cb12 != null){
                cb12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b){
                            impactos[11] = "1";
                        }else{
                            impactos[11] = "0";
                        }
                    }
                });
            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    public String convertirString(String[] impactos){
        StringBuilder cadena = new StringBuilder();
        for (String impacto1 : impactos) {
            cadena = cadena.append(impacto1);
        }
        return cadena.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_atras_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent i;
        if (id == R.id.atras) {
            setResult(RESULT_CANCELED, null);
            finish();
            return true;
        }
        if (id == R.id.save) {
            try {

                ControlListas cl = new ControlListas(this);

                String danos = convertirString(impactos);
                if (modo == MenuDatos.C_IMPACTOS_A) {
                    ImpactosA impactosA = new ImpactosA(this);
                    impactosA.impactosAXML(danos);
                    if (cl.listaActiva().equalsIgnoreCase("impactosA")) {
                        cl.activarCircunstanciasAXML();
                    }
                }
                if (modo == MenuDatos.C_IMPACTOS_B) {
                    ImpactosB impactosB = new ImpactosB(this);
                    impactosB.impactosBXML(danos);
                    if (cl.listaActiva().equalsIgnoreCase("impactosB")) {
                        cl.activarCircunstaciasBXML();
                    }
                }

                i = new Intent(ParteImpactos.this, MenuDatos.class);
                startActivity(i);
            } catch (IOException | TransformerConfigurationException | SAXException | ParserConfigurationException e) {
                e.printStackTrace();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void leerImpactosAXML() throws ParserConfigurationException, IOException, SAXException {
        //Obtenemos la referencia al fichero XML de entrada
        FileInputStream fil = openFileInput("impactosA.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document dom = builder.parse(fil);

        Element raiz = dom.getDocumentElement();

        NodeList items = raiz.getElementsByTagName("impactosA");

        for (int i = 0; i < items.getLength(); i++) {
            Node nodoAseguradora = items.item(i);
            for (int j = 0; j < nodoAseguradora.getChildNodes().getLength(); j++) { //cabezera, vehiculaA, VehiculoB
                Node nodoDetalles = nodoAseguradora.getChildNodes().item(j);
                switch (nodoDetalles.getNodeName()) {
                    case "impactosA":
                        impactosA = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;


                }
            }
        }
    }

    public void leerImpactosBXML() throws ParserConfigurationException, IOException, SAXException {
        //Obtenemos la referencia al fichero XML de entrada
        FileInputStream fil = openFileInput("impactosB.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document dom = builder.parse(fil);

        Element raiz = dom.getDocumentElement();

        NodeList items = raiz.getElementsByTagName("impactosB");

        for (int i = 0; i < items.getLength(); i++) {
            Node nodoAseguradora = items.item(i);
            for (int j = 0; j < nodoAseguradora.getChildNodes().getLength(); j++) { //cabezera, vehiculaA, VehiculoB
                Node nodoDetalles = nodoAseguradora.getChildNodes().item(j);
                switch (nodoDetalles.getNodeName()) {
                    case "impactosB":
                        impactosA = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;


                }
            }
        }
    }
}
