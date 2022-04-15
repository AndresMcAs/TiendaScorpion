package baseDatos;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import modelo.ExcepcionProducto;
import modelo.Producto;

public interface ProductoDao {

    boolean agregarProducto(Producto producto) throws SQLException;
    ObservableList<Producto>consultarProductos() throws ExcepcionProducto;
    Producto buscarProducto(String nombre);
    int borrarPorNombre(String nombre);
    void modificarProducto(Producto producto);
}
