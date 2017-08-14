using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebServiceCliente.Models
{
    public class Usuario
    {

        private Usuario(){ }

        private static Usuario user = null;

        public String login { get; set; }
        public String nome { get; set; }
        public String senha { get; set; }

        public static Usuario getInstance()
        {
            if(user == null)
            {
                return new Usuario();
            }
            else
            {
                return user;
            }
        }
    }
}