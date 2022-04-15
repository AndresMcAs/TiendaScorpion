/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import baseDatos.UsuarioDaoImp;
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
import modelo.Usuario;

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
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
	
    }    

    @FXML
    private void iniciarSesion(ActionEvent event) throws IOException {
	

	UsuarioDaoImp usuarioDao = new UsuarioDaoImp();
	Usuario usuario = usuarioDao.obtenerUsuarioPorCorreoYContrasenia(txtFCorreo.getText(),
		txtFcontrasenia.getText());

	if (usuario != null) {

	    if (usuario.getRol().equals("ADMIN")) {
		Scene scene = new Scene(loadFXML("/vista/gestionProductos"));
		Stage secundaryStage = new Stage();
		secundaryStage.setScene(scene);
		secundaryStage.initModality(Modality.APPLICATION_MODAL);
		secundaryStage.show();
		limpiar();

	    } else {
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
    
    private static Parent loadFXML(String fxml) throws IOException {
   	FXMLLoader fxmlLoader = new FXMLLoader(Tienda.class.getResource(fxml + ".fxml"));
   	return fxmlLoader.load();
       }
    
    private void limpiar() {
	
	txtFCorreo.setText("");
	txtFcontrasenia.setText("");
    }
    
}
