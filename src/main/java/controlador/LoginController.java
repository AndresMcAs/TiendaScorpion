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
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Administrador;
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
    
     FXMLLoader fxmlLoader;
    
  

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
			
			switch (usuario.getPuesto()) {

			case "ADMIN":
				scene = new Scene(loadFXML("/vista/gestionProductos"));
				// pasamos el nombre del empleado que acceso al sistema
				GestionProductosController controlProducto = fxmlLoader.getController();
				controlProducto.setNombreEmp(usuario.getNombre() + " " + usuario.getApellidoPat());
				secundaryStage = new Stage();
				secundaryStage.setScene(scene);
				secundaryStage.initModality(Modality.APPLICATION_MODAL);
				secundaryStage.show();
				  
				limpiar();
				break;

			case "VENDEDOR":
				
				scene = new Scene(loadFXML("/vista/VentaDeProductos"));

				secundaryStage = new Stage();
				secundaryStage.setScene(scene);
				secundaryStage.initModality(Modality.WINDOW_MODAL);
				secundaryStage.show();
				limpiar();
				break;
			case "GERENTE":
				break;
			default:
				Alert alerta = new Alert(Alert.AlertType.ERROR);
				alerta.setTitle("Inicio Sesion");
				alerta.setContentText("Funcionalidad no permitida");
				alerta.showAndWait();
				limpiar();
				break;

			}

		} else {
			Alert alerta = new Alert(Alert.AlertType.WARNING);
			alerta.setTitle("Inicio Sesion");
			alerta.setContentText("Usuario / Contrase??a no validos");
			alerta.showAndWait();
			limpiar();

		}
	  
    }
    
    private  Parent loadFXML(String fxml) throws IOException {
    fxmlLoader = new FXMLLoader(Tienda.class.getResource(fxml + ".fxml"));
   	return fxmlLoader.load();
       }
    
    private void limpiar() {
	
	txtFCorreo.setText("");
	txtFcontrasenia.setText("");
    }
    
}
