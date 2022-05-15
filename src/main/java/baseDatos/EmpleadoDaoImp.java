package baseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;
import modelo.Empleado;
import modelo.excepciones.AplicacionExcepcion;

public class EmpleadoDaoImp implements EmpleadoDao {
    private AdminBd admin;
    private Connection conexion;
    private boolean conexionTransferida;
    private Statement stm;
    private Alert alerta = new Alert(Alert.AlertType.INFORMATION);
    
    public EmpleadoDaoImp() {
	admin = new AdminBd();
	conexion = null;
    }

    public EmpleadoDaoImp(Connection conexion) {
	this.conexion = conexion;
	this.conexionTransferida = true;

    }

    @Override
    public Empleado obtenerUsuarioPorCorreoYContrasenia(String correo, String contrasenia) {

	Empleado usuarioHallado;

	ResultSet usuarioSet;

	try {
	    conexion = admin.conectar();
	    stm = conexion.createStatement();
	    usuarioSet = stm.executeQuery(
		    "select * from empleados where correo='" + correo + "' and contrasenia='" + contrasenia + "'");
	    if (!usuarioSet.next()) {

	
		conexion.close();
		return null;
	    } else {

		String nombre = usuarioSet.getString("nombre");
		String apellidoPat = usuarioSet.getString("apellidopat");
		String apellidoMat = usuarioSet.getString("apellidomat");
		String puesto = usuarioSet.getString("puesto");

		usuarioHallado = new Empleado(nombre, apellidoPat, apellidoMat, puesto);
		return usuarioHallado;
	    }
	} catch (SQLException e) {

	    alerta.setTitle("Empleado");
	    alerta.setContentText("Error al consultar la BD" + "\n" + e.getMessage());
	    alerta.showAndWait();
	    return null;
	}

    }

    @Override
    public Empleado registrarEmpleado(Empleado empleado) throws AplicacionExcepcion {
	int resultInsert = 0;
	String sql;
	PreparedStatement ps = null;

	if (conexionTransferida == false) {
	    conexion = admin.conectar();
	}
	try {

	    sql = "insert into empleados (nombre,apellidopat, apellidomat, correo,contrasenia,puesto)"
		    + "values(?, ?, ?, ?, ?, ?)";

	    ps = conexion.prepareStatement(sql);
	    ps.setString(1, empleado.getNombre());
	    ps.setString(2, empleado.getApellidoPat());
	    ps.setString(3, empleado.getApellidoMat());
	    ps.setString(4, empleado.getCorreo());
	    ps.setString(5, empleado.getContrasenia());
	    ps.setString(6, empleado.getPuesto());
       System.out.println("empleado");
	    resultInsert = ps.executeUpdate();

	    if (resultInsert != 0) {
		conexion.close();
		return empleado;

	    } else {
		conexion.close();
		return null;
	    }
	} catch (SQLException e) {

	    alerta.setTitle("Empleado");
	    alerta.setContentText("Error no se inserto el Empleado:" + empleado.getNombre() + "\n" + e.getMessage());
	    alerta.showAndWait();
	    return null;
	}

    }

}
