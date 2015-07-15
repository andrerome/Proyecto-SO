/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rss;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author joalguzm
 */
public class RSSProvider {
    public String tema;
    public URL url;  

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
