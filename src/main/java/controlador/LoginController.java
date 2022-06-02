/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import baseDatos.EmpleadoDaoImp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Empleado;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class LoginController implements Initializable {

	@FXML
	private TextField txtFCorreo;
	@FXML
	private PasswordField txtFcontrasenia;
	@FXML
	private Button btnIniciarSesion;

	private FXMLLoader fxmlLoader;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	@FXML
	private void iniciarSesion(ActionEvent event) throws IOException {

		EmpleadoDaoImp usuarioDao = new EmpleadoDaoImp();
		Empleado usuario = usuarioDao.obtenerUsuarioPorCorreoYContrasenia(txtFCorreo.getText(),
				txtFcontrasenia.getText());

		if (usuario != null) {
			Scene scene;
			Stage secundaryStage;


			if (usuario.getPuesto().equals("ADMIN")) {
				lanzarSiguienteVentana("gestionProductos.fxml");
				
			}
			else if (usuario.getPuesto().equals("CAJERO")) {

				scene = new Scene(loadFXML("/vista/VentaDeProductos"));
                VentaDeProductosController controlVentas = fxmlLoader.getController();
                controlVentas.setNombreCajero(usuario.getNombre() + " " + usuario.getApellidoPat());
				secundaryStage = new Stage();
				secundaryStage.setScene(scene);
				secundaryStage.initModality(Modality.WINDOW_MODAL);
				secundaryStage.show();
				limpiar();
				
			} 
			else if (usuario.getPuesto().equals("GERENTE")) {
				
			}
			else {
				Alert alerta = new Alert(Alert.AlertType.ERROR);
				alerta.setTitle("Inicio Sesion");
				alerta.setContentText("Funcionalidad no permitida");
				alerta.showAndWait();
				limpiar();
			}

			
		} else {
			Alert alerta = new Alert(Alert.AlertType.WARNING);
			alerta.setTitle("Inicio Sesion");
			alerta.setContentText("Usuario / Contraseña no validos");
			alerta.showAndWait();
			limpiar();

		}

    }

	private Parent loadFXML(String fxml) throws IOException {
		fxmlLoader = new FXMLLoader(Tienda.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	private void limpiar() {

		txtFCorreo.setText("");
		txtFcontrasenia.setText("");

	}
	
	private void lanzarSiguienteVentana(String vista) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GestionProductosController.class.getResource("/vista/"+vista));
        AnchorPane ventana = (AnchorPane) loader.load();
        Scene scene = new Scene(ventana);
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        ((Stage) (btnIniciarSesion.getScene().getWindow())).close();
	}

}
