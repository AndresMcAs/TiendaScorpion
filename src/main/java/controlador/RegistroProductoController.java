
package controlador;

import baseDatos.ProductoDao;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.ExcepcionProducto;
import modelo.Producto;

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

  /**
  * Initializes the controller class.
  */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    
      Date myDate = new Date();
      String fecha = new SimpleDateFormat("dd-MM-yyyy").format(myDate);
      fechaRegistro.setText(fecha);
       
  }    
  
  /**
  * inserccion de un prodcuto en la base de datos tienda 
  *  utilizando el objeto ProductoDao
 * @throws SQLException 
  */
  @FXML
  private void guardarProducto(ActionEvent event) throws SQLException {

    Producto producto = new Producto();
    ProductoDao productoDao = new ProductoDao();
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
        producto.setImagen(imgProducto.getAccessibleText());
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
}