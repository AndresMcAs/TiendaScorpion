package modelo.ventas;

public class Tarjeta {
	private int numeroTarjeta;
	private int pin;
	private double saldo;
	private String propietario;
	
	public Tarjeta(int numeroTarjeta, int pin, double saldo, String propietario) {
		super();
		this.numeroTarjeta = numeroTarjeta;
		this.pin = pin;
		this.saldo = saldo;
		this.propietario = propietario;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public int getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public int getPin() {
		return pin;
	}

	public String getPropietario() {
		return propietario;
	}

}
