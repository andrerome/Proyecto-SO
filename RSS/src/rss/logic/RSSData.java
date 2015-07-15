package rss.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class RSSData {
    // TODO: Implementar una clase genérica para los datos de una entrada RSS
    public String title="";
    public String link="";
    public String description="";
    public String pubDate; //TODO: guaradar como Date

   //final List<FeedMessage> entries = new ArrayList<FeedMessage>();

    public RSSData(String title, String link, String description, String language,
        String copyright, String pubDate) {
          this.title = title;
          this.link = link;
          this.description = description;
          this.pubDate = pubDate;
    }

    /*
    public List<FeedMessage> getMessages() {
      return entries;
    }
    */
  
 
    public String getTitle() {
      return title;
    }

    public String getLink() {
      return link;
    }

    public String getDescription() {
      return description;
    }

    public String getPubDate() {
      return pubDate;
    }

    @Override
    public String toString() {
      return "Feed [title=" + title +",description=" + description
          + ", link=" + link + ", pubDate="
          + pubDate + "]";
    }


    public RSSData(Element item) {
        this.title = RSSReader.getValue(item, "title");
        this.link = RSSReader.getValue(item, "link");  
        this.description = RSSReader.getValue(item, "description");
        this.pubDate = RSSReader.getValue(item, "pubDate");
        
        /*SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy  HH:mm:ss");
        try {
           this.pubDate = formatter.parse(RSSReader.getValue(item, "pubDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }*/                      
    }
}