package modelo.ventas;

import modelo.excepciones.AplicacionExcepcion;

public abstract class Pago {

	protected Long identificador;
	protected double totalPago;

	public abstract void realizarPago(double totaVenta) throws AplicacionExcepcion;

}
