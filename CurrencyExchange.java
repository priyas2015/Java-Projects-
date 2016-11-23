import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;


import javax.xml.bind.annotation.*;
import javax.xml.transform.stream.StreamSource;

/**
 * Created by PriyaShah on 7/1/16.
 */


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class CurrencyExchange {

    public List<Object> exchangeRates;

    public String time;


    public void main(String[] args){

        getRates();
    }


    public String nodeToString(Node node) throws TransformerException {
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter sw = new StringWriter();
        t.transform(new DOMSource(node), new StreamResult(sw));
        return sw.toString();
    }


    public void getRates(){

        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(URL.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        Unmarshaller jaxbUnmarshaller = null;
        try {
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        URL url = null;
        try {
            url = new URL("http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Object currObject = null;
        try {
            currObject = jaxbUnmarshaller.unmarshal(url);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        System.out.println(currObject.toString());


    }
}

        /*try {

            URL url = null; //can be changed based on the date
            url = new URL("http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");

            URLConnection connection = url.openConnection();


            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(url.openStream());

            document.getDocumentElement().normalize();

            NodeList nodes = document.getElementsByTagName("Cube time =");

            for (int i = 0; i < nodes.getLength(); i++) {

                System.out.println(nodeToString(nodes.item(i)));
            }

            //System.out.println("DOC PARSE: " + document.adoptNode(nodes.item(17)) + "length: " + nodes.getLength());


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
*/


/*

    //Gets the current exchange rates
    public static ArrayList getExchangeRates(ArrayList toExchange){

        ArrayList toReturn = new ArrayList();

        //converted currency values
        double converted_EUR;
        double converted_GBP;
        double converted_CAD;

        //converts the currencies for the day and prints it out
        converted_CAD = findExchangeRateAndConvert("CAD", "USD", toExchange.get(1));
        converted_EUR = findExchangeRateAndConvert("EUR", "USD", toExchange.get(2));
        converted_GBP = findExchangeRateAndConvert("GBP", "USD", toExchange.get(3));

        toReturn.add(converted_CAD);
        toReturn.add(converted_EUR);
        toReturn.add(converted_GBP);

        return toReturn;
    }
}



*/
