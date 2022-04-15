package modelo;

import lombok.Data;

/**
 * 
 * @author Andres Mendoza 
 *
 */

@Data
public class Usuario  {

  private Long id;
  private String nombre;
  private String apellidoPat;
  private String apellidoMat;
  private String correo;
  private String contrasenia;
  private String rol;
  private ValidarDatosImp validar = new ValidarDatosImp();
 
  public Usuario() {

  }

  public Usuario(String nombre, String apellidoPat, String apellidoMat, String rol) {
      super();
      this.nombre = nombre;
      this.apellidoPat = apellidoPat;
      this.apellidoMat = apellidoMat;
      this.rol = rol;
  }

  public Usuario(String nombre, String apellidoPat, String apellidoMat, String correo, String contrasenia, String rol) {
      super();
      this.nombre = nombre;
      this.apellidoPat = apellidoPat;
      this.apellidoMat = apellidoMat;
      this.correo = correo;
      this.contrasenia = contrasenia;
      this.rol = rol;
  }

  public void setNombre(String nombreUsuario) throws AplicacionExcepcion {
      
      if (validar.ValidarNombre(nombreUsuario)) {
        this.nombre = nombreUsuario;
      } else {
        throw new AplicacionExcepcion("Escribe un nombre de usuario valido");
      }
  }

 

}
