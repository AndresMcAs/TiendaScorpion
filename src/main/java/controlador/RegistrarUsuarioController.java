/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Usuario;
import baseDatos.UsuarioDaoImp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.AplicacionExcepcion;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class RegistrarUsuarioController implements Initializable {

    @FXML
    private TextField txtFNombre;
    @FXML
    private TextField txtFapellidoPat;
    @FXML
    private TextField txtFapellidoMat;
    @FXML
    private TextField txtFcorreo;
    @FXML
    private Text txtPcontrasenia;
    @FXML
    private PasswordField txtPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cancelarRegistro(ActionEvent event) {
	
	txtFapellidoMat.setText("");
	txtFapellidoPat.setText("");
	txtFNombre.setText("");
	txtFcorreo.setText("");
	txtPassword.setText("");
    }

    @FXML
    private void RegistrarUsuario(ActionEvent event) throws AplicacionExcepcion {
        
        Usuario usuario = new Usuario();
        Usuario usuarioGuardado = new Usuario();
        UsuarioDaoImp usuarioDao = new UsuarioDaoImp();
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Producto");
       try { 
       if(txtFNombre.getText().length() != 0 && txtFcorreo.getText().length() != 0 && 
	  txtFapellidoPat.getText().length() !=0  && txtPassword.getText().length() != 0) {
        
	usuario.setNombre(txtFNombre.getText());
        usuario.setApellidoPat(txtFapellidoPat.getText());
        usuario.setApellidoMat(txtFapellidoMat.getText());
        usuario.setCorreo(txtFcorreo.getText());
        usuario.setContrasenia(txtPassword.getText());
        usuario.setRol("VENDEDOR");
        usuarioGuardado = usuarioDao.registrarUsuario(usuario);
        if (usuarioGuardado != null) {

	    alerta.setContentText("Registro Exitoso! bien venido " + usuario.getNombre());
	    alerta.showAndWait();
	    salirRegistro(event);
	}
    } else {
	alerta.setContentText("Ingresa los campos obligatorios");
	alerta.showAndWait();
    }
       } catch(AplicacionExcepcion e) {
	   
	   alerta.setContentText(e.getMessage());
	      alerta.showAndWait();
       }
    }

    @FXML
    private void salirRegistro(ActionEvent event) {
	Node source = (Node) event.getSource();
	Stage stage = (Stage) source.getScene().getWindow();
	stage.close();
    }
    
}
