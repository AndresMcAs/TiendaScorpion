package modelo.ventas;

public interface IPago {



	 double realizarPago(double totalVenta, double monto);
	 
	 boolean pagoCompleto(double totalVenta, double monto);
/*
	public double getCambio() {
		return cambio;
	}

	public double getMonto() {
		return monto;
	}
	public void setCambio(double cambio) {
		this.cambio = cambio;
	}
	*/
	
}
