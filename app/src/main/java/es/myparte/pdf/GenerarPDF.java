package es.myparte.pdf;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import es.myparte.Main;
import es.myparte.R;
import es.myparte.clases.Parte;
import es.myparte.database.ParteDbAdapter;
import harmony.java.awt.Color;

public class GenerarPDF extends AppCompatActivity {

    private final static String NOMBRE_DIRECTORIO = "MiPdf";
    private final static String NOMBRE_DOCUMENTO = "parte.pdf";
    private final static String ETIQUETA_ERROR = "ERROR";

    private Parte parte = new Parte(this);
    private ImageView mostrar;
    private Image imagen3;
    private Image imagen2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_pdf);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        if (extra == null) return;

        mostrar = (ImageView) findViewById(R.id.idPdf);

        if (extra.containsKey(ParteDbAdapter.C_COLUMNA_ID)) {
            long id = extra.getLong(ParteDbAdapter.C_COLUMNA_ID);
            try {
                generarPdf(id);
            } catch (IOException | BadElementException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_atras_email, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent i;
        if (id == R.id.atras) {
            i = new Intent(GenerarPDF.this, Main.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.email) {
            sendEmail();
        }
        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void generarPdf(long id) throws IOException, BadElementException {
        try {
            parte = Parte.find(this, id);

        // Creamos el documento.
            Document doc = new Document(PageSize.A4, 10, 10, 10, 10);
            Image imagen1 = Image.getInstance(parte.getPar_foto1());
            String d = parte.getPar_foto2();
            if (!parte.getPar_foto2().equalsIgnoreCase("null")) {
                imagen2 = Image.getInstance(parte.getPar_foto2());
            }
            if (!parte.getPar_foto3().equalsIgnoreCase("null")) {
                imagen3 = Image.getInstance(parte.getPar_foto3());
            }

            doc.addAuthor("myParte");
            doc.addTitle("DECLARACION AMISTOSA DE ACCIDENTES");

            // Creamos el fichero con el nombre que deseemos.
            File f = crearFichero(NOMBRE_DOCUMENTO);

            // Creamos el flujo de datos de salida para el fichero donde
            // guardaremos el pdf.
            FileOutputStream ficheroPdf = new FileOutputStream(f.getAbsolutePath());

            // Asociamos el flujo que acabamos de crear al documento.
            PdfWriter writer = PdfWriter.getInstance(doc, ficheroPdf);


            // Incluimos el píe de página y una cabecera
            HeaderFooter cabecera = new HeaderFooter(new Phrase(
                    "DECLARACIÓN AMISTOSA DE ACCIDENTE"), false);
            HeaderFooter pie = new HeaderFooter(new Phrase(
                    "Autor:Jose Oscar Lozano Hernaiz"), false);

            doc.setHeader(cabecera);
            doc.setFooter(pie);

            // Abrimos el documento.////////////////////////////////////////
            doc.open();

            Font fuente= new Font();
            fuente.setSize(7);

            Font fuente12 = new Font();
            fuente12.setSize(12);
            fuente12.setColor(Color.BLUE);

            Font fuente8 = new Font();
            fuente8.setSize(8);
            fuente8.setColor(Color.RED);

            Paragraph p1 = new Paragraph("DATOS GENERALES",fuente12);
            Paragraph fecha = new Paragraph("FECHA",fuente);
            Paragraph localiza = new Paragraph("LOCALIZACION",fuente);
            Paragraph lugar1 = new Paragraph("LUGAR",fuente);
            Paragraph heridos = new Paragraph("HERIDOS",fuente);
            Paragraph atestado = new Paragraph("ATESTADOS",fuente);
            Paragraph codPos = new Paragraph("COD POSTAL",fuente);
            Paragraph poblacion = new Paragraph("POBLACION",fuente);
            Paragraph si = new Paragraph("SI",fuente);
            Paragraph no = new Paragraph("NO",fuente);

            Paragraph datoFecha = new Paragraph(parte.getPar_fecha(),fuente);
            Paragraph datoPais = new Paragraph(parte.getPar_pais(),fuente);
            Paragraph datoDireccion = new Paragraph(parte.getPar_direccion(),fuente);
            Paragraph datoCodPostal = new Paragraph(parte.getPar_codigoPostal(),fuente);
            Paragraph datoCiudad = new Paragraph(parte.getPar_ciudad(),fuente);
            Paragraph datoAtestado = new Paragraph(parte.getPar_atestado(),fuente);
            Paragraph vacio = new Paragraph("0",fuente);



            //p1.setAlignment(Paragraph.ALIGN_CENTER);
           // Font paraFont = new Font(Font.COURIER);

           // p1.setFont(paraFont);


            PdfPTable table = new PdfPTable(8);


            PdfPCell celdaInicio = new PdfPCell(p1);
            celdaInicio.setColspan(8);
            table.addCell(celdaInicio);

            ///////////////////////////////////////////// segunda fila
            PdfPCell fec = new PdfPCell(fecha);
            table.addCell(fec);

            PdfPCell loc = new PdfPCell(localiza);
            table.addCell(loc);

            PdfPCell lug = new PdfPCell(lugar1);
            table.addCell(lug);

            PdfPCell lugar = new PdfPCell(datoDireccion);
            lugar.setColspan(3);
            table.addCell(lugar);

            PdfPCell her = new PdfPCell(heridos);
            table.addCell(her);
            PdfPCell ate = new PdfPCell(atestado);
            table.addCell(ate);

            ///////////////////////////////////////////////// tercera fila
            PdfPCell dfec = new PdfPCell(datoFecha);
            table.addCell(dfec);

            PdfPCell pas = new PdfPCell(datoPais);
            table.addCell(pas);

            PdfPCell cp = new PdfPCell(codPos);
            table.addCell(cp);

            PdfPCell datoCp = new PdfPCell(datoCodPostal);
            table.addCell(datoCp);

            PdfPCell pob = new PdfPCell(poblacion);
            table.addCell(pob);

            PdfPCell datoPob = new PdfPCell(datoCiudad);
            table.addCell(datoPob);

            if (parte.getPar_herido().equalsIgnoreCase("false")){
                PdfPCell nos = new PdfPCell(no);
                table.addCell(nos);
            }else{
                PdfPCell sis = new PdfPCell(si);
                table.addCell(sis);
            }

            if (!parte.getPar_atestado().equalsIgnoreCase("null")){
                PdfPCell datoAtes = new PdfPCell(datoAtestado);
                table.addCell(datoAtes);
            }else{
                PdfPCell datoVacio = new PdfPCell(vacio);
                table.addCell(datoVacio);
            }

            doc.add(table);

            Paragraph p01 = new Paragraph(" ");
            doc.add(p01);

            /////////////////////////////////////  tabla vehiculo A

            PdfPTable table1 = new PdfPTable(2);

            Paragraph vehiculoA = new Paragraph("VEHICULO A",fuente12);
            Paragraph asegurad = new Paragraph("ASEGURADO",fuente8);
            Paragraph vehiculo = new Paragraph("VEHICULO",fuente8);
            Paragraph seguro = new Paragraph("ASEGURADORA",fuente8);
            Paragraph conductor = new Paragraph("CONDUCTOR",fuente8);
            Paragraph impactos = new Paragraph("DAÑOS",fuente8);
            Paragraph cirucunstancias = new Paragraph("CIRCUNSTACIAS",fuente8);
            Paragraph nombre = new Paragraph("NOMBRE:",fuente);
            Paragraph email = new Paragraph("EMAIL:",fuente);
            Paragraph telefono = new Paragraph("TELEFONO:",fuente);
            Paragraph matricula = new Paragraph("MATRICULA:", fuente);
            Paragraph marca = new Paragraph("MARCA:", fuente);
            Paragraph modelo = new Paragraph("MODELO:", fuente);
            Paragraph remolque = new Paragraph("REMOLQUE:",fuente);
            Paragraph matriRemol = new Paragraph("MAT.REMOLQUE",fuente);
            Paragraph impacto = new Paragraph("IMPACTOS:", fuente);
            Paragraph circunstacias = new Paragraph("CIRCUNSTACIAS:",fuente);


            Paragraph dNomAseA = new Paragraph(parte.getPar_aseNombreA(),fuente);
            Paragraph dEmaAseA = new Paragraph(parte.getPar_aseEmailA(),fuente);
            Paragraph dTlfAseA = new Paragraph(parte.getPar_aseTelefonoA(),fuente);
            Paragraph datoMatriculaA = new Paragraph(parte.getPar_vehMatriculaA(),fuente);
            Paragraph datoMarcaA = new Paragraph(parte.getPar_vehMarcaA(),fuente);
            Paragraph datoModeloA = new Paragraph(parte.getPar_vehModeloA(),fuente);
            Paragraph datoRemolqueA = new Paragraph(parte.getPar_vehRemolqueA(),fuente);
            Paragraph datoMatRemoqA = new Paragraph(parte.getPar_vehMatRemolA(),fuente);
            Paragraph datoNomSegA = new Paragraph(parte.getPar_segNombreA(),fuente);
            Paragraph datoEmailSegA = new Paragraph(parte.getPar_segEmailA(),fuente);
            Paragraph datoTlfSegA = new Paragraph(parte.getPar_segTelefonoA(),fuente);
            Paragraph datoNomConA = new Paragraph(parte.getPar_conNombreA(),fuente);
            Paragraph datoEmailConA = new Paragraph(parte.getPar_conEmailA(),fuente);
            Paragraph datoTlfConA = new Paragraph(parte.getPar_conTelefonoA(),fuente);
            Paragraph datoImpA = new Paragraph(parte.getPar_danosA(),fuente);
            Paragraph datoCirA = new Paragraph(parte.getPar_circunstanciasA(),fuente);




            /////////////////////////////////////////////////////////////// fila 1
            PdfPCell celdaVehA = new PdfPCell(vehiculoA);
            celdaVehA.setColspan(2);
            table1.addCell(celdaVehA);

            /////////////////////////////////////////// fila 2 ASEGURADO

            PdfPCell aseA = new PdfPCell(asegurad);
            aseA.setColspan(2);
            table1.addCell(aseA);

            ////////////////////////////////////////// fila 3

            PdfPCell nom = new PdfPCell(nombre);
            table1.addCell(nom);

            PdfPCell dAseNomA = new PdfPCell(dNomAseA);
            table1.addCell(dAseNomA);

            /// fila 4

            PdfPCell ema = new PdfPCell(email);
            table1.addCell(ema);

            PdfPCell dAseEmaA = new PdfPCell(dEmaAseA);
            table1.addCell(dAseEmaA);

            // fila 5

            PdfPCell tlf = new PdfPCell(telefono);
            table1.addCell(tlf);

            PdfPCell dAseTlfA = new PdfPCell(dTlfAseA);
            table1.addCell(dAseNomA);

            // fila 6  VEHICULO

            PdfPCell vheA = new PdfPCell(vehiculo);
            vheA.setColspan(2);
            table1.addCell(vheA);

            // fila 7

            PdfPCell mat = new PdfPCell(matricula);
            table1.addCell(mat);

            PdfPCell dMatricula  = new PdfPCell(datoMatriculaA);
            table1.addCell(dMatricula);

            //fila 8

            PdfPCell mar = new PdfPCell(marca);
            table1.addCell(mar);

            PdfPCell dMarca = new PdfPCell(datoMarcaA);
            table1.addCell(dMarca);

            //fila 9

            PdfPCell mod = new PdfPCell(modelo);
            table1.addCell(mod);

            PdfPCell dMod = new PdfPCell(datoModeloA);
            table1.addCell(dMod);

            //fila 10

            PdfPCell rem = new PdfPCell(remolque);
            table1.addCell(rem);

            PdfPCell drem = new PdfPCell(datoRemolqueA);
            table1.addCell(drem);

            //fila 10

            PdfPCell mrem = new PdfPCell(matriRemol);
            table1.addCell(mrem);

            PdfPCell dMrem = new PdfPCell(datoMatRemoqA);
            table1.addCell(dMrem);

            //////////// fila 11 SEGURO

            PdfPCell segA = new PdfPCell(seguro);
            segA.setColspan(2);
            table1.addCell(segA);

            /////////// fila 12

            PdfPCell nomseg = new PdfPCell(nombre);
            table1.addCell(nomseg);

            PdfPCell dNomSeg = new PdfPCell(datoNomSegA);
            table1.addCell(dNomSeg);

            /// fila 13

            PdfPCell segEmail = new PdfPCell(email);
            table1.addCell(segEmail);

            PdfPCell dEmailSeg = new PdfPCell(datoEmailSegA);
            table1.addCell(dEmailSeg);

            // fila 14

            PdfPCell segtlf = new PdfPCell(telefono);
            table1.addCell(segtlf);

            PdfPCell dTlfSeg = new PdfPCell(datoTlfSegA);
            table1.addCell(dTlfSeg);

            //////////// fila 15 CONDUCTOR

            PdfPCell conA = new PdfPCell(conductor);
            conA.setColspan(2);
            table1.addCell(conA);

            /////////// fila 16

            PdfPCell nomcon = new PdfPCell(nombre);
            table1.addCell(nomcon);

            PdfPCell dNomCon = new PdfPCell(datoNomConA);
            table1.addCell(dNomCon);

            /// fila 17

            PdfPCell conEmail = new PdfPCell(email);
            table1.addCell(conEmail);

            PdfPCell dEmailCon = new PdfPCell(datoEmailConA);
            table1.addCell(dEmailCon);

            // fila 18

            PdfPCell contlf = new PdfPCell(telefono);
            table1.addCell(contlf);

            PdfPCell dTlfCon = new PdfPCell(datoTlfConA);
            table1.addCell(dTlfCon);

            //////////// fila 19 IMPACTOS

            PdfPCell imp = new PdfPCell(impactos);
            imp.setColspan(2);
            table1.addCell(imp);

            /////////// fila 20

            PdfPCell impa = new PdfPCell(datoImpA);
            impa.setColspan(2);
            table1.addCell(impa);

            //////////// fila 21 IMPACTOS

            PdfPCell cir = new PdfPCell(cirucunstancias);
            cir.setColspan(2);
            table1.addCell(cir);

            /////////// fila 22

            PdfPCell cira = new PdfPCell(datoCirA);
            cira.setColspan(2);
            table1.addCell(cira);

            doc.add(table1);

            Paragraph p21 = new Paragraph(" ");
            doc.add(p21);

/////////////////////////////////////  tabla vehiculo B

            PdfPTable table2 = new PdfPTable(2);

            Paragraph vehiculoB = new Paragraph("VEHICULO B",fuente12);

            Paragraph dNomAseB = new Paragraph(parte.getPar_aseNombreB(),fuente);
            Paragraph dEmaAseB = new Paragraph(parte.getPar_aseEmailB(),fuente);
            Paragraph dTlfAseB = new Paragraph(parte.getPar_aseTelefonoB(),fuente);
            Paragraph datoMatriculaB = new Paragraph(parte.getPar_vehMatriculaB(),fuente);
            Paragraph datoMarcaB = new Paragraph(parte.getPar_vehMarcaB(),fuente);
            Paragraph datoModeloB = new Paragraph(parte.getPar_vehModeloB(),fuente);
            Paragraph datoRemolqueB = new Paragraph(parte.getPar_vehRemolqueB(),fuente);
            Paragraph datoMatRemoqB = new Paragraph(parte.getPar_vehMatRemolB(),fuente);
            Paragraph datoNomSegB = new Paragraph(parte.getPar_segNombreB(),fuente);
            Paragraph datoEmailSegB = new Paragraph(parte.getPar_segEmailB(),fuente);
            Paragraph datoTlfSegB = new Paragraph(parte.getPar_segTelefonoB(),fuente);
            Paragraph datoNomConB = new Paragraph(parte.getPar_conNombreB(),fuente);
            Paragraph datoEmailConB = new Paragraph(parte.getPar_conEmailB(),fuente);
            Paragraph datoTlfConB = new Paragraph(parte.getPar_conTelefonoB(),fuente);
            Paragraph datoImpB = new Paragraph(parte.getPar_danosB(),fuente);
            Paragraph datoCirB = new Paragraph(parte.getPar_circunstanciasB(),fuente);

            /////////////////////////////////////////////////////////////// fila 1
            PdfPCell celdaVehB = new PdfPCell(vehiculoB);
            celdaVehB.setColspan(2);
            table2.addCell(celdaVehB);

            /////////////////////////////////////////// fila 2 ASEGURADO

            PdfPCell aseB = new PdfPCell(asegurad);
            aseB.setColspan(2);
            table2.addCell(aseB);

            ////////////////////////////////////////// fila 3

            PdfPCell nomB = new PdfPCell(nombre);
            table2.addCell(nomB);

            PdfPCell dAseNomB = new PdfPCell(dNomAseB);
            table2.addCell(dAseNomB);

            /// fila 4

            PdfPCell emaB = new PdfPCell(email);
            table2.addCell(emaB);

            PdfPCell dAseEmaB = new PdfPCell(dEmaAseB);
            table2.addCell(dAseEmaB);

            // fila 5

            PdfPCell tlfB = new PdfPCell(telefono);
            table2.addCell(tlfB);

            PdfPCell dAseTlfB = new PdfPCell(dTlfAseB);
            table2.addCell(dAseNomB);

            // fila 6  VEHICULO

            PdfPCell vheB = new PdfPCell(vehiculo);
            vheB.setColspan(2);
            table2.addCell(vheB);

            // fila 7

            PdfPCell matB = new PdfPCell(matricula);
            table2.addCell(matB);

            PdfPCell dMatriculaB  = new PdfPCell(datoMatriculaB);
            table2.addCell(dMatriculaB);

            //fila 8

            PdfPCell marB = new PdfPCell(marca);
            table2.addCell(marB);

            PdfPCell dMarcaB = new PdfPCell(datoMarcaB);
            table2.addCell(dMarcaB);

            //fila 9

            PdfPCell modB = new PdfPCell(modelo);
            table2.addCell(modB);

            PdfPCell dModB = new PdfPCell(datoModeloB);
            table2.addCell(dModB);

            //fila 10

            PdfPCell remB = new PdfPCell(remolque);
            table2.addCell(remB);

            PdfPCell dremB = new PdfPCell(datoRemolqueB);
            table2.addCell(dremB);

            //fila 10

            PdfPCell mremB = new PdfPCell(matriRemol);
            table2.addCell(mremB);

            PdfPCell dMremB = new PdfPCell(datoMatRemoqB);
            table2.addCell(dMremB);

            //////////// fila 11 SEGURO

            PdfPCell segB = new PdfPCell(seguro);
            segB.setColspan(2);
            table2.addCell(segB);

            /////////// fila 12

            PdfPCell nomsegB = new PdfPCell(nombre);
            table2.addCell(nomsegB);

            PdfPCell dNomSegB = new PdfPCell(datoNomSegB);
            table2.addCell(dNomSegB);

            /// fila 13

            PdfPCell segEmailB = new PdfPCell(email);
            table2.addCell(segEmailB);

            PdfPCell dEmailSegB = new PdfPCell(datoEmailSegB);
            table2.addCell(dEmailSegB);

            // fila 14

            PdfPCell segtlfB = new PdfPCell(telefono);
            table2.addCell(segtlfB);

            PdfPCell dTlfSegB = new PdfPCell(datoTlfSegB);
            table2.addCell(dTlfSegB);

            //////////// fila 15 CONDUCTOR

            PdfPCell conB = new PdfPCell(conductor);
            conB.setColspan(2);
            table2.addCell(conB);

            /////////// fila 16

            PdfPCell nomconB = new PdfPCell(nombre);
            table2.addCell(nomconB);

            PdfPCell dNomConB = new PdfPCell(datoNomConB);
            table2.addCell(dNomConB);

            /// fila 17

            PdfPCell conEmailB = new PdfPCell(email);
            table2.addCell(conEmailB);

            PdfPCell dEmailConB = new PdfPCell(datoEmailConB);
            table2.addCell(dEmailConB);

            // fila 18

            PdfPCell contlfB = new PdfPCell(telefono);
            table2.addCell(contlfB);

            PdfPCell dTlfConB = new PdfPCell(datoTlfConB);
            table2.addCell(dTlfConB);

            //////////// fila 19 IMPACTOS

            PdfPCell impB = new PdfPCell(impactos);
            impB.setColspan(2);
            table2.addCell(impB);

            /////////// fila 20

            PdfPCell impaB = new PdfPCell(datoImpB);
            impaB.setColspan(2);
            table2.addCell(impaB);

            //////////// fila 21 IMPACTOS

            PdfPCell cirB = new PdfPCell(cirucunstancias);
            cirB.setColspan(2);
            table2.addCell(cirB);

            /////////// fila 22

            PdfPCell ciraB = new PdfPCell(datoCirB);
            ciraB.setColspan(2);
            table2.addCell(ciraB);

            doc.add(table2);

            Paragraph p03 = new Paragraph(" ");
            doc.add(p03);

            PdfPTable table3 = new PdfPTable(3);

            imagen1.setAlignment(Element.ALIGN_CENTER);
            imagen1.scalePercent(20);
            table3.addCell(imagen1);

            if (parte.getPar_foto2().equalsIgnoreCase("null")) {
                table3.addCell("No foto");
            }else{
                imagen2.setAlignment(Element.ALIGN_CENTER);
                imagen2.scalePercent(20);
                table3.addCell(imagen2);
            }

            if (parte.getPar_foto3().equalsIgnoreCase("null")){
                table3.addCell("No foto");
            }else{
                imagen3.setAlignment(Element.ALIGN_CENTER);
                imagen3.scalePercent(20);
                table3.addCell(imagen3);
            }
            doc.add(table3);

            //pie pagina
            Phrase footerText = new Phrase("Pie de página.");
            HeaderFooter pdfFooter = new HeaderFooter(footerText, false);
            doc.setFooter(pdfFooter);

            doc.close();

        } catch (DocumentException | IOException e) {

            Log.e(ETIQUETA_ERROR, e.getMessage());

        } finally {




            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    + "/" + NOMBRE_DIRECTORIO + "/" + NOMBRE_DOCUMENTO);

            if (file.exists()) {

                int currentPage = 0;
                int x = 600;
                int y = 850;

                Bitmap bitmap = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_4444);
                currentPage = 0;
                PdfRenderer renderer = null;

                try {
                    renderer = new PdfRenderer(ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY));
                    android.graphics.Matrix m = mostrar.getImageMatrix();
                    Rect r = new Rect(0, 0, x, y);
                    renderer.openPage(currentPage).render(bitmap, r, m, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
                    mostrar.setImageMatrix(m);
                    mostrar.setImageBitmap(bitmap);
                    mostrar.invalidate();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
    }


    public static File crearFichero(String nombreFichero) throws IOException {
        File ruta = getRuta();
        File fichero = null;
        if (ruta != null)
            fichero = new File(ruta, nombreFichero);

        return fichero;
    }


    public static File getRuta() {
        File ruta = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            ruta = new File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    NOMBRE_DIRECTORIO);

            if (ruta != null) {
                if (!ruta.mkdirs()) {
                    if (!ruta.exists()) {
                        return null;
                    }
                }
            }
        } else {
        }
        return ruta;
    }

    public void sendEmail() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                + "/" + NOMBRE_DIRECTORIO + "/" + NOMBRE_DOCUMENTO);
        if (file.exists()) {
            Uri pdfUri = Uri.fromFile(file);
            Intent s = new Intent(Intent.ACTION_SEND);
            s.setType("message/rfc822");
            s.putExtra(Intent.EXTRA_STREAM, pdfUri);
            s.putExtra(Intent.EXTRA_EMAIL, new String[]{parte.getPar_aseEmailA(),
                                                        parte.getPar_aseEmailB(),
                                                        parte.getPar_segEmailA(),
                                                        parte.getPar_segEmailB()});
            s.putExtra(Intent.EXTRA_SUBJECT, R.string.asusnto_email);
            s.putExtra(Intent.EXTRA_TEXT, R.string.texto_email);

            try {
                startActivity(Intent.createChooser(s, "send"));
            } catch (ActivityNotFoundException e) {
                Toast.makeText(GenerarPDF.this, R.string.error_email, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(GenerarPDF.this, R.string.error_email_fichero, Toast.LENGTH_SHORT).show();
        }

    }


}

