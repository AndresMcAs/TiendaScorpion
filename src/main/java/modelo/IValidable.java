
package modelo;

public interface IValidable {
    
    boolean validarNombreProducto(String nombre);
    boolean validarNumeroUnidades(String numeroEntero);
    boolean validarCostoProducto(String numeroDouble);

}
