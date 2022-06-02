package modelo;

import java.io.PrintWriter;
import java.util.Random;
import modelo.ventas.IPago;

public class Ticket {
	
	private Venta venta;
	private String nombreCajero;
	private IPago pago;
	
	public Ticket() {}
	public Ticket(Venta venta, String nombreCajero,IPago pago) {
		this.venta = venta;
		this.nombreCajero = nombreCajero;
		this.pago = pago;
	}
	
	public void imprimirTicket() {
		
		
		 try {
			    Random r = new Random(); 
			    int numero = r.nextInt(5000);
	            PrintWriter writer = new PrintWriter("/C:/Users/Asus/Documents/venta"+ numero+".txt", "UTF-8");
	            writer.println("\t Tienda Scorpion S.A de CV");
	            writer.println("\t Rio Danubio 51, Piso 2");
	            writer.println("\t Sucursal:739 Colonia Roma");
	            writer.println("\t No.Ticket:" + numero);
	            writer.println("\t Cajero:" + nombreCajero);
	            writer.println("_______________________________________________");
	            writer.println("No.\tProducto\tcantidad\tprecio");
	            writer.println("_______________________________________________");
	            int i = 1;		
	            for(Producto p : venta.getProductos()) {
	    			
	            	writer.println(i + "\t"+  p.getNombreProducto() + " 1 "+"  "+ p.getCostoUnidad());
	        		i++;
	        		}
	            writer.println("_______________________________________________");
	            writer.println("Total    ------->" + "\t$" +venta.getTotalVenta());
	           // writer.println("Efectivo ------->" + "\t$" + pago.getMonto());
	           // writer.println("Cambio   ------->" + "\t$" + String.format("%.2f",pago.getCambio()));
	            writer.println("_______________________________________________");
	            writer.println("\t " + venta.getFechaVenta());
	            writer.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
		
	

}
