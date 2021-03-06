
package modelo;

import lombok.Data;
import modelo.excepciones.ExcepcionProducto;

/**
 *
 * @author AndresMC
 */
@Data
public class Producto {

	private long idProducto;
	private String nombreProducto;
	private int numeroUnidades;
	private double costoUnidad;
	private String descripcion;
	private String fechaRegistro;
	private String imagen;
	private ValidarDatosImp validar = new ValidarDatosImp();

	public Producto() {

	}

	public Producto(String nombreProducto, int numeroUnidades,
			        double costoUnidad, String descripcion) {
		this.nombreProducto = nombreProducto;
		this.numeroUnidades = numeroUnidades;
		this.costoUnidad = costoUnidad;
		this.descripcion = descripcion;
	}
	
	/*
	 * @param nombreProducto
	 * 
	 * @throws ExcepcionProducto
	 */
	public void setNombreProducto(String nombreProducto) throws ExcepcionProducto {

		if (validar.ValidarNombre(nombreProducto)) {
			this.nombreProducto = nombreProducto;
		} else {
			throw new ExcepcionProducto("Escribe un nombre de producto valido");
		}
	}

	/*
	 * @param numeroUnidades
	 * 
	 * @throws ExcepcionProducto
	 */
	public void setNumeroUnidades(String numeroUnidades) throws ExcepcionProducto {

		if (validar.validarEnteros(numeroUnidades)) {
			this.numeroUnidades = Integer.parseInt(numeroUnidades);
		} else {
			throw new ExcepcionProducto(
					"Error: Solo se admiten números " + 
			        "enteros mayores a 0" + 
					" en el campo unidades");
		}
	}

	/**
	 * Verifica que solo se ingresen numeros de tipo double
	 * 
	 * @param costoUnidad
	 * @throws ExcepcionProducto
	 */
	public void setCostoUnidad(String costoUnidad) throws ExcepcionProducto {

		if (validar.validarDouble(costoUnidad)) {
			this.costoUnidad = Double.parseDouble(costoUnidad);
		} else {
			throw new ExcepcionProducto("Error: solo se admiten números en " +
		                                "el campo costo: (0.00)");
		}
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
    
	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public int getNumeroUnidades() {
		return numeroUnidades;
	}

	public double getCostoUnidad() {
		return costoUnidad;
	}

	public long getIdProducto() {
		return idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

}
