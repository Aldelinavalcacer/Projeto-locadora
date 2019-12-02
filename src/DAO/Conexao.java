
package DAO;

import java.sql.*;
import javax.swing.JOptionPane;
public class Conexao {
    public static Connection AbrirConexao(){
        Connection con = null;
        try {
            Class.forName("com.sql.jdbc.Driver");
            String url= "jdbc:msql://localhost/locadora";
            con = DriverManager.getConnection (url, "root", "123");
        } catch (Exception e){
            JOptionPane.showMessageDialog (null, "Erro na conexão com o banco",
                    "Video Locadora", JOptionPane.ERROR_MESSAGE);
            e.getMessage();
        }
        return con;
    }
    public static void FecharConexao (Connection con){
        try {
            con.close();
        } catch (Exception e){
            System.out.println (e.getMessage());
        }
    }
    
}
