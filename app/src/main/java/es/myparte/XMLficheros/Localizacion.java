package es.myparte.XMLficheros;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by oscarlozanohernaiz.
 */
public class Localizacion {
    private Context context;

    public Localizacion(Context context) {
        this.context = context;
    }

    public void crearlocalizacionXML()
            throws IOException, ParserConfigurationException, SAXException, TransformerConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            // raiz elementos
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Class");
            doc.appendChild(rootElement);


            Element localizacion = doc.createElement("localizacion");
            rootElement.appendChild(localizacion);

            Element fechaG = doc.createElement("fecha");
            localizacion.appendChild(fechaG);
            fechaG.appendChild(doc.createTextNode("null"));

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(doc);
            FileOutputStream _stream = context.getApplicationContext().openFileOutput("localizacion.xml", context.getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result = new StreamResult(_stream);
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }

    }



    public void localizacionXML(
            String fecha, String direccion, String ciudad, String codPos, String pais, String atestado,
            String herido , String distintosAB , String objetos,  String testigos, String nombreTestigo, String telefonoTestigo,
            String latitud1, String longitud1)
            throws IOException, ParserConfigurationException, SAXException, TransformerConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            // raiz elementos
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Class");
            doc.appendChild(rootElement);

            Element localizacion = doc.createElement("localizacion");
            rootElement.appendChild(localizacion);


            Element fechaG = doc.createElement("fecha");
            Element direccionG = doc.createElement("direccion");
            Element ciudadG = doc.createElement("ciudad");
            Element codPosG = doc.createElement("codigoPostal");
            Element paisG = doc.createElement("pais");
            Element atestadoG = doc.createElement("atestado");
            Element heridoG = doc.createElement("herido");
            Element distintosABG = doc.createElement("distintosAB");
            Element objetosG = doc.createElement("objetosA");
            Element testigosG = doc.createElement("testigos");
            Element nombreTes = doc.createElement("nombreTestigo");
            Element telefonoTes = doc.createElement("telefonoTestigo");
            Element latitud = doc.createElement("latitud");
            Element longitud = doc.createElement("longitud");

            localizacion.appendChild(fechaG);
            fechaG.appendChild(doc.createTextNode(fecha));

            localizacion.appendChild(direccionG);
            direccionG.appendChild(doc.createTextNode(direccion));

            localizacion.appendChild(ciudadG);
            ciudadG.appendChild(doc.createTextNode(ciudad));

            localizacion.appendChild(codPosG);
            codPosG.appendChild(doc.createTextNode(codPos));

            localizacion.appendChild(paisG);
            paisG.appendChild(doc.createTextNode(pais));

            localizacion.appendChild(atestadoG);
            atestadoG.appendChild(doc.createTextNode(atestado));

            localizacion.appendChild(heridoG);
            heridoG.appendChild(doc.createTextNode(herido));

            localizacion.appendChild(distintosABG);
            distintosABG.appendChild(doc.createTextNode(distintosAB));

            localizacion.appendChild(objetosG);
            objetosG.appendChild(doc.createTextNode(objetos));

            localizacion.appendChild(testigosG);
            testigosG.appendChild(doc.createTextNode(testigos));

            localizacion.appendChild(nombreTes);
            nombreTes.appendChild(doc.createTextNode(nombreTestigo));

            localizacion.appendChild(telefonoTes);
            telefonoTes.appendChild(doc.createTextNode(telefonoTestigo));

            localizacion.appendChild(latitud);
            latitud.appendChild(doc.createTextNode(latitud1));

            localizacion.appendChild(longitud);
            longitud.appendChild(doc.createTextNode(longitud1));



            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();


            Transformer transformer = transformerFactory.newTransformer();


            DOMSource source = new DOMSource(doc);
            FileOutputStream _stream = context.getApplicationContext().openFileOutput("localizacion.xml", context.getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result = new StreamResult(_stream);
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException e) {

            e.printStackTrace();
        }

    }
}
