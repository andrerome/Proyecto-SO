package rss.logic;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import rss.gui.RSSReaderFrame;

public class RSSReader {
    
    private static final LinkedList <RSSData> feed = new LinkedList <RSSData> ();
    private static final LinkedList <RSSProvider> providers = new LinkedList <RSSProvider> ();
    private static final LinkedList <FeedFetcher> fetchers = new LinkedList <FeedFetcher> ();
    public static rss.RSSReaderTest instance = null;
    private URL rssURL;

    public RSSReader() {}

    public void setURL(URL url) {
      rssURL = url;
   }

    public void writeFeed() {
      try {
         DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
         Document doc = builder.parse(rssURL.openStream());

         NodeList items = doc.getElementsByTagName("item");

         for (int i = 0; i < items.getLength(); i++) {
            Element item = (Element)items.item(i);
            System.out.println(getValue(item, "title"));
            System.out.println(getValue(item, "description"));
            System.out.println(getValue(item, "link"));
            System.out.println();
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

    public static String getValue(Element parent, String nodeName) {
      Node temp = parent.getElementsByTagName(nodeName).item(0).getFirstChild();
      
      return temp!=null?temp.getNodeValue():"";
   }
    public static void main(String[] args) throws MalformedURLException {
        // TODO: Crear threads productores (FeedFetcher)
        
        RSSReaderFrame frame = new RSSReaderFrame(feed);
        
        //RSS disponibles para escoger
                
        
        
        ArrayList<String> urls= new ArrayList<String>();
        urls.add("http://blogdesuperheroes.es/feed");
        urls.add("http://www.eluniverso.com/rss/noticias.xml");
        urls.add("http://www.microsiervos.com/index.xml");
        urls.add("http://www.espol.edu.ec/trabajo.aspx");
        
        providers.add(new RSSProvider("Comics ", "http://blogdesuperheroes.es/feed"));
        providers.add(new RSSProvider("El Universo","http://www.eluniverso.com/rss/noticias.xml"));
        providers.add(new RSSProvider("Tecnologia","http://www.microsiervos.com/index.xml"));
        providers.add(new RSSProvider("ESPOL","http://www.espol.edu.ec/trabajo.aspx"));
        
        for(int i=0; i<urls.size(); i++){
        
            FeedFetcher f = new FeedFetcher(urls.get(i));
            fetchers.add(f);
            f.start();
        }
        
        
        /*FeedFetcher f1 = new FeedFetcher("http://blogdesuperheroes.es/feed");
        FeedFetcher f2 = new FeedFetcher("http://www.microsiervos.com/index.xml");
        FeedFetcher f3 = new FeedFetcher("http://www.eluniverso.com/rss/noticias.xml");
        FeedFetcher f4 = new FeedFetcher("http://www.espol.edu.ec/trabajo.aspx");
        
        fetchers.add(f1);
        fetchers.add(f2);
        fetchers.add(f3);
        fetchers.add(f4);

        f1.start();
        f2.start();
        f3.start();
        f4.start();*/
        
        UserFeedUpdater updater = new UserFeedUpdater(feed, frame);
        updater.start();
        
        frame.setVisible(true);
    }
    
    public static void forcePull() {
        for(FeedFetcher fetcher : fetchers)
            fetcher.forcePull();
    }
}