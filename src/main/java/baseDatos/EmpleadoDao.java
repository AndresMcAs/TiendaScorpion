package baseDatos;

import modelo.Empleado;
import modelo.excepciones.AplicacionExcepcion;


public interface EmpleadoDao {
  
  /**
  * Metodo para obtener al usuario 
  * @param correo
  * @param contrasenia
  * @return Objeto usuario
  */
  Empleado obtenerUsuarioPorCorreoYContrasenia(String correo, String contrasenia);
  Empleado registrarEmpleado(Empleado empleado) throws AplicacionExcepcion;

  
}
