
package controlador;

import baseDatos.ProductoDaoImp;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    @FXML
    private Button btnRegresar;
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
  
  /**
  * inserccion de un prodcuto en la base de datos tienda 
  *  utilizando el objeto ProductoDao
 * @throws SQLException 
  */
  @FXML
  private void guardarProducto(ActionEvent event) throws SQLException {

    Producto producto = new Producto();
    ProductoDaoImp productoDao = new ProductoDaoImp();
    boolean productoAgregado = false;    
    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
    alerta.setTitle("Producto");
    
    try { 
   
      if (txtFNombreProducto.getText().length() != 0 && txtFNumeroUnidades.getText().length() != 0
          && txtFCosto.getText().length() != 0) {
        producto.setNombreProducto(txtFNombreProducto.getText());
        producto.setNumeroUnidades(txtFNumeroUnidades.getText());
        producto.setCostoUnidad(txtFCosto.getText());
        producto.setDescripcion(txtADescripcion.getText());
        producto.setFechaRegistro(fechaRegistro.getText());
       // producto.setImagen(imgProducto.getAccessibleText());
        productoAgregado = productoDao.agregarProducto(producto);
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
  private void regresarGestionProductos(ActionEvent event) {
    Node source = (Node) event.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
    
  }

  @FXML
  private void cancelarRegistroProducto(ActionEvent event) {
    limpiar();
  }

    @FXML
    private void seleccionarArchivo(ActionEvent event) {
    	
    	Node source = (Node) event.getSource();
    	Stage stage = (Stage) source.getScene().getWindow();
    	FileChooser btnSeleccionImagen= new FileChooser();
    	File archivo =btnSeleccionImagen.showOpenDialog(stage);
        if (archivo != null) {
        	
  		 String origen = archivo.getPath();
  		 javafx.scene.image.Image imagen = new javafx.scene.image.Image(origen);
  		  
  		  imgProducto.setImage(imagen);
        }
    
    }
    
    private void colocarIconoBoton() {
    	
    	URL urlproductoNuevo = getClass().getResource("/iconos/lista-de-verificacion.gif");
    	
    	Image imagenProducto= new Image(urlproductoNuevo.toString(),16,16,false,true);
    	
    	
    	btnGuardarProducto.setGraphic(new ImageView(imagenProducto));
        
    }

    
    
   

}
