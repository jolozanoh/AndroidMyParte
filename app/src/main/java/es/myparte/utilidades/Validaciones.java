package es.myparte.utilidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by oscarlozanohernaiz.
 */
public class Validaciones {

    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    public Validaciones(){

    }

    public static boolean validateEmail(String email) {

        Pattern pattern = Pattern.compile(PATTERN_EMAIL);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }

    public static boolean camposBlanco(String paramString)
    {
        boolean isValido = false;
        if (paramString.length() == 0) {
            isValido = true;
        }
        return isValido;

    }
}
