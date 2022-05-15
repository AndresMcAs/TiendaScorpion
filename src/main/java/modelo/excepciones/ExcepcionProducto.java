
package modelo.excepciones;

/**
 *
 * @author Andres Mendoza 
 */
public class ExcepcionProducto extends Exception {
    
 
  private static final long serialVersionUID = 1L;

  public ExcepcionProducto(String mensaje) {
    super(mensaje);
   }
}
