package modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.excepciones.AplicacionExcepcion;


public class Venta {

	private Long numeroVenta;
	private ArrayList<Producto>productos;
	private double totalVenta;
	private String fechaVenta;
	private int cantidadProducto;
	private int totalProductos;
	private  Date myDate = new Date();
    private  String fecha = new SimpleDateFormat("dd-MM-yyyy").format(myDate);
	
	public Venta( ) {
		this.totalVenta = 0.0;
		this.fechaVenta = this.fecha;
		this.cantidadProducto = 0;
		this.totalProductos = 0;
		this.productos = new ArrayList<Producto>();
	}
	public Long getNumeroVenta() {
		return numeroVenta;
	}
	public List<Producto> getProductos() {
		return productos;
	}
	public double getTotalVenta() {
		return totalVenta;
	}
	public String getFechaVenta() {
		return fechaVenta;
	}
	public int getCantidadProducto() {
		return cantidadProducto;
	}
	public int getTotalProductos() {
		return totalProductos;
	}
	
	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}
	
	/**
	 * Agrega a una lista de productos que seran vendidos
	 * @param producto
	 * @throws AplicacionExcepcion verifica la existencias del producto 
	 */
	public void agregarProductos(Producto producto,int cantidad) throws AplicacionExcepcion {
	
		//realizar una busqueda en con el dao y agregar el producto hallado 
		// una vez realizada la venta cambiar el numero de unidades del producto vendido 
		
		if (producto.getNumeroUnidades() == 0) {
			throw new AplicacionExcepcion("Producto sin existencias");
		}
		if (producto.getNumeroUnidades() < cantidad) {
			throw new AplicacionExcepcion("El numero de unidades disponibles es menor a las que necesitas \n"
					+ "unidades Disponibles: " + producto.getNumeroUnidades());
		}
		for (int i = 0; i < cantidad; i++) {
			productos.add(producto);
		}
		totalProductos = totalProductos + cantidad;
	}
	
	/**
	 * Calcula el total de los productos vendidos
	 * @return totalVenta
	 */
	public double calcularTotalVenta() {
		totalVenta = 0;
		for(Producto p: productos) {
			
			totalVenta += p.getCostoUnidad();
		}
		
		return totalVenta ;
	}
	
	
}
