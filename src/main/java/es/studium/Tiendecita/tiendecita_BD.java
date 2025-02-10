package es.studium.Tiendecita;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class tiendecita_BD {
    // Cambia estos valores según tu configuración de MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/tiendecitaJMRM"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "studium2024;"; 

    public static Connection conectar() {
        Connection conexion = null;
        try {
            // Conexión a la base de datos
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("¡Conexión exitosa a la base de datos!");
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos:");
            e.printStackTrace();
        }
        return conexion;
    }
}
