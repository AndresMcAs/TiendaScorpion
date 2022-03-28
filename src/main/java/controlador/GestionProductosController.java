
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import baseDatos.ProductoDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.ExcepcionProducto;
import modelo.Producto;

/**
 * FXML Controller class
 *
 * @author Andres Mendoza
 */
public class GestionProductosController implements Initializable {

    @FXML
    private TableView<Producto> tablaProductos;
    @FXML
    private TableColumn<Producto, String> columnProducto;
    @FXML
    private TableColumn<Producto, Double> columnCostoUnidad;
    @FXML
    private TableColumn<Producto, Integer> columnUnidades;
    @FXML
    private TableColumn<Producto, String> columnFechaRegistro;
    @FXML
    private TableColumn<Producto, String> columnDescripcion;
    @FXML
    private TextField nombreProducto;
    @FXML
    private TextField costoProducto;
    @FXML
    private TextField unidadesProducto;
    @FXML
    private TextField descripcionProducto;
    @FXML
    private TextField txtBuscarProducto;
    @FXML
    private Button btnBuscarProducto;
    @FXML
    private Button btnCancelar;
    ObservableList<Producto> listaProductos;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
	inicializarTablaProductos();

    }

    @FXML
    private void regresarPrincipal(ActionEvent event) {
	Node source = (Node) event.getSource();
	Stage stage = (Stage) source.getScene().getWindow();
	stage.close();
    }

    @FXML
    private void agregarProducto(ActionEvent event) throws IOException {
	Scene scene = new Scene(loadFXML("/vista/registroProducto"));
	Stage secundaryStage = new Stage();
	secundaryStage.setScene(scene);
	secundaryStage.initModality(Modality.APPLICATION_MODAL);
	secundaryStage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
	FXMLLoader fxmlLoader = new FXMLLoader(Tienda.class.getResource(fxml + ".fxml"));
	return fxmlLoader.load();
    }

    @FXML
    private void modificarProducto(ActionEvent event) {

    }

    @FXML
    private void eliminarProducto(ActionEvent event) {
	ProductoDao productoDao = new ProductoDao();
	String nombre = nombreProducto.getText();
	String confirmacion = String.format("Estas seguro de eliminar el producto?", nombre);
        int respuesta = JOptionPane.showConfirmDialog(null, confirmacion,
             "Eliminar Producto", JOptionPane.YES_NO_OPTION);
       if (respuesta == JOptionPane.YES_OPTION) {
	    
	  productoDao.borrarPorNombre(nombre);
	  tablaProductos.refresh();
	  }else {
	    cancelarAccion(event);
	}
	
	
    }

    public void inicializarTablaProductos() {
	ProductoDao productoDao = new ProductoDao();

	try {
	    // observableList con un la lista de productos que devuelve productoDao
	    listaProductos = FXCollections.observableArrayList(productoDao.consultarProductos());
	    tablaProductos.setItems(listaProductos);
	    columnProducto.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombreProducto"));
	    columnCostoUnidad.setCellValueFactory(new PropertyValueFactory<Producto, Double>("costoUnidad"));
	    columnUnidades.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("numeroUnidades"));
	    columnFechaRegistro.setCellValueFactory(new PropertyValueFactory<Producto, String>("fechaRegistro"));
	    columnDescripcion.setCellValueFactory(new PropertyValueFactory<Producto, String>("descripcion"));
	    tablaProductos.refresh();

	} catch (ExcepcionProducto e) {
	    e.printStackTrace();
	}

    }

    @FXML
    private void buscarProducto(ActionEvent event) {
	ProductoDao productoDao = new ProductoDao();
	if (txtBuscarProducto.getText().length() != 0) {

	    Producto producto = productoDao.buscarProducto(txtBuscarProducto.getText());
            if(producto != null) {
		nombreProducto.setText(producto.getNombreProducto());
		costoProducto.setText(String.valueOf(producto.getCostoUnidad()));
		unidadesProducto.setText(String.valueOf(producto.getNumeroUnidades()));
		descripcionProducto.setText(producto.getDescripcion());
		btnCancelar.setDisable(false);
		btnEliminar.setDisable(false);
		btnModificar.setDisable(false);
            } else {
                 
                 cancelarAccion(event);
            }
	} else {
	    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
	    alerta.setTitle("Busqueda Producto");
	    alerta.setContentText("Escribe el nombre del producto que deseas buscar");
	    alerta.showAndWait();
	}
    }

    @FXML
    private void cancelarAccion(ActionEvent event) {

	txtBuscarProducto.setText("");
	nombreProducto.setText("");
	costoProducto.setText("");
	unidadesProducto.setText("");
	descripcionProducto.setText("");
	btnCancelar.setDisable(true);
	btnEliminar.setDisable(true);
	btnModificar.setDisable(true);
    }

    @FXML
    private void seleccionarProducto(MouseEvent event) {
	
	nombreProducto.setText(tablaProductos.getSelectionModel().getSelectedItem().getNombreProducto());
	costoProducto.setText(String.valueOf(tablaProductos.getSelectionModel().getSelectedItem().getCostoUnidad()));
	unidadesProducto.setText(String.valueOf(tablaProductos.getSelectionModel().getSelectedItem().getNumeroUnidades()));
	descripcionProducto.setText(tablaProductos.getSelectionModel().getSelectedItem().getDescripcion());
	
	btnCancelar.setDisable(false);
	btnEliminar.setDisable(false);
	btnModificar.setDisable(false);

    }

}
