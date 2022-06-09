/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import baseDatos.ProductoDaoImp;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelo.Producto;
import modelo.excepciones.ExcepcionProducto;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class InventarioProductosController implements Initializable {

    @FXML
    private TableView<Producto> tabProductos;
    @FXML
    private TableColumn<Producto, Integer> columnUnidades;
    @FXML
    private TableColumn<Producto, String> columnProductos;
    @FXML
    private TableColumn<Producto, String> columnFecha;
    @FXML
    private TableColumn<Producto, Double> colunncosto;
    ObservableList<Producto> listaProductos;
  
    @FXML
    private Button botonImprmir;
    @FXML
    private MenuItem perfil;
    private String nombreGerente;
    public void setNombreGerente(String nombreGerente) {
		this.nombreGerente = nombreGerente;
	}
    
    public String getNombreGerente() {
		return nombreGerente;
	}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializaTabla();
        
        Platform.runLater(() -> {
			perfil.setText(getNombreGerente());
		});
        URL urlProductoNuevo = getClass().getResource("/iconos/usuario.png");
		Image imaperfil= new Image(urlProductoNuevo.toString(), 16, 16, false, true);
		this.perfil.setGraphic(new ImageView(imaperfil));
		
    }    

    @FXML
    private void cerrarInvetario(ActionEvent event) {
    	String confirmacion = String.format("¿Segur@ deseas cerrar sesión?");

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Confirmación");
		alert.setContentText(confirmacion);
		Optional<ButtonType> action = alert.showAndWait();

		if (action.get() == ButtonType.OK) {
    	Stage  stage=(Stage)this.botonImprmir.getScene().getWindow();
		stage.close();}
    }
    
    public void inicializaTabla() {
    	
    	ProductoDaoImp productoDao = new ProductoDaoImp();

		try {
			// observableList con un la lista de productos que devuelve productoDao
			listaProductos = FXCollections.observableArrayList(productoDao.consultarProductos());
			tabProductos.setItems(listaProductos);
			columnProductos.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombreProducto"));
			colunncosto.setCellValueFactory(new PropertyValueFactory<Producto, Double>("costoUnidad"));
			columnUnidades.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("numeroUnidades"));
			columnFecha.setCellValueFactory(new PropertyValueFactory<Producto, String>("fechaRegistro"));
			

		} catch (ExcepcionProducto e) {
			e.printStackTrace();
		}

    }
    
    @FXML
    private void imprmirInventario(ActionEvent event) {
    }
    
    
}
