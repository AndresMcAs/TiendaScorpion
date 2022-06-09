package modelo.ventas;

public class PagoEnEfectivo implements IPago {

	
	private double cambio;
	
	public double getCambio() {
		return cambio;
	}

	public PagoEnEfectivo() {
	}

	@Override
	public boolean realizarPago(double totalVenta, double monto) {
       boolean pagoExito = false ;
		if(pagoCompleto(totalVenta, monto)) {
        	 this.cambio = monto-totalVenta; 
        	 pagoExito =true;
        } 
		return pagoExito;
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
