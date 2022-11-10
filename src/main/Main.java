
package main;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        
        model.autenticacao_GUI.DateAuth();
        try {
            model.autenticacao_GUI.Auth();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (model.autenticacao_GUI.token == true){
            new view.splash_GUI().setVisible(true);
        }else{
            System.exit(0);
        }
        
    }
    
}
