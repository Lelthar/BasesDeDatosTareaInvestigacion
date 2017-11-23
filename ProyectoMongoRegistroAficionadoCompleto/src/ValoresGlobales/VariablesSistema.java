/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ValoresGlobales;

/**
 *
 * @author Jeanca
 */
public class VariablesSistema {
    private static String NOMBRE_BD;
    private static String NOMBRE_USUARIO;
    private static String PASSWORD_USUARIO;
    
    // Métodos set
    public static void setNombreBD(String str) {
        NOMBRE_BD = str; 
    }
    
    public static void setNombreUsuario(String str) {
        NOMBRE_USUARIO = str;
    }
    
    public static void setPasswordUsuario(String str) {
        PASSWORD_USUARIO = str;
    }
    
    // Métodos get
    public static String getNombreBD() {
        return NOMBRE_BD;
    }
    
    public static String getNombreUsuario() {
        return NOMBRE_USUARIO;
    }
    
    public static String getPasswordUsuario() {
        return PASSWORD_USUARIO;
    }
}
