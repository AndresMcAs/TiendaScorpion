package baseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;
import modelo.AplicacionExcepcion;

import modelo.Usuario;

public class UsuarioDaoImp implements UsuarioDao {
    private AdminBd admin;
    private Connection conexion;
    private boolean conexionTransferida;
    private Statement stm;
    private Alert alerta = new Alert(Alert.AlertType.INFORMATION);
    
    public UsuarioDaoImp() {
	admin = new AdminBd();
	conexion = null;
    }

    public UsuarioDaoImp(Connection conexion) {
	this.conexion = conexion;
	this.conexionTransferida = true;

    }

    @Override
    public Usuario obtenerUsuarioPorCorreoYContrasenia(String correo, String contrasenia) {

	Usuario usuarioHallado;

	ResultSet usuarioSet;

	try {
	    conexion = admin.obtenerConexion();
	    stm = conexion.createStatement();
	    usuarioSet = stm.executeQuery(
		    "select * from usuarios where correo='" + correo + "' and contrasenia='" + contrasenia + "'");
	    if (!usuarioSet.next()) {

	
		conexion.close();
		return null;
	    } else {

		String nombre = usuarioSet.getString("nombre");
		String apellidoPat = usuarioSet.getString("apellidopat");
		String apellidoMat = usuarioSet.getString("apellidomat");
		String rol = usuarioSet.getString("rol");

		usuarioHallado = new Usuario(nombre, apellidoPat, apellidoMat, rol);
		return usuarioHallado;
	    }
	} catch (SQLException e) {

	    alerta.setTitle("Usuario");
	    alerta.setContentText("Error al consultar la BD" + "\n" + e.getMessage());
	    alerta.showAndWait();
	    return null;
	}

    }

    @Override
    public Usuario registrarUsuario(Usuario usuario) throws AplicacionExcepcion {
	int resultInsert = 0;
	String sql;
	PreparedStatement ps = null;

	if (conexionTransferida == false) {
	    conexion = admin.obtenerConexion();
	}
	try {

	    sql = "insert into usuarios (nombre,apellidopat, apellidomat, correo,contrasenia,rol)"
		    + "values(?, ?, ?, ?, ?, ?)";

	    ps = conexion.prepareStatement(sql);
	    ps.setString(1, usuario.getNombre());
	    ps.setString(2, usuario.getApellidoPat());
	    ps.setString(3, usuario.getApellidoMat());
	    ps.setString(4, usuario.getCorreo());
	    ps.setString(5, usuario.getContrasenia());
	    ps.setString(6, usuario.getRol());

	    resultInsert = ps.executeUpdate();

	    if (resultInsert != 0) {
		conexion.close();
		return usuario;

	    } else {
		conexion.close();
		return null;
	    }
	} catch (SQLException e) {

	    alerta.setTitle("Usuario");
	    alerta.setContentText("Error no se inserto el Usuario:" + usuario.getNombre() + "\n" + e.getMessage());
	    alerta.showAndWait();
	    return null;
	}

    }

}
