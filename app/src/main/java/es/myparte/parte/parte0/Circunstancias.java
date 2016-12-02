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
import android.widget.CompoundButton;
import android.widget.Switch;

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
import es.myparte.XMLficheros.CircunstanciasA;
import es.myparte.XMLficheros.CircunstanciasB;
import es.myparte.XMLficheros.ControlListas;
import es.myparte.parte.MenuDatos;

public class Circunstancias extends AppCompatActivity {

    private String[] circuntancias = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
    private String circuntancia = null;
    private int modo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circunstancias);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        modo = extra.getInt(MenuDatos.C_MODO_MENUDATOS);


        Switch sw00 = (Switch) findViewById(R.id.sw00);
        Switch sw01 = (Switch) findViewById(R.id.sw01);
        Switch sw02 = (Switch) findViewById(R.id.sw02);
        Switch sw03 = (Switch) findViewById(R.id.sw03);
        Switch sw04 = (Switch) findViewById(R.id.sw04);
        Switch sw05 = (Switch) findViewById(R.id.sw05);
        Switch sw06 = (Switch) findViewById(R.id.sw06);
        Switch sw07 = (Switch) findViewById(R.id.sw07);
        Switch sw08 = (Switch) findViewById(R.id.sw08);
        Switch sw09 = (Switch) findViewById(R.id.sw09);
        Switch sw10 = (Switch) findViewById(R.id.sw10);
        Switch sw11 = (Switch) findViewById(R.id.sw11);
        Switch sw12 = (Switch) findViewById(R.id.sw12);
        Switch sw13 = (Switch) findViewById(R.id.sw13);
        Switch sw14 = (Switch) findViewById(R.id.sw14);
        Switch sw15 = (Switch) findViewById(R.id.sw15);

        try {

            if (modo == MenuDatos.C_CIRCUNSTANCIA_A) {
                leerCircunstanciasAXML();
            }
            if (modo == MenuDatos.C_CIRCUNSTANCIA_B) {
                leerCircunstanciasBXML();
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }


        if (!circuntancia.equalsIgnoreCase("0000000000000000")){
            circuntancias = new String[16];
            for (int i = 0; i<=circuntancia.length()-1; i++){
                circuntancias[i]=String.valueOf(circuntancia.charAt(i));
                switch (i){
                    case 0:
                        if (circuntancias[i].equalsIgnoreCase("1")){
                            sw00.setChecked(true);
                        }
                        break;
                    case 1:
                        if (circuntancias[i].equalsIgnoreCase("1")){
                            sw01.setChecked(true);
                        }
                        break;
                    case 2:
                        if (circuntancias[i].equalsIgnoreCase("1")){
                            sw02.setChecked(true);
                        }
                        break;
                    case 3:
                        if (circuntancias[i].equalsIgnoreCase("1")){
                            sw03.setChecked(true);
                        }
                        break;
                    case 4:
                        if (circuntancias[i].equalsIgnoreCase("1")){
                            sw04.setChecked(true);
                        }
                        break;
                    case 5:
                        if (circuntancias[i].equalsIgnoreCase("1")){
                            sw05.setChecked(true);
                        }
                        break;
                    case 6:
                        if (circuntancias[i].equalsIgnoreCase("1")){
                            sw06.setChecked(true);
                        }
                        break;
                    case 7:
                        if (circuntancias[i].equalsIgnoreCase("1")){
                            sw07.setChecked(true);
                        }
                        break;
                    case 8:
                        if (circuntancias[i].equalsIgnoreCase("1")){
                            sw08.setChecked(true);
                        }
                        break;
                    case 9:
                        if (circuntancias[i].equalsIgnoreCase("1")){
                            sw09.setChecked(true);
                        }
                        break;
                    case 10:
                        if (circuntancias[i].equalsIgnoreCase("1")){
                            sw10.setChecked(true);
                        }
                        break;
                    case 11:
                        if (circuntancias[i].equalsIgnoreCase("1")){
                            sw11.setChecked(true);
                        }
                        break;
                    case 12:
                        if (circuntancias[i].equalsIgnoreCase("1")){
                            sw12.setChecked(true);
                        }
                        break;
                    case 13:
                        if (circuntancias[i].equalsIgnoreCase("1")){
                            sw13.setChecked(true);
                        }
                        break;
                    case 14:
                        if (circuntancias[i].equalsIgnoreCase("1")){
                            sw14.setChecked(true);
                        }
                        break;
                    case 15:
                        if (circuntancias[i].equalsIgnoreCase("1")){
                            sw15.setChecked(true);
                        }
                        break;
                }
            }
        }



        if (sw00 != null){
            sw00.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        circuntancias[0] = "1";
                    }else{
                        circuntancias[0] = "0";
                    }
                }
            });
        }
        if (sw01 != null){
            sw01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        circuntancias[1] = "1";
                    }else{
                        circuntancias[1] = "0";
                    }
                }
            });
        }
        if (sw02 != null){
            sw02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        circuntancias[2] = "1";
                    }else{
                        circuntancias[2] = "0";
                    }
                }
            });
        }
        if (sw03 != null){
            sw03.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        circuntancias[3] = "1";
                    }else{
                        circuntancias[3] = "0";
                    }
                }
            });
        }
        if (sw04 != null){
            sw04.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        circuntancias[4] = "1";
                    }else{
                        circuntancias[4] = "0";
                    }
                }
            });
        }
        if (sw05 != null){
            sw05.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        circuntancias[5] = "1";
                    }else{
                        circuntancias[5] = "0";
                    }
                }
            });
        }
        if (sw06 != null){
            sw06.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        circuntancias[6] = "1";
                    }else{
                        circuntancias[6] = "0";
                    }
                }
            });
        }
        if (sw07 != null){
            sw07.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        circuntancias[7] = "1";
                    }else{
                        circuntancias[7] = "0";
                    }
                }
            });
        }
        if (sw08 != null){
            sw08.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        circuntancias[8] = "1";
                    }else{
                        circuntancias[8] = "0";
                    }
                }
            });
        }
        if (sw09 != null){
            sw09.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        circuntancias[9] = "1";
                    }else{
                        circuntancias[9] = "0";
                    }
                }
            });
        }
        if (sw10 != null){
            sw10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        circuntancias[10] = "1";
                    }else{
                        circuntancias[10] = "0";
                    }
                }
            });
        }
        if (sw11 != null){
            sw11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        circuntancias[11] = "1";
                    }else{
                        circuntancias[11] = "0";
                    }
                }
            });
        }
        if (sw12 != null){
            sw12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        circuntancias[12] = "1";
                    }else{
                        circuntancias[12] = "0";
                    }
                }
            });
        }
        if (sw13 != null){
            sw13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        circuntancias[13] = "1";
                    }else{
                        circuntancias[13] = "0";
                    }
                }
            });
        }
        if (sw14 != null){
            sw14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        circuntancias[14] = "1";
                    }else{
                        circuntancias[14] = "0";
                    }
                }
            });
        }
        if (sw15 != null){
            sw15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        circuntancias[15] = "1";
                    }else{
                        circuntancias[15] = "0";
                    }
                }
            });
        }
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
                String circun = convertirString(circuntancias);
                if (modo == MenuDatos.C_CIRCUNSTANCIA_A) {
                    CircunstanciasA circunstanciasA = new CircunstanciasA(this);
                    circunstanciasA.circunstanciasAXML(circun);
                    if (cl.listaActiva().equalsIgnoreCase("circunstanciasA")) {
                        cl.activarAseguradoBXML();
                    }
                }
                if (modo == MenuDatos.C_CIRCUNSTANCIA_B) {
                    CircunstanciasB circunstanciasB = new CircunstanciasB(this);
                    circunstanciasB.circunstanciasBXML(circun);
                    if (cl.listaActiva().equalsIgnoreCase("circunstanciasB")) {
                        cl.activarTerminarXML();
                    }
                }
                i = new Intent(Circunstancias.this, MenuDatos.class);
                startActivity(i);
            } catch (IOException | ParserConfigurationException | SAXException | TransformerConfigurationException e) {
                e.printStackTrace();
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public String convertirString(String[] cadenaN){
        String cadena1;
        StringBuilder cadena = new StringBuilder();
        for (String aCadenaN : cadenaN) {
            cadena = cadena.append(aCadenaN);
        }
        cadena1 = cadena.toString();
        return cadena1;
    }

    public void leerCircunstanciasAXML() throws ParserConfigurationException, IOException, SAXException {
        //Obtenemos la referencia al fichero XML de entrada
        FileInputStream fil = openFileInput("circunstanciasA.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document dom = builder.parse(fil);

        Element raiz = dom.getDocumentElement();

        NodeList items = raiz.getElementsByTagName("circunstanciasA");

        for (int i = 0; i < items.getLength(); i++) {
            Node nodoAseguradora = items.item(i);
            for (int j = 0; j < nodoAseguradora.getChildNodes().getLength(); j++) {
                Node nodoDetalles = nodoAseguradora.getChildNodes().item(j);
                switch (nodoDetalles.getNodeName()) {
                    case "circunstanciasA":
                        circuntancia = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                }
            }
        }
    }

    public void leerCircunstanciasBXML() throws ParserConfigurationException, IOException, SAXException {
        //Obtenemos la referencia al fichero XML de entrada
        FileInputStream fil = openFileInput("circunstanciasB.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document dom = builder.parse(fil);

        Element raiz = dom.getDocumentElement();

        NodeList items = raiz.getElementsByTagName("circunstanciasB");

        for (int i = 0; i < items.getLength(); i++) {
            Node nodoAseguradora = items.item(i);
            for (int j = 0; j < nodoAseguradora.getChildNodes().getLength(); j++) {
                Node nodoDetalles = nodoAseguradora.getChildNodes().item(j);
                switch (nodoDetalles.getNodeName()) {
                    case "circunstanciasB":
                        circuntancia = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                }
            }
        }
    }


}

