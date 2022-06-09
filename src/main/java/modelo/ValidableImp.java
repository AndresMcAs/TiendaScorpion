package modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * valida la entra de los datos para el producto
 * @author Asus
 *
 */
public class ValidableImp implements IValidable {
   

    public ValidableImp()  {
    }


    /**
     * Verifica que se ingrese un nombre valido que solo contenga letras y la
     * longitud sea mayor a 3
     * 
     */
    @Override
    public boolean validarNombreProducto(String nombre) {
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
    public boolean validarNumeroUnidades(String numeroEntero) {
	Pattern pat = Pattern.compile("[[1-9][0-9]*]{1,5}"); 
	Matcher mat = pat.matcher(numeroEntero);

	if (mat.matches())
	    return true;

	else
	    return false;
    }
    
    /**
     * valida que solo se ingresen datos de tipo double 
     */
    @Override
    public boolean validarCostoProducto(String numeroDouble) {
	Pattern pat = Pattern.compile("[+]?([[0-9][0-9]*]{1,5}[.])?[0-9]+");
	Matcher mat = pat.matcher(numeroDouble);

	if (mat.matches())
	    return true;
	else
	    return false;
    }
}
