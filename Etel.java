import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;

import java.util.Scanner;

class Etel {
    private int ar;
    private String nev;
    private String leiras;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public int getAr() {
        return ar;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }


    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public static void Add() {
        try {
            File file = new File("etelek.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document dom = dBuilder.parse(file);
            dom.normalize();
            Scanner sc = new Scanner(System.in);

            System.out.println("Milyen étel típust szeretne hozzáadni: ");
            String newType = sc.nextLine();
            Element newData = dom.createElement(newType);

            Element newID = dom.createElement("id");
            int id = 0;
            if(newType.equals("pizza")) id = Pizza.generateID();
            else if (newType.equals("hamburger")) id = Hamburger.generateID();
            newID.setTextContent(Integer.toString(id));
            newData.appendChild(newID);
            System.out.println("Az új "+newType+" ID-je: "+id);

            Element newName = dom.createElement("nev");
            System.out.println("Add meg az új " + newType + " nevét: ");
            String name = sc.nextLine();
            newName.setTextContent(name);
            newData.appendChild(newName);

            Element newPrice = dom.createElement("ar");
            System.out.println("Add meg az új " + newType + " árát: ");
            String price = sc.nextLine();
            newPrice.setTextContent(price);
            newData.appendChild(newPrice);

            if (newType.equals("pizza")) {
                Element newLength = dom.createElement("hossz");
                System.out.println("Add meg az új " + newType + " méretét: ");
                String length = sc.nextLine();
                newLength.setTextContent(length);
                newData.appendChild(newLength);
            }

            Element newDetails = dom.createElement("leiras");
            System.out.println("Add meg az új " + newType + " leírásást: ");
            String details = sc.nextLine();
            newDetails.setTextContent(details);
            newData.appendChild(newDetails);

            Element rootElement = dom.getDocumentElement();
            rootElement.appendChild(newData);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File("xmlFormat.xslt")));
            FileOutputStream output = new FileOutputStream("etelek.xml");

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "no");

            DOMSource source = new DOMSource(dom);
            StreamResult result = new StreamResult(output);

            transformer.transform(source, result);
            }catch (Exception e) {
        }
    }


}
