
package model;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class criar_r_DAO {
    
    static String url = "jdbc:mysql://localhost/salonplus";
    static String username = "root";
    static String password = "";
    
    public static void carregaReservas() throws SQLException{
        
        String SQL = "select * from reservas";
        
        DefaultTableModel model = (DefaultTableModel)  view.home_GUI.registros.getModel();
        model.setRowCount(0);
        
        Connection con = (Connection) DriverManager.getConnection(url, username, password);
        
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            
            while(rs.next()){
                String reservador = rs.getString("reservador");
                String data = rs.getString("data");
                String tempo = rs.getString("tempo");
                String sala = rs.getString("sala");
                
                String tBD[] = {reservador, data, tempo, sala};
                model.addRow(tBD);
                
            }
        }catch (Exception e){
        }
        
    }
    
    public static void registraReserva(){
        
        if ("Sala não selecionada".equals(view.home_GUI.sala.getText())){
            JOptionPane.showMessageDialog(null, "Selecione uma sala antes.");
        }else{
            
            String reservador = view.home_GUI.reservador.getText();
            String data = view.home_GUI.data.getText();
            String tempo = view.home_GUI.tempo.getText();
            String sala = view.home_GUI.sala.getText();
            String type = view.home_GUI.tipoData.getSelectedItem().toString().toLowerCase();
            
            
            controller.Conexao_BD.carregaDriver();

            Connection con = null;

            try{
                con = (Connection) DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                Logger.getLogger(criar_r_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            String SQL = "insert into reservas(reservador,data,tempo,sala) values(?,?,?,?)";

            try{
                PreparedStatement insert = (PreparedStatement) con.prepareStatement(SQL);
                insert.setString(1, reservador);
                insert.setString(2, data);
                insert.setString(3, tempo+" "+type);
                insert.setString(4, sala);

                insert.execute();

            }catch (Exception ex) {
                Logger.getLogger(criar_r_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                carregaReservas();
            } catch (SQLException ex) {
                Logger.getLogger(criar_r_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    
    
}
