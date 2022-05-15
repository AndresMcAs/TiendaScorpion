/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Empleado;
import modelo.excepciones.AplicacionExcepcion;
import baseDatos.EmpleadoDaoImp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    @FXML
    private ComboBox<String> comboPuesto;
    @FXML
    private Text txtPcontrasenia1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboPuesto.getItems().add("ADMIN");
        comboPuesto.getItems().add("VENDEDOR");
        comboPuesto.getItems().add("GERENTE");
     
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
        
        Empleado usuario = new Empleado();
        Empleado usuarioGuardado = new Empleado();
        EmpleadoDaoImp usuarioDao = new EmpleadoDaoImp();
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Producto");
       try { 
       if(txtFNombre.getText().length() != 0 && txtFcorreo.getText().length() != 0 && 
	      txtFapellidoPat.getText().length() !=0  && txtPassword.getText().length() != 0 &&
	      !comboPuesto.getSelectionModel().isEmpty()) {
        
	usuario.setNombre(txtFNombre.getText());
        usuario.setApellidoPat(txtFapellidoPat.getText());
        usuario.setApellidoMat(txtFapellidoMat.getText());
        usuario.setCorreo(txtFcorreo.getText());
        usuario.setContrasenia(txtPassword.getText());
        usuario.setPuesto(comboPuesto.getSelectionModel().getSelectedItem());
        usuarioGuardado = usuarioDao.registrarEmpleado(usuario);
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
