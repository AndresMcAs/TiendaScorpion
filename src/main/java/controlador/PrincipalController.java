
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author andres Mendoza 
 */
public class PrincipalController implements Initializable {

  @FXML
  private Button btnVentas;
  @FXML
  private Button btnGestionProductos;

  /**
  * Initializes the controller class.
  */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
        // TODO
  }    
    
  /**
  * realiza el proceso de ventas de los productos 
  * @param event 
  */
  @FXML
  private void ventas(ActionEvent event) {

  }
    
  /**
  * gestiona los productos realizando insertado,borrado
  * edicion y busqueda de los productos de la tienda 
  * @param event 
  */
  @FXML
  private void gestionProductos(ActionEvent event) throws IOException {
     
    Scene scene = new Scene(loadFXML("/vista/gestionProductos"));
    Stage secundaryStage = new Stage();
    secundaryStage.setScene(scene);
    secundaryStage.initModality(Modality.APPLICATION_MODAL);
    secundaryStage.show();
  }

  @FXML
  private void cerrarAplicacion(ActionEvent event) {
    System.exit(0);
  }

  private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Tienda.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }  
    
}
