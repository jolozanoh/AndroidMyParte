package es.myparte.parte.parte0;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;

import es.myparte.R;
import es.myparte.XMLficheros.ControlListas;
import es.myparte.XMLficheros.Fotografias;
import es.myparte.parte.MenuDatos;

public class TomarFotografias extends AppCompatActivity implements View.OnClickListener {
    private static int FOTO_GENERAL = 100;
    private static int FOTO_NEUMATICOS = 101;
    private static int FOTO_OTROS = 102;

    private ImageView ivFotoGeneral;
    private ImageView ivFotoNeumaticos;
    private ImageView ivFotoOtros;
    private Button btFotoGeneral;
    private Button btFotoNeumaticos;
    private Button btFotoOtros;

    private String uriFoto1 = "";
    private String uriFoto2 = "";
    private String uriFoto3 = "";
    boolean hechaFoto1 = false;
    boolean hacerFoto2 = false;
    boolean hacerFoto3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomar_fotografias);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            ivFotoGeneral = (ImageView) findViewById(R.id.ivFotoGeneral);
            ivFotoNeumaticos = (ImageView) findViewById(R.id.ivFotoNeumaticos);
            ivFotoOtros = (ImageView) findViewById(R.id.ivFotoOtros);

            btFotoGeneral = (Button) findViewById(R.id.btFotoGeneral);
            btFotoNeumaticos = (Button) findViewById(R.id.btFotoNeumaticos);
            btFotoOtros = (Button) findViewById(R.id.btFotoOtros);

            btFotoGeneral.setOnClickListener(this);
            btFotoNeumaticos.setOnClickListener(this);
            btFotoOtros.setOnClickListener(this);
            leerFotografiasXML();
            if (!uriFoto1.equalsIgnoreCase("null")) {







                cargarImagenes();
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }


    }


    private void cargarImagenes() throws FileNotFoundException {
        Uri selectedImage = Uri.fromFile(new File(uriFoto1));
        InputStream is = getContentResolver().openInputStream(selectedImage);
        assert is != null;
        BufferedInputStream bis = new BufferedInputStream(is);
        Bitmap bitmap = BitmapFactory.decodeStream(bis);
        ivFotoGeneral.setImageBitmap(bitmap);
        hechaFoto1 = true;
        hacerFoto2 = true;


        if (!uriFoto2.equalsIgnoreCase("null")){
            selectedImage = Uri.fromFile(new File(uriFoto2));
            is = getContentResolver().openInputStream(selectedImage);
            assert is != null;
            bis = new BufferedInputStream(is);
            bitmap = BitmapFactory.decodeStream(bis);
            ivFotoNeumaticos.setImageBitmap(bitmap);
            hacerFoto3 = true;
        }
        if (!uriFoto3.equalsIgnoreCase("null")){

            selectedImage = Uri.fromFile(new File(uriFoto3));
            is = getContentResolver().openInputStream(selectedImage);
            assert is != null;
            bis = new BufferedInputStream(is);
            bitmap = BitmapFactory.decodeStream(bis);
            ivFotoOtros.setImageBitmap(bitmap);
        }
    }

    @SuppressLint("SimpleDateFormat")
    private String getCode(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String date = dateFormat.format(new Date());
        return "pic_" + date;
    }


    @Override
    public void onClick(View v) {

        Intent i;
        if (v == btFotoGeneral){
            if (!uriFoto1.equalsIgnoreCase("null")) {
                File file = new File(uriFoto1);
                file.delete();
            }
            uriFoto1 = Environment.getExternalStorageDirectory() + "/Pictures/" + "pic1_" + this.getCode() + ".jpg";
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            int code = FOTO_GENERAL;
            Uri output = Uri.fromFile(new File(uriFoto1));
            intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
            startActivityForResult(intent, code);
        }
        if (v == btFotoNeumaticos){if (hacerFoto2){
                if (!uriFoto2.equalsIgnoreCase("null")) {
                    File file = new File(uriFoto2);
                    file.delete();
                }
                uriFoto2 = Environment.getExternalStorageDirectory() + "/Pictures/" + "pic2_" + this.getCode() + ".jpg";
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                int code = FOTO_NEUMATICOS;
                Uri output = Uri.fromFile(new File(uriFoto2));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
                startActivityForResult(intent, code);
            }else {
                Toast.makeText(TomarFotografias.this, "Realiza primero la foto general", Toast.LENGTH_SHORT).show();
            }
        }
        if (v == btFotoOtros){
            if (hacerFoto3){
                if (!uriFoto3.equalsIgnoreCase("null")) {
                    File file = new File(uriFoto3);
                    file.delete();
                }
                uriFoto3 = Environment.getExternalStorageDirectory() + "/Pictures/" + "pic3_" + this.getCode() + ".jpg";
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                int code = FOTO_OTROS;
                Uri output = Uri.fromFile(new File(uriFoto3));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
                startActivityForResult(intent, code);
            }else{
                Toast.makeText(TomarFotografias.this, "Realiza primero las fotos general y neumaticos", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == FOTO_GENERAL) {
            if (data != null) {
                if (data.hasExtra("data")) {
                    ivFotoGeneral.setImageBitmap((Bitmap) data.getParcelableExtra("data"));
                }
            } else {
                ivFotoGeneral.setImageBitmap(BitmapFactory.decodeFile(uriFoto1));
                new MediaScannerConnection.MediaScannerConnectionClient() {
                    private MediaScannerConnection msc = null; {
                        msc = new MediaScannerConnection(getApplicationContext(), this); msc.connect();
                    }
                    public void onMediaScannerConnected() {
                        msc.scanFile(uriFoto1, null);
                    }
                    public void onScanCompleted(String path, Uri uri) {
                        msc.disconnect();
                    }
                };
            }
            hechaFoto1 = true;
            hacerFoto2 = true;
        }
        if (requestCode == FOTO_NEUMATICOS) {
            if (data != null) {
                if (data.hasExtra("data")) {
                    ivFotoNeumaticos.setImageBitmap((Bitmap) data.getParcelableExtra("data"));
                }
            } else {
                ivFotoNeumaticos.setImageBitmap(BitmapFactory.decodeFile(uriFoto2));
                new MediaScannerConnection.MediaScannerConnectionClient() {
                    private MediaScannerConnection msc = null; {
                        msc = new MediaScannerConnection(getApplicationContext(), this); msc.connect();
                    }
                    public void onMediaScannerConnected() {
                        msc.scanFile(uriFoto2, null);
                    }
                    public void onScanCompleted(String path, Uri uri) {
                        msc.disconnect();
                    }
                };
            }
            hacerFoto3 = true;
        }
        if (requestCode == FOTO_OTROS) {
            if (data != null) {
                if (data.hasExtra("data")) {
                    ivFotoOtros.setImageBitmap((Bitmap) data.getParcelableExtra("data"));
                }
            } else {
                ivFotoOtros.setImageBitmap(BitmapFactory.decodeFile(uriFoto3));
                new MediaScannerConnection.MediaScannerConnectionClient() {
                    private MediaScannerConnection msc = null; {
                        msc = new MediaScannerConnection(getApplicationContext(), this); msc.connect();
                    }
                    public void onMediaScannerConnected() {
                        msc.scanFile(uriFoto3, null);
                    }
                    public void onScanCompleted(String path, Uri uri) {
                        msc.disconnect();
                    }
                };
            }

        }
    }

    private void cancelar() {
        setResult(RESULT_CANCELED, null);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_atras_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try {
            int id = item.getItemId();

            if (id == R.id.atras) {
                cancelar();
                return true;
            }
            if (id == R.id.save) {
                //if (file.equalsIgnoreCase(" ")) {
                if (hechaFoto1) {
                    guardar();
                }else{
                    Toast.makeText(TomarFotografias.this, "Debe tomar al menos la foto general", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return super.onOptionsItemSelected(item);
    }

    private void guardar() throws ParserConfigurationException, SAXException, IOException {
        try {
            if (uriFoto2.equalsIgnoreCase("")){
                uriFoto2 = "null";
            }
            if (uriFoto3.equalsIgnoreCase("")){
                uriFoto3 = "null";
            }
            Fotografias fotografias = new Fotografias(this);
            fotografias.fotografiasXML(uriFoto1,uriFoto2,uriFoto3);

            ControlListas cl = new ControlListas(this);
            if (cl.listaActiva().equalsIgnoreCase("fotografias")) {
                cl.activarLocalizacionXML();
            }

            Intent i = new Intent(TomarFotografias.this, MenuDatos.class);
            startActivity(i);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        setResult(RESULT_OK);
        finish();
    }


    private void leerFotografiasXML() throws IOException, ParserConfigurationException, SAXException {
        //Obtenemos la referencia al fichero XML de entrada
        FileInputStream fil = openFileInput("fotografias.xml");


        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document dom = builder.parse(fil);

        Element raiz = dom.getDocumentElement();

        NodeList items = raiz.getElementsByTagName("fotografias");

        for(int i = 0; i<items.getLength();i++){
            Node nodoFografias = items.item(i);
            for(int j = 0; j < nodoFografias.getChildNodes().getLength(); j++){
                Node nodoFotografia = nodoFografias.getChildNodes().item(j);
                switch (nodoFotografia.getNodeName()) {
                    case "fotografia1":
                        uriFoto1 = nodoFotografia.getChildNodes().item(0).getNodeValue();
                        break;
                    case "fotografia2":
                        uriFoto2 = nodoFotografia.getChildNodes().item(0).getNodeValue();
                        break;
                    case "fotografia3":
                        uriFoto3 = nodoFotografia.getChildNodes().item(0).getNodeValue();
                        break;
                }

            }

        }

    }
}



