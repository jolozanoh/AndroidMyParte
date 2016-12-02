package es.myparte;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import es.myparte.emergencias.Emergencias;
import es.myparte.miPerfil.MiPerfil;
import es.myparte.parte.MenuParte;

public class Main extends AppCompatActivity implements View.OnClickListener{

    private Button miPerfil;
    private Button parte;
    private Button emergencias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        miPerfil = (Button) findViewById(R.id.btMiPerfil);
        if (miPerfil != null) {
            miPerfil.setOnClickListener(this);
        }
        parte = (Button) findViewById(R.id.btParte);
        if (parte != null) {
            parte.setOnClickListener(this);
        }

        emergencias = (Button) findViewById(R.id.btEmergencias);
        if (emergencias != null) {
            emergencias.setOnClickListener(this);
        }


    }


    @Override
    public void onClick(View v) {
        Intent i;
        if (v == miPerfil){
            i = new Intent(Main.this, MiPerfil.class);
            startActivity(i);
        }
        if (v == parte){
            i = new Intent(Main.this, MenuParte.class);
            startActivity(i);
        }
        if (v == emergencias){
            i = new Intent(Main.this, Emergencias.class);
            startActivity(i);
        }
    }
}
