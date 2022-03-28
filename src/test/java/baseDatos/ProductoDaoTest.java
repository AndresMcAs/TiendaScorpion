package baseDatos;

import java.sql.SQLException;
import modelo.ExcepcionProducto;
import modelo.Producto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author HP
 */
public class ProductoDaoTest {
    
  public ProductoDaoTest() {
  }
    
  @BeforeAll
  public static void setUpClass() {
  }
    
  @AfterAll
  public static void tearDownClass() {
  }
    
  @BeforeEach
  public void setUp() {
  }
    
  @AfterEach
  public void tearDown() {
  }

  /**
  * probando conexion a la base de datos 
  */
  @Test 
  public void debeConectar() throws SQLException {
    AdminBd con = new AdminBd();
    con.conectar();
    assertNotNull(con);
  }
   
  @Test
  public void debeGuardar() throws SQLException {
      Producto producto = new Producto();
      ProductoDao productoDao = new ProductoDao();

      try {
	  producto.setNombreProducto("Galleta");
	  producto.setNumeroUnidades("20");
	  producto.setCostoUnidad("12.50");
	  producto.setDescripcion("Galletas Emperador");
	  productoDao.agregarProducto(producto);

      } catch (ExcepcionProducto e) {
	  System.out.println("" + e.getMessage());
      }
  }
 
  @Test 
  void debeBuscar() {
      Producto producto = new Producto();
      ProductoDao productoDao = new ProductoDao();
      producto = productoDao.buscarProducto("Galleta");
      assertNotNull(producto);
      
  }
  

}
