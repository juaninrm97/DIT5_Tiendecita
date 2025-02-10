package es.studium.Tiendecita;

import java.sql.Connection;

public class PruebaConexion {
    public static void main(String[] args) {
        Connection conexion = tiendecita_BD.conectar();
        if (conexion != null) {
            System.out.println("Conexión realizada correctamente.");
        } else {
            System.out.println("No se pudo conectar a la base de datos.");
        }
    }
}
