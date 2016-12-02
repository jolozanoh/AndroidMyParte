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
public class VehiculoA {
    Context context;

    public VehiculoA(Context context) {
        this.context = context;
    }

    public void crearVehiculoAXML() throws IOException, ParserConfigurationException, SAXException, TransformerConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            // raiz elementos
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Class");
            doc.appendChild(rootElement);

            // staff elements
            Element vehiA = doc.createElement("vehiculoA");
            rootElement.appendChild(vehiA);


            // firstname elements
            Element matriculas = doc.createElement("matricula");
            vehiA.appendChild(matriculas);
            matriculas.appendChild(doc.createTextNode("null"));

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();


            Transformer transformer = transformerFactory.newTransformer();


            DOMSource source = new DOMSource(doc);
            FileOutputStream _stream = context.getApplicationContext().openFileOutput("vehiculoA.xml", context.getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result = new StreamResult(_stream);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void vehiculoAXML(
            String matricula , String marca, String modelo, String remolque, String matriculaRemolque)
            throws IOException, ParserConfigurationException, SAXException, TransformerConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            // raiz elementos
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Class");
            doc.appendChild(rootElement);

            // staff elements
            Element vehiculoA = doc.createElement("vehiculoA");
            rootElement.appendChild(vehiculoA);


            // firstname elements
            Element matriculaA = doc.createElement("matricula");
            vehiculoA.appendChild(matriculaA);
            matriculaA.appendChild(doc.createTextNode(matricula));
            Element marcaA = doc.createElement("marca");
            vehiculoA.appendChild(marcaA);
            marcaA.appendChild(doc.createTextNode(marca));
            Element modeloA = doc.createElement("modelo");
            vehiculoA.appendChild(modeloA);
            modeloA.appendChild(doc.createTextNode(modelo));

            Element remolqueA = doc.createElement("remolque");
            vehiculoA.appendChild(remolqueA);
            remolqueA.appendChild(doc.createTextNode(remolque));
            Element matriculaRemolqueA = doc.createElement("matriculaRemolque");
            vehiculoA.appendChild(matriculaRemolqueA);
            matriculaRemolqueA.appendChild(doc.createTextNode(matriculaRemolque));

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();


            Transformer transformer = transformerFactory.newTransformer();


            DOMSource source = new DOMSource(doc);
            FileOutputStream _stream = context.getApplicationContext().openFileOutput("vehiculoA.xml", context.getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result = new StreamResult(_stream);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }
}
