package modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarDatosImp implements ValidarDatos {
   

    public ValidarDatosImp()  {
    }


    /**
     * Verifica que se ingrese un nombre valido que solo contenga letras y la
     * longitud sea mayor a 3
     * 
     */
    @Override
    public boolean ValidarNombre(String nombre) {
	Pattern pat = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]*((\\s)+[A-ZÁÉÍÓÚ][a-záéíóúñ]+)*");
	Matcher mat = pat.matcher(nombre);
	if (mat.matches()) 
	    return true;
	else 
	    return false;
	
    }
    
    /**
     * Verifica que solo se ingresan datos de tipo entero 
     * 
     */
    @Override
    public boolean validarEnteros(String numeroEntero) {
	Pattern pat = Pattern.compile("[1-9][0-9]*");
	String numero = String.valueOf(numeroEntero);
	Matcher mat = pat.matcher(numero);

	if (mat.matches())
	    return true;

	else
	    return false;
    }
    
    /**
     * valida que solo se ingresen datos de tipo double 
     */
    @Override
    public boolean validarDouble(String numeroDouble) {
	Pattern pat = Pattern.compile("[+-]?([0-9]*[.])?[0-9]+");
	String numero = String.valueOf(numeroDouble);
	Matcher mat = pat.matcher(numero);

	if (mat.matches())
	    return true;
	else
	    return false;
    }
}
