package persistencia;

import model.Ballari;
import model.Jutge;
import model.ParellaBall;
import java.io.File;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import model.Competicio;
import principal.GestorCompeticioException;
import model.Modalitat;

public class GestorXML implements ProveedorPersistencia{

    private Document doc;
    private Competicio competicio;

    public Competicio getCompeticio() {
        return competicio;
    }

    public void setCompeticio(Competicio competicio) {
        this.competicio = competicio;
    }

    public void desarCompeticio(String nomFitxer, Competicio competicio) throws GestorCompeticioException {
        construeixModel(competicio);
        desarModel(nomFitxer);
    }

    public void carregarCompeticio(String nomFitxer) throws GestorCompeticioException {
        carregarFitxer(nomFitxer);
        llegirFitxerCompeticio();
    }

    
    public void construeixModel(Competicio competicio) throws GestorCompeticioException {
        //Es construeix el document model
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Element fill, net, renet;

        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            throw new GestorCompeticioException("GestorXML.model");
        }

        this.doc = (Document) builder.newDocument();

        //Element arrel
        Element arrel = doc.createElement("competicio");
        arrel.setAttribute("edicio", String.valueOf(competicio.getEdicio()));
        arrel.setAttribute("any", String.valueOf(competicio.getAny()));
        arrel.setAttribute("poblacio", competicio.getPoblacio());
        doc.appendChild(arrel);

