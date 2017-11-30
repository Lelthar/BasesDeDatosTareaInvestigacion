/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Gerald
 */
public class AgregarComentario extends javax.swing.JFrame {
    public int tamanoY = 300;
    public int posicionX = 10;
    public int posicionY = 10;
    
    private String numero_aficionado;
    
    private int numero_resumen;
    
    private int numero_comentarios;
    
    private DB baseDatos;
    
    private DBCollection tablaAficionado;
    private DBCollection tablaPartido;
    private DBCollection tablaResumen_partido;
    private DBCollection tablaVideos;
    private DBCollection tablaComentarios;
    private DBCollection tablaAficionado_borrado;
    
    ArrayList<String> videosPath = new ArrayList<>();
    /**
     * Creates new form AgregarComentario
     */
    public AgregarComentario(String aficionado, int pNumero_resumen) {
        initComponents();
        this.numero_aficionado = aficionado;
        
        this.numero_resumen = pNumero_resumen;
        
        obtenerTablas("MongoBaseDatos");
        obtenerComentarios();
        
        
    }
    
    /**
     * Conecta a NetBeans con Mongo.
     */
    private void obtenerTablas(String nombreBD) {
        Mongo mongo;
        try {
            mongo = new Mongo("localhost", 27017);  
            this.baseDatos = mongo.getDB(nombreBD);
            
            this.tablaAficionado = baseDatos.getCollection("aficionado");
            this.tablaResumen_partido = baseDatos.getCollection("resumen_partido");
            this.tablaVideos = baseDatos.getCollection("videos");
            this.tablaComentarios = baseDatos.getCollection("comentarios");
            this.tablaPartido = baseDatos.getCollection("partido");
            this.tablaAficionado_borrado = baseDatos.getCollection("aficionado_borrado");
            
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.toString());
        }
    }
    
    private void obtenerComentarios(){
        BasicDBObject documento = new BasicDBObject();
        documento.put("codigo_aficionado", "ADMINISTRADOR");
        
        DBCursor  cursorComentarios = this.tablaComentarios.find();
        numero_comentarios = this.tablaComentarios.find().count() + 1;
        System.out.println(numero_comentarios);
        
        while(cursorComentarios.hasNext()) {
            BasicDBObject consulta = new BasicDBObject();
            DBObject tuplaComentario = cursorComentarios.next();
            consulta.put("codigo_aficionado", tuplaComentario.get("codigo_aficionado").toString());
            
            if( Math.round(Float.parseFloat(tuplaComentario.get("numero_resumen").toString())) == this.numero_resumen && tuplaComentario.get("comentario_padre").toString().equals("null") && this.tablaAficionado_borrado.find(consulta).count() == 0){
                
                String numeroPadre = tuplaComentario.get("numero_comentario").toString();
                DBCursor cursorComentariosHijos = this.tablaComentarios.find();
                
                String numeroComentario = tuplaComentario.get("numero_comentario").toString();
                
                BasicDBObject consultaNombreAficionado = new BasicDBObject();
                consultaNombreAficionado.put("codigo_aficionado", tuplaComentario.get("codigo_aficionado").toString());
                String foto = this.tablaAficionado.find(consultaNombreAficionado).next().get("foto_aficionado").toString();
                String correo_aficionado = this.tablaAficionado.find(consultaNombreAficionado).next().get("correo_electronico").toString();
                
                String codigo_aficionado = tuplaComentario.get("codigo_aficionado").toString();
               
                String comentario_texto = tuplaComentario.get("comentario").toString();
                
                agregarComentario(numeroComentario,codigo_aficionado,correo_aficionado,comentario_texto,foto,posicionX,posicionY,true);
                
                this.posicionY += 155;
                this.tamanoY += 155;
                this.panelScroll.setPreferredSize(new Dimension(897, this.tamanoY));
                
                while(cursorComentariosHijos.hasNext()){
                    
                    BasicDBObject consultaHijo = new BasicDBObject();
                    DBObject tuplaComentarioHijo = cursorComentariosHijos.next();
                    consultaHijo.put("codigo_aficionado", tuplaComentarioHijo.get("codigo_aficionado").toString());
                    
                    if(Math.round(Float.parseFloat(tuplaComentarioHijo.get("numero_resumen").toString())) == this.numero_resumen && tuplaComentarioHijo.get("comentario_padre").toString().equals(Integer.toString(Math.round(Float.parseFloat(numeroPadre)))) && this.tablaAficionado_borrado.find(consultaHijo).count() == 0){
                        
                        String numeroComentarioHijo = tuplaComentarioHijo.get("numero_comentario").toString();
                
                        BasicDBObject consultaNombreAficionadoHijo = new BasicDBObject();
                        consultaNombreAficionadoHijo.put("codigo_aficionado", tuplaComentarioHijo.get("codigo_aficionado").toString());
                        String fotoHijo = this.tablaAficionado.find(consultaNombreAficionadoHijo).next().get("foto_aficionado").toString();
                        String correo_aficionadoHijo = this.tablaAficionado.find(consultaNombreAficionadoHijo).next().get("correo_electronico").toString();

                        String codigo_aficionadHijoo = tuplaComentarioHijo.get("codigo_aficionado").toString();

                        String comentario_textoHijo = tuplaComentarioHijo.get("comentario").toString();
                        agregarComentario(numeroComentarioHijo,codigo_aficionadHijoo,correo_aficionadoHijo,comentario_textoHijo,fotoHijo,posicionX+50,posicionY,false);

                        this.posicionY += 155;
                        this.tamanoY += 155;
                        this.panelScroll.setPreferredSize(new Dimension(897, this.tamanoY));
                    }else if(Math.round(Float.parseFloat(tuplaComentarioHijo.get("numero_resumen").toString())) == this.numero_resumen && this.tablaAficionado_borrado.find(consultaHijo).count() > 0){
                         
                        DBCursor cursorAficionadoEliminado = this.tablaAficionado_borrado.find(consultaHijo);
                        
                        comentarioEliminado(this.posicionX,this.posicionY,cursorAficionadoEliminado.next().get("Aqui va fecha y hora").toString());
                        this.posicionY += 155;
                        this.tamanoY += 155;
                        this.panelScroll.setPreferredSize(new Dimension(897, this.tamanoY));
                    }
                }
            }else if(Math.round(Float.parseFloat(tuplaComentario.get("numero_resumen").toString())) == this.numero_resumen && this.tablaAficionado_borrado.find(consulta).count() > 0){
                
                DBCursor cursorAficionadoEliminado = this.tablaAficionado_borrado.find(consulta);
              
                comentarioEliminado(this.posicionX,this.posicionY,cursorAficionadoEliminado.next().get("Aqui va fecha y hora").toString());
                this.posicionY += 155;
                this.tamanoY += 155;
                this.panelScroll.setPreferredSize(new Dimension(897, this.tamanoY));
            }
            
        }
        
        BasicDBObject consultaVideos = new BasicDBObject();
        consultaVideos.put("numero_resumen", this.numero_resumen);
        
        DBCursor  cursorVideos = this.tablaVideos.find(consultaVideos);
        
        while(cursorVideos.hasNext()) {
            DBObject tuplasVideos = cursorVideos.next();
            this.videosPath.add(tuplasVideos.get("video").toString());
        }
        
        BasicDBObject consultaResumenes = new BasicDBObject();
        consultaResumenes.put("numero_resumen", this.numero_resumen);
        
        DBCursor cursorResumen  = this.tablaResumen_partido.find(consultaResumenes);
        DBObject tuplaResumen = null;
        if(cursorResumen.hasNext()){
            tuplaResumen = cursorResumen.next();
        }
        
        
        textAreaResumen.setText(tuplaResumen.get("texto_resumen").toString());
        
        BasicDBObject consultaPartidos = new BasicDBObject();
        int casa = Math.round(Float.parseFloat(tuplaResumen.get("numero_partido").toString()));
        System.out.println(casa);
        consultaPartidos.put("numero_partido", casa);
        
        DBCursor cursorPartido  = this.tablaPartido.find(consultaPartidos);
        DBObject tuplaPartido = null; 
        if(cursorPartido.hasNext()){
            tuplaPartido = cursorPartido.next();
        }
        lblEquipo1.setText(tuplaPartido.get("equipo1").toString());
        lblEquipo2.setText(tuplaPartido.get("equipo2").toString());
    }

    public void agregarComentario(String numero, String nombre, String correo, String comentario, String foto, int posicionX, int posicionY, boolean padre){
        
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBounds(posicionX,posicionY,866,145);
        panelPrincipal.setBackground(Color.gray);
        
        JLabel numeroLbl = new JLabel();
        numeroLbl.setBounds(6,6,30,16);
        numeroLbl.setText(numero);
        
        panelPrincipal.add(numeroLbl);
        
        
        JLabel nombreLbl = new JLabel();
        nombreLbl.setText(nombre);
        nombreLbl.setBounds(196,6,130,16);
        
        JLabel correooLbl = new JLabel();
        correooLbl.setText(correo);
        correooLbl.setBounds(317,6,190,16);
        
        JLabel fotoLbl = new JLabel();
        ImageIcon fotoIcono = new ImageIcon(foto);
        Icon iconFoto = new ImageIcon(fotoIcono.getImage().getScaledInstance(73, 67, Image.SCALE_SMOOTH));
        fotoLbl.setIcon(iconFoto);
        fotoLbl.setBounds(6,34,73,67);
        
        if(padre){
            JButton replayBtn = new JButton();
            replayBtn.setBounds(6,113,60,26);
            replayBtn.setText("Reply");
            
            replayBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirVentanaComentarActionPerformed(evt);
            }
        });
            
            panelPrincipal.add(replayBtn);
        }
 
        JScrollPane scrollPanelNuevo = new JScrollPane();
        scrollPanelNuevo.setBounds(78,34,782,97);
        JTextArea comentarios = new JTextArea();
        comentarios.setBounds(0,0,776,99);
        comentarios.setText(comentario);
        scrollPanelNuevo.setViewportView(comentarios);
        
        
        panelPrincipal.add(nombreLbl);
        panelPrincipal.add(correooLbl);
        panelPrincipal.add(fotoLbl);
        
        panelPrincipal.add(scrollPanelNuevo);
        
        
        panelScroll.add(panelPrincipal);
        
    }
    
    private void abrirVentanaComentarActionPerformed(java.awt.event.ActionEvent evt) {       
        VentanaComentarioHijo ventanaComentario = new VentanaComentarioHijo(this.numero_comentarios, this.numero_resumen, numero_aficionado);
        this.setVisible(false);
        ventanaComentario.setVisible(true);
    }
    
    public void comentarioEliminado( int posicionX, int posicionY,String fechahora){
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        
        panelPrincipal.setBounds(posicionX,posicionY,866,145);
        panelPrincipal.setBackground(Color.gray);
        
        JLabel borrado = new JLabel();
        borrado.setBounds(300,30,78,50);
        borrado.setText("Borrado");
        borrado.setFont(new Font("Serif", Font.PLAIN, 20));
        
        JLabel fechaHoraLb = new JLabel();
        fechaHoraLb.setBounds(250,60,350,50);
        fechaHoraLb.setText("Fecha de eliminacion: "+fechahora);
        fechaHoraLb.setFont(new Font("Serif", Font.PLAIN, 20));
        
        panelPrincipal.add(borrado);
        panelPrincipal.add(fechaHoraLb);
        panelScroll.add(panelPrincipal);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaResumen = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        lblEquipo1 = new javax.swing.JLabel();
        lblEquipo2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        comboBoxVideos = new javax.swing.JComboBox<>();
        btnVerVideo = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelScroll = new javax.swing.JPanel();
        btnComentar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Equipo2:");

        jLabel2.setText("Equipo1:");

        textAreaResumen.setColumns(20);
        textAreaResumen.setRows(5);
        jScrollPane2.setViewportView(textAreaResumen);

        jLabel3.setText("Resumen:");

        jLabel6.setText("Videos:");

        comboBoxVideos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "video 1", "video 2" }));

        btnVerVideo.setText("Ver");
        btnVerVideo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerVideoActionPerformed(evt);
            }
        });

        jLabel7.setText("Comentarios: ");

        panelScroll.setPreferredSize(new java.awt.Dimension(897, 300));

        javax.swing.GroupLayout panelScrollLayout = new javax.swing.GroupLayout(panelScroll);
        panelScroll.setLayout(panelScrollLayout);
        panelScrollLayout.setHorizontalGroup(
            panelScrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 897, Short.MAX_VALUE)
        );
        panelScrollLayout.setVerticalGroup(
            panelScrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelScroll);

        btnComentar.setText("Comentar");
        btnComentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComentarActionPerformed(evt);
            }
        });

        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEquipo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEquipo1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 265, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnVerVideo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(comboBoxVideos, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(47, 47, 47))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnComentar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblEquipo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxVideos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEquipo2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnVerVideo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnComentar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerVideoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerVideoActionPerformed
        // TODO add your handling code here:
        /*y+= 110;
        panelScroll.setPreferredSize(new Dimension(897, this.y));*/
        String video = comboBoxVideos.getSelectedItem().toString();
        System.out.println(video);
        if(video.equals("video 1")){
            try {
                Desktop.getDesktop().browse(new URI(videosPath.get(0)));
            } catch (IOException ex) {
                Logger.getLogger(AgregarComentario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
                Logger.getLogger(AgregarComentario.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }else{
            try {
                Desktop.getDesktop().browse(new URI(videosPath.get(1)));
            } catch (IOException ex) {
                Logger.getLogger(AgregarComentario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
                Logger.getLogger(AgregarComentario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_btnVerVideoActionPerformed

    private void btnComentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComentarActionPerformed
        // TODO add your handling code here:
        VentanaComentario ventanaComentario = new VentanaComentario(this.numero_comentarios, this.numero_resumen, numero_aficionado);
        this.setVisible(false);
        ventanaComentario.setVisible(true);
    }//GEN-LAST:event_btnComentarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AgregarComentario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarComentario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarComentario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarComentario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgregarComentario("ADMINISTRADOR",1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComentar;
    private javax.swing.JButton btnVerVideo;
    private javax.swing.JComboBox<String> comboBoxVideos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblEquipo1;
    private javax.swing.JLabel lblEquipo2;
    private javax.swing.JPanel panelScroll;
    private javax.swing.JTextArea textAreaResumen;
    // End of variables declaration//GEN-END:variables
}
