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
public class ImpactosA {
    private Context context;

    public ImpactosA(Context context) {
        this.context = context;
    }

    public void crearImpactosAXML()
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
            Element impactos = doc.createElement("impactosA");
            rootElement.appendChild(impactos);

            // firstname elements
            Element impA = doc.createElement("impactosA");
            impactos.appendChild(impA);
            impA.appendChild(doc.createTextNode("000000000000"));


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();


            Transformer transformer = transformerFactory.newTransformer();


            DOMSource source = new DOMSource(doc);
            FileOutputStream _stream = context.getApplicationContext().openFileOutput("impactosA.xml", context.getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result = new StreamResult(_stream);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }


    public void impactosAXML(
            String impacto)
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
            Element impactos = doc.createElement("impactosA");
            rootElement.appendChild(impactos);

            // firstname elements
            Element impA = doc.createElement("impactosA");
            impactos.appendChild(impA);
            impA.appendChild(doc.createTextNode(impacto));


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();


            Transformer transformer = transformerFactory.newTransformer();


            DOMSource source = new DOMSource(doc);
            FileOutputStream _stream = context.getApplicationContext().openFileOutput("impactosA.xml", context.getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result = new StreamResult(_stream);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }
}
