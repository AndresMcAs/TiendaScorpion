package baseDatos;

import java.sql.SQLException;
import modelo.ExcepcionProducto;
import modelo.Producto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author HP
 */
public class ProductoDaoImpTest {
    
  public ProductoDaoImpTest() {
  }

   
  /**
  * probando conexion a la base de datos 
  */
  @Test 
  public void debeConectar() throws SQLException {
    AdminBd con = new AdminBd();
    con.obtenerConexion();
    assertNotNull(con);
  }
   @Disabled
  @Test
  public void debeGuardar() throws SQLException {
      Producto producto = new Producto();
     ProductoDaoImp productoDao = new ProductoDaoImp();

      try {
	  producto.setNombreProducto("Galletas");
	  producto.setNumeroUnidades("20");
	  producto.setCostoUnidad("12.50");
	  producto.setFechaRegistro("10-04-22");
	  producto.setDescripcion("Galletas Emperador");
	  productoDao.agregarProducto(producto);

      } catch (ExcepcionProducto e) {
	  System.out.println("" + e.getMessage());
      }
  }
 
  @Test 
  void debeBuscar() {
      Producto producto = new Producto();
      ProductoDaoImp productoDao = new ProductoDaoImp();
      producto = productoDao.buscarProducto("Galletas");
      assertNotNull(producto);
      
  }
  

}
