import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Hamburger extends Etel{
    public Hamburger(int id, String nev, int ar, String leiras){
        this.setId(id);
        this.setNev(nev);
        this.setAr(ar);
        this.setLeiras(leiras);
    }
    public static void Listing(){
        try {
            //XML beolvasása, parsolása
            File file = new File("etelek.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.normalize();
            NodeList hamburgerek = doc.getElementsByTagName("hamburger");

            for (int i = 0; i < hamburgerek.getLength(); i++) {
                Node elem = hamburgerek.item(i);
                if (elem.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElem = (Element) elem;
                    int id = Integer.parseInt(eElem.getElementsByTagName("id").item(0).getTextContent().trim());
                    String nev = eElem.getElementsByTagName("nev").item(0).getTextContent().trim();
                    int ar = Integer.parseInt(eElem.getElementsByTagName("ar").item(0).getTextContent().trim());
                    String leiras = eElem.getElementsByTagName("leiras").item(0).getTextContent().trim();

                    System.out.println("Hamburger idja: "+id);
                    System.out.println("Hamburger neve: "+nev);
                    System.out.println("Hamburger ára: "+ar);
                    System.out.println("Hamburger leírása:\n"+leiras+"\n");
                }
            }
        }catch (Exception e){}
    }

    public static void Delete(){
        {
            try {
                File file = new File("etelek.xml");
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document dom = dBuilder.parse(file);
                dom.normalize();
                Scanner sc = new Scanner(System.in);

                System.out.println("Kérem a törlendő étel IDjét vagy nevét: ");
                String search = sc.nextLine();
                NodeList hamburgerek = dom.getElementsByTagName("hamburger");

                for (int i = 0; i < hamburgerek.getLength(); i++) {
                    Node elem = hamburgerek.item(i);

                    if (elem.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElem = (Element) elem;

                        if (search.equals(eElem.getElementsByTagName("id").item(0).getTextContent().trim())
                                || search.equals(eElem.getElementsByTagName("nev").item(0).getTextContent().trim())) {
                            Node parentNode = eElem.getParentNode();
                            parentNode.removeChild(eElem);
                        }

                    }
                }


                DOMSource source = new DOMSource(dom);
                StreamResult result = new StreamResult("etelek.xml");

                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();

                transformer.transform(source, result);

            }catch (Exception e){}
        }
    }

    public static void Update() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Kérem a módoítani kívánt hamburger IDjét vagy nevét");
        String search = sc.nextLine();

            try {
                File file = new File("etelek.xml");
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(file);
                doc.normalize();
                NodeList hamburgerek = doc.getElementsByTagName("hamburger");

                for (int i = 0; i < hamburgerek.getLength(); i++) {
                    Node elem = hamburgerek.item(i);

                    if (elem.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElem = (Element) elem;

                        if (search.equals(eElem.getElementsByTagName("id").item(0).getTextContent().trim())
                                || search.equals(eElem.getElementsByTagName("nev").item(0).getTextContent().trim())) {
                            NodeList childNodes = elem.getChildNodes();
                            for (int j = 0; j < childNodes.getLength(); j++) {
                                Node childNode = childNodes.item(j);
                                if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                                    String modAkar;
                                    switch (childNode.getNodeName()) {
                                        case "nev":
                                            System.out.println("Szeretne nevet módosítani? (I/N)");
                                            modAkar = sc.nextLine();
                                            if (modAkar.toUpperCase().equals("I")) {
                                                System.out.print("Kérem adja meg az új adatot: ");
                                                String ujAdat = sc.nextLine();
                                                childNode.setTextContent(ujAdat);
                                            }
                                            break;
                                        case "ar":
                                            System.out.println("Szeretne árat módosítani? (I/N)");
                                            modAkar = sc.nextLine();
                                            if (modAkar.toUpperCase().equals("I")) {
                                                System.out.print("Kérem adja meg az új adatot: ");
                                                String ujAdat = sc.nextLine();
                                                childNode.setTextContent(ujAdat);
                                            }
                                            break;
                                        case "leiras":
                                            System.out.println("Szeretne leírást módosítani? (I/N)");
                                            modAkar = sc.nextLine();
                                            if (modAkar.toUpperCase().equals("I")) {
                                                System.out.print("Kérem adja meg az új adatot: ");
                                                String ujAdat = sc.nextLine();
                                                childNode.setTextContent(ujAdat);
                                            }
                                            break;

                                    }
                                }

                            }
                            TransformerFactory transformerFactory = TransformerFactory.newInstance();
                            Transformer transformer = transformerFactory.newTransformer();
                            FileOutputStream output = new FileOutputStream("etelek.xml");

                            DOMSource source = new DOMSource(doc);
                            StreamResult result = new StreamResult(output);

                            transformer.transform(source, result);
                        }
                    }
                }
            }catch (Exception e){System.out.println(e);}

    }

    public static int generateID(){
        int id = 22000 + (int) (Math.random() * 1000) + 1;
        try {
            File file = new File("etelek.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document dom = dBuilder.parse(file);
            dom.normalize();
            boolean usedID;
            do {
                usedID = false;
                NodeList pizzak = dom.getElementsByTagName("hamburger");
                for (int i = 0; i < pizzak.getLength(); i++) {
                    Node elem = pizzak.item(i);
                    if (elem.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElem = (Element) elem;
                        if (id == Integer.parseInt(eElem.getElementsByTagName("id").item(0).getTextContent().trim())) {
                            usedID = true;
                            id = 22000 + (int) (Math.random() * 1000) + 1;
                            break;
                        }
                    }
                }
            }while(usedID);

        }catch (Exception e){}

        return id;
    }
}
