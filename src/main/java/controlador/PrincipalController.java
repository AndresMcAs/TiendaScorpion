
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author andres Mendoza
 */
public class PrincipalController implements Initializable {

    @FXML
    private ImageView imgGestionProdutos;
    @FXML
    private ImageView imgInventario;
    @FXML
    private ImageView imgVentas;
    @FXML
    private ImageView imagRegistroPersonal;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		imagRegistroPersonal.setCursor(Cursor.HAND);
		imagRegistroPersonal.getCursor();
		imgGestionProdutos.setCursor(Cursor.HAND);
		imgGestionProdutos.getCursor();
		imgInventario.setCursor(Cursor.HAND);
		imgInventario.getCursor();
		imgVentas.setCursor(Cursor.HAND);
		imgVentas.getCursor();
	}

	
	@FXML
	private void cerrarAplicacion(ActionEvent event) {
		System.exit(0);
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Tienda.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	/**
	 * gestiona los productos realizando insertado,borrado edicion y busqueda de los
	 * productos de la tienda
	 * 
	 * @param event
	 */
	@FXML
	private void gestionarProductos(MouseEvent event) throws IOException {
		Scene scene = new Scene(loadFXML("/vista/Login"));
		Stage secundaryStage = new Stage();
		secundaryStage.setScene(scene);
		secundaryStage.initModality(Modality.APPLICATION_MODAL);
		secundaryStage.show();
	}

	@FXML
	private void realizarVentaProductos(MouseEvent event) throws IOException {

		Scene scene = new Scene(loadFXML("/vista/Login"));
		Stage secundaryStage = new Stage();
		secundaryStage.setScene(scene);
		secundaryStage.initModality(Modality.APPLICATION_MODAL);
		secundaryStage.show();
	}

	@FXML
	private void registrarEmpleados(MouseEvent event) throws IOException {
		Scene scene = new Scene(loadFXML("/vista/RegistrarUsuario"));
		Stage secundaryStage = new Stage();
		secundaryStage.setScene(scene);
		secundaryStage.initModality(Modality.APPLICATION_MODAL);
		secundaryStage.show();
	}

    @FXML
    private void VisualizarInventario(MouseEvent event) throws IOException {
    	
    	Scene scene = new Scene(loadFXML("/vista/Login"));
		Stage secundaryStage = new Stage();
		secundaryStage.setScene(scene);
		secundaryStage.initModality(Modality.APPLICATION_MODAL);
		secundaryStage.show();
    }

}