        for (int i = 0; i < competicio.getComponents().size(); i++) {

            if (competicio.getComponents().get(i) instanceof Ballari) {

                fill = doc.createElement("ballari");

                fill.setAttribute("nif", ((Ballari) competicio.getComponents().get(i)).getNif());
                fill.setAttribute("nom", ((Ballari) competicio.getComponents().get(i)).getNom());
                fill.setAttribute("sexe", ((Ballari) competicio.getComponents().get(i)).getSexe());
                fill.setAttribute("modalitat", ((Ballari) competicio.getComponents().get(i)).getModalitat());

                if (((Ballari) competicio.getComponents().get(i)).isEmparellat()) {
                    fill.setAttribute("emparellat", "1");
                } else {
                    fill.setAttribute("emparellat", "0");
                }

                arrel.appendChild(fill);

            } else if (competicio.getComponents().get(i) instanceof Jutge) {

                fill = doc.createElement("jutge");

                fill.setAttribute("nif", ((Jutge) competicio.getComponents().get(i)).getNif());
                fill.setAttribute("nom", ((Jutge) competicio.getComponents().get(i)).getNom());

                arrel.appendChild(fill);

            } else if (competicio.getComponents().get(i) instanceof Modalitat) {

                fill = doc.createElement("modalitat");

                fill.setAttribute("codi", ((Modalitat) competicio.getComponents().get(i)).getCodi());
                fill.setAttribute("nom", ((Modalitat) competicio.getComponents().get(i)).getNom());

                //En lloc de fer un recorregut pel HashMap, també podriem comprovar quines de les dues claus és amb:
                //((Modalitat) competicio.getComponents().get(i)).getComponents().containsKey("clau"), on clau
                //pot ser "parellesBall" o "jutges".
                Iterator codi = ((Modalitat) competicio.getComponents().get(i)).getComponents().keySet().iterator();

                while (codi.hasNext()) {

                    Object codiActual = codi.next();

                    if (codiActual.equals("parellesBall")) {

                        for (int j = 0; j < ((Modalitat) competicio.getComponents().get(i)).getComponents().get("parellesBall").size(); j++) {

                            net = doc.createElement("parellaBall");

                            net.setAttribute("numInscripcio", String.valueOf(((ParellaBall) ((Modalitat) competicio.getComponents().get(i)).getComponents().get("parellesBall").get(j)).getNumInscripcio()));
                            net.setAttribute("puntuacio", String.valueOf(((ParellaBall) ((Modalitat) competicio.getComponents().get(i)).getComponents().get("parellesBall").get(j)).getPuntuacio()));

                            renet = doc.createElement("ballari");

                            renet.setAttribute("nif", ((ParellaBall) ((Modalitat) competicio.getComponents().get(i)).getComponents().get("parellesBall").get(j)).getBallari().getNif());
                            renet.setAttribute("nom", ((ParellaBall) ((Modalitat) competicio.getComponents().get(i)).getComponents().get("parellesBall").get(j)).getBallari().getNom());
                            renet.setAttribute("sexe", ((ParellaBall) ((Modalitat) competicio.getComponents().get(i)).getComponents().get("parellesBall").get(j)).getBallari().getSexe());
                            renet.setAttribute("modalitat", ((ParellaBall) ((Modalitat) competicio.getComponents().get(i)).getComponents().get("parellesBall").get(j)).getBallari().getModalitat());

                            if (((ParellaBall) ((Modalitat) competicio.getComponents().get(i)).getComponents().get("parellesBall").get(j)).getBallari().isEmparellat()) {
                                renet.setAttribute("emparellat", "1");
                            } else {
                                renet.setAttribute("emparellat", "0");
                            }

                            net.appendChild(renet);

                            renet = doc.createElement("ballarina");

                            renet.setAttribute("nif", ((ParellaBall) ((Modalitat) competicio.getComponents().get(i)).getComponents().get("parellesBall").get(j)).getBallarina().getNif());
                            renet.setAttribute("nom", ((ParellaBall) ((Modalitat) competicio.getComponents().get(i)).getComponents().get("parellesBall").get(j)).getBallarina().getNom());
                            renet.setAttribute("sexe", ((ParellaBall) ((Modalitat) competicio.getComponents().get(i)).getComponents().get("parellesBall").get(j)).getBallarina().getSexe());
                            renet.setAttribute("modalitat", ((ParellaBall) ((Modalitat) competicio.getComponents().get(i)).getComponents().get("parellesBall").get(j)).getBallarina().getModalitat());

                            if (((ParellaBall) ((Modalitat) competicio.getComponents().get(i)).getComponents().get("parellesBall").get(j)).getBallarina().isEmparellat()) {
                                renet.setAttribute("emparellat", "1");
                            } else {
                                renet.setAttribute("emparellat", "0");
                            }

                            net.appendChild(renet);

                            fill.appendChild(net);

                        }

                    } else { //És jutge

                        for (int j = 0; j < ((Modalitat) competicio.getComponents().get(i)).getComponents().get("jutges").size(); j++) {

                            net = doc.createElement("jutge");

                            net.setAttribute("nif", ((Jutge) ((Modalitat) competicio.getComponents().get(i)).getComponents().get("jutges").get(j)).getNif());
                            net.setAttribute("nom", ((Jutge) ((Modalitat) competicio.getComponents().get(i)).getComponents().get("jutges").get(j)).getNom());

                            fill.appendChild(net);
                        }
                    }

                }

                arrel.appendChild(fill);
            }
        }
    }

    public void desarModel(String nomFitxer) throws GestorCompeticioException {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(doc);
            File f = new File(nomFitxer + ".xml");
            StreamResult result = new StreamResult(f);
            transformer.transform(source, result);
        } catch (Exception ex) {
            throw new GestorCompeticioException("GestorXML.desar");
        }
    }

    public void carregarFitxer(String nomFitxer) throws GestorCompeticioException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            File f = new File(nomFitxer + ".xml");
            doc = builder.parse(f);
        } catch (Exception ex) {
            throw new GestorCompeticioException("GestorXML.carrega");
        }
    }

    
    private void llegirFitxerCompeticio() throws GestorCompeticioException {
        String component;

        Element arrel = doc.getDocumentElement();

        //Es crea l'objecte competició
        competicio = new Competicio(Integer.parseInt(arrel.getAttribute("edicio")), Integer.parseInt(arrel.getAttribute("any")), arrel.getAttribute("poblacio"));

        //Recorregut de nodes fill d'un element       
        NodeList llistaFills = arrel.getChildNodes();

        for (int i = 0; i < llistaFills.getLength(); i++) {

            Node fill = llistaFills.item(i);

            if (fill.getNodeType() == Node.ELEMENT_NODE) {

                component = fill.getNodeName();

                if (component.equals("ballari")) {

                    Ballari nouBallari = new Ballari(((Element) fill).getAttribute("nif"), ((Element) fill).getAttribute("nom"), ((Element) fill).getAttribute("sexe"));

                    nouBallari.setModalitat(((Element) fill).getAttribute("modalitat"));

                    if (((Element) fill).getAttribute("emparellat").equals("1")) {
                        nouBallari.setEmparellat(true);
                    }

                    competicio.addBallari(nouBallari);

                } else if (component.equals("jutge")) {

                    competicio.addJutge(new Jutge(((Element) fill).getAttribute("nif"), ((Element) fill).getAttribute("nom")));

                } else if (component.equals("modalitat")) {

                    Modalitat novaModalitat = new Modalitat(((Element) fill).getAttribute("codi"), ((Element) fill).getAttribute("nom"));

                    NodeList nets = fill.getChildNodes();

                    for (int j = 0; j < nets.getLength(); j++) {

                        Node net = nets.item(j);

                        if (net != null && net.getNodeType() == Node.ELEMENT_NODE) {

                            if (net.getNodeName().equals("parellaBall")) {

                                ParellaBall novaParellaBall = null;

                                NodeList renets = net.getChildNodes();

                                for (int k = 0; k < renets.getLength(); k++) {

                                    Node renet = nets.item(j);

                                    if (renet != null && renet.getNodeType() == Node.ELEMENT_NODE) {

                                        Ballari nouBallari = null;
                                        Ballari novaBallarina = null;

                                        if (renet.getNodeName().equals("ballari")) {

                                            nouBallari = new Ballari(((Element) renet).getAttribute("nif"), ((Element) renet).getAttribute("nom"), ((Element) renet).getAttribute("sexe"));;

                                            nouBallari.setModalitat(((Element) renet).getAttribute("modalitat"));

                                            if (((Element) renet).getAttribute("emparellat").equals("1")) {
                                                nouBallari.setEmparellat(true);
                                            }

                                        } else { //Ballarina

                                            novaBallarina = new Ballari(((Element) renet).getAttribute("nif"), ((Element) renet).getAttribute("nom"), ((Element) renet).getAttribute("sexe"));;

                                            novaBallarina.setModalitat(((Element) renet).getAttribute("modalitat"));

                                            if (((Element) renet).getAttribute("emparellat").equals("1")) {
                                                novaBallarina.setEmparellat(true);
                                            }

                                        }

                                        novaParellaBall = new ParellaBall(nouBallari, novaBallarina);

                                        novaParellaBall.setNumInscripcio(Integer.parseInt(((Element) net).getAttribute("numInscripcio")));

                                        novaParellaBall.setPuntuacio(Integer.parseInt(((Element) net).getAttribute("puntuacio")));

                                    }

                                }

                                novaModalitat.getComponents().get("parellesBall").add(novaParellaBall);
                                
                            }else{ //És jutge
                                
                                novaModalitat.getComponents().get("jutges").add(new Jutge(((Element) net).getAttribute("nif"), ((Element) net).getAttribute("nom")));
                                
                            }
                        }
                    }
                    
                    competicio.addModalitat(novaModalitat);
                }
            }
        }
    }
}
