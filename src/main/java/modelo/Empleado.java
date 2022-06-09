package modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelo.excepciones.AplicacionExcepcion;

/**
 * 
 * @author Andres Mendoza 
 *
 */

public class Empleado  {

	protected String nombre;
	protected String apellidoPat;
	protected String apellidoMat;
	private String correo;
	private String contrasenia;
	protected String puesto;
	private IValidable validar = new ValidableImp();

	public Empleado() {

	}

	public Empleado(String nombre, String apellidoPat, String apellidoMat, String rol) {
		super();
		this.nombre = nombre;
		this.apellidoPat = apellidoPat;
		this.apellidoMat = apellidoMat;
		this.puesto = rol;
	}

	public Empleado(String nombre, String apellidoPat, String apellidoMat, String correo, String contrasenia,
			String rol) {
		super();
		this.nombre = nombre;
		this.apellidoPat = apellidoPat;
		this.apellidoMat = apellidoMat;
		this.correo = correo;
		this.contrasenia = contrasenia;
		this.puesto = rol;
	}

	public void setNombre(String nombreUsuario) throws AplicacionExcepcion {

		Pattern pat = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]*((\\s)+[A-ZÁÉÍÓÚ][a-záéíóúñ]+)*");
		Matcher mat = pat.matcher(nombreUsuario);
		if (mat.matches()) {
			this.nombre = nombreUsuario;
		} else {
			throw new AplicacionExcepcion("Escribe un nombre de usuario valido");
		}
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

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getNombre() {
		return nombre;
	}

}
