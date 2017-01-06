/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.anime.downloader.maven.series;

import com.mycompany.anime.downloader.maven.descarga.Descarga;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author alumno
 */
public class Serie {
    
    private int id;
    private String name;
    private String urlcapitulo[];
    private int capitulos;
    private String url;
    private String[] categorias;

    /**
     * Método que convierte una cadena de la forma "yyyy-MM-dd HH:mm" a Date
     * <PRE> Clase_Java cj = new Clase_Java();
     * Date date = cj.StringToDate("2012-10-01 12:00")</PRE>
     * @param id String que debe tener la forma "yyyy-MM-dd HH:mm"
     * @return Date Un objeto Date con la fecha parseada
     * @exception ParseException Error de parseo, ocurre cuando no se puede convertir un String a Date          
     * @since incluido desde la version 1.0
     */
    public Serie(int id,String url) {
        this.id=id;
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
            des=new Descarga[urlcapitulo.length];
            for (int i = 0; i < urlcapitulo.length; i++) {
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

            this.name = "";
            for (String string : url.split("/")[3].split("-")) {
                name += string + " ";
            }

            Elements vector = doc.getElementsByClass("serie-capitulos__list__item");
            urlcapitulo = new String[vector.size()];
            capitulos = urlcapitulo.length;
            
            
            for (int i = 0; i < vector.size(); i++) {
                urlcapitulo[vector.size() - i - 1] = (String) vector.get(i).child(1).attr("href");
                
            }


            //name=doc.getElementsByClass("serie-header__title").html();
//            Elements vector=doc.getElementsByClass("serie-capitulos__list__item");
//            for (int i = 0; i < vector.toArray().length; i++) {
//                System.out.println(vector.get(i).html());
//            }
        } catch (IOException ex) {
            Logger.getLogger(Serie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getName() {
        return name;
    }

    public String[] getUrlcapitulo() {
        return urlcapitulo;
    }

    public int getCapitulos() {
        return capitulos;
    }

    public String[] getCategorias() {
        return categorias;
    }

    public String getUrl() {
        return url;
    }
    
    

}
    