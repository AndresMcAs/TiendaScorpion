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

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getApellidoPat() {
	return apellidoPat;
}

public void setApellidoPat(String apellidoPat) {
	this.apellidoPat = apellidoPat;
}

public String getApellidoMat() {
	return apellidoMat;
}

public void setApellidoMat(String apellidoMat) {
	this.apellidoMat = apellidoMat;
}

public String getCorreo() {
	return correo;
}

public void setCorreo(String correo) {
	this.correo = correo;
}

public String getContrasenia() {
	return contrasenia;
}

public void setContrasenia(String contrasenia) {
	this.contrasenia = contrasenia;
}

public String getRol() {
	return rol;
}

public void setRol(String rol) {
	this.rol = rol;
}

public ValidarDatosImp getValidar() {
	return validar;
}

public void setValidar(ValidarDatosImp validar) {
	this.validar = validar;
}

public String getNombre() {
	return nombre;
}

 

}
