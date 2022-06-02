package baseDatos;

import modelo.Empleado;
import modelo.excepciones.AplicacionExcepcion;

public interface EmpleadoDao {

	Empleado obtenerUsuarioPorCorreoYContrasenia(String correo, String contrasenia);

	Empleado registrarEmpleado(Empleado empleado) throws AplicacionExcepcion;

}
