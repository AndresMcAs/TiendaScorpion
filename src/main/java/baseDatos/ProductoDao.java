package baseDatos;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import modelo.Producto;
import modelo.excepciones.ExcepcionProducto;

public interface ProductoDao {

	boolean agregarProducto(Producto producto) throws SQLException;

	ObservableList<Producto> consultarProductos() throws ExcepcionProducto;

	Producto buscarProducto(String nombre);

	int borrarPorNombre(String nombre);

	void modificarProducto(Producto producto);

	void modificarProductoExistencias(Producto producto, int cantidad);
}
