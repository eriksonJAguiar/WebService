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
    public boolean setResposta(@WebParam(name = "ra") String ra,
            @WebParam(name = "resp") String resp,
            @WebParam(name = "questao") int questao) {
        Respostas r = new Respostas();
        RespostasDao dao = new RespostasDao();
        QuestoesDao daoQ = new QuestoesDao();
        boolean acertou = false;
        r.setRa(ra);

        if (resp.equals("")) {
            r.setAtivo(false);
        } else {
            r.setAtivo(true);
        }
        
        Questoes q = daoQ.find(questao);

        if (resp.equals(q.getCorreta())) {
            r.setAcertou(true);
            acertou = true;
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
        
        return acertou;
    }

}
