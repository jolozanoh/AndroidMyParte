package es.myparte.parte;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
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

import es.myparte.R;
import es.myparte.clases.Parte;
import es.myparte.database.ParteDbAdapter;
import es.myparte.miPerfil.aseguradora.AddAseguradora;
import es.myparte.miPerfil.usuario.AddUsuario;
import es.myparte.miPerfil.vehiculo.AddVehiculo;
import es.myparte.parte.parte0.Circunstancias;
import es.myparte.parte.parte0.DireccionAccidente;
import es.myparte.parte.parte0.ParteImpactos;
import es.myparte.parte.parte0.TomarFotografias;
import es.myparte.pdf.GenerarPDF;

public class MenuDatos extends AppCompatActivity implements View.OnClickListener{
    private Button btCancelar;


    private boolean isfotografias = false;
    private boolean isGeneral = false;
    private boolean isVehiculoA = false;
    private boolean isVehiculo = false;
    private boolean isAseCompania = false;
    private boolean isConductor = false;
    private boolean isGolpes = false;
    private boolean isCircunstancias = false;

    private boolean isVehiculoB = false;
    private boolean isVehiculB = false;
    private boolean isAseCompaniaB = false;
    private boolean isConductorB = false;
    private boolean isGolpesB = false;
    private boolean isCircunstanciasB = false;

    private boolean isTerminar = false;



    private String[] tomaFotografias = {"Tomar Fotografias"};
    private String[] localizacion = {"Localizacion y Otros"};
    private String[] enviarEmail = {"TEMINAR Y ENVIAR EMAIL"};

    public static final String C_MODO_MENUDATOS = "modo";
    public static final int C_FOTOGRAFIAS = 100;
    public static final int C_LOCALIZACION = 101;
    public static final int C_ASEGURADOA = 102;
    public static final int C_VEHICULO_A = 103;
    public static final int C_ASEGURADORA_A = 104;
    public static final int C_CONDUCTOR_A = 105;
    public static final int C_IMPACTOS_A =106;
    public static final int C_CIRCUNSTANCIA_A = 107;

    public static final int C_ASEGURADO_B = 108;
    public static final int C_VEHICULO_B = 109;
    public static final int C_ASEGURADORA_B = 110;
    public static final int C_CONDUCTOR_B = 111;
    public static final int C_VEHICULOB = 112;
    public static final int C_IMPACTOS_B = 113;
    public static final int C_CIRCUNSTANCIA_B = 114;

    public static final int C_GENERAR_PDF = 551;

    public static final int C_TERMINAR = 999;

