package pucaberta.pucminas.com.app;

/**
 * Created by lucas on 02/03/2018.
 * Update at 02/03/2018
 */

public class Constants {

    public static final String ISLOGGED = "logged";

    public class SharedPreferences {
        public static final String PATH = "puc_path";
    }

    public class API{
        public static final String INSCRITO = "inscrito/";
        public static final String GETSTATUS = "getstatus/";
        public static final String GETDADOS = "getdados/";
        public static final String GETPALESTRAS = "inscrito/getpalestras/";
        public static final String GETCURSOS = "getcursos/";

        public class Register{
            public static final String REGISTER = "register";
            public static final String INDIVIDUAL = "http://portal.pucminas.br/pucaberta/inscrito_cadastro.php";
            public static final String ESCOLA = "http://portal.pucminas.br/pucaberta/escola/escola_cadastro.php";
        }
    }

    public class USER{
        public static final String NOME = "nome";
        public static final String CPF = "cpf";
        public static final String NASC = "nasc";
        public static final String SERIE = "serie";
        public static final String CNPJ = "cnpj";
        public static final String ESCOLA = "escola";
    }

}
