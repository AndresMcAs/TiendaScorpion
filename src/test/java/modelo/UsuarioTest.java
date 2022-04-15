package modelo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import baseDatos.UsuarioDaoImp;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UsuarioTest {

  public UsuarioTest() {}
    
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	@Test
	
	public void debeGuardarUsuario() throws AplicacionExcepcion {
	System.out.println("Entrando a debeGuardarUsuario");
		
	       UsuarioDaoImp usuarioDao = new UsuarioDaoImp();
		Usuario usuario = null;
	
		usuario = new Usuario("Andres", "Mendoza","Contreras","andres@uacm.edu.mx","1234","ADMIN");
		Usuario usuarioGuardado = usuarioDao.registrarUsuario(usuario);
		
		assertNotNull(usuarioGuardado);
		assertNotNull(usuarioGuardado.getId());
	}
    
}

