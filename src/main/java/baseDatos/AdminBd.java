
package baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Andres Mendoza
 */
public class AdminBd {
  private final  String url = "jdbc:oracle:thin:@localhost:1521:XE";
  private final String usuario;
  private final String contra;
  private static boolean isDriverLoaded = false;
  
  /**
   *  constructor para la conexion a la base de datos 
   */
  
  public AdminBd() {
    this.usuario = "tienda";
    this.contra = "tienda";
    
  }
  /**.
   * Funncion que se encarga de ralizar la conexion a la base de datos
   *
   */
  
  public Connection conectar()  {
       
    Connection con = null;
    if (isDriverLoaded) {
      try {
	con  = DriverManager.getConnection(url, usuario, contra);
    } catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
    }
      //System.out.println("Connection established");
    }
    return con;
  }
  
  /**
   * carga el driver 
   */
  static {
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
     // System.out.println("Driver Loaded");
      isDriverLoaded = true;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

    
}
