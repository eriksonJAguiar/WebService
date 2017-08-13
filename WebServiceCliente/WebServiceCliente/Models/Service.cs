using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebServiceCliente.Models
{
    public class Service
    {
        public PerguntasWSDL.questoes getQuestao()
        {
            PerguntasWSDL.questoes q = null;

            try
            {

                PerguntasWSDL.PerguntasClient c = new PerguntasWSDL.PerguntasClient();

                q = c.getQuestao();
            }
            catch (Exception)
            {

                throw;
            }

            return q;
           
        }
        public bool setResposta(String ra, String resposta, int questao)
        {
            bool r = false;
            try
            {
                PerguntasWSDL.PerguntasClient c = new PerguntasWSDL.PerguntasClient();

                r = c.setResposta(ra, resposta, questao);
            }
            catch (Exception)
            {

                throw;
            }

            return r;

        }
       
    }
}