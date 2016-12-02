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
public class Fotografias {
    private Context context;

    public Fotografias(Context context) {
        this.context = context;
    }

    public void crearfotografiasXML()
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
            Element fotografias = doc.createElement("fotografias");
            rootElement.appendChild(fotografias);

            // firstname elements
            Element foto1 = doc.createElement("fotografia1");
            fotografias.appendChild(foto1);
            foto1.appendChild(doc.createTextNode("null"));

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();


            Transformer transformer = transformerFactory.newTransformer();


            DOMSource source = new DOMSource(doc);
            FileOutputStream _stream = context.getApplicationContext().openFileOutput("fotografias.xml", context.getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result = new StreamResult(_stream);
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    public void fotografiasXML(
            String fotografia1, String fotografia2, String fotografia3)
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
            Element fotografias = doc.createElement("fotografias");
            rootElement.appendChild(fotografias);

            // firstname elements
            Element foto1 = doc.createElement("fotografia1");
            fotografias.appendChild(foto1);
            foto1.appendChild(doc.createTextNode(fotografia1));
            Element foto2 = doc.createElement("fotografia2");
            fotografias.appendChild(foto2);
            foto2.appendChild(doc.createTextNode(fotografia2));
            Element foto3 = doc.createElement("fotografia3");
            fotografias.appendChild(foto3);
            foto3.appendChild(doc.createTextNode(fotografia3));

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();


            Transformer transformer = transformerFactory.newTransformer();


            DOMSource source = new DOMSource(doc);
            FileOutputStream _stream = context.getApplicationContext().openFileOutput("fotografias.xml", context.getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result = new StreamResult(_stream);
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }

    }

}
