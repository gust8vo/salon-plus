
package model;

import com.mysql.jdbc.Connection;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                String hora = rs.getString("horario");
                
                String tBD[] = {reservador, sala, hora, data, tempo};
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
            String hora = view.home_GUI.horario.getText();
            String type = view.home_GUI.tipoData.getSelectedItem().toString().toLowerCase();
            
            
            controller.Conexao_BD.carregaDriver();

            Connection con = null;

            try{
                con = (Connection) DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                Logger.getLogger(criar_r_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            String SQL = "insert into reservas(reservador,sala,horario,data,tempo) values(?,?,?,?,?)";

            try{
                PreparedStatement insert = (PreparedStatement) con.prepareStatement(SQL);
                insert.setString(1, reservador);
                insert.setString(2, sala);
                insert.setString(3, hora);
                insert.setString(4, data);
                insert.setString(5, tempo+" "+type);

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
    
    public static void baixarReserva(){
        
        controller.Conexao_BD.carregaDriver();

        Connection con = null;
        
        try{
            con = (Connection) DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(criar_r_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DefaultTableModel model = (DefaultTableModel) view.home_GUI.registros.getModel();
        int row = view.home_GUI.registros.getSelectedRow();
        String responsavel = view.home_GUI.registros.getModel().getValueAt(row, 0).toString();
        String sala = view.home_GUI.registros.getModel().getValueAt(row, 1).toString();
        String hora = view.home_GUI.registros.getModel().getValueAt(row, 2).toString();
        String data = view.home_GUI.registros.getModel().getValueAt(row, 3).toString();
        
        String SQL = "delete from reservas where reservador=? and sala=? and horario=? and data=?";
        
        try{
            PreparedStatement insert = (PreparedStatement) con.prepareStatement(SQL);
            insert.setString(1, responsavel);
            insert.setString(2, sala);
            insert.setString(3, hora);
            insert.setString(4, data);
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
    
    public static void gerarRelatorio() throws SQLException{
        
        String SQL = "select * from reservas";
        
        Connection con = (Connection) DriverManager.getConnection(url, username, password);
        
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
            String horaFormatada = formatterHora.format(agora);
            
            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
            String dataFormatada = formatterData.format(agora);
            
            while(rs.next()){
                String reservador = rs.getString("reservador");
                String data = rs.getString("data");
                String tempo = rs.getString("tempo");
                String sala = rs.getString("sala");
                String hora = rs.getString("horario");
                
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("logs.txt", true)));
                    out.println(reservador + " | " + data + " | " + tempo + " | " + sala + " | " + hora + " | Data de Registro: "
                            + horaFormatada + " " + dataFormatada);
                    out.close();
                } catch (IOException e) {
                    
                }
                
            }
            System.out.println("Registro criado.");
        }catch (Exception e){
        }
        
    }
    
}
