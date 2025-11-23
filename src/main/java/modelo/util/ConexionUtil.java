package modelo.util;
import java.sql.*;

public class ConexionUtil {

    private String url;
    private String user;
    private String password;

    private static ConexionUtil instance = null;

    private ConexionUtil() {
        url = "jdbc:mysql://localhost:3306/proyecto_cercos";
        user = "root";
        password = "";
    }

    public static ConexionUtil getInstance() {
        if (instance == null) {
            instance = new ConexionUtil(); 
        }
        return instance;
    }

    public Connection getConexion() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conexion = DriverManager.getConnection(url, user, password);
        return conexion;
    }
}
