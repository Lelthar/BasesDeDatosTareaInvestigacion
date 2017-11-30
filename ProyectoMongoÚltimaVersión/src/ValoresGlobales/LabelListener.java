/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ValoresGlobales;

import Interfaz.AgregarComentario;
import Interfaz.CRUDComentario;
import Interfaz.CRUDPartido;
import Interfaz.CRUDResumen;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Jeanca
 */
public class LabelListener {
    
    public static void createListener(JLabel jLabel, int opcion) {
        JLabel jLabelListener = jLabel;
        jLabelListener.addMouseListener(new MouseListener(){
            
            @Override
            public void mouseClicked(MouseEvent e) { 
                jLabelListener.setForeground(new java.awt.Color(66, 73, 73));
            }
            
            @Override
            @SuppressWarnings("ConvertToStringSwitch")
            public void mousePressed(MouseEvent e) {
                jLabelListener.setForeground(new java.awt.Color(66, 73, 73));
                switch (opcion) {

                    // CRUD partidos.
                    case 1:
                        if (VariablesSistema.getNombreUsuario().equals("")) {
                            JOptionPane.showMessageDialog(null, "ERROR: debe iniciar sesión primero.", "", JOptionPane.ERROR_MESSAGE);
                        } else if (VariablesSistema.getNombreUsuario().equals("ADMINISTRADOR")){
                            CRUDResumen partido = new CRUDResumen();
                            partido.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "ERROR: solo ADMINISTRADOR puede acceder a esta función.", "", JOptionPane.ERROR_MESSAGE);
                        }
                        break;

                    // CRUD comentarios.
                    case 2:
                        if (!VariablesSistema.getNombreUsuario().equals("")) {
                            CRUDComentario comentario = new CRUDComentario();
                            comentario.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "ERROR: debe iniciar sesión primero.", "", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    
                    // Consulta de un Resumen.
                    case 3:
                        if (!VariablesSistema.getNombreUsuario().equals("")) {
                            JTextField entradaPartido = new JTextField();
                            Object[] mensaje = { "Numero de resumen: ", entradaPartido };
                            int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Llene la siguiente información: ", JOptionPane.OK_CANCEL_OPTION);
                            if (opcion == JOptionPane.OK_OPTION) {
                                String partido = entradaPartido.getText();
                                if (partido.equals("")) {
                                    
                                } else {
                                    
                                    AgregarComentario ventanaResumenes = new AgregarComentario(VariablesSistema.getNombreUsuario(),Integer.parseInt(partido));
                                    ventanaResumenes.setVisible(true);
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "ERROR: debe iniciar sesión primero.", "", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                jLabelListener.setForeground(Color.WHITE);
            }

            @Override
            public void mouseEntered(MouseEvent e) { 
                jLabelListener.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) { 
                jLabelListener.setForeground(Color.WHITE);
            }
        });
    }    
}
