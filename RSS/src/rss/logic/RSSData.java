package rss.logic;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class RSSData {
    public String title="";
    public String link="";
    public String description="";
    public String pubDate; //TODO: guaradar como Date
    
    public RSSData(String title, String link, String description, String language,
        String copyright, String pubDate) {
          this.title = title;
          this.link = link;
          this.description = description;
          this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return title;
    }
    
    public RSSData(Element item) {
        this.title = getValue(item, "title");
        this.link = getValue(item, "link");  
        this.description = getValue(item, "description");
        this.pubDate = getValue(item, "pubDate");
        
        /*SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy  HH:mm:ss");
        try {
           this.pubDate = formatter.parse(RSSReader.getValue(item, "pubDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
    }
    
    public static String getValue(Element parent, String nodeName) {
        Node temp = parent.getElementsByTagName(nodeName).item(0).getFirstChild();
        return temp!=null?temp.getNodeValue():"";
    }
}