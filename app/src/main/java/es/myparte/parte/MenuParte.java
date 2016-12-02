package es.myparte.parte;

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

import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;

import es.myparte.Main;
import es.myparte.R;
import es.myparte.XMLficheros.AseguradoA;
import es.myparte.XMLficheros.AseguradoB;
import es.myparte.XMLficheros.CircunstanciasA;
import es.myparte.XMLficheros.CircunstanciasB;
import es.myparte.XMLficheros.ConductorA;
import es.myparte.XMLficheros.ConductorB;
import es.myparte.XMLficheros.ControlListas;
import es.myparte.XMLficheros.Fotografias;
import es.myparte.XMLficheros.ImpactosA;
import es.myparte.XMLficheros.ImpactosB;
import es.myparte.XMLficheros.Localizacion;
import es.myparte.XMLficheros.SeguroA;
import es.myparte.XMLficheros.SeguroB;
import es.myparte.XMLficheros.VehiculoA;
import es.myparte.XMLficheros.VehiculoB;
import es.myparte.pdf.ListPartesPdf;

public class MenuParte extends AppCompatActivity implements View.OnClickListener {
    private Button menuDatos;
    private Button reenviarPartes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_parte);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ControlListas pantallas = new ControlListas(this);
        Fotografias fotografias = new Fotografias(this);
        Localizacion localizacion = new Localizacion(this);
        AseguradoA aseguradoA = new AseguradoA(this);
        VehiculoA vehiculoA = new VehiculoA(this);
        SeguroA seguroA = new SeguroA(this);
        ConductorA conductorA = new ConductorA(this);
        ImpactosA impactosA = new ImpactosA(this);
        CircunstanciasA circunstanciasA = new CircunstanciasA(this);

        AseguradoB aseguradoB = new AseguradoB(this);
        VehiculoB vehiculoB = new VehiculoB(this);
        SeguroB seguroB = new SeguroB(this);
        ConductorB conductorB = new ConductorB(this);
        ImpactosB impactosB = new ImpactosB(this);
        CircunstanciasB circunstanciasB = new CircunstanciasB(this);



        try {
            pantallas.crearListasXML();
            fotografias.crearfotografiasXML();
            localizacion.crearlocalizacionXML();
            aseguradoA.crearAseguradoAXML();
            vehiculoA.crearVehiculoAXML();
            seguroA.crearAseguradoraAXML();
            conductorA.crearConductorAXML();
            impactosA.crearImpactosAXML();
            circunstanciasA.crearCircunstanciasAXML();
            aseguradoB.crearAseguradoBXML();
            vehiculoB.crearVehiculoBXML();
            seguroB.crearAseguradoraBXML();
            conductorB.crearConductorBXML();
            impactosB.crearImpactosBXML();
            circunstanciasB.crearCircunstanciasBXML();

        } catch (IOException | SAXException | ParserConfigurationException | TransformerConfigurationException e) {
            e.printStackTrace();
        }


        menuDatos = (Button) findViewById(R.id.btNuevoParte);
        menuDatos.setOnClickListener(this);

        reenviarPartes = (Button)findViewById(R.id.btReenviarParte);
        reenviarPartes.setOnClickListener(this);

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
            i = new Intent(MenuParte.this, Main.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        if (v == menuDatos){
            i = new Intent(MenuParte.this, MenuDatos.class);
            startActivity(i);
        }

        if (v == reenviarPartes){
            i = new Intent(MenuParte.this, ListPartesPdf.class);
            startActivity(i);
        }


    }
}

