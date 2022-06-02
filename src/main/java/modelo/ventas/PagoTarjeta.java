package modelo.ventas;



public class PagoTarjeta implements IPago {

	private Tarjeta tarjeta;
	private Tarjeta terjetaRecuperada;

	public boolean verificarTarjeta(String numeroTarjeta, int pin) {

		tarjeta = new Tarjeta();
		boolean tarjetaValida = false;
		
		for (Tarjeta tarjeta : tarjeta.listarTarjetas()) {
			if (tarjeta.getNumeroTarjeta().equals(numeroTarjeta) && tarjeta.getPin() == pin) {
				terjetaRecuperada = tarjeta;
				tarjetaValida = true;
			}
		}

		return tarjetaValida;
	}

	@Override
	public double realizarPago(double totalVenta, double monto) {
		double resultado = -1;
		if (totalVenta <= terjetaRecuperada.getSaldo()) {
			terjetaRecuperada.setSaldo(terjetaRecuperada.getSaldo() - monto);
			
		     resultado= totalVenta - monto;
			
		}
		return resultado;
	}


	@Override
	public boolean pagoCompleto(double totalVenta, double monto) {
		// TODO Auto-generated method stub
		return false;
	}

}
