package modelo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import baseDatos.EmpleadoDaoImp;
import modelo.excepciones.AplicacionExcepcion;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmpleadoTest {

  public EmpleadoTest() {}
    
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	@Test
	
	public void debeGuardarUsuario() throws AplicacionExcepcion {
	System.out.println("Entrando a debeGuardarUsuario");
		
	       EmpleadoDaoImp usuarioDao = new EmpleadoDaoImp();
		Empleado usuario = null;
	
		usuario = new Empleado("Andres", "Mendoza","Contreras","andres@uacm.edu.mx","1234","ADMIN");
		Empleado usuarioGuardado = usuarioDao.registrarEmpleado(usuario);
		
		assertNotNull(usuarioGuardado);
		
	}
    
}

