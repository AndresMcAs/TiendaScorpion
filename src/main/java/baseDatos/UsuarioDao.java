package baseDatos;

import java.util.List;
import modelo.AplicacionExcepcion;
import modelo.Usuario;


public interface UsuarioDao {
  
  /**
  * Metodo para obtener al usuario 
  * @param correo
  * @param contrasenia
  * @return Objeto usuario
  */
  Usuario obtenerUsuarioPorCorreoYContrasenia(String correo, String contrasenia);
  Usuario registrarUsuario(Usuario usuario) throws AplicacionExcepcion;

  
}
