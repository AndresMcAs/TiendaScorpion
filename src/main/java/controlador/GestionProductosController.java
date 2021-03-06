
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import baseDatos.ProductoDaoImp;
import javafx.application.Platform;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Producto;
import modelo.excepciones.ExcepcionProducto;

/**
 * FXML Controller class
 *
 * @author Andres_Mendoza
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
    @FXML
    private Button btnActualizar;
    @FXML
    private Label txtLusuario;
    @FXML
    private TextField txtf;
    
    private long idProducto;
    private Alert alerta = new Alert(Alert.AlertType.INFORMATION);  
    private String nombreEmp;  //atributo para recuperar el nombre del usuario que ingreso al sistema 
  
	public String getNombreEmp() {
		return nombreEmp;
	}

	public void setNombreEmp(String nombreEmp) {
		this.nombreEmp = nombreEmp;
	}

	/**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
	  inicializarTablaProductos();
	  
      Platform.runLater(()->{
    	  txtf.setText(getNombreEmp());
      });
	   
    }

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
	private void modificarProducto(ActionEvent event) throws ExcepcionProducto {
		ProductoDaoImp productoDao = new ProductoDaoImp();
		Producto producto = new Producto();
		try {
			producto.setNombreProducto(nombreProducto.getText());
			producto.setCostoUnidad(costoProducto.getText());
			producto.setNumeroUnidades(unidadesProducto.getText());
			producto.setDescripcion(descripcionProducto.getText());
			producto.setIdProducto(idProducto);

			String confirmacion = String.format("Estas seguro de modificar el producto?", nombreProducto.getText());

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setHeaderText(null);
			alert.setTitle("Confirmaci??n");
			alert.setContentText(confirmacion);
			Optional<ButtonType> action = alert.showAndWait();
			if (action.get() == ButtonType.OK) {
				productoDao.modificarProducto(producto);
				cancelarAccion(event);
			} else {
				cancelarAccion(event);
			}

		} catch (ExcepcionProducto ep) {
			alerta.setContentText(ep.getMessage());
			alerta.showAndWait();
		}

	}

	@FXML
	private void eliminarProducto(ActionEvent event) {
		
		ProductoDaoImp productoDao = new ProductoDaoImp();
		//obtenemos el nombre del producto a eliminar ya sea buscandolo o selecionando en la lista de productos 
		String nombre = nombreProducto.getText();
		String confirmacion = String.format("Estas seguro de eliminar el producto?" + nombre);
		
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Confirmaci??n");
		alert.setContentText(confirmacion);
		Optional<ButtonType> action = alert.showAndWait();
		
		if (action.get() == ButtonType.OK) {
			productoDao.borrarPorNombre(nombre);
			cancelarAccion(event);
		} else {
			cancelarAccion(event);
		}
		
	}

	public void inicializarTablaProductos() {
		ProductoDaoImp productoDao = new ProductoDaoImp();

		try {
			// observableList con un la lista de productos que devuelve productoDao
			listaProductos = FXCollections.observableArrayList(productoDao.consultarProductos());
			tablaProductos.setItems(listaProductos);
			columnProducto.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombreProducto"));
			columnCostoUnidad.setCellValueFactory(new PropertyValueFactory<Producto, Double>("costoUnidad"));
			columnUnidades.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("numeroUnidades"));
			columnFechaRegistro.setCellValueFactory(new PropertyValueFactory<Producto, String>("fechaRegistro"));
			columnDescripcion.setCellValueFactory(new PropertyValueFactory<Producto, String>("descripcion"));

		} catch (ExcepcionProducto e) {
			e.printStackTrace();
		}

	}

	@FXML
	private void buscarProducto(ActionEvent event) {
		ProductoDaoImp productoDao = new ProductoDaoImp();
		if (txtBuscarProducto.getText().length() != 0) {

			Producto producto = productoDao.buscarProducto(txtBuscarProducto.getText());
			if (producto != null) {
				nombreProducto.setText(producto.getNombreProducto());
				costoProducto.setText(String.valueOf(producto.getCostoUnidad()));
				unidadesProducto.setText(String.valueOf(producto.getNumeroUnidades()));
				descripcionProducto.setText(producto.getDescripcion());
				idProducto = producto.getIdProducto();
				btnCancelar.setDisable(false);
				btnEliminar.setDisable(false);
				btnModificar.setDisable(false);

			} else {

				cancelarAccion(event);
			}
		} else {

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
		unidadesProducto
				.setText(String.valueOf(tablaProductos.getSelectionModel().getSelectedItem().getNumeroUnidades()));
		descripcionProducto.setText(tablaProductos.getSelectionModel().getSelectedItem().getDescripcion());
		idProducto = tablaProductos.getSelectionModel().getSelectedItem().getIdProducto();
		btnCancelar.setDisable(false);
		btnEliminar.setDisable(false);
		btnModificar.setDisable(false);

	}

	@FXML
	private void actualizarTabla(ActionEvent event) {
		inicializarTablaProductos();
	}

	@FXML
	private void salirAplicacion(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	private void cerrarGestionProductos(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void CerrarSesesionGestion(ActionEvent event) {

		// logout

	}

    @FXML
    private void mensajeAyuda(ActionEvent event) {
    	
        String ayuda="En gestion de productos puedes realizar lo siguiente:" + "\n"
                + "1- Observar la lista de los productos registrados. " + "\n"
                + "2- Buscar algun producto por su nombre, escribe el nombre del producto y oprime el boton buscar.\n"
                + "3- Registrar un nuevo producto, oprime el boton registrar producto. \n" 
                + "4- Eliminar un producto, selecciona en la tabla o busca el producto que deseas eliminar y oprime "
                + "el boton de eliminar. \n"
                +"5- Modificar alg??n producto, selecciona o busca el producto a modificar cambia los datos y oprime el boton "
                + "modificar.";
		Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		alerta.setTitle("Gestion Productos");
		alerta.setContentText(ayuda);
		alerta.showAndWait();
    }

}
