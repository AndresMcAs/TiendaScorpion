package modelo.ventas;

public class PagoEnEfectivo implements IPago {

	
	private double cambio;
	
	public PagoEnEfectivo() {
	}

	@Override
	public double realizarPago(double totalVenta, double monto) {
        if(pagoCompleto(totalVenta, monto)) {
        	return cambio = monto-totalVenta; 
        } 
		return totalVenta-monto;
	}

	@Override
	public boolean pagoCompleto(double totalVenta, double monto) {
		boolean pagoRealizado;
		if (totalVenta <= monto) {
			pagoRealizado = true;
		}
		else pagoRealizado = false ;
		return pagoRealizado;
	}

	

	

}
