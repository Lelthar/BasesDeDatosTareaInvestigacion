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
import com.mongodb.Mongo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
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
    int y = 300;
    
    private DB baseDatos;
    private DBCollection tabla;
    
    ArrayList<String> videosPath = new ArrayList<>();
    /**
     * Creates new form AgregarComentario
     */
    public AgregarComentario() {
        initComponents();
        //this.setLayout(null);
 
        
        /*ImageIcon foto = new ImageIcon("src/Complementos/flecha.png");
        Icon iconFoto = new ImageIcon(foto.getImage().getScaledInstance(this.lblFoto.getWidth(), 
                        this.lblFoto.getWidth(), Image.SCALE_SMOOTH));
        this.lblFoto.setIcon(iconFoto);*/
        //panelScroll.setLayout(new GridLayout(6, 1));

        
       /* System.out.println("Numero "+lblNumero.getLocation());
        System.out.println("Nombre "+lblNombre.getLocation());
        System.out.println("Correo "+lblCorreo.getLocation());
        System.out.println("Comentario "+lblScroll.getLocation());
        System.out.println("Foto "+lblFoto.getLocation());
        System.out.println("Reply "+lblReply.getLocation());
        System.out.println("Panel "+panelComentario.getLocation());*/
       
        agregarComentario("1", "Puto", "putoelquelolea@gmail.com", "puto el que lo lea", "src/Complementos/flecha.png", 10, 10,true);
        agregarComentario("2", "Puto2", "putoelquelolea2@gmail.com", "puto el que lo lea x2", "src/Complementos/flecha.png", 50, 165,false);
        agregarComentario("3", "Puto3", "putoelquelolea@gmail.com", "puto el que lo lea", "src/Complementos/flecha.png", 10, 320,true);
        mongoConexionAficionado("MongoBaseDatos","aficionado");
        obtenerComentarios();
    }
    
    /**
     * Conecta a NetBeans con Mongo.
     */
    private void mongoConexionAficionado(String nombreBD, String nombreTabla) {
        Mongo mongo;
        try {
            mongo = new Mongo("localhost", 27017);  
            this.baseDatos = mongo.getDB(nombreBD);
            this.tabla = baseDatos.getCollection(nombreTabla);
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.toString());
        }
    }
    
    private void obtenerComentarios(){
        BasicDBObject documento = new BasicDBObject();
        documento.put("codigo_aficionado", "ADMINISTRADOR");
        DBCursor  cursor = this.tabla.find(documento);
        while(cursor.hasNext()) {
            System.out.println(cursor.next());
        }
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

        panelScroll.setPreferredSize(new java.awt.Dimension(897, 1000));

        javax.swing.GroupLayout panelScrollLayout = new javax.swing.GroupLayout(panelScroll);
        panelScroll.setLayout(panelScrollLayout);
        panelScrollLayout.setHorizontalGroup(
            panelScrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 897, Short.MAX_VALUE)
        );
        panelScrollLayout.setVerticalGroup(
            panelScrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelScroll);

        btnComentar.setText("Comentar");

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
                            .addComponent(comboBoxVideos, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVerVideo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEquipo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
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
                Desktop.getDesktop().open(new File(videosPath.get(0)));
            } catch (IOException ex) {
                Logger.getLogger(AgregarComentario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                Desktop.getDesktop().open(new File(videosPath.get(1)));
            } catch (IOException ex) {
                Logger.getLogger(AgregarComentario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_btnVerVideoActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgregarComentario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComentar;
    private javax.swing.JButton btnVerVideo;
    private javax.swing.JComboBox<String> comboBoxVideos;
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
