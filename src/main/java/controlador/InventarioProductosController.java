/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import baseDatos.ProductoDaoImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializaTabla();
    }    

    @FXML
    private void cerrarInvetario(ActionEvent event) {
    	System.exit(0);
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
    
}
