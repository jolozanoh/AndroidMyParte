package es.myparte.XMLficheros;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
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
public class ControlListas {
    private Context context;

    public ControlListas(Context context) {
        this.context = context;
    }

    public void crearListasXML() throws IOException, ParserConfigurationException, SAXException, TransformerConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            // raiz elementos
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Class");
            doc.appendChild(rootElement);

            // staff elements
            Element pantallas = doc.createElement("pantallas");
            rootElement.appendChild(pantallas);


            // firstname elements
            Element fotografias = doc.createElement("fotografias");

            pantallas.appendChild(fotografias);

            fotografias.appendChild(doc.createTextNode("true"));

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();


            Transformer transformer = transformerFactory.newTransformer();


            DOMSource source = new DOMSource(doc);
            FileOutputStream _stream = context.getApplicationContext().openFileOutput("pantallas.xml", context.getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result = new StreamResult(_stream);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void activarLocalizacionXML() throws IOException, ParserConfigurationException, SAXException, TransformerConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            // raiz elementos
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Class");
            doc.appendChild(rootElement);

            // staff elements
            Element pantallas = doc.createElement("pantallas");
            rootElement.appendChild(pantallas);


            // firstname elements
            Element fotografias = doc.createElement("fotografias");
            pantallas.appendChild(fotografias);
            fotografias.appendChild(doc.createTextNode("true"));

            Element localizacion = doc.createElement("localizacion");
            pantallas.appendChild(localizacion);
            localizacion.appendChild(doc.createTextNode("true"));

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();


            Transformer transformer = transformerFactory.newTransformer();


            DOMSource source = new DOMSource(doc);
            FileOutputStream _stream = context.getApplicationContext().openFileOutput("pantallas.xml", context.getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result = new StreamResult(_stream);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }



    public void activarAseguradoAXML() throws IOException, ParserConfigurationException, SAXException, TransformerConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            // raiz elementos
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Class");
            doc.appendChild(rootElement);

            // staff elements
            Element pantallas = doc.createElement("pantallas");
            rootElement.appendChild(pantallas);


            // firstname elements
            Element fotografias = doc.createElement("fotografias");
            pantallas.appendChild(fotografias);
            fotografias.appendChild(doc.createTextNode("true"));

            Element localizacion = doc.createElement("localizacion");
            pantallas.appendChild(localizacion);
            localizacion.appendChild(doc.createTextNode("true"));

            Element vehiA = doc.createElement("vehiculoA");
            pantallas.appendChild(vehiA);


            Element aseg = doc.createElement("aseguradoA");
            vehiA.appendChild(aseg);
            aseg.appendChild(doc.createTextNode("true"));

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();


            Transformer transformer = transformerFactory.newTransformer();


            DOMSource source = new DOMSource(doc);
            FileOutputStream _stream = context.getApplicationContext().openFileOutput("pantallas.xml", context.getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result = new StreamResult(_stream);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void activarConductorAXML() throws IOException, ParserConfigurationException, SAXException, TransformerConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            // raiz elementos
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Class");
            doc.appendChild(rootElement);

            // staff elements
            Element pantallas = doc.createElement("pantallas");
            rootElement.appendChild(pantallas);


            // firstname elements
            Element fotografias = doc.createElement("fotografias");
            pantallas.appendChild(fotografias);
            fotografias.appendChild(doc.createTextNode("true"));

            Element localizacion = doc.createElement("localizacion");
            pantallas.appendChild(localizacion);
            localizacion.appendChild(doc.createTextNode("true"));

            Element vehiA = doc.createElement("vehiculoA");
            pantallas.appendChild(vehiA);


            Element aseg = doc.createElement("aseguradoA");
            vehiA.appendChild(aseg);
            aseg.appendChild(doc.createTextNode("true"));

            Element ve = doc.createElement("vehiculoA");
            vehiA.appendChild(ve);
            ve.appendChild(doc.createTextNode("true"));

            Element ase = doc.createElement("aseguradoraA");
            vehiA.appendChild(ase);
            ase.appendChild(doc.createTextNode("true"));

            Element con = doc.createElement("conductorA");
            vehiA.appendChild(con);
            con.appendChild(doc.createTextNode("true"));

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();


            Transformer transformer = transformerFactory.newTransformer();


            DOMSource source = new DOMSource(doc);
            FileOutputStream _stream = context.getApplicationContext().openFileOutput("pantallas.xml", context.getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result = new StreamResult(_stream);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void activarVehiculoAXML() throws IOException, ParserConfigurationException, SAXException, TransformerConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            // raiz elementos
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Class");
            doc.appendChild(rootElement);

            // staff elements
            Element pantallas = doc.createElement("pantallas");
            rootElement.appendChild(pantallas);


            // firstname elements
            Element fotografias = doc.createElement("fotografias");
            pantallas.appendChild(fotografias);
            fotografias.appendChild(doc.createTextNode("true"));

            Element localizacion = doc.createElement("localizacion");
            pantallas.appendChild(localizacion);
            localizacion.appendChild(doc.createTextNode("true"));

            Element vehiA = doc.createElement("vehiculoA");
            pantallas.appendChild(vehiA);

            Element aseg = doc.createElement("aseguradoA");
            vehiA.appendChild(aseg);
            aseg.appendChild(doc.createTextNode("true"));

            Element vehi = doc.createElement("vehiculoA");
            vehiA.appendChild(vehi);
            vehi.appendChild(doc.createTextNode("true"));

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();


            Transformer transformer = transformerFactory.newTransformer();


            DOMSource source = new DOMSource(doc);
            FileOutputStream _stream = context.getApplicationContext().openFileOutput("pantallas.xml", context.getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result = new StreamResult(_stream);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void activarAseguradorasAXML() throws IOException, ParserConfigurationException, SAXException, TransformerConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            // raiz elementos
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Class");
            doc.appendChild(rootElement);

            // staff elements
            Element pantallas = doc.createElement("pantallas");
            rootElement.appendChild(pantallas);


            // firstname elements
            Element fotografias = doc.createElement("fotografias");
            pantallas.appendChild(fotografias);
            fotografias.appendChild(doc.createTextNode("true"));

            Element localizacion = doc.createElement("localizacion");
            pantallas.appendChild(localizacion);
            localizacion.appendChild(doc.createTextNode("true"));

            Element vehiA = doc.createElement("vehiculoA");
            pantallas.appendChild(vehiA);

            Element aseg = doc.createElement("aseguradoA");
            vehiA.appendChild(aseg);
            aseg.appendChild(doc.createTextNode("true"));

            Element vehi = doc.createElement("vehiculoA");
            vehiA.appendChild(vehi);
            vehi.appendChild(doc.createTextNode("true"));

            Element asegura = doc.createElement("aseguradoraA");
            vehiA.appendChild(asegura);
            asegura.appendChild(doc.createTextNode("true"));

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();


            Transformer transformer = transformerFactory.newTransformer();


            DOMSource source = new DOMSource(doc);
            FileOutputStream _stream = context.getApplicationContext().openFileOutput("pantallas.xml", context.getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result = new StreamResult(_stream);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void activarImpactosAXML() throws IOException, ParserConfigurationException, SAXException, TransformerConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            // raiz elementos
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Class");
            doc.appendChild(rootElement);

            // staff elements
            Element pantallas = doc.createElement("pantallas");
            rootElement.appendChild(pantallas);


            // firstname elements
            Element fotografias = doc.createElement("fotografias");
            pantallas.appendChild(fotografias);
            fotografias.appendChild(doc.createTextNode("true"));

            Element localizacion = doc.createElement("localizacion");
            pantallas.appendChild(localizacion);
            localizacion.appendChild(doc.createTextNode("true"));

            Element vehiA = doc.createElement("vehiculoA");
            pantallas.appendChild(vehiA);


            Element aseg = doc.createElement("aseguradoA");
            vehiA.appendChild(aseg);
            aseg.appendChild(doc.createTextNode("true"));

            Element ve = doc.createElement("vehiculoA");
            vehiA.appendChild(ve);
            ve.appendChild(doc.createTextNode("true"));

            Element ase = doc.createElement("aseguradoraA");
            vehiA.appendChild(ase);
            ase.appendChild(doc.createTextNode("true"));

            Element con = doc.createElement("conductorA");
            vehiA.appendChild(con);
            con.appendChild(doc.createTextNode("true"));

            Element imp = doc.createElement("impactosA");
            vehiA.appendChild(imp);
            imp.appendChild(doc.createTextNode("true"));

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();


            Transformer transformer = transformerFactory.newTransformer();


            DOMSource source = new DOMSource(doc);
            FileOutputStream _stream = context.getApplicationContext().openFileOutput("pantallas.xml", context.getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result = new StreamResult(_stream);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void activarCircunstanciasAXML() throws IOException, ParserConfigurationException, SAXException, TransformerConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            // raiz elementos
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Class");
            doc.appendChild(rootElement);

            // staff elements
            Element pantallas = doc.createElement("pantallas");
            rootElement.appendChild(pantallas);


            // firstname elements
            Element fotografias = doc.createElement("fotografias");
            pantallas.appendChild(fotografias);
            fotografias.appendChild(doc.createTextNode("true"));

            Element localizacion = doc.createElement("localizacion");
            pantallas.appendChild(localizacion);
            localizacion.appendChild(doc.createTextNode("true"));

            Element vehiA = doc.createElement("vehiculoA");
            pantallas.appendChild(vehiA);


            Element aseg = doc.createElement("aseguradoA");
            vehiA.appendChild(aseg);
            aseg.appendChild(doc.createTextNode("true"));

            Element ve = doc.createElement("vehiculoA");
            vehiA.appendChild(ve);
            ve.appendChild(doc.createTextNode("true"));

            Element ase = doc.createElement("aseguradoraA");
            vehiA.appendChild(ase);
            ase.appendChild(doc.createTextNode("true"));

            Element con = doc.createElement("conductorA");
            vehiA.appendChild(con);
            con.appendChild(doc.createTextNode("true"));

            Element imp = doc.createElement("impactosA");
            vehiA.appendChild(imp);
            imp.appendChild(doc.createTextNode("true"));

            Element cir = doc.createElement("circunstanciasA");
            vehiA.appendChild(cir);
            cir.appendChild(doc.createTextNode("true"));

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();


            Transformer transformer = transformerFactory.newTransformer();


            DOMSource source = new DOMSource(doc);
            FileOutputStream _stream = context.getApplicationContext().openFileOutput("pantallas.xml", context.getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result = new StreamResult(_stream);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void activarAseguradoBXML() throws IOException, ParserConfigurationException, SAXException, TransformerConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            // raiz elementos
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Class");
            doc.appendChild(rootElement);

            // staff elements
            Element pantallas = doc.createElement("pantallas");
            rootElement.appendChild(pantallas);


            // firstname elements
            Element fotografias = doc.createElement("fotografias");
            pantallas.appendChild(fotografias);
            fotografias.appendChild(doc.createTextNode("true"));

            Element localizacion = doc.createElement("localizacion");
            pantallas.appendChild(localizacion);
            localizacion.appendChild(doc.createTextNode("true"));

            Element vehiA = doc.createElement("vehiculoA");
            pantallas.appendChild(vehiA);


            Element aseg = doc.createElement("aseguradoA");
            vehiA.appendChild(aseg);
            aseg.appendChild(doc.createTextNode("true"));

            Element ve = doc.createElement("vehiculoA");
            vehiA.appendChild(ve);
            ve.appendChild(doc.createTextNode("true"));

            Element ase = doc.createElement("aseguradoraA");
            vehiA.appendChild(ase);
            ase.appendChild(doc.createTextNode("true"));

            Element con = doc.createElement("conductorA");
            vehiA.appendChild(con);
            con.appendChild(doc.createTextNode("true"));

            Element imp = doc.createElement("impactosA");
            vehiA.appendChild(imp);
            imp.appendChild(doc.createTextNode("true"));

            Element cir = doc.createElement("circunstanciasA");
            vehiA.appendChild(cir);
            cir.appendChild(doc.createTextNode("true"));

            Element vehiB = doc.createElement("vehiculoB");
            pantallas.appendChild(vehiB);

            Element asegb = doc.createElement("aseguradoB");
            vehiB.appendChild(asegb);
            asegb.appendChild(doc.createTextNode("true"));


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();


            Transformer transformer = transformerFactory.newTransformer();


            DOMSource source = new DOMSource(doc);
            FileOutputStream _stream = context.getApplicationContext().openFileOutput("pantallas.xml", context.getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result = new StreamResult(_stream);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void activarVehiculoBXML() throws IOException, ParserConfigurationException, SAXException, TransformerConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            // raiz elementos
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Class");
            doc.appendChild(rootElement);

            // staff elements
            Element pantallas = doc.createElement("pantallas");
            rootElement.appendChild(pantallas);


            // firstname elements
            Element fotografias = doc.createElement("fotografias");
            pantallas.appendChild(fotografias);
            fotografias.appendChild(doc.createTextNode("true"));

            Element localizacion = doc.createElement("localizacion");
            pantallas.appendChild(localizacion);
            localizacion.appendChild(doc.createTextNode("true"));

            Element vehiA = doc.createElement("vehiculoA");
            pantallas.appendChild(vehiA);


            Element aseg = doc.createElement("aseguradoA");
            vehiA.appendChild(aseg);
            aseg.appendChild(doc.createTextNode("true"));

            Element ve = doc.createElement("vehiculoA");
            vehiA.appendChild(ve);
            ve.appendChild(doc.createTextNode("true"));

            Element ase = doc.createElement("aseguradoraA");
            vehiA.appendChild(ase);
            ase.appendChild(doc.createTextNode("true"));

            Element con = doc.createElement("conductorA");
            vehiA.appendChild(con);
            con.appendChild(doc.createTextNode("true"));

            Element imp = doc.createElement("impactosA");
            vehiA.appendChild(imp);
            imp.appendChild(doc.createTextNode("true"));

            Element cir = doc.createElement("circunstanciasA");
            vehiA.appendChild(cir);
            cir.appendChild(doc.createTextNode("true"));

            Element vehiB = doc.createElement("vehiculoB");
            pantallas.appendChild(vehiB);

            Element asegb = doc.createElement("aseguradoB");
            vehiB.appendChild(asegb);
            asegb.appendChild(doc.createTextNode("true"));

            Element veB = doc.createElement("vehiculoB");
            vehiB.appendChild(veB);
            veB.appendChild(doc.createTextNode("true"));


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();


            Transformer transformer = transformerFactory.newTransformer();


            DOMSource source = new DOMSource(doc);
            FileOutputStream _stream = context.getApplicationContext().openFileOutput("pantallas.xml", context.getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result = new StreamResult(_stream);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void activarAseguradoraBXML() throws IOException, ParserConfigurationException, SAXException, TransformerConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            // raiz elementos
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Class");
            doc.appendChild(rootElement);

            // staff elements
            Element pantallas = doc.createElement("pantallas");
            rootElement.appendChild(pantallas);


            // firstname elements
            Element fotografias = doc.createElement("fotografias");
            pantallas.appendChild(fotografias);
            fotografias.appendChild(doc.createTextNode("true"));

            Element localizacion = doc.createElement("localizacion");
            pantallas.appendChild(localizacion);
            localizacion.appendChild(doc.createTextNode("true"));

            Element vehiA = doc.createElement("vehiculoA");
            pantallas.appendChild(vehiA);


            Element aseg = doc.createElement("aseguradoA");
            vehiA.appendChild(aseg);
            aseg.appendChild(doc.createTextNode("true"));

            Element ve = doc.createElement("vehiculoA");
            vehiA.appendChild(ve);
            ve.appendChild(doc.createTextNode("true"));

            Element ase = doc.createElement("aseguradoraA");
            vehiA.appendChild(ase);
            ase.appendChild(doc.createTextNode("true"));

            Element con = doc.createElement("conductorA");
            vehiA.appendChild(con);
            con.appendChild(doc.createTextNode("true"));

            Element imp = doc.createElement("impactosA");
            vehiA.appendChild(imp);
            imp.appendChild(doc.createTextNode("true"));

            Element cir = doc.createElement("circunstanciasA");
            vehiA.appendChild(cir);
            cir.appendChild(doc.createTextNode("true"));

            Element vehiB = doc.createElement("vehiculoB");
            pantallas.appendChild(vehiB);

            Element asegb = doc.createElement("aseguradoB");
            vehiB.appendChild(asegb);
            asegb.appendChild(doc.createTextNode("true"));

            Element veB = doc.createElement("vehiculoB");
            vehiB.appendChild(veB);
            veB.appendChild(doc.createTextNode("true"));

            Element asegB = doc.createElement("aseguradoraB");
            vehiB.appendChild(asegB);
            asegB.appendChild(doc.createTextNode("true"));


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();


            Transformer transformer = transformerFactory.newTransformer();


            DOMSource source = new DOMSource(doc);
            FileOutputStream _stream = context.getApplicationContext().openFileOutput("pantallas.xml", context.getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result = new StreamResult(_stream);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void activarConductorBXML() throws IOException, ParserConfigurationException, SAXException, TransformerConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            // raiz elementos
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Class");
            doc.appendChild(rootElement);

            // staff elements
            Element pantallas = doc.createElement("pantallas");
            rootElement.appendChild(pantallas);


            // firstname elements
            Element fotografias = doc.createElement("fotografias");
            pantallas.appendChild(fotografias);
            fotografias.appendChild(doc.createTextNode("true"));

            Element localizacion = doc.createElement("localizacion");
            pantallas.appendChild(localizacion);
            localizacion.appendChild(doc.createTextNode("true"));

            Element vehiA = doc.createElement("vehiculoA");
            pantallas.appendChild(vehiA);


            Element aseg = doc.createElement("aseguradoA");
            vehiA.appendChild(aseg);
            aseg.appendChild(doc.createTextNode("true"));

            Element ve = doc.createElement("vehiculoA");
            vehiA.appendChild(ve);
            ve.appendChild(doc.createTextNode("true"));

            Element ase = doc.createElement("aseguradoraA");
            vehiA.appendChild(ase);
            ase.appendChild(doc.createTextNode("true"));

            Element con = doc.createElement("conductorA");
            vehiA.appendChild(con);
            con.appendChild(doc.createTextNode("true"));

            Element imp = doc.createElement("impactosA");
            vehiA.appendChild(imp);
            imp.appendChild(doc.createTextNode("true"));

            Element cir = doc.createElement("circunstanciasA");
            vehiA.appendChild(cir);
            cir.appendChild(doc.createTextNode("true"));

            Element vehiB = doc.createElement("vehiculoB");
            pantallas.appendChild(vehiB);

            Element asegb = doc.createElement("aseguradoB");
            vehiB.appendChild(asegb);
            asegb.appendChild(doc.createTextNode("true"));

            Element veB = doc.createElement("vehiculoB");
            vehiB.appendChild(veB);
            veB.appendChild(doc.createTextNode("true"));

            Element asegB = doc.createElement("aseguradoraB");
            vehiB.appendChild(asegB);
            asegB.appendChild(doc.createTextNode("true"));

            Element condB = doc.createElement("conductorB");
            vehiB.appendChild(condB);
            condB.appendChild(doc.createTextNode("true"));


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();


            Transformer transformer = transformerFactory.newTransformer();


            DOMSource source = new DOMSource(doc);
            FileOutputStream _stream = context.getApplicationContext().openFileOutput("pantallas.xml", context.getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result = new StreamResult(_stream);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void activarImpactosBXML() throws IOException, ParserConfigurationException, SAXException, TransformerConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            // raiz elementos
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Class");
            doc.appendChild(rootElement);

            // staff elements
            Element pantallas = doc.createElement("pantallas");
            rootElement.appendChild(pantallas);


            // firstname elements
            Element fotografias = doc.createElement("fotografias");
            pantallas.appendChild(fotografias);
            fotografias.appendChild(doc.createTextNode("true"));

            Element localizacion = doc.createElement("localizacion");
            pantallas.appendChild(localizacion);
            localizacion.appendChild(doc.createTextNode("true"));

            Element vehiA = doc.createElement("vehiculoA");
            pantallas.appendChild(vehiA);


            Element aseg = doc.createElement("aseguradoA");
            vehiA.appendChild(aseg);
            aseg.appendChild(doc.createTextNode("true"));

            Element ve = doc.createElement("vehiculoA");
            vehiA.appendChild(ve);
            ve.appendChild(doc.createTextNode("true"));

            Element ase = doc.createElement("aseguradoraA");
            vehiA.appendChild(ase);
            ase.appendChild(doc.createTextNode("true"));

            Element con = doc.createElement("conductorA");
            vehiA.appendChild(con);
            con.appendChild(doc.createTextNode("true"));

            Element imp = doc.createElement("impactosA");
            vehiA.appendChild(imp);
            imp.appendChild(doc.createTextNode("true"));

            Element cir = doc.createElement("circunstanciasA");
            vehiA.appendChild(cir);
            cir.appendChild(doc.createTextNode("true"));

            Element vehiB = doc.createElement("vehiculoB");
            pantallas.appendChild(vehiB);

            Element asegb = doc.createElement("aseguradoB");
            vehiB.appendChild(asegb);
            asegb.appendChild(doc.createTextNode("true"));

            Element veB = doc.createElement("vehiculoB");
            vehiB.appendChild(veB);
            veB.appendChild(doc.createTextNode("true"));

            Element asegB = doc.createElement("aseguradoraB");
            vehiB.appendChild(asegB);
            asegB.appendChild(doc.createTextNode("true"));

            Element condB = doc.createElement("conductorB");
            vehiB.appendChild(condB);
            condB.appendChild(doc.createTextNode("true"));

            Element impB = doc.createElement("impactosB");
            vehiB.appendChild(impB);
            impB.appendChild(doc.createTextNode("true"));



            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();


            Transformer transformer = transformerFactory.newTransformer();


            DOMSource source = new DOMSource(doc);
            FileOutputStream _stream = context.getApplicationContext().openFileOutput("pantallas.xml", context.getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result = new StreamResult(_stream);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void activarCircunstaciasBXML() throws IOException, ParserConfigurationException, SAXException, TransformerConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            // raiz elementos
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Class");
            doc.appendChild(rootElement);

            // staff elements
            Element pantallas = doc.createElement("pantallas");
            rootElement.appendChild(pantallas);


            // firstname elements
            Element fotografias = doc.createElement("fotografias");
            pantallas.appendChild(fotografias);
            fotografias.appendChild(doc.createTextNode("true"));

            Element localizacion = doc.createElement("localizacion");
            pantallas.appendChild(localizacion);
            localizacion.appendChild(doc.createTextNode("true"));

            Element vehiA = doc.createElement("vehiculoA");
            pantallas.appendChild(vehiA);


            Element aseg = doc.createElement("aseguradoA");
            vehiA.appendChild(aseg);
            aseg.appendChild(doc.createTextNode("true"));

            Element ve = doc.createElement("vehiculoA");
            vehiA.appendChild(ve);
            ve.appendChild(doc.createTextNode("true"));

            Element ase = doc.createElement("aseguradoraA");
            vehiA.appendChild(ase);
            ase.appendChild(doc.createTextNode("true"));

            Element con = doc.createElement("conductorA");
            vehiA.appendChild(con);
            con.appendChild(doc.createTextNode("true"));

            Element imp = doc.createElement("impactosA");
            vehiA.appendChild(imp);
            imp.appendChild(doc.createTextNode("true"));

            Element cir = doc.createElement("circunstanciasA");
            vehiA.appendChild(cir);
            cir.appendChild(doc.createTextNode("true"));

            Element vehiB = doc.createElement("vehiculoB");
            pantallas.appendChild(vehiB);

            Element asegb = doc.createElement("aseguradoB");
            vehiB.appendChild(asegb);
            asegb.appendChild(doc.createTextNode("true"));

            Element veB = doc.createElement("vehiculoB");
            vehiB.appendChild(veB);
            veB.appendChild(doc.createTextNode("true"));

            Element asegB = doc.createElement("aseguradoraB");
            vehiB.appendChild(asegB);
            asegB.appendChild(doc.createTextNode("true"));

            Element condB = doc.createElement("conductorB");
            vehiB.appendChild(condB);
            condB.appendChild(doc.createTextNode("true"));

            Element impB = doc.createElement("impactosB");
            vehiB.appendChild(impB);
            impB.appendChild(doc.createTextNode("true"));

            Element cirB = doc.createElement("circunstanciasB");
            vehiB.appendChild(cirB);
            cirB.appendChild(doc.createTextNode("true"));



            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();


            Transformer transformer = transformerFactory.newTransformer();


            DOMSource source = new DOMSource(doc);
            FileOutputStream _stream = context.getApplicationContext().openFileOutput("pantallas.xml", context.getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result = new StreamResult(_stream);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void activarTerminarXML() throws IOException, ParserConfigurationException, SAXException, TransformerConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            // raiz elementos
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Class");
            doc.appendChild(rootElement);

            // staff elements
            Element pantallas = doc.createElement("pantallas");
            rootElement.appendChild(pantallas);


            // firstname elements
            Element fotografias = doc.createElement("fotografias");
            pantallas.appendChild(fotografias);
            fotografias.appendChild(doc.createTextNode("true"));

            Element localizacion = doc.createElement("localizacion");
            pantallas.appendChild(localizacion);
            localizacion.appendChild(doc.createTextNode("true"));

            Element vehiA = doc.createElement("vehiculoA");
            pantallas.appendChild(vehiA);


            Element aseg = doc.createElement("aseguradoA");
            vehiA.appendChild(aseg);
            aseg.appendChild(doc.createTextNode("true"));

            Element ve = doc.createElement("vehiculoA");
            vehiA.appendChild(ve);
            ve.appendChild(doc.createTextNode("true"));

            Element ase = doc.createElement("aseguradoraA");
            vehiA.appendChild(ase);
            ase.appendChild(doc.createTextNode("true"));

            Element con = doc.createElement("conductorA");
            vehiA.appendChild(con);
            con.appendChild(doc.createTextNode("true"));

            Element imp = doc.createElement("impactosA");
            vehiA.appendChild(imp);
            imp.appendChild(doc.createTextNode("true"));

            Element cir = doc.createElement("circunstanciasA");
            vehiA.appendChild(cir);
            cir.appendChild(doc.createTextNode("true"));

            Element vehiB = doc.createElement("vehiculoB");
            pantallas.appendChild(vehiB);

            Element asegb = doc.createElement("aseguradoB");
            vehiB.appendChild(asegb);
            asegb.appendChild(doc.createTextNode("true"));

            Element veB = doc.createElement("vehiculoB");
            vehiB.appendChild(veB);
            veB.appendChild(doc.createTextNode("true"));

            Element asegB = doc.createElement("aseguradoraB");
            vehiB.appendChild(asegB);
            asegB.appendChild(doc.createTextNode("true"));

            Element condB = doc.createElement("conductorB");
            vehiB.appendChild(condB);
            condB.appendChild(doc.createTextNode("true"));

            Element impB = doc.createElement("impactosB");
            vehiB.appendChild(impB);
            impB.appendChild(doc.createTextNode("true"));

            Element cirB = doc.createElement("circunstanciasB");
            vehiB.appendChild(cirB);
            cirB.appendChild(doc.createTextNode("true"));

            Element terminar = doc.createElement("terminar");
            pantallas.appendChild(terminar);

            Element term = doc.createElement("terminar");
            terminar.appendChild(term);
            term.appendChild(doc.createTextNode("true"));

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();


            Transformer transformer = transformerFactory.newTransformer();


            DOMSource source = new DOMSource(doc);
            FileOutputStream _stream = context.getApplicationContext().openFileOutput("pantallas.xml", context.getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result = new StreamResult(_stream);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }



    public String listaActiva() throws ParserConfigurationException, IOException, SAXException {
        String pantallaActiva = "";

        FileInputStream fil = context.openFileInput("pantallas.xml");


        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document dom = builder.parse(fil);

        Element raiz = dom.getDocumentElement();

        NodeList items = raiz.getElementsByTagName("pantallas");

        for (int i = 0; i < items.getLength(); i++) {
            Node nodoPantalla = items.item(i);
            for (int j = 0; j < nodoPantalla.getChildNodes().getLength(); j++) {
                Node nodoPrincipal = nodoPantalla.getChildNodes().item(j);
                if (nodoPrincipal.getNodeType() == Node.ELEMENT_NODE) {
                    switch (nodoPrincipal.getNodeName()) {
                        case "fotografias":
                            if (nodoPrincipal.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                pantallaActiva = "fotografias";
                            }
                            break;
                        case "localizacion":
                            if (nodoPrincipal.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                pantallaActiva = "localizacion";
                            }
                            break;

                        case "vehiculoA":
                            for (int r = 0; r < nodoPrincipal.getChildNodes().getLength(); r++) {
                                Node nodoVehiculoA = nodoPrincipal.getChildNodes().item(r);
                                switch (nodoVehiculoA.getNodeName()) {
                                    case "aseguradoA":
                                        if (nodoVehiculoA.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                            pantallaActiva = "aseguradoA";
                                        }
                                        break;
                                    case "vehiculoA":
                                        if (nodoVehiculoA.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                            pantallaActiva = "vehiculoA";
                                        }
                                        break;
                                    case "aseguradoraA":
                                        if (nodoVehiculoA.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                            pantallaActiva = "aseguradoraA";
                                        }
                                        break;
                                    case "conductorA":
                                        if (nodoVehiculoA.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                            pantallaActiva = "conductorA";
                                        }
                                        break;
                                    case "impactosA":
                                        if (nodoVehiculoA.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                            pantallaActiva = "impactosA";
                                        }
                                        break;
                                    case "circunstanciasA":
                                        if (nodoVehiculoA.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                            pantallaActiva = "circunstanciasA";
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
                                            pantallaActiva = "aseguradoB";
                                        }
                                        break;
                                    case "vehiculoB":
                                        if (nodoVehiculoB.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                            pantallaActiva = "vehiculoB";
                                        }
                                        break;
                                    case "aseguradoraB":
                                        if (nodoVehiculoB.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                            pantallaActiva = "aseguradoraB";
                                        }
                                        break;
                                    case "conductorB":
                                        if (nodoVehiculoB.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                            pantallaActiva = "conductorB";
                                        }
                                        break;
                                    case "impactosB":
                                        if (nodoVehiculoB.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                            pantallaActiva = "impactosB";
                                        }
                                        break;
                                    case "circunstanciasB":
                                        if (nodoVehiculoB.getChildNodes().item(0).getNodeValue().equalsIgnoreCase("true")) {
                                            pantallaActiva = "circunstanciasB";
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
                                            pantallaActiva = "terminar";
                                        }
                                        break;
                                }
                            }

                    }

                }
            }
        }
        return pantallaActiva;
    }
}


