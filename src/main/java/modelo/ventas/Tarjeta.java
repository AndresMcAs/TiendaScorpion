package modelo.ventas;

import java.util.ArrayList;
import java.util.List;

public class Tarjeta {
	private String numeroTarjeta;
	private int pin;
	private double saldo;
	private List<Tarjeta> tarjetas = new ArrayList<>();
	
	public Tarjeta() {}
	public Tarjeta(String numeroTarjeta, int pin, double saldo) {
		
		this.numeroTarjeta = numeroTarjeta;
		this.pin = pin;
		this.saldo = saldo;
		
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public int getPin() {
		return pin;
	}
    
	public List<Tarjeta> listarTarjetas() {
		
		tarjetas.add(new Tarjeta("1234567234", 1304, 20000));
		tarjetas.add(new Tarjeta("1827654321", 1421, 15000));
		tarjetas.add(new Tarjeta("1534567234", 1452, 2000));
		
		
		return tarjetas;
	}
	
	
}
