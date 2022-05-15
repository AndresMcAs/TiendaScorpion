package modelo;

import java.io.IOException;

import controlador.GestionProductosController;
import controlador.Tienda;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Administrador extends Empleado{
    
	Scene scene;
	Stage secundaryStage;
	FXMLLoader fxmlLoader;
    public Administrador (Empleado admin) {
    	this.nombre = admin.getNombre();
    	this.apellidoPat= admin.getApellidoPat();
    	this.puesto = admin.getPuesto();
    } 	
    
	public void registrarEmpleados() throws IOException {
		

		
		
	}
	
	public void registrarProdctos() throws IOException {
		
		
	}
	
	 private  Parent loadFXML(String fxml) throws IOException {
		    fxmlLoader = new FXMLLoader(Tienda.class.getResource(fxml + ".fxml"));
		   	return fxmlLoader.load();
		       }
}
