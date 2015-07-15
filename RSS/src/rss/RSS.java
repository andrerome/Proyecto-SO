/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rss;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import java.net.URL;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Andres
 */
public class RSS {

    /**
     * @param args the command line arguments
     */

   
  
    public static void main(String[] args) {
        
       
        
        try { 
            
        String url = "http://www.tullyrankin.com/feed/rss";
        
        URL feedUrl = new URL(url);
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedUrl));
        for (SyndEntry entry : (List<SyndEntry>)feed.getEntries()) {
            System.out.println(entry.getTitle());
        }
        
        
      } catch (Exception e) {  
         e.printStackTrace();  
      }  
        
        Feed feed = new Feed();
        Productor p = new Productor(feed);
        Consumidor c = new Consumidor(feed);
        p.start();
        c.start();
    }
public static class Feed {
    private int valor;
    private boolean disponible=false;
    public synchronized void meter(int nv) {
        if (disponible) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        valor = nv;
        disponible=true;
        System.out.println("Produce: "+valor);
        notify();
    }
    public synchronized void sacar() {
        if (!disponible) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        System.out.println("Consume: "+valor);
        valor=0;
        disponible=false;
        notify();
    }
}
public static class Productor extends Thread {
    Feed f;
    public Productor(Feed nc) {
        f = nc;
    }
    public void run() {
        int i;
        for (i=1; i<=10; i++)
            f.meter(i);
    }
}

public static class Consumidor extends Thread {
    Feed f;
    public Consumidor(Feed nc) {
        f = nc;
    }
    public void run() {
        int i;
        for (i=1; i<=10; i++)
            f.sacar();
    }
}

}
