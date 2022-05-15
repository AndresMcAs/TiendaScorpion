package modelo.ventas;

import modelo.excepciones.AplicacionExcepcion;

public class PagoEnEfectivo extends Pago {
    
	private double cambio;
	private double efectivo;

	public PagoEnEfectivo() {
	}

	public PagoEnEfectivo(double efectivo) {
		super();
		this.cambio = 0;
		this.efectivo = efectivo;
	}

	@Override
	public void realizarPago(double totalVenta) throws AplicacionExcepcion {

		if (totalVenta < efectivo || totalVenta == efectivo) {

			this.cambio = efectivo - totalVenta;
			throw new AplicacionExcepcion("Pago realizado con exito");
		} else {
			throw new AplicacionExcepcion("falta efectivo, para realizar la venta ");
		}
	}

	public double getCambio() {
		return cambio;
	}

	public double getEfectivo() {
		return efectivo;
	}

	public void setEfectivo(double efectivo) {
		this.efectivo = efectivo;
	}
	

}
