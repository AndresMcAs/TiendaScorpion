package modelo.ventas;

import modelo.excepciones.AplicacionExcepcion;

public class ContextoPago {
	
	private IPago pago;
	private double acumulador=0.0;

	
	public void setContextoPago(IPago pago) {
		this.pago = pago;
	}
	
	public double ejecutarPago(double totalVenta , double monto) throws AplicacionExcepcion {
		double resultado = pago.realizarPago(totalVenta, monto);
		acumulador = resultado;
		return resultado;
	}

	public double getAcumulador() {
		return acumulador;
	}

	public void setAcumulador(double acumulador) {
		this.acumulador = acumulador;
	}
	
	
}
