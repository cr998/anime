/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.anime.downloader.maven.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.apache.commons.lang.ArrayUtils;

/**
 *
 * @author Cristian Martiez Montes desde IMac de cesar
 * @version 1.0.0-Alpha
 */
public class Imagen extends javax.swing.JPanel {

    private URL urlimagen;
    private ImageIcon imagen;
    private boolean error = false;

    public Imagen(URL urlimagen) {
        this.setSize(300, 400); //se selecciona el tamaño del panel
        this.urlimagen = urlimagen;
        getWebImg();

    }

    public Imagen(String urlimagen) {
        this.setSize(300, 400); //se selecciona el tamaño del panel
        try {
            this.urlimagen = new URL(urlimagen);
        } catch (MalformedURLException ex) {
            this.error = true;
            Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
            getWebImg();
        }
    }

    private void getWebImg() {

        new Thread() {
            @Override
            public void run() {
                try {
                    String path = "http://www.animeyt.tv/files/img/series/s_3167_poster.jpeg";
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url
                            .openConnection();
                    connection.setRequestProperty(
                            "User-Agent",
                            "Canadre");

                    BufferedImage bfimage = ImageIO.read(connection.getInputStream());
                    Imagen.this.imagen = new ImageIcon(bfimage);
                    

                } catch (IOException ex) {
                    Imagen.this.error = true;
                    Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        };
    }

    //Se crea un método cuyo parámetro debe ser un objeto Graphics
    public void paint(Graphics grafico) {
        Dimension height = getSize();

        //Se selecciona la imagen que tenemos en el paquete de la //ruta del programa

        //se dibuja la imagen que tenemos en el paquete Images //dentro de un panel
        grafico.drawImage(imagen.getImage(), 0, 0, height.width, height.height, null);
        setOpaque(false);
        super.paintComponent(grafico);
    }

}
