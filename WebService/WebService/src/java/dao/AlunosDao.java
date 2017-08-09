package dao;

import controlles.ConnectionFactory;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Alunos;


public class AlunosDao implements Serializable{
    public void insert(Alunos a) {
        String sql = "INSERT INTO alunos(ra,nome,email,codCurso,acertos)"
                + " VALUES(?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = ConnectionFactory.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, a.getRa());
            pstm.setString(2, a.getNome());
            pstm.setString(3, a.getEmail());
            pstm.setInt(4, a.getCodCurso());
            pstm.setInt(5, a.getAcertos());

            pstm.execute();

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(RespostasDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void delete(String  ra) {
        String sql = "DELETE FROM alunos WHERE ra = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            pstm.setString(1, ra);

            pstm.execute();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
               conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(RespostasDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void update(Alunos a) {

        String sql = "UPDATE respostas SET nome = ?, email = ?, codCurso = ?, acertos = ? WHERE ra = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = ConnectionFactory.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);
            
           
            pstm.setString(1, a.getNome());
            pstm.setString(2, a.getEmail());
            pstm.setInt(3, a.getCodCurso());
            pstm.setInt(4, a.getAcertos());
            
            pstm.setString(5, a.getRa());
            
            pstm.execute();

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (pstm != null) {

                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }
    public ArrayList<Alunos> findAll() {
         String sql = "SELECT * FROM respostas";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        ArrayList<Alunos> al = new ArrayList<Alunos>();

        try {
            
             conn = ConnectionFactory.createConnectionToMySQL();
            
            pstm = conn.prepareStatement(sql);
            
            rset = pstm.executeQuery(); 
            
            while(rset.next()){
                Alunos a = new Alunos();
                
                a.setRa(rset.getString("ra"));
                a.setNome(rset.getString("nome"));
                a.setEmail(rset.getString("email"));
                a.setCodCurso(rset.getInt("codCurso"));
                a.setAcertos(rset.getInt("acertos"));
                
                al.add(a);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RespostasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
             try {
                 conn.close();
             } catch (SQLException ex) {
                 Logger.getLogger(RespostasDao.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        
        return al;
    }

    public Alunos find(String ra) {
         String sql = "SELECT * FROM respostas WHERE ra = ? ";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        Alunos a = new Alunos();

        try {
            
             conn = ConnectionFactory.createConnectionToMySQL();
            
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, ra);
            
            rset = pstm.executeQuery();    
            
            while(rset.next()){
                
                a.setRa(rset.getString("ra"));
                a.setNome(rset.getString("nome"));
                a.setEmail(rset.getString("email"));
                a.setCodCurso(rset.getInt("codCurso"));
                a.setAcertos(rset.getInt("acertos"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RespostasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
             try {
                 conn.close();
             } catch (SQLException ex) {
                 Logger.getLogger(RespostasDao.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        
        return a;
    }
}
