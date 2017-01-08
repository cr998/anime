/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.anime.downloader.maven.graphics;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Cristian Martiez Montes desde IMac de cesar
 * @version 1.0.0-Alpha
 */
public class Imagen extends javax.swing.JLabel {

    private URL urlimagen;
    private ImageIcon imagen;
    private boolean error = false;

    public Imagen(URL urlimagen) {
        this.urlimagen = urlimagen;
        getWebImg();

    }

    public Imagen(String urlimagen) {
        try {
            this.urlimagen = new URL(urlimagen);
            getWebImg();
        } catch (MalformedURLException ex) {
            this.error = true;
            Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    private void getWebImg() {

                try {
                    HttpURLConnection connection = (HttpURLConnection) urlimagen
                            .openConnection();
                    connection.setRequestProperty(
                            "User-Agent",
                            "Canadre");

                    BufferedImage bfimage = ImageIO.read(connection.getInputStream());
                    this.imagen = new ImageIcon(bfimage);
                    this.setSize(new Dimension(imagen.getIconWidth(),imagen.getIconHeight()));
                    this.setIcon(imagen);
                    System.out.println("Imagen obtenida");
                    

                } catch (IOException ex) {
                    Imagen.this.error = true;
                    Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
                }
            
    }
    
    

}
