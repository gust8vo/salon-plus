
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class autenticacao_GUI {
    
    static String url = "jdbc:mysql://localhost/salonplus";
    static String username = "root";
    static String password = "";
    
    public static boolean token = false;
    
    public static void Auth() throws SQLException{
        
        String SQL = "select * from autenticador";
        
        String keys = JOptionPane.showInputDialog(null, "Digite sua chave de autenticação").toLowerCase();
        
        Connection con = (Connection) DriverManager.getConnection(url, username, password);
        
        try{
            
            PreparedStatement pst = con.prepareStatement(SQL);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                if ("seap294195".equals(rs.getString("user").toLowerCase())){
                    
                    if (keys.equals(rs.getString("key").toLowerCase())){

                        if ("true".equals(rs.getString("validacao").toLowerCase())){
                            token = true;
                        }else{
                            System.exit(0);
                        }
                    
                    }
                    
                }
        }
        }catch (Exception e){
        }
        
    }
    
    public static void DateAuth(){
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = new java.util.Date();
        String data = dateFormat.format(date);
        
        if (data.equals("30/11/2023")){
            JOptionPane.showMessageDialog(null, "Seu software não está atualizado");
            System.exit(0);
        }
        
    }
    
}
