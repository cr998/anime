/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.anime.downloader.maven.series;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author cr998
 */
public class ScanSeries {

    private static int total;

    public static ArrayList<Serie> search(String termino) throws IOException {
        ArrayList<Serie> series = new ArrayList<Serie>();

        try{
        int page = 1;
        while (true) {
            series.addAll(getUrlSeries("http://www.animeyt.tv/busqueda?terminos=" + termino + "&", page));
            page++;
        }
        }catch(ArrayIndexOutOfBoundsException e){
            
        }
        
        return series;
    }

    public static ArrayList<Serie> getUrlSeries(String urlbase, int i) throws ArrayIndexOutOfBoundsException {
        ArrayList<Serie> seriesperpage = new ArrayList<Serie>();

        try {
            Document doc = Jsoup.connect(urlbase + "page=" + i).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36s").get();
            Elements animes = doc.getElementsByClass("anime__img-container");

            if (animes.size() <= 0) {
                throw new ArrayIndexOutOfBoundsException();
            }

            for (Element anime : animes) {
                seriesperpage.add(new Serie(anime.attr("href")));
            }

        } catch (IOException ex) {
            Logger.getLogger(ScanSeries.class.getName()).log(Level.SEVERE, null, ex);
        }

        return seriesperpage;
    }
}
