using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace WebServiceCliente.Controllers
{
    public class QuizController : Controller
    {
       
        public ActionResult Index()
        {
            var s = new Models.Service();
            PerguntasWSDL.questoes q = s.getQuestao();
            return View(q);
        }

        [HttpPost]
        public ActionResult Index(string radio, PerguntasWSDL.questoes q)
        {
            return View();
        }
    }
}