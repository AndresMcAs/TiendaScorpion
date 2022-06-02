package baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdminBd1 {
    public static Connection con;
    public static String base_datos_nombre = "d7bt5jntmj6frj";
    public static String usuario = "umnacldyrramxe";
    public static String passw = "32b3700cd064a82ecae646962cd22352c430620f605d43195322521c3b5d9d99";
    public static String url = "jdbc:postgresql://ec2-50-19-32-96.compute-1.amazonaws.com:5432/d7bt5jntmj6frj";

   
    
    public static Connection conectar() {

	try {
	    Class.forName("org.postgresql.Driver");
	    con = DriverManager.getConnection(url, usuario, passw);

	} catch (ClassNotFoundException e) {
	    System.out.println("No se encontro la clase");
	    e.printStackTrace();
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return con;
    }

    public static void cerrar() {

	try {
	    if (con != null)
		con.close();
	} catch (SQLException e) {
	    System.out.println("Error: No se logro cerrar la conexion: " + e);

	}

    }

}
