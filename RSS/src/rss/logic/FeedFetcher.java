package rss.logic;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.util.LinkedList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class FeedFetcher extends Thread {
    private String url = null;
    private long lastFetch = 0;
    private Date lastPost = null;    
    private boolean forcePull = false;
    
    public FeedFetcher(String url) {
        this.url = url;
    }
    
    private void pull() throws MalformedURLException, SAXException, ParserConfigurationException, IOException {
        System.out.println("pull from: "+url);
        
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        URL tmp = new URL(this.url);
        Document doc = builder.parse(tmp.openStream());
        NodeList items = doc.getElementsByTagName("item");        
        LinkedList <RSSData> dataToAdd = new LinkedList <RSSData> ();
        
        for (int i = 0; i < items.getLength(); i++) {
            Element item = (Element)items.item(i);
            RSSData new_data = new RSSData(item);
            SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z");
            
            try {
               Date datetmp = formatter.parse(new_data.pubDate);
                if(lastFetch== 0){
                    dataToAdd.add(new_data);
                    
                    if(lastPost==null) {
                        lastPost= datetmp;
                    } else if(datetmp.after(this.lastPost))
                        this.lastPost = datetmp;
                    
                    this.lastPost = datetmp;
                } else if (lastFetch!= 0 && new_data != null && datetmp.after(this.lastPost)) {
                    dataToAdd.add(new_data);
                    this.lastPost = datetmp;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }                                  
        }
        
        Config.RSS_BUFFER.addData(dataToAdd);
        lastFetch = System.currentTimeMillis();
    }
    
    public long getElapsedTime() {
        return System.currentTimeMillis() - this.lastFetch;
    }
    
    public void forcePull() {
        this.forcePull = true;
    }
    
    @Override
    public void run() {
        while (true) {
            while (getElapsedTime() <= Config.TIME_TO_WAIT) {
                if (forcePull) {
                    forcePull = false;
                    break;
                }
            };

            try {
                pull();
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