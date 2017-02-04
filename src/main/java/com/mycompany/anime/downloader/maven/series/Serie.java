/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.anime.downloader.maven.series;

import com.mycompany.anime.downloader.maven.descarga.Descarga;
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
 * @author alumno
 */
public class Serie {
    
    private int id;
    private String urlimagen;
    private String name;
    private ArrayList<String> urlcapitulo;
    private int capitulos;
    private String url;
    private ArrayList<String> categorias;
    private String estado;

    
    public Serie(int id,String url) {
        this.id=id;
        this.url = url;
    }

    public Serie(String url) {
        this.url = url;
    }
    
    public boolean isDescargable() {
        if (capitulos < 30) {
            return true;
        } else {
            return false;
        }
    }

    public Descarga[] descargar(String dirsalida) {
        
        Descarga[] des = null;
        if (urlcapitulo == null) {
            System.out.println("olle, no has obtenido los datos antes de descargar");
        } else {
            des=new Descarga[urlcapitulo.size()];
            for (int i = 0; i < urlcapitulo.size(); i++) {
                //des[i]=new Descarga(urlcapitulo[i], dirsalida+this.name+"\\");
            }
        }
        return des;
    }

    
    /*
    *
    */
    public void getData() {
        try {
            Document doc = Jsoup.connect(url).userAgent("mozilla").get();
            
            this.name=doc.select(".serie-header__title").text();
            this.capitulos=doc.select(".serie-capitulos__list__item").size();
            this.categorias = new ArrayList<String>();
            for (Element e : doc.select(".serie-header__genero:lt(1) > li > a")){
                this.categorias.add(e.text());
            }
            this.urlcapitulo=new ArrayList<String>();
            for (Element e : doc.select(".serie-capitulos__list__item > a")){
                this.urlcapitulo.add(e.attr("href"));
            }
            this.estado=doc.select(".serie-header__genero:lt(2) > li > a").text();
            this.urlimagen=doc.select(".serie-header__img").attr("src");
            
            
        } catch (IOException ex) {
            Logger.getLogger(Serie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUrlimagen() {
        return urlimagen;
    }

    public void setUrlimagen(String urlimagen) {
        this.urlimagen = urlimagen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getName() {
        return name;
    }

    public int getCapitulos() {
        return capitulos;
    }

    public ArrayList<String> getUrlcapitulo() {
        return urlcapitulo;
    }

    public ArrayList<String> getCategorias() {
        return categorias;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for (String cat : this.categorias) {
            sb.append(" "+cat+" ");
        }
        return this.name+"  ->  "+sb.toString();
    }
  
    
    

}
    