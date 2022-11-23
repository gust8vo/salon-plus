
package controller;

import javax.swing.JOptionPane;

public class Conexao_BD {
    
    public static  void carregaDriver(){
       try { // Conecttando o driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            

       } catch (Exception ex) { // Erro driver não encontrado
            JOptionPane.showMessageDialog(null, "O sistema não conseguiu conectar o driver.", "Erro [001] Encontrado.", 0);
            System.exit(0);
        }

    }
    
}
