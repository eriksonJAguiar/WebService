package services;

import dao.QuestoesDao;
import dao.RespostasDao;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import models.Questoes;
import models.Respostas;

@WebService()
public class Perguntas {

    @WebMethod(operationName = "getQuestao")
    public Questoes getQuestao() {
        QuestoesDao dao = new QuestoesDao();
        Questoes q = new Questoes();

        try {
            q = dao.findRand();
        } catch (SQLException ex) {
            Logger.getLogger(Perguntas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return q;
    }

    @WebMethod(operationName = "setResposta")
    public void setResposta(@WebParam(name = "ra") String ra,
            @WebParam(name = "resp") String resp,
            @WebParam(name = "q") Questoes q) {
        Respostas r = new Respostas();
        RespostasDao dao = new RespostasDao();

        r.setRa(ra);

        if (resp.equals("")) {
            r.setAtivo(false);
        } else {
            r.setAtivo(true);
        }

        if (resp.equals(q.getCorreta())) {
            r.setAcertou(true);
        } else {
            r.setAcertou(false);
        }

        r.setIdQuestao(q.getId());

        r.setResposta(resp);

        try {
            dao.insert(r);
        } catch (Exception ex) {
            Logger.getLogger(Perguntas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