    private Parte parte = new Parte(this);
    private long id;



    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_datos);


        //Recupera parametros y los muestra en el TextView
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if ( bundle != null ) {
            Toast.makeText(MenuDatos.this, bundle.getString("Nombre"), Toast.LENGTH_SHORT).show();
            //;
            //bundle.getString("Correo");
            //bundle.getString("Telefono");
        }
        btCancelar = (Button)findViewById(R.id.btCancelar);
        btCancelar.setOnClickListener(this);

        ListView fotografias = (ListView) findViewById(R.id.lvFotografias);

        TextView tvGeneral = (TextView) findViewById(R.id.tvGenerales);
        ListView lvGeneral = (ListView) findViewById(R.id.lvGenerales);

        TextView tvVehiculoA = (TextView) findViewById(R.id.tvVehiculoA);
        ListView lvVehiculoA = (ListView) findViewById(R.id.lvVehiculoA);

        TextView tvVehiculoB = (TextView) findViewById(R.id.tvVehiculoB);
        ListView lvVehiculoB = (ListView) findViewById(R.id.lvVehiculoB);

        TextView tvTerminar = (TextView) findViewById(R.id.tvTerminar);
        ListView lvTerminar = (ListView) findViewById(R.id.lvTerminar);



        try {
            leerXMLpantallas();
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        if (isfotografias){
            tvGeneral.setVisibility(View.INVISIBLE);
            lvGeneral.setVisibility(View.INVISIBLE);
            tvVehiculoA.setVisibility(View.INVISIBLE);
            lvVehiculoA.setVisibility(View.INVISIBLE);
            tvVehiculoB.setVisibility(View.INVISIBLE);
            lvVehiculoB.setVisibility(View.INVISIBLE);
            tvTerminar.setVisibility(View.INVISIBLE);
            lvTerminar.setVisibility(View.INVISIBLE);
            fotografias.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tomaFotografias));
            this.setFotografias(fotografias);

        }
        if (isGeneral){
            tomaFotografias = new String[]{"® Tomar Fotografias"};
            fotografias.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tomaFotografias));
            this.setFotografias(fotografias);
            tvGeneral.setVisibility(View.VISIBLE);
            lvGeneral.setVisibility(View.VISIBLE);
            lvGeneral.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, localizacion));
            this.setGeneral(lvGeneral);

        }
        String[] vehiculoA;
        if (isVehiculoA){
            localizacion = new String[]{"® Localizacion y Otros"};
            lvGeneral.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, localizacion));
            this.setGeneral(lvGeneral);
            tvVehiculoA.setVisibility(View.VISIBLE);
            lvVehiculoA.setVisibility(View.VISIBLE);

            vehiculoA = new String[]{"Asegurado"};

            if (isVehiculo){
                vehiculoA = new String[]{"® Asegurado","Vehiculo"};
            }
            if (isAseCompania){
                vehiculoA = new String[]{"® Asegurado", "® Vehiculo", "Aseguradora"};
            }
            if (isConductor){
                vehiculoA = new String[]{"® Asegurado", "® Vehiculo", "® Aseguradora","Conductor"};
            }
            if (isGolpes){
                vehiculoA = new String[]{"® Asegurado", "® Vehiculo", "® Aseguradora","® Conductor","Impactos"};
            }
            if (isCircunstancias){
                vehiculoA = new String[]{"® Asegurado", "® Vehiculo", "® Aseguradora","® Conductor","® Impactos", "Circunstancias"};
            }

            lvVehiculoA.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, vehiculoA));
            this.setVehiculoA(lvVehiculoA);

        }
        String[] vehiculoB;
        if (isVehiculoB){
            vehiculoA = new String[]{"® Asegurado", "® Vehiculo", "® Aseguradora","® Conductor","® Impactos", "® Circunstancias"};
            lvVehiculoA.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, vehiculoA));
            this.setVehiculoA(lvVehiculoA);
            tvVehiculoB.setVisibility(View.VISIBLE);
            lvVehiculoB.setVisibility(View.VISIBLE);

            vehiculoB = new String[]{"Asegurado"};

            if (isVehiculB){
                vehiculoB = new String[]{"® Asegurado","Vehiculo"};
            }
            if (isAseCompaniaB){
                vehiculoB = new String[]{"® Asegurado", "® Vehiculo", "Aseguradora"};
            }
            if (isConductorB){
                vehiculoB = new String[]{"® Asegurado", "® Vehiculo", "® Aseguradora","Conductor"};
            }
            if (isGolpesB){
                vehiculoB = new String[]{"® Asegurado", "® Vehiculo", "® Aseguradora","® Conductor","Impactos"};
            }
            if (isCircunstanciasB){
                vehiculoB = new String[]{"® Asegurado", "® Vehiculo", "® Aseguradora","® Conductor","® Impactos", "Circunstancias"};
            }

            lvVehiculoB.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, vehiculoB));
            this.setVehiculoB(lvVehiculoB);

        }
        if (isTerminar){
            vehiculoB = new String[]{"® Asegurado", "® Vehiculo", "® Aseguradora","® Conductor","® Impactos", "® Circunstancias"};
            lvVehiculoB.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, vehiculoB));
            this.setVehiculoB(lvVehiculoB);
            tvTerminar.setVisibility(View.VISIBLE);
            lvTerminar.setVisibility(View.VISIBLE);
            lvTerminar.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, enviarEmail));
            this.setTerminar(lvTerminar);
        }
    }

    public void setFotografias(ListView mListView){
        ListAdapter mListAdaptar = mListView.getAdapter();
        if (mListAdaptar == null){
            return;
        }
        int height = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < mListAdaptar.getCount(); i++){
            View listItem = mListAdaptar.getView(i, null, mListView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            height += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = mListView.getLayoutParams();
        params.height = height + (mListView.getDividerHeight() * (mListAdaptar.getCount() - 1));
        mListView.setLayoutParams(params);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MenuDatos.this, TomarFotografias.class);
                i.putExtra(C_MODO_MENUDATOS, C_FOTOGRAFIAS);
                startActivityForResult(i, C_FOTOGRAFIAS);
            }
        });
        mListView.requestFocus();
    }

    public void setGeneral(ListView mListView) {
        ListAdapter mListAdaptar = mListView.getAdapter();
        if (mListAdaptar == null) {
            return;
        }
        int height = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < mListAdaptar.getCount(); i++) {
            View listItem = mListAdaptar.getView(i, null, mListView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            height += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = mListView.getLayoutParams();
        params.height = height + (mListView.getDividerHeight() * (mListAdaptar.getCount() - 1));
        mListView.setLayoutParams(params);
        mListView.requestFocus();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i;
                switch (position){
                    case 0:
                        i = new Intent(MenuDatos.this, DireccionAccidente.class);
                        i.putExtra(C_MODO_MENUDATOS, C_LOCALIZACION);
                        startActivityForResult(i, C_LOCALIZACION);
                        break;

                }
            }
        });
    }

    public void setVehiculoA(ListView mListView) {
        ListAdapter mListAdaptar = mListView.getAdapter();
        if (mListAdaptar == null) {
            return;
        }
        int height = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < mListAdaptar.getCount(); i++) {
            View listItem = mListAdaptar.getView(i, null, mListView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            height += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = mListView.getLayoutParams();
        params.height = height + (mListView.getDividerHeight() * (mListAdaptar.getCount() - 1));
        mListView.setLayoutParams(params);
        mListView.requestFocus();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i;
                switch (position) {
                    case 0:
                        i = new Intent(MenuDatos.this, AddUsuario.class);
                        i.putExtra(C_MODO_MENUDATOS, C_ASEGURADOA);
                        startActivityForResult(i, C_ASEGURADOA);
                        break;
                    case 1:
                        i = new Intent(MenuDatos.this, AddVehiculo.class);
                        i.putExtra(C_MODO_MENUDATOS, C_VEHICULO_A);
                        startActivityForResult(i, C_VEHICULO_A);
                        break;
                    case 2:
                        i = new Intent(MenuDatos.this, AddAseguradora.class);
                        i.putExtra(C_MODO_MENUDATOS, C_ASEGURADORA_A);
                        startActivityForResult(i, C_ASEGURADORA_A);
                        break;
                    case 3:
                        i = new Intent(MenuDatos.this, AddUsuario.class);
                        i.putExtra(C_MODO_MENUDATOS, C_CONDUCTOR_A);
                        startActivityForResult(i, C_CONDUCTOR_A);
                        break;
                    case 4:
                        i = new Intent(MenuDatos.this, ParteImpactos.class);
                        i.putExtra(C_MODO_MENUDATOS, C_IMPACTOS_A);
                        startActivityForResult(i, C_IMPACTOS_A);
                        break;
                    case 5:
                        i = new Intent(MenuDatos.this, Circunstancias.class);
                        i.putExtra(C_MODO_MENUDATOS, C_CIRCUNSTANCIA_A);
                        startActivityForResult(i, C_CIRCUNSTANCIA_A);
                        break;
                }

            }
        });
    }

    public void setVehiculoB(ListView mListView) {
        ListAdapter mListAdaptar = mListView.getAdapter();
        if (mListAdaptar == null) {
            return;
        }
        int height = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < mListAdaptar.getCount(); i++) {
            View listItem = mListAdaptar.getView(i, null, mListView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            height += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = mListView.getLayoutParams();
        params.height = height + (mListView.getDividerHeight() * (mListAdaptar.getCount() - 1));
        mListView.setLayoutParams(params);
        mListView.requestFocus();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i;
                switch (position) {
                    case 0:
                        i = new Intent(MenuDatos.this, AddUsuario.class);
                        i.putExtra(C_MODO_MENUDATOS, C_ASEGURADO_B);
                        startActivityForResult(i, C_ASEGURADO_B);
                        break;
                    case 1:
                        i = new Intent(MenuDatos.this, AddVehiculo.class);
                        i.putExtra(C_MODO_MENUDATOS, C_VEHICULO_B);
                        startActivityForResult(i, C_VEHICULO_B);
                        break;
                    case 2:
                        i = new Intent(MenuDatos.this, AddAseguradora.class);
                        i.putExtra(C_MODO_MENUDATOS, C_ASEGURADORA_B);
                        startActivityForResult(i, C_ASEGURADORA_B);
                        break;
                    case 3:
                        i = new Intent(MenuDatos.this, AddUsuario.class);
                        i.putExtra(C_MODO_MENUDATOS, C_CONDUCTOR_B);
                        startActivityForResult(i, C_CONDUCTOR_B);
                        break;
                    case 4:
                        i = new Intent(MenuDatos.this, ParteImpactos.class);
                        i.putExtra(C_MODO_MENUDATOS, C_IMPACTOS_B);
                        startActivityForResult(i, C_IMPACTOS_B);
                        break;
                    case 5:
                        i = new Intent(MenuDatos.this, Circunstancias.class);
                        i.putExtra(C_MODO_MENUDATOS, C_CIRCUNSTANCIA_B);
                        startActivityForResult(i, C_CIRCUNSTANCIA_B);
                        break;
                }
            }
        });
    }

    public void setTerminar(ListView mListView) {
        ListAdapter mListAdaptar = mListView.getAdapter();
        if (mListAdaptar == null) {
            return;
        }
        int height = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < mListAdaptar.getCount(); i++) {
            View listItem = mListAdaptar.getView(i, null, mListView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            height += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = mListView.getLayoutParams();
        params.height = height + (mListView.getDividerHeight() * (mListAdaptar.getCount() - 1));
        mListView.setLayoutParams(params);
        mListView.requestFocus();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i;
                switch (position){
                    case 0:
                        try {
                            guardar();
                            id = parte.getId();
                            Intent intent = new Intent(MenuDatos.this, GenerarPDF.class);
                            intent.putExtra(C_MODO_MENUDATOS, C_GENERAR_PDF);
                            intent.putExtra(ParteDbAdapter.C_COLUMNA_ID, id);
                            startActivityForResult(intent, C_GENERAR_PDF);
                            break;
                        } catch (IOException | ParserConfigurationException | SAXException e) {
                            e.printStackTrace();
                        }


                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case C_FOTOGRAFIAS:
                if (resultCode == RESULT_OK)
                    return;
                break;
            case C_LOCALIZACION:
                if (resultCode == RESULT_OK)
                    return;
                break;
            case C_ASEGURADOA:
                if (resultCode == RESULT_OK)
                    return;
                break;
            case C_VEHICULO_A:
                if (resultCode == RESULT_OK)
                    return;
                break;
            case C_ASEGURADORA_A:
                if (resultCode == RESULT_OK)
                    return;
                break;
            case C_VEHICULOB:
                if (resultCode == RESULT_OK)
                    return;
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View v) {
        Intent i;
        if (v == btCancelar){
            i = new Intent(MenuDatos.this, MenuParte.class);
            startActivity(i);
        }

    }

    public void leerXMLpantallas() throws IOException, ParserConfigurationException, SAXException {
        //Obtenemos la referencia al fichero XML de entrada
        FileInputStream fil = openFileInput("pantallas.xml");


        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document dom = builder.parse(fil);

        Element raiz = dom.getDocumentElement();

        NodeList items = raiz.getElementsByTagName("pantallas");

        for(int i = 0; i<items.getLength();i++){
            Node nodoPantalla = items.item(i);
            for(int j = 0; j < nodoPantalla.getChildNodes().getLength(); j++){
                Node nodoPrincipal = nodoPantalla.getChildNodes().item(j);
                if (nodoPrincipal.getNodeType() == Node.ELEMENT_NODE){
                    switch (nodoPrincipal.getNodeName()) {
                        case "fotografias":
                            if (nodoPrincipal.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                isfotografias = true;
                            }
                            break;
                        case "localizacion":
                            if (nodoPrincipal.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                isGeneral = true;
                            }
                            break;
                        case "vehiculoA":
                            for (int r = 0; r < nodoPrincipal.getChildNodes().getLength(); r++) {
                                Node nodoVehiculoA = nodoPrincipal.getChildNodes().item(r);
                                switch (nodoVehiculoA.getNodeName()) {
                                    case "aseguradoA":
                                        if (nodoVehiculoA.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                            isVehiculoA = true;
                                        }
                                        break;
                                    case "vehiculoA":
                                        if (nodoVehiculoA.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                            isVehiculo = true;
                                        }
                                        break;
                                    case "aseguradoraA":
                                        if (nodoVehiculoA.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                            isAseCompania = true;
                                        }
                                        break;
                                    case "conductorA":
                                        if (nodoVehiculoA.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                            isConductor = true;
                                        }
                                        break;
                                    case "impactosA":
                                        if (nodoVehiculoA.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                            isGolpes = true;
                                        }
                                        break;
                                    case "circunstanciasA":
                                        if (nodoVehiculoA.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                            isCircunstancias = true;
                                        }
                                        break;
                                }
                            }
                            break;
                        case "vehiculoB":
                            for (int r = 0; r < nodoPrincipal.getChildNodes().getLength(); r++) {
                                Node nodoVehiculoB = nodoPrincipal.getChildNodes().item(r);
                                switch (nodoVehiculoB.getNodeName()) {
                                    case "aseguradoB":
                                        if (nodoVehiculoB.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                            isVehiculoB = true;
                                        }
                                        break;
                                    case "vehiculoB":
                                        if (nodoVehiculoB.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                            isVehiculB = true;
                                        }
                                        break;
                                    case "aseguradoraB":
                                        if (nodoVehiculoB.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                            isAseCompaniaB = true;
                                        }
                                        break;
                                    case "conductorB":
                                        if (nodoVehiculoB.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                            isConductorB = true;
                                        }
                                        break;
                                    case "impactosB":
                                        if (nodoVehiculoB.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                            isGolpesB = true;
                                        }
                                        break;
                                    case "circunstanciasB":
                                        if (nodoVehiculoB.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                            isCircunstanciasB = true;
                                        }
                                        break;
                                }
                            }
                            break;
                        case "terminar":
                            for (int r = 0; r < nodoPrincipal.getChildNodes().getLength(); r++) {
                                Node nodoTerminar = nodoPrincipal.getChildNodes().item(r);
                                switch (nodoTerminar.getNodeName()) {
                                    case "terminar":
                                        if (nodoTerminar.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                            isTerminar = true;
                                        }
                                        break;
                                }
                            }

                    }
                }
            }

        }
    }

    private void guardar() throws IOException, ParserConfigurationException, SAXException {
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
                        parte.setPar_fecha(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "direccion":
                        parte.setPar_direccion(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "ciudad":
                        parte.setPar_ciudad(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "codigoPostal":
                        parte.setPar_codigoPostal(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "pais":
                        parte.setPar_pais(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "atestado":
                        parte.setPar_atestado(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "herido":
                        parte.setPar_herido(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "distintosAB":
                        parte.setPar_distintosAB(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "objetosA":
                        parte.setPar_objetosA(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "testigos":
                        parte.setPar_testigos(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "nombretestigos":
                        parte.setPar_nombreTestigo(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "telefonoTestigo":
                        parte.setPar_telefonoTestigo(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "latitud":
                        parte.setPar_latitud(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "longitud":
                        parte.setPar_longitud(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                }
            }
        }

        fil = openFileInput("fotografias.xml");
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        dom = builder.parse(fil);
        raiz = dom.getDocumentElement();
        items = raiz.getElementsByTagName("fotografias");

        for(int i = 0; i<items.getLength();i++){
            Node nodoFografias = items.item(i);
            for(int j = 0; j < nodoFografias.getChildNodes().getLength(); j++){
                Node nodoFotografia = nodoFografias.getChildNodes().item(j);
                switch (nodoFotografia.getNodeName()) {
                    case "fotografia1":
                        parte.setPar_foto1(nodoFotografia.getChildNodes().item(0).getNodeValue());
                        break;
                    case "fotografia2":
                        parte.setPar_foto2(nodoFotografia.getChildNodes().item(0).getNodeValue());
                        break;
                    case "fotografia3":
                        parte.setPar_foto3(nodoFotografia.getChildNodes().item(0).getNodeValue());
                        break;
                }

            }

        }

        fil = openFileInput("aseguradoA.xml");
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        dom = builder.parse(fil);
        raiz = dom.getDocumentElement();
        items = raiz.getElementsByTagName("aseguradoA");
        for (int i = 0; i < items.getLength(); i++) {
            Node nodoAseguradora = items.item(i);
            for (int j = 0; j < nodoAseguradora.getChildNodes().getLength(); j++) {
                Node nodoDetalles = nodoAseguradora.getChildNodes().item(j);
                switch (nodoDetalles.getNodeName()) {
                    case "nombre":
                        parte.setPar_aseNombreA(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "email":
                        parte.setPar_aseEmailA(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "telefono":
                        parte.setPar_aseTelefonoA(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;

                }
            }
        }

        fil = openFileInput("aseguradoB.xml");
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        dom = builder.parse(fil);
        raiz = dom.getDocumentElement();
        items = raiz.getElementsByTagName("aseguradoB");
        for (int i = 0; i < items.getLength(); i++) {
            Node nodoAseguradora = items.item(i);
            for (int j = 0; j < nodoAseguradora.getChildNodes().getLength(); j++) {
                Node nodoDetalles = nodoAseguradora.getChildNodes().item(j);
                switch (nodoDetalles.getNodeName()) {
                    case "nombre":
                        parte.setPar_aseNombreB(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "email":
                        parte.setPar_aseEmailB(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "telefono":
                        parte.setPar_aseTelefonoB(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;

                }
            }
        }

        fil = openFileInput("conductorA.xml");
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        dom = builder.parse(fil);
        raiz = dom.getDocumentElement();
        items = raiz.getElementsByTagName("conductorA");
        for (int i = 0; i < items.getLength(); i++) {
            Node nodoAseguradora = items.item(i);
            for (int j = 0; j < nodoAseguradora.getChildNodes().getLength(); j++) {
                Node nodoDetalles = nodoAseguradora.getChildNodes().item(j);
                switch (nodoDetalles.getNodeName()) {
                    case "nombre":
                        parte.setPar_conNombreA(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "email":
                        parte.setPar_conEmailA(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "telefono":
                        parte.setPar_conTelefonoA(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;

                }
            }
        }

        fil = openFileInput("conductorB.xml");
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        dom = builder.parse(fil);
        raiz = dom.getDocumentElement();
        items = raiz.getElementsByTagName("conductorB");
        for (int i = 0; i < items.getLength(); i++) {
            Node nodoAseguradora = items.item(i);
            for (int j = 0; j < nodoAseguradora.getChildNodes().getLength(); j++) {
                Node nodoDetalles = nodoAseguradora.getChildNodes().item(j);
                switch (nodoDetalles.getNodeName()) {
                    case "nombre":
                        parte.setPar_conNombreB(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "email":
                        parte.setPar_conEmailB(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "telefono":
                        parte.setPar_conTelefonoB(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;

                }
            }
        }

        fil = openFileInput("aseguradoraA.xml");
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        dom = builder.parse(fil);
        raiz = dom.getDocumentElement();
        items = raiz.getElementsByTagName("aseguradoraA");
        for (int i = 0; i < items.getLength(); i++) {
            Node nodoAseguradora = items.item(i);
            for (int j = 0; j < nodoAseguradora.getChildNodes().getLength(); j++) {
                Node nodoDetalles = nodoAseguradora.getChildNodes().item(j);
                switch (nodoDetalles.getNodeName()) {
                    case "nombre":
                        parte.setPar_segNombreA(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "email":
                        parte.setPar_segEmailA(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "telefono":
                        parte.setPar_segTelefonoA(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;

                }
            }
        }

        fil = openFileInput("aseguradoraB.xml");
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        dom = builder.parse(fil);
        raiz = dom.getDocumentElement();
        items = raiz.getElementsByTagName("aseguradoraB");
        for (int i = 0; i < items.getLength(); i++) {
            Node nodoAseguradora = items.item(i);
            for (int j = 0; j < nodoAseguradora.getChildNodes().getLength(); j++) {
                Node nodoDetalles = nodoAseguradora.getChildNodes().item(j);
                switch (nodoDetalles.getNodeName()) {
                    case "nombre":
                        parte.setPar_segNombreB(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "email":
                        parte.setPar_segEmailB(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "telefono":
                        parte.setPar_segTelefonoB(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;

                }
            }
        }

        fil = openFileInput("vehiculoA.xml");
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        dom = builder.parse(fil);
        raiz = dom.getDocumentElement();
        items = raiz.getElementsByTagName("vehiculoA");
        for (int i = 0; i < items.getLength(); i++) {
            Node nodoVehiculo = items.item(i);
            for (int j = 0; j < nodoVehiculo.getChildNodes().getLength(); j++) {
                Node nodoDetalles = nodoVehiculo.getChildNodes().item(j);
                switch (nodoDetalles.getNodeName()) {
                    case "matricula":
                        parte.setPar_vehMatriculaA(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "marca":
                        parte.setPar_vehMarcaA(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "modelo":
                        parte.setPar_vehModeloA(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "remolque":
                        parte.setPar_vehRemolqueA(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "matriculaRemolque":
                        parte.setPar_vehMatRemolA(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;

                }
            }
        }

        fil = openFileInput("vehiculoB.xml");
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        dom = builder.parse(fil);
        raiz = dom.getDocumentElement();
        items = raiz.getElementsByTagName("vehiculoB");
        for (int i = 0; i < items.getLength(); i++) {
            Node nodoVehiculo = items.item(i);
            for (int j = 0; j < nodoVehiculo.getChildNodes().getLength(); j++) {
                Node nodoDetalles = nodoVehiculo.getChildNodes().item(j);
                switch (nodoDetalles.getNodeName()) {
                    case "matricula":
                        parte.setPar_vehMatriculaB(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "marca":
                        parte.setPar_vehMarcaB(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "modelo":
                        parte.setPar_vehModeloB(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "remolque":
                        parte.setPar_vehRemolqueB(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                    case "matriculaRemolque":
                        parte.setPar_vehMatRemolB(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;

                }
            }
        }

        fil = openFileInput("impactosA.xml");
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        dom = builder.parse(fil);
        raiz = dom.getDocumentElement();
        items = raiz.getElementsByTagName("impactosA");
        for (int i = 0; i < items.getLength(); i++) {
            Node nodoAseguradora = items.item(i);
            for (int j = 0; j < nodoAseguradora.getChildNodes().getLength(); j++) {
                Node nodoDetalles = nodoAseguradora.getChildNodes().item(j);
                switch (nodoDetalles.getNodeName()) {
                    case "impactosA":
                        parte.setPar_danosA(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;


                }
            }
        }

        fil = openFileInput("impactosB.xml");
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        dom = builder.parse(fil);
        raiz = dom.getDocumentElement();
        items = raiz.getElementsByTagName("impactosB");
        for (int i = 0; i < items.getLength(); i++) {
            Node nodoAseguradora = items.item(i);
            for (int j = 0; j < nodoAseguradora.getChildNodes().getLength(); j++) {
                Node nodoDetalles = nodoAseguradora.getChildNodes().item(j);
                switch (nodoDetalles.getNodeName()) {
                    case "impactosB":
                        parte.setPar_danosB(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;


                }
            }

        }

        fil = openFileInput("circunstanciasA.xml");
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        dom = builder.parse(fil);
        raiz = dom.getDocumentElement();
        items = raiz.getElementsByTagName("circunstanciasA");
        for (int i = 0; i < items.getLength(); i++) {
            Node nodoAseguradora = items.item(i);
            for (int j = 0; j < nodoAseguradora.getChildNodes().getLength(); j++) {
                Node nodoDetalles = nodoAseguradora.getChildNodes().item(j);
                switch (nodoDetalles.getNodeName()) {
                    case "circunstanciasA":
                        parte.setPar_circunstanciasA(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                }
            }
        }

        fil = openFileInput("circunstanciasB.xml");
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        dom = builder.parse(fil);
        raiz = dom.getDocumentElement();
        items = raiz.getElementsByTagName("circunstanciasB");
        for (int i = 0; i < items.getLength(); i++) {
            Node nodoAseguradora = items.item(i);
            for (int j = 0; j < nodoAseguradora.getChildNodes().getLength(); j++) {
                Node nodoDetalles = nodoAseguradora.getChildNodes().item(j);
                switch (nodoDetalles.getNodeName()) {
                    case "circunstanciasB":
                        parte.setPar_circunstanciasB(nodoDetalles.getChildNodes().item(0).getNodeValue());
                        break;
                }
            }
        }

        parte.save();
    }
}

