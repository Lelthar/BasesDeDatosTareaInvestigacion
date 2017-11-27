/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ValoresGlobales;

import Interfaz.CRUDComentario;
import Interfaz.CRUDPartido;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
                            CRUDPartido partido = new CRUDPartido();
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
