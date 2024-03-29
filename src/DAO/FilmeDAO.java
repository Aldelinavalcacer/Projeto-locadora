package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Modelo.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Laura
 */
public class FilmeDAO extends ExecuteSQL {
    
    public FilmeDAO(Connection con) {
        super(con);
    }
    
    public String Inserir_Filme(Filme a){
        try{
            String sql = "insert into filme values(0,?,?,?,?,?,?)";
            PreparedStatement ps = getCon().prepareStatement(sql);
            
            ps.setString(1, a.getTitulo());
            ps.setInt(2, a.getAno());
            ps.setString(3, a.getDuracao());
            ps.setInt(4, a.getCod_categoria());
            ps.setInt(5, a.getCod_classificacao());
            ps.setString(6, a.getCapa());
            
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso";
            } else {
                return "Erro ao inserir!";
            }
        }catch(SQLException e){
            return e.getMessage();
        }
    }
    
    public List<Filme> ListarComboFilme(){
        String sql = "select titulo from filme order by titulo";
        List<Filme> lista = new ArrayList<>();
        
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {
                    Filme a = new Filme();
                    a.setTitulo(rs.getString(1));
                    lista.add(a);
                }
                return lista;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Filme> ConsultaCodigoFilme(String nome) {
        String sql = "select idfilme from filme where titulo = '"+ nome +"'";
        List<Filme> lista = new ArrayList<>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {
                    Filme a = new Filme();
                    a.setCodigo(rs.getInt(1));
                    lista.add(a);
                }
                return lista;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Filme> ConsultaNomeFilme(int cod) {
        String sql = "select titulo, capa from filme where idfilme = "+ cod +"";
        List<Filme> lista = new ArrayList<>();
        try {
            
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Filme a = new Filme();
                    a.setTitulo(rs.getString(1));
                    a.setCapa(rs.getString(2));
                    lista.add(a);
                }
                return lista;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    public String Alterar_Filme(Filme a) {
    String sql = "update filme set titulo = ? ,ano = ? ,duracao = ? "
            + ",cod_categoria = ? ,cod_classificacao = ? ,capa = ?";
    try {
        PreparedStatement ps = getCon() .prepareStatement(sql);
        ps.setString(1, a.getTitulo());
        ps.setInt(2, a.getAno());
        ps.setString(3, a.getDuracao());
        ps.setInt(4, a.getCod_categoria());
        ps.setInt(5, a.getCod_classificacao());
        ps.setString(6, a.getCapa());
        ps.setInt(7, a.getCodigo());
        if (ps.executeUpdate() > 0) {
            return "Atualizado com sucesso.";
        } else {
            return "Erro ao Atualizar";
        }
    } catch (SQLException e) {
        return e.getMessage();
    }
}

    public List<Filme> ListarFilme() {
        String sql = "select idfilme,titulo,ano,duracao,cod_categoria,cod_classificacao from filme";
        List<Filme> lista = new ArrayList<>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {
            Filme a = new Filme();
            a.setCodigo(rs.getInt(1));
            a.setTitulo(rs.getString(2));
            a.setAno(rs.getInt(3));
            a.setDuracao(rs.getString(4));
            a.setCod_categoria(rs.getInt(5));
            a.setCod_classificacao(rs.getInt(6));
            a.setCapa(rs.getString((7)));
            
            lista.add(a);
                }
                return lista;
            } else {
                return null;
            }
       } catch (SQLException e) {
           return null; 
       }
   
    }
    
    public boolean Testar_Filme(int cod) {
            boolean Resultado = false;
            try {
                
                String sql = "select * from filme where idfilme = " + cod + "";
                PreparedStatement ps = getCon().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                if (rs != null) {
                    while (rs.next()) {
                        Resultado = true;
                    }
                }
            } catch (SQLException ex) {
                ex.getMessage();
            }
            return Resultado;
      }
    
    
    public List<Filme> CapturarFilme(int cod) {
            String sql = "select * from filme where idfilme =" + cod + " ";
            List<Filme> lista = new ArrayList<>();
            try {
                PreparedStatement ps = getCon().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Filme a = new Filme();
                        a.setCodigo(rs.getInt(1));
                        a.setTitulo(rs.getString(2));
                        a.setAno(rs.getInt(3));
                        a.setDuracao(rs.getString(4));
                        a.setCod_categoria(rs.getInt(5));
                        a.setCod_classificacao(rs.getInt(6));
                        a.setCapa(rs.getString(7));
                        lista.add(a);
                    }
                    return lista;
                 } else {
                    return null;
                }
            } catch (SQLException e) {
                return null;
            }
        }
    
    public String Excluir_Filme(Filme a){
        String sql = "delete from filme where idfilme = ? and titulo = ?";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1, a.getCodigo());
            ps.setString(2, a.getTitulo());
            if (ps.executeUpdate() > 0) {
                return "Excluido com sucesso";
            } else {
                return "Erro ao excluir";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public List<Filme> Pesquisar_Cod_Filme(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

