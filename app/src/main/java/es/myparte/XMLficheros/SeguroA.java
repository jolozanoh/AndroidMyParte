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
public class SeguroA {
    Context context;

    public SeguroA(Context context) {
        this.context = context;
    }

    public void crearAseguradoraAXML() throws IOException, ParserConfigurationException, SAXException, TransformerConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            // raiz elementos
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Class");
            doc.appendChild(rootElement);

            // staff elements
            Element aseguradoA = doc.createElement("aseguradoraA");
            rootElement.appendChild(aseguradoA);


            // firstname elements
            Element nombre = doc.createElement("nombre");
            aseguradoA.appendChild(nombre);
            nombre.appendChild(doc.createTextNode("null"));

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();


            Transformer transformer = transformerFactory.newTransformer();


            DOMSource source = new DOMSource(doc);
            FileOutputStream _stream = context.getApplicationContext().openFileOutput("aseguradoraA.xml", context.getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result = new StreamResult(_stream);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void aseguradoraAXML(String nombre , String correo, String telefono)
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
            Element aseguradoA = doc.createElement("aseguradoraA");
            rootElement.appendChild(aseguradoA);


            // firstname elements
            Element nombreA = doc.createElement("nombre");
            aseguradoA.appendChild(nombreA);
            nombreA.appendChild(doc.createTextNode(nombre));
            Element correoA = doc.createElement("email");
            aseguradoA.appendChild(correoA);
            correoA.appendChild(doc.createTextNode(correo));
            Element telefonoA = doc.createElement("telefono");
            aseguradoA.appendChild(telefonoA);
            telefonoA.appendChild(doc.createTextNode(telefono));

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();


            Transformer transformer = transformerFactory.newTransformer();


            DOMSource source = new DOMSource(doc);
            FileOutputStream _stream = context.getApplicationContext().openFileOutput("aseguradoraA.xml", context.getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result = new StreamResult(_stream);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }
}
