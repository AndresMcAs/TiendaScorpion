
package baseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import modelo.ExcepcionProducto;
import modelo.Producto;

/**
 *
 * @author HP
 */
public class ProductoDaoImp implements ProductoDao{
  private AdminBd admin;
  private Connection conexion;
  private boolean conexionTransferida;
  private Statement stm;
  private Alert alerta = new Alert(Alert.AlertType.INFORMATION);
  
  public ProductoDaoImp() {
    admin = new AdminBd();
    conexion = null;
  }
  
  public ProductoDaoImp(Connection conexion) {
    this.conexion = conexion;
    this.conexionTransferida = true;  
    
  }  
  
  /**
   * Agrega el producto a registrar en la bsase de datos en la tabla productos
   * @param producto
   * @return
   * @throws SQLException
   */
  @Override
  public boolean agregarProducto(Producto producto) throws SQLException {

      int resultInsert = 0;
      String sql;
      PreparedStatement ps = null;

      if (conexionTransferida == false) {
	  conexion = admin.obtenerConexion();
      }
      try {

	  sql = "insert into productos (nombre, unidades, costo, descripcion,fecha)" + 
	         "values(?, ?, ?, ?, ?)";

	  ps = conexion.prepareStatement(sql);
	  ps.setString(1, producto.getNombreProducto());
	  ps.setInt(2, producto.getNumeroUnidades());
	  ps.setDouble(3, producto.getCostoUnidad());
	  ps.setString(4, producto.getDescripcion());
	  ps.setString(5, producto.getFechaRegistro());
	  // ps.setString(6, producto.getImagen());
	
	  resultInsert = ps.executeUpdate();

	  if (resultInsert != 0) {
	      conexion.close();
	      return true;

	  } else {
	      conexion.close();
	      return false;
	  }
      } catch (SQLException e) {
	
	  alerta.setTitle("Producto");
	  alerta.setContentText(
		  "Error no se inserto el producto:" + producto.getNombreProducto() + "\n" + e.getMessage());
	  alerta.showAndWait();
	  return false;
      }

  }
  
  @Override
  public ObservableList<Producto> consultarProductos() throws ExcepcionProducto {

      String sql;
      PreparedStatement ps = null;
      ResultSet rs;
      Producto producto = null;
      ObservableList<Producto> lista = FXCollections.observableArrayList();
      sql = "select nombre,unidades,costo,descripcion,fecha from productos";
      try {
	  conexion = admin.obtenerConexion();
	  ps = conexion.prepareStatement(sql);
	  rs = ps.executeQuery();

	  while (rs.next()) {
	      producto = new Producto();
	      producto.setNombreProducto(rs.getString("nombre"));
	      producto.setNumeroUnidades(rs.getString("unidades"));
	      producto.setCostoUnidad(rs.getString("costo"));
	      producto.setDescripcion(rs.getString("descripcion"));
	      producto.setFechaRegistro(rs.getString("fecha"));
	      lista.add(producto);
	  }
	  rs.close();
	  ps.close();
	  conexion.close();
      } catch (SQLException e) {
	  e.printStackTrace();
      }
      
      return lista;
  }
  
  @Override
  public Producto buscarProducto(String nombre) {
      
      Producto productoHallado;

      ResultSet productoSet;

      try {
	  conexion = admin.obtenerConexion();
	  stm = conexion.createStatement();
	  productoSet = stm.executeQuery("select * from productos where nombre= "
	  + "'" + nombre.trim() + "'");
	  if (!productoSet.next()) {

	      alerta.setTitle("Usuario");
	      alerta.setContentText("Producto no encontrado");
	      alerta.showAndWait();
	      conexion.close();
	      return null;
	  } else {

	      String nombreProducto = productoSet.getString("nombre");
	      int unidades = productoSet.getInt("unidades");
	      double costo = productoSet.getDouble("costo");
	      String descripcion = productoSet.getString("descripcion");

	      productoHallado = new Producto(nombreProducto, unidades, costo, descripcion);
	      return productoHallado;
	  }
      } catch (SQLException e) {
	      alerta.setTitle("Producto");
	      alerta.setContentText("Error al consultar la base de datos" +"\n"+e.getMessage());
	      alerta.showAndWait();
	  return null;
      }

  }
  
  @Override
  public int borrarPorNombre(String nombre) {
      int result = 0;

      try {
	  String sql = "delete from productos where nombre= ?";
	  conexion = admin.obtenerConexion();
	  PreparedStatement stmt = conexion.prepareStatement(sql);
	  stmt.setString(1, nombre);
	  result = stmt.executeUpdate();

	  alerta.setTitle("Producto");
	  alerta.setContentText("Se borrado el producto con exito ");
	  alerta.showAndWait();
	  
      } catch (SQLException e) {
	  e.printStackTrace();
	  
	  alerta.setTitle("Producto");
	  alerta.setContentText("Error no se borro el producto:" + nombre + 
	  	"\n" + e.getMessage() );
	  alerta.showAndWait();
      }
      return result;
  }

  @Override
  public void modificarProducto(Producto producto) {
      // TODO Auto-generated method stub

  }
}
