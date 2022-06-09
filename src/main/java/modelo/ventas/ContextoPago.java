package modelo.ventas;

import modelo.excepciones.AplicacionExcepcion;

public class ContextoPago {
	
	private IPago pago;
	private double acumulador=0.0;
    private double cambio = 0.0;
    private double monto = 0.0;
	
	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public double getCambio() {
		return cambio;
	}

	public void setCambio(double cambio) {
		this.cambio = cambio;
	}

	public void setContextoPago(IPago pago) {
		this.pago = pago;
	}
	
	public boolean ejecutarPago(double totalVenta , double monto) throws AplicacionExcepcion {
		 return pago.realizarPago(totalVenta, monto);
		
	}

	public double getAcumulador() {
		return acumulador;
	}

	public void setAcumulador(double acumulador) {
		this.acumulador = acumulador;
	}
	
	
}
