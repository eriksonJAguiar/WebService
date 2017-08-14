using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Security;
using WebServiceCliente.Models;

namespace WebServiceCliente.Controllers
{
    public class LoginController : Controller
    {
        // GET: Login
        public ActionResult Index()
        {
            return View();
        }

        [HttpPost]
        public ActionResult Index(String ra, String senha)
        {
            Service s = new Service();
            Usuario u = Usuario.getInstance();

            if (ModelState.IsValid)
            {
                if ((u = s.login(ra,senha)) != null)
                {
                    FormsAuthentication.SetAuthCookie(ra, false);
                    Session["login"] = u.login;
                    Session["senha"] = u.senha;
                    Session["nome"] = u.nome;
                    return RedirectToAction("Index", "Home/Index");
                }
                else
                {
                    ModelState.AddModelError("", "Login data is incorrect!");
                    return RedirectToAction("Index", "Login/Index");

                }
            }
            return View();

        }
        public ActionResult Logout()
        {
            FormsAuthentication.SignOut();
            return RedirectToAction("Index", "Home/Index");
        }

    }
}