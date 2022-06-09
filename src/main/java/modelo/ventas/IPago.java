package modelo.ventas;

public interface IPago {

	 boolean realizarPago(double totalVenta, double monto);
	 boolean pagoCompleto(double totalVenta, double monto);
}
