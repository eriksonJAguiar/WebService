using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using WebServiceCliente.Models;

namespace WebServiceCliente.Controllers
{
    public class QuizController : Controller
    {

        public ActionResult Index()
        {
            var s = new Service();
            PerguntasWSDL.questoes q = s.getQuestao();
            return View(q);
        }

        [HttpPost]
        public ActionResult Index(string radio, Int32 id)
        {
            var s = new Service();
            var a = s.setResposta("a12004", radio, id);

            if (a)
            {
                TempData["RespostaCerta"] = "Resposta Certa";
            }
            else
            {
                TempData["RespostaErrada"] = "Resposta Errada";
            }

            return RedirectToAction("Index", "Quiz");
        }
    }
}