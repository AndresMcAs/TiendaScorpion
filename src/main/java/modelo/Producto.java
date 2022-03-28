
package modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author AndresMC
 */
public class Producto {

  private long idProducto;
  private String nombreProducto;
  private int numeroUnidades;
  private double costoUnidad;
  private String descripcion;
  private String fechaRegistro;
  private String imagen;

  public Producto(){
  
  }   
  
  public Producto(String nombreProducto, int numeroUnidades, double costoUnidad, String descripcion) {
      this.nombreProducto = nombreProducto;
      this.numeroUnidades = numeroUnidades;
      this.costoUnidad = costoUnidad;
      this.descripcion = descripcion;
  }

public void setIdProducto(long idProducto) {
    this.idProducto = idProducto;
  }
  
  /**
   * Verifica que se ingrese un nombre valido que solo contenga letras 
   * y la longitud sea mayor a 3
   * @param nombreProducto
   * @throws ExcepcionProducto
   */
  public void setNombreProducto(String nombreProducto) throws ExcepcionProducto {
    Pattern pat =  Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]*((\\s)+[A-ZÁÉÍÓÚ][a-záéíóúñ]+)*");
    Matcher mat = pat.matcher(nombreProducto);
    if (mat.matches()) {
      this.nombreProducto = nombreProducto;
    } else {
      throw new ExcepcionProducto("Escribe un nombre de producto valido");
    }
  }
  
  /**
   * Verifica que solo se ingresan datos de tipo entero en el camopo unidades 
   * @param numeroUnidades
   * @throws ExcepcionProducto
   */
  public void setNumeroUnidades(String numeroUnidades) throws ExcepcionProducto {
    Pattern pat =  Pattern.compile("[1-9][0-9]*");
    String numero = String.valueOf(numeroUnidades);
    Matcher mat = pat.matcher(numero);
      
    if (mat.matches()) {
      this.numeroUnidades = Integer.parseInt(numeroUnidades);
    } else {
      throw new ExcepcionProducto("Error: Solo se admiten números enteros mayores a 0"
                                   + " en el campo unidades");
    }
  }
  
  /**
   * Verifica que solo se ingresen numeros de tipo double
   * @param costoUnidad
   * @throws ExcepcionProducto
   */
  public void setCostoUnidad(String costoUnidad) throws ExcepcionProducto {
    Pattern pat =  Pattern.compile("[+-]?([0-9]*[.])?[0-9]+");
    String numero = String.valueOf(costoUnidad);
    Matcher mat = pat.matcher(numero);
    
    if (mat.matches()) {
      this.costoUnidad = Double.parseDouble(costoUnidad);
    } else  {
      throw new ExcepcionProducto("Error: solo se admiten números en el campo costo: (0.00)");
    }
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public void setFechaRegistro(String fechaRegistro) {
    this.fechaRegistro = fechaRegistro;
  }

  public void setImagen(String imagen) {
    this.imagen = imagen;
  }

  public long getIdProducto() {
    return idProducto;
  }

  public String getNombreProducto() {
    return nombreProducto;
  }

  public int getNumeroUnidades() {
    return numeroUnidades;
  }

  public double getCostoUnidad() {
    return costoUnidad;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public String getFechaRegistro() {
    return fechaRegistro;
  }

  public String getImagen() {
    return imagen;
  }


}
