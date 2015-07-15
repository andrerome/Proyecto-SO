/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rss;
/**
 *
 * @author joalguzm
 */
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RSSReader {

   private static RSSReader instance = null;

   private URL rssURL;

   private RSSReader() {}

   public static RSSReader getInstance() {
      if (instance == null)
         instance = new RSSReader();
      return instance;
   }

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

   public String getValue(Element parent, String nodeName) {
      Node temp = parent.getElementsByTagName(nodeName).item(0).getFirstChild();
      
      return temp!=null?temp.getNodeValue():"";
   }

   public static void main(String[] args) {
      
      try {
         RSSOptions options = new RSSOptions();
         RSSReader reader = RSSReader.getInstance();
          for (RSSProvider rss : options.rssAvailible) {
              reader.setURL(rss.url);
              reader.writeFeed();
          }
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}