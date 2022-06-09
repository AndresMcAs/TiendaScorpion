
package controlador;

import baseDatos.ProductoDao;
import baseDatos.ProductoDaoImp;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modelo.Producto;
import modelo.excepciones.ExcepcionProducto;

/**
 * FXML Controller class
 *
 * @author Andres Mendoza
 */
public class RegistroProductoController implements Initializable {

	@FXML
	private TextField txtFNombreProducto;
	@FXML
	private TextField txtFNumeroUnidades;
	@FXML
	private TextField txtFCosto;
	@FXML
	private TextArea txtADescripcion;
	@FXML
	private ImageView imgProducto;
	@FXML
	private Button btnSeleccionImagen;
	@FXML
	private Button btnGuardarProducto;
	@FXML
	private Button btnCancelar;
	@FXML
	private Pane panelRegistro;
	@FXML
	private TextField fechaRegistro;
	private Producto producto;
	private ObservableList<Producto> listaProductos;
    @FXML
    private MenuItem btnCerrar;
    
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		Date myDate = new Date();
		String fecha = new SimpleDateFormat("dd-MM-yyyy").format(myDate);
		fechaRegistro.setText(fecha);
		colocarIconoBoton();
		

	}
	
	public void inicializarAtributos(ObservableList<Producto> listaProductos) {
		
		this.listaProductos = listaProductos;
	}
    
	/**
	 * inserccion de un prodcuto en la base de datos tienda utilizando el objeto
	 * ProductoDao
	 * 
	 * @throws SQLException
	 */
	@FXML
	private void guardarProducto(ActionEvent event) throws SQLException {
        
	    Producto productoGuardado = new Producto();
		ProductoDao productoDao = new ProductoDaoImp();
		boolean productoAgregado = false;
		Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		alerta.setTitle("Producto");

		try { 

			if (txtFNombreProducto.getText().length() != 0 && txtFNumeroUnidades.getText().length() != 0
					&& txtFCosto.getText().length() != 0) {
				productoGuardado.setNombreProducto(txtFNombreProducto.getText());
				productoGuardado.setNumeroUnidades(txtFNumeroUnidades.getText());
				productoGuardado.setCostoUnidad(txtFCosto.getText());
				productoGuardado.setDescripcion(txtADescripcion.getText());
				productoGuardado.setFechaRegistro(fechaRegistro.getText());
				productoGuardado.setImagen(imgProducto.getAccessibleText());
				
				this.producto = productoGuardado;
			    productoAgregado = productoDao.agregarProducto(productoGuardado);
				
				if (productoAgregado == true) {
					
					alerta.setContentText("Registro Exitoso!");
					alerta.showAndWait();
					
				limpiar();
				}
			} else {

				alerta.setContentText("Ingresa los campos obligatorios");
				alerta.showAndWait();
			}
		} catch (ExcepcionProducto e) {

			alerta.setContentText(e.getMessage());
			alerta.showAndWait();
		}
	}

	/**
	 * limpia los campos del formulario registro producto
	 */
	public void limpiar() {

		txtFNombreProducto.setText("");
		txtFNumeroUnidades.setText("");
		txtFCosto.setText("");
		txtFNumeroUnidades.setText("");
		txtADescripcion.setText("");
	}

	

	@FXML
	private void cancelarRegistroProducto(ActionEvent event) {
		
		String confirmacion = String.format("¿Estas seguro de cancelar el registro?" );

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Confirmación");
		alert.setContentText(confirmacion);
		Optional<ButtonType> action = alert.showAndWait();

		if (action.get() == ButtonType.OK) {
			limpiar();
		} else {
			
		}

	}

	private void colocarIconoBoton() {

		URL urlProductoNuevo = getClass().getResource("/iconos/lista-de-verificacion.gif");

		Image imaProductoNuevo = new Image(urlProductoNuevo.toString(), 16, 16, false, true);

		btnGuardarProducto.setGraphic(new ImageView(imaProductoNuevo));

	}

	@FXML
	private void seleccionarImagen(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		File archivo = fileChooser.showOpenDialog(stage);
       
		if (archivo != null) {
			
			String origen = archivo.getPath();
			Image imagen = new Image(origen);
			imgProducto.setImage(imagen);
		}
		 
	}

	public Producto getProducto() {
		return producto;
	}

    @FXML
    private void regresarGestion(ActionEvent event) {
    	
    	Stage  stage=(Stage)this.btnGuardarProducto.getScene().getWindow();
		stage.close();
		
		
    }
  
}
