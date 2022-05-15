package modelo.ventas;

import modelo.excepciones.AplicacionExcepcion;

public class FabricaPago {

	private Pago pago;

	public Pago obtenerTipoPago(String tipoPago, double total, double efectivo) throws AplicacionExcepcion {

		if (tipoPago.equalsIgnoreCase("efectivo")) {
			pago = new PagoEnEfectivo(efectivo);
			pago.realizarPago(total);

		}
		if (tipoPago.equalsIgnoreCase("tarjeta")) {
			pago = new PagoConTarjeta();
			pago.realizarPago(total);

		}
		return pago;
	}
}

