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
import models.Respostas;

public class RespostasDao implements Serializable {

    public void insert(Respostas r) {
        String sql = "INSERT INTO respostas(idQuestao,ra,resposta,acertou,ativo)"
                + " VALUES(?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = ConnectionFactory.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, r.getIdQuestao());
            pstm.setString(2, r.getRa());
            pstm.setString(3, r.getResposta());
            pstm.setBoolean(4, r.getAcertou());
            pstm.setBoolean(5, r.getAtivo());

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

    public void delete(int id) {
        String sql = "DELETE FROM respostas WHERE id = ?";

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
                Logger.getLogger(RespostasDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void update(Respostas r) {

        String sql = "UPDATE respostas SET idQuestao = ?, ra = ?, repostas = ?, acertou = ?, ativo = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = ConnectionFactory.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, r.getIdQuestao());
            pstm.setString(2, r.getRa());
            pstm.setString(3, r.getResposta());
            pstm.setBoolean(4, r.getAcertou());
            pstm.setBoolean(5, r.getAtivo());

            pstm.setInt(6, r.getId());

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

    public ArrayList<Respostas> findAll() {
        String sql = "SELECT * FROM respostas";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        ArrayList<Respostas> rl = new ArrayList<Respostas>();

        try {

            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {
                Respostas resp = new Respostas();

                resp.setAcertou(rset.getBoolean("acertou"));
                resp.setAtivo(rset.getBoolean("ativo"));
                resp.setId(rset.getInt("id"));
                resp.setIdQuestao(rset.getInt("idQuestao"));
                resp.setRa(rset.getString("ra"));
                resp.setResposta(rset.getString("resposta"));

                rl.add(resp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RespostasDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(RespostasDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return rl;
    }

    public Respostas find(int id) {
        String sql = "SELECT * FROM respostas WHERE id = ? ";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        Respostas resp = new Respostas();

        try {

            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, id);

            rset = pstm.executeQuery();

            while (rset.next()) {
                resp.setAcertou(rset.getBoolean("acertou"));
                resp.setAtivo(rset.getBoolean("ativo"));
                resp.setId(rset.getInt("id"));
                resp.setIdQuestao(rset.getInt("idQuestao"));
                resp.setRa(rset.getString("ra"));
                resp.setResposta(rset.getString("resposta"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(RespostasDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(RespostasDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return resp;
    }
}
