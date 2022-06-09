
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	public TableView<Producto> tablaProductos;
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
	private ObservableList<Producto> listaProductos;
	@FXML
	private Button btnModificar;
	@FXML
	private Button btnEliminar;

	private long idProducto;
	private Alert alerta = new Alert(Alert.AlertType.INFORMATION);
    private String nombreAdministrador;
    @FXML
    private Menu perfil;
    @FXML
    private MenuItem nombreUsuario;
    @FXML
    private Button btnRegistroProducto;
    public void setNombreAdministrador(String nombreAdministrador) {
		this.nombreAdministrador = nombreAdministrador;
	}
    public String getNombreAdministrador() {
		return nombreAdministrador;
	}

	/**
	 * Initializes the controller class.
	 */

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		inicializarTablaProductos();
		
		Platform.runLater(() -> {
			nombreUsuario.setText(getNombreAdministrador());
		});
		
		URL urlProductoNuevo = getClass().getResource("/iconos/usuario.png");
		Image imaperfil= new Image(urlProductoNuevo.toString(), 16, 16, false, true);
		this.perfil.setGraphic(new ImageView(imaperfil));
		this.nombreUsuario.setGraphic(new ImageView(imaperfil) );
	}

	@FXML
	private void agregarProducto(ActionEvent event) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/registroProducto.fxml"));
		Parent root = loader.load();
		RegistroProductoController controlador = loader.getController();
		controlador.inicializarAtributos(listaProductos);
		Scene scene= new Scene(root);
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.showAndWait();
		
		Producto p = controlador.getProducto();
		if (p!=null) {
			this.listaProductos.add(p);
			this.tablaProductos.refresh();
		}
		
	}


	@FXML
	private void modificarProducto(ActionEvent event) throws ExcepcionProducto {
		ProductoDaoImp productoDao = new ProductoDaoImp();
		
		try {
			Producto producto = this.tablaProductos.getSelectionModel().getSelectedItem();
			producto.setNombreProducto(nombreProducto.getText());
			producto.setCostoUnidad(costoProducto.getText());
			producto.setNumeroUnidades(unidadesProducto.getText());
			producto.setDescripcion(descripcionProducto.getText());
			producto.setIdProducto(idProducto);

			String confirmacion = String.format("Estas seguro de modificar el producto?", nombreProducto.getText());

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setHeaderText(null);
			alert.setTitle("Confirmación");
			alert.setContentText(confirmacion);
			Optional<ButtonType> action = alert.showAndWait();
			if (action.get() == ButtonType.OK) {
				productoDao.modificarProducto(producto);
				this.listaProductos.remove(producto);
				this.listaProductos.add(producto);
				this.tablaProductos.refresh();
				this.tablaProductos.scrollTo(this.tablaProductos.getItems().size());
				cancelarAccion(event);
			} else {
				cancelarAccion(event);
				this.tablaProductos.refresh();
			}

		} catch (ExcepcionProducto ep) {
			alerta.setContentText(ep.getMessage());
			alerta.showAndWait();
		}

	}

	@FXML
	private void eliminarProducto(ActionEvent event) {

		ProductoDaoImp productoDao = new ProductoDaoImp();
		// obtenemos el nombre del producto a eliminar ya sea buscandolo o selecionando
		// en la lista de productos
		String nombre = nombreProducto.getText();
		Producto producto = this.tablaProductos.getSelectionModel().getSelectedItem();
		String confirmacion = String.format("¿Estas seguro de eliminar el producto? " + nombre);

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Confirmación");
		alert.setContentText(confirmacion);
		Optional<ButtonType> action = alert.showAndWait();

		if (action.get() == ButtonType.OK) {
			productoDao.borrarPorNombre(nombre);
			this.listaProductos.remove(producto);
			this.tablaProductos.refresh();
			this.tablaProductos.scrollTo(this.tablaProductos.getItems().size());
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
			this.columnProducto.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombreProducto"));
			this.columnCostoUnidad.setCellValueFactory(new PropertyValueFactory<Producto, Double>("costoUnidad") );
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
  
		nombreProducto.setText((String) tablaProductos.getSelectionModel().getSelectedItem().getNombreProducto());
		costoProducto.setText(String.valueOf(tablaProductos.getSelectionModel().getSelectedItem().getCostoUnidad()));
		unidadesProducto.setText(String.valueOf( tablaProductos.getSelectionModel().getSelectedItem().getNumeroUnidades()));
		descripcionProducto.setText((String) tablaProductos.getSelectionModel().getSelectedItem().getDescripcion());
		idProducto = tablaProductos.getSelectionModel().getSelectedItem().getIdProducto();
		
		btnCancelar.setDisable(false);
		btnEliminar.setDisable(false);
		btnModificar.setDisable(false);
   
	}

	

	@FXML
	private void salirAplicacion(ActionEvent event) {
		
		String confirmacion = String.format("¿Seguro de salir de gestion productos?");

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Confirmación");
		alert.setContentText(confirmacion);
		Optional<ButtonType> action = alert.showAndWait();

		if (action.get() == ButtonType.OK) {
		Stage  stage=(Stage)this.btnBuscarProducto.getScene().getWindow();
		stage.close();
		} else {
			
		}
	}

	

	@FXML
	private void mensajeAyuda(ActionEvent event) {

		String ayuda = "En gestion de productos puedes realizar lo siguiente:" + "\n"
				+ "1- Observar la lista de los productos registrados. " + "\n"
				+ "2- Buscar algun producto por su nombre, escribe el nombre del producto y oprime el boton buscar.\n"
				+ "3- Registrar un nuevo producto, oprime el boton registrar producto. \n"
				+ "4- Eliminar un producto, selecciona en la tabla o busca el producto que deseas eliminar y oprime "
				+ "el boton de eliminar. \n"
				+ "5- Modificar algún producto, selecciona o busca el producto a modificar cambia los datos y oprime el boton "
				+ "modificar.";
		Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		alerta.setTitle("Gestion Productos");
		alerta.setContentText(ayuda);
		alerta.showAndWait();
	}

    @FXML
    private void actualizarDatos(ActionEvent event) {
    	inicializarTablaProductos();
    	
    }


}
