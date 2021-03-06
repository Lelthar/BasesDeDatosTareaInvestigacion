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
import java.net.UnknownHostException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Jeanca
 */
public class CRUDComentario extends javax.swing.JFrame {

    private DB baseDatos;
    private DBCollection tabla;
    
    /**
     * Creates new form CRUDComentario
     */
    public CRUDComentario() {
        initComponents();
        mongoConexionAficionado("MongoBaseDatos", "comentario");
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ver_jButton = new javax.swing.JButton();
        borrar_jButton = new javax.swing.JButton();
        modificar_jButton = new javax.swing.JButton();
        crear_jButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        mis_comentarios_jTextArea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Opciones:");

        ver_jButton.setText("Ver");
        ver_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ver_jButtonActionPerformed(evt);
            }
        });

        borrar_jButton.setText("Borrar ");
        borrar_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrar_jButtonActionPerformed(evt);
            }
        });

        modificar_jButton.setText("Modificar");
        modificar_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificar_jButtonActionPerformed(evt);
            }
        });

        crear_jButton.setText("Crear");
        crear_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crear_jButtonActionPerformed(evt);
            }
        });

        mis_comentarios_jTextArea.setColumns(20);
        mis_comentarios_jTextArea.setRows(5);
        jScrollPane1.setViewportView(mis_comentarios_jTextArea);

        jLabel2.setText("Mis comentarios:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(modificar_jButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(borrar_jButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ver_jButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(crear_jButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(ver_jButton)
                        .addGap(18, 18, 18)
                        .addComponent(borrar_jButton)
                        .addGap(19, 19, 19)
                        .addComponent(modificar_jButton)
                        .addGap(18, 18, 18)
                        .addComponent(crear_jButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Obtiene los comentarios de la Base de Datos.
     * @param evt 
     */
    private void ver_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ver_jButtonActionPerformed
        // TODO add your handling code here:
        this.mis_comentarios_jTextArea.setText("");
        DBCursor cursor = this.tabla.find();
        
        // Ciclo que recupera las tuplas
        while(cursor.hasNext()){
            this.mis_comentarios_jTextArea.append(cursor.next() + "\n");
        } 
    }//GEN-LAST:event_ver_jButtonActionPerformed

    /**
     * Borra un comentario de la Base de Datos.
     * @param evt 
     */
    private void borrar_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrar_jButtonActionPerformed
        // TODO add your handling code here:
        JTextField num_comentario_JTextField = new JTextField();
        Object[] mensaje = { "Número de Comentario: ", num_comentario_JTextField };
        int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Eliminar Comentario ", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            String numComentario = num_comentario_JTextField.getText();
            if (numComentario.equals("")) {
                JOptionPane.showMessageDialog(null, "ERROR: la entrada está vacía.", "", JOptionPane.ERROR_MESSAGE);
            } else {
                BasicDBObject documento = new BasicDBObject();
                documento.put("id_comentario", Float.parseFloat(numComentario)); // FLOAT
                DBCursor cursor = this.tabla.find(documento);
                if (cursor.count() == 0) {
                    JOptionPane.showMessageDialog(null, "ERROR: el comentario que desea eliminar no existe.", "", JOptionPane.ERROR_MESSAGE);
                } else {
                    this.tabla.remove(documento);
                    JOptionPane.showMessageDialog(null, "El elemento se ha eliminado correctamente.");
                }
            }
        }
    }//GEN-LAST:event_borrar_jButtonActionPerformed

    /**
     * Modifica un comentario de la Base de Datos.
     * @param evt 
     */
    private void modificar_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificar_jButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modificar_jButtonActionPerformed

    /**
     * Creaun comentario y lo agrega a la Base de Datos.
     * @param evt 
     */
    private void crear_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crear_jButtonActionPerformed
        // TODO add your handling code here:
        JTextArea comentario_JTextArea = new JTextArea();        
        Object[] mensaje = { "Escriba un comentario: ", comentario_JTextArea };
        int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Crear Comentario ", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            String texto = comentario_JTextArea.getText();
            if (texto.equals("")) {
                JOptionPane.showMessageDialog(null, "ERROR: el comentario debe contener texto.", "", JOptionPane.ERROR_MESSAGE);
            } else {
                int numRegistros = this.tabla.find().count();
                float id_comentario;
                DBCursor cursor = this.tabla.find().skip(numRegistros - 1);
                BasicDBObject documento = new BasicDBObject();
                Date date = new Date();
                id_comentario = Float.parseFloat(cursor.next().get("id_comentario").toString()) + 1;
                documento.put("id_comentario", id_comentario);
                documento.put("texto", texto);
                documento.put("fecha", date.getDate());
                this.tabla.insert(documento);
                JOptionPane.showMessageDialog(null, "El elemento se ha agregado correctamente.");
            }
        }
    }//GEN-LAST:event_crear_jButtonActionPerformed

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
            java.util.logging.Logger.getLogger(CRUDComentario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CRUDComentario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CRUDComentario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CRUDComentario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CRUDComentario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton borrar_jButton;
    private javax.swing.JButton crear_jButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea mis_comentarios_jTextArea;
    private javax.swing.JButton modificar_jButton;
    private javax.swing.JButton ver_jButton;
    // End of variables declaration//GEN-END:variables
}
