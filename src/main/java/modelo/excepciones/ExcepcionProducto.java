
package modelo.excepciones;

/**
 * Excepcion_Producto para la validacion de datos ingresados
 * 
 * @author Andres_Mendoza
 */
public class ExcepcionProducto extends Exception {

	private static final long serialVersionUID = 1L;

	public ExcepcionProducto(String mensaje) {
		super(mensaje);
	}
}
