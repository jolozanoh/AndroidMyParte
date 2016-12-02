package es.myparte.parte.parte0;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;

import es.myparte.R;
import es.myparte.XMLficheros.ControlListas;
import es.myparte.XMLficheros.Localizacion;
import es.myparte.parte.MenuDatos;

public class DireccionAccidente extends AppCompatActivity implements View.OnClickListener {


    private Button btMapa;
    private EditText etFecha;

    private Switch swTestigo;


    private int year, month, day;
    private EditText etDireccion;
    private EditText etCiudad;
    private EditText etCodPos;
    private EditText etPais;
    private EditText etNumAtestado;


    private Switch swHerido;
    private Switch swDistinstosAB;
    private Switch swObjetosA;

    String fecha, direccion, ciudad, codPos, pais, atestado, nombreTestigo, telefonoTestigo;
    String herido = "false";
    String distintosAB = "false";
    String objetosA = "false";
    String testigos = "false";

    private LinearLayout llTestigoNombre;
    private LinearLayout llTestigoTelefono;

    private EditText etTestigoNombre;
    private EditText etTestigoTelefono;


    private EditText etLatitud;
    private EditText etLongitud;

    private String latitud;
    private String longitud;

    private LocationManager locManager;
    private LocationListener locListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_direccion_accidente);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etLatitud = (EditText) findViewById(R.id.etLatitud);
        etLongitud = (EditText) findViewById(R.id.etLongitud);

        etDireccion = (EditText) findViewById(R.id.etDireccion);
        etCodPos = (EditText) findViewById(R.id.etCp);
        etCiudad = (EditText) findViewById(R.id.etCiudad);
        etPais = (EditText) findViewById(R.id.etPais);
        etNumAtestado = (EditText) findViewById(R.id.etAtestado);

        llTestigoNombre = (LinearLayout) findViewById(R.id.llTestigoNombre);
        llTestigoTelefono = (LinearLayout) findViewById(R.id.llTestigoTelefono);
        llTestigoNombre.setVisibility(View.INVISIBLE);
        llTestigoTelefono.setVisibility(View.INVISIBLE);

        etTestigoNombre = (EditText) findViewById(R.id.etTestigoNombre);
        etTestigoTelefono = (EditText) findViewById(R.id.etTestigoTelefono);

        swHerido = (Switch) findViewById(R.id.swHeridos);
        swDistinstosAB = (Switch) findViewById((R.id.swDistintosAB));
        swObjetosA = (Switch) findViewById(R.id.swObjetosA);
        swTestigo = (Switch) findViewById(R.id.swTestigos);

        etFecha = (EditText) findViewById(R.id.etFecha);


        try {
            leerLocalizacionXML();

            if (!fecha.equalsIgnoreCase("null")) {

                etFecha.setText(fecha);
                etDireccion.setText(direccion);
                etCiudad.setText(ciudad);
                etCodPos.setText(codPos);
                etPais.setText(pais);
                if (atestado.equalsIgnoreCase("null")) {
                    etNumAtestado.setText("");
                } else {
                    etNumAtestado.setText(atestado);
                }
                if (herido.equalsIgnoreCase("true")) {
                    swHerido.setChecked(true);
                }
                if (distintosAB.equalsIgnoreCase("true")) {
                    swDistinstosAB.setChecked(true);
                }
                if (objetosA.equalsIgnoreCase("true")) {
                    swObjetosA.setChecked(true);
                }
                if (testigos.equalsIgnoreCase("true")) {
                    swTestigo.setChecked(true);
                    llTestigoNombre.setVisibility(View.VISIBLE);
                    llTestigoTelefono.setVisibility(View.VISIBLE);
                    etTestigoTelefono.setText(telefonoTestigo);
                    etTestigoNombre.setText(nombreTestigo);
                }
                etLatitud.setText(latitud);
                etLongitud.setText(longitud);


            } else {

                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");
                String formattedDate = df.format(c.getTime());

                etFecha.setText(formattedDate);
                fecha = formattedDate;

                comenzarLocalizacion();

                ///////////////////////////////
                etDireccion.setText("Muswell Hill Road");
                direccion = etDireccion.getText().toString();
                etCodPos.setText("N10 3SH");
                codPos = etCodPos.getText().toString();
                direccion = etDireccion.getText().toString();
                etCiudad.setText("London");
                ciudad = etCiudad.getText().toString();
                etPais.setText("Reino Unido");
                pais = etPais.getText().toString();
                etNumAtestado.setText("null");
                atestado = etNumAtestado.getText().toString();

                ///////////////////////////////

            }

            if (swHerido != null) {
                swHerido.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b == true) {
                            herido = "true";
                        } else {
                            herido = "false";
                        }
                    }
                });
            }

            if (swDistinstosAB != null) {
                swDistinstosAB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b == true) {
                            distintosAB = "true";
                        } else {
                            distintosAB = "false";
                        }
                    }
                });
            }

            if (swObjetosA != null) {
                swObjetosA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b == true) {
                            objetosA = "true";


                        } else {
                            objetosA = "false";
                        }
                    }
                });
            }

            if (swTestigo != null) {
                swTestigo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b == true) {
                            testigos = "true";
                            llTestigoNombre.setVisibility(View.VISIBLE);
                            llTestigoTelefono.setVisibility(View.VISIBLE);
                        } else {
                            testigos = "false";
                            llTestigoNombre.setVisibility(View.INVISIBLE);
                            llTestigoTelefono.setVisibility(View.INVISIBLE);
                            etTestigoNombre.setText("null");
                            etTestigoTelefono.setText("null");
                        }
                    }
                });
            }



            btMapa = (Button) findViewById(R.id.btLocalizacion);
            btMapa.setOnClickListener(this);


        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
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
            cancelar();
            return true;
        }
        if (id == R.id.save) {
            try {
                guardar();
            } catch (ParserConfigurationException | SAXException | TransformerConfigurationException | IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void guardar() throws ParserConfigurationException, SAXException, TransformerConfigurationException, IOException {


        ControlListas cl = new ControlListas(this);
        if (cl.listaActiva().equalsIgnoreCase("localizacion")) {
            cl.activarAseguradoAXML();
        }
        fecha = etFecha.getText().toString();
        direccion = etDireccion.getText().toString();
        ciudad = etCiudad.getText().toString();
        codPos = etCodPos.getText().toString();
        pais = etPais.getText().toString();
        if (etNumAtestado.getText().toString().equalsIgnoreCase("")) {
            atestado = "null";
        } else {
            atestado = etNumAtestado.getText().toString();
        }

        latitud = etLatitud.getText().toString();
        longitud = etLongitud.getText().toString();
        nombreTestigo = etTestigoNombre.getText().toString();
        if (etTestigoNombre.getText().toString().equalsIgnoreCase("")) {
            nombreTestigo = "null";
        }
        telefonoTestigo = etTestigoTelefono.getText().toString();
        if (etTestigoTelefono.getText().toString().equalsIgnoreCase("")) {
            telefonoTestigo = "null";
        }


        Localizacion localizacion = new Localizacion(this);
        localizacion.localizacionXML(fecha, direccion, ciudad, codPos, pais, atestado,
                herido, distintosAB, objetosA, testigos, nombreTestigo, telefonoTestigo, latitud, longitud);

        Intent i = new Intent(DireccionAccidente.this, MenuDatos.class);
        startActivity(i);
    }


    private void cancelar() {
        setResult(RESULT_CANCELED, null);
        finish();
    }

    private void comenzarLocalizacion() {
        //Obtenemos una referencia al LocationManager
        locManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);

        //Obtenemos la �ltima posici�n conocida

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        //Mostramos la �ltima posici�n conocida
        mostrarPosicion(loc);

        //Nos registramos para recibir actualizaciones de la posici�n
        locListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                mostrarPosicion(location);

            }

            public void onProviderDisabled(String provider){
                Toast.makeText(DireccionAccidente.this, "Proveedor de servicios OFF", Toast.LENGTH_SHORT).show();
            }
            public void onProviderEnabled(String provider){
                Toast.makeText(DireccionAccidente.this, "Proveedor de servicios ON", Toast.LENGTH_SHORT).show();
            }
            public void onStatusChanged(String provider, int status, Bundle extras){
                Toast.makeText(DireccionAccidente.this, "Estado del proveedor: " + status, Toast.LENGTH_SHORT).show();
            }
        };

        locManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 500, 0, locListener);
    }

    private void mostrarPosicion(Location loc) {
        if(loc != null){
            etLatitud.setText(String.valueOf(loc.getLatitude()));
            etLongitud.setText(String.valueOf(loc.getLongitude()));
            latitud = String.valueOf(loc.getLatitude());
            longitud = String.valueOf(loc.getLongitude());
            if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0){
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());

                try {
                    List<Address> list = geocoder.getFromLocation(loc.getLatitude(),loc.getLongitude(),200);
                    if(!list.isEmpty()){
                        Toast.makeText(DireccionAccidente.this, "entra", Toast.LENGTH_SHORT).show();
                        Address address = list.get(0);
                        etDireccion.setText(address.getAddressLine(0));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            Log.i("", String.valueOf(loc.getLatitude() + " - " + String.valueOf(loc.getLongitude())));
        }
        else
        {
            etLatitud.setText("1.0");
            etLongitud.setText("1.0");
        }
    }

    public void leerLocalizacionXML() throws IOException, ParserConfigurationException, SAXException {
        //Obtenemos la referencia al fichero XML de entrada
        FileInputStream fil = openFileInput("localizacion.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document dom = builder.parse(fil);

        Element raiz = dom.getDocumentElement();

        NodeList items = raiz.getElementsByTagName("localizacion");

        for (int i = 0; i < items.getLength(); i++) {
            Node nodoLocalizacion = items.item(i);
            for (int j = 0; j < nodoLocalizacion.getChildNodes().getLength(); j++) {
                Node nodoDetalles = nodoLocalizacion.getChildNodes().item(j);
                switch (nodoDetalles.getNodeName()) {
                    case "fecha":
                        fecha = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "direccion":
                        direccion = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "ciudad":
                        ciudad = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "codigoPostal":
                        codPos = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "pais":
                        pais = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "atestado":
                        atestado = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "herido":
                        herido = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "distintosAB":
                        distintosAB = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "objetosA":
                        objetosA = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "testigos":
                        testigos = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "nombreTestigo":
                        nombreTestigo = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "telefonoTestigo":
                        telefonoTestigo = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "latitud":
                        latitud = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;
                    case "longitud":
                        longitud = nodoDetalles.getChildNodes().item(0).getNodeValue();
                        break;

                }
            }
        }
    }


    @Override
    public void onClick(View view) {

        Intent intent;
        intent = new Intent(DireccionAccidente.this ,Ubicacion.class);
        intent.putExtra("latitud", latitud);
        intent.putExtra("longitud", longitud);
        startActivity(intent);
    }
}

