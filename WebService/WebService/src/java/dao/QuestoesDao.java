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
import models.Questoes;

public class QuestoesDao implements Serializable {

    public void insert(Questoes q) {
        String sql = "INSERT INTO questoes(enunciado,A,B,C,D,E,correta) VALUES(?,?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = ConnectionFactory.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, q.getEnunciado());
            pstm.setString(2, q.getA());
            pstm.setString(3, q.getB());
            pstm.setString(4, q.getC());
            pstm.setString(5, q.getD());
            pstm.setString(6, q.getE());
            pstm.setString(7, q.getCorreta());
            pstm.setInt(2, q.getId());

            pstm.execute();

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Questoes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM questoes WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, id);

            pstm.execute();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuestoesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void update(Questoes q) {

        String sql = "UPDATE respostas SET enunciado = ?, A = ?, B = ?, C = ?, D = ?, E = ?, correta = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = ConnectionFactory.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, q.getEnunciado());
            pstm.setString(2, q.getA());
            pstm.setString(3, q.getB());
            pstm.setString(4, q.getC());
            pstm.setString(5, q.getD());
            pstm.setString(6, q.getE());
            pstm.setString(7, q.getCorreta());
            pstm.setInt(2, q.getId());

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

    public Questoes findRand() throws SQLException {
        String sql = "SELECT * FROM questoes ORDER BY RAND() limit 1";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        Questoes q = new Questoes();

        try {

            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {
                q.setId(rset.getInt("id"));
                q.setEnunciado(rset.getString("enunciado"));
                q.setA(rset.getString("A"));
                q.setB(rset.getString("B"));
                q.setC(rset.getString("C"));
                q.setD(rset.getString("D"));
                q.setE(rset.getString("E"));
                q.setCorreta(rset.getString("correta"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuestoesDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }

        return q;
    }

    public ArrayList<Questoes> findAll() {
        String sql = "SELECT * FROM questoes";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        ArrayList<Questoes> ql = new ArrayList<Questoes>();

        try {

            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {
                Questoes q = new Questoes();

                q.setId(rset.getInt("id"));
                q.setEnunciado(rset.getString("enunciado"));
                q.setA(rset.getString("A"));
                q.setB(rset.getString("B"));
                q.setC(rset.getString("C"));
                q.setD(rset.getString("D"));
                q.setE(rset.getString("E"));
                q.setCorreta(rset.getString("correta"));

                ql.add(q);
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuestoesDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuestoesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ql;
    }

    public Questoes find(int id) {
        String sql = "SELECT * FROM questoes WHERE id = ? ";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        Questoes q = new Questoes();

        try {

            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, id);

            rset = pstm.executeQuery();

            while (rset.next()) {
                q.setId(rset.getInt("id"));
                q.setEnunciado(rset.getString("enunciado"));
                q.setA(rset.getString("A"));
                q.setB(rset.getString("B"));
                q.setC(rset.getString("C"));
                q.setD(rset.getString("D"));
                q.setE(rset.getString("E"));
                q.setCorreta(rset.getString("correta"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuestoesDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuestoesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return q;
    }
}
