package rss.logic;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.util.LinkedList;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class FeedFetcher extends Thread {
    private String url = null;
    private long lastFetch = 0;
    
    private static int ID = 0; // TODO: Remover esto. Fue colocado con fines de prueba.
    private static Random rand = new Random(); // TODO: Remover esto. Fue colocado con fines de prueba.
    
    public FeedFetcher(String url) {
        this.url = url;
    }
    
    private RSSData createData() { // TODO: Remover esto. Fue colocado con fines de prueba.
        if (rand.nextBoolean())
            return null;
        
        ID++;
        return new RSSData("title: "+ID, url, "descripcion", "idioma", "copyright", new Date());
    }
    
    private void pull() throws MalformedURLException, SAXException, ParserConfigurationException, IOException {
        System.out.println("pull from: "+url);
        
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        URL tmp = new URL(this.url);
        Document doc = builder.parse(tmp.openStream());
        NodeList items = doc.getElementsByTagName("item");        
        LinkedList <RSSData> dataToAdd = new LinkedList <RSSData> ();
        
        //TODO: No volver a crear noticias que ya fueron creadas anteriormente
        for (int i = 0; i < items.getLength(); i++) {
            Element item = (Element)items.item(i);
            RSSData new_data = new RSSData(item);
            
            if (new_data != null)
                dataToAdd.add(new_data);
         }   
        
        /*for (int i=0; i<5; i++) {
            RSSData new_data = createData();
            if (new_data != null)
                dataToAdd.add(new_data);
        };*/
        Config.RSS_BUFFER.addData(dataToAdd);
        lastFetch = System.currentTimeMillis();        
        
    }
    
    public long getElapsedTime() {
        return System.currentTimeMillis() - this.lastFetch;
    }
    
    @Override
    public void run() {
        while (true) {
            while (getElapsedTime() <= Config.TIME_TO_WAIT) {
                if (Config.FORCE_PULL) {
                    break;
                }
            };

            try {
                // TODO: Esperar a que liberen el buffer
                pull();
                // TODO: Notificar que se dejó de usar el buffer
            } catch (SAXException ex) {
                Logger.getLogger(FeedFetcher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(FeedFetcher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FeedFetcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}