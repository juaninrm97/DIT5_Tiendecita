package es.studium.Tiendecita;

import java.awt.Choice;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Metodos_Articulos {

    // Configuración de conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/tiendecitaJMRM";
    private static final String USER = "root";
    private static final String PASSWORD = "studium2024;";  // Cambia esto si es necesario

    // Método para insertar un artículo en la base de datos
    public static boolean insertarArticulo(String descripcion, double precio, int stock) {
        String query = "INSERT INTO Articulos (descripcionArticulo, precioArticulo, stockArticulo) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            // Establecer los valores de los parámetros
            ps.setString(1, descripcion);
            ps.setDouble(2, precio);
            ps.setInt(3, stock);

            // Ejecutar la actualización de la base de datos
            int rowsAffected = ps.executeUpdate();  // Ejecuta la inserción

            // Si se inserta correctamente, devolver true
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Si hay un error, devuelve false
        }
    }
    
    public static void eliminarArticulo(String descripcionArticulo) {
        String query = "DELETE FROM Articulos WHERE descripcionArticulo = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, descripcionArticulo); // Usamos la descripción para eliminar el artículo
            int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Artículo eliminado exitosamente.");
            } else {
                System.out.println("No se encontró el artículo para eliminar.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void cargarIdsArticulos(Choice choice) {
        String query = "SELECT idArticulos FROM Articulos";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            // Iterar sobre el resultado y añadir los IDs al Choice
            while (rs.next()) {
                int idArticulo = rs.getInt("idArticulos");
                choice.add(String.valueOf(idArticulo)); // Convertir a String y añadir al Choice
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Opcional: Mostrar un mensaje de error en la consola o la interfaz
        }
    }
    
    public static boolean modificarArticulo(int idArticulos, String nuevaDescripcion, double nuevoPrecio, int nuevoStock) {
        String query = "UPDATE Articulos SET descripcionArticulo = ?, precioArticulo = ?, stockArticulo = ? WHERE idArticulos = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            // Establecer los valores de los parámetros
            ps.setString(1, nuevaDescripcion);
            ps.setDouble(2, nuevoPrecio);
            ps.setInt(3, nuevoStock);
            ps.setInt(4, idArticulos);  // Usamos el ID para localizar el artículo en la base de datos

            // Ejecutar la actualización de la base de datos
            int rowsAffected = ps.executeUpdate();

            // Si se modifica correctamente, devolver true
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Si hay un error, devuelve false
        }
    }

    public static List<String> consultarArticulos() {
        List<String> articulos = new ArrayList<>();
        String query = "SELECT descripcionArticulo, precioArticulo, stockArticulo FROM Articulos";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String descripcion = rs.getString("descripcionArticulo");
                double precio = rs.getDouble("precioArticulo");
                int stock = rs.getInt("stockArticulo");

                // Formatear la información del artículo
                String articulo = "Descripción: " + descripcion + ", Precio: " + precio    + ", Stock: " + stock;
                articulos.add(articulo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articulos;
    }
    
}
