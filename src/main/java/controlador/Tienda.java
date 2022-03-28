
package controlador;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author AndresMC
 */
public class Tienda extends Application {
  private static Scene scene;
  
  @Override
  public void start(Stage primaryStage)throws IOException {
    scene = new Scene(loadFXML("/vista/principal"));
    primaryStage.setScene(scene);
    primaryStage.show();
  }
    
  private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Tienda.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }

  /**
  * @param args the command line arguments
  */
  public static void main(String[] args) {
    launch(args);
  }
    
}
