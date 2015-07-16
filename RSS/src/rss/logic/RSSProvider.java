package rss.logic;

import java.net.MalformedURLException;
import java.net.URL;

public class RSSProvider {
    public String tema;
    public URL url;
    public boolean suscrito = false;

    public RSSProvider(String tema, String url) throws MalformedURLException {
        this.tema = tema;
        this.url = new URL(url);
    }

    public String getTema() {
        return tema;
    }

    public URL getUrl() {
        return url;
    }
}