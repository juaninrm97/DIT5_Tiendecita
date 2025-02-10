package es.studium.Tiendecita;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Metodos_Ticket {

    private static final String URL = "jdbc:mysql://localhost:3306/tiendecitaJMRM";
    private static final String USER = "root";
    private static final String PASSWORD = "studium2024;";

 
    public static boolean insertarTicket(String idTicket, String fechaTicket, double precioTotal, List<Integer> numeroArticulos) {
        // Consulta para insertar en la tabla Tickets
        String sqlTicket = "INSERT INTO Tickets (idTickets, fechaTicket, precioTotalTicket, articulosTicket) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmtTicket = conn.prepareStatement(sqlTicket, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Convertir la fecha en formato dd-MM-yyyy a java.sql.Date
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date utilDate = sdf.parse(fechaTicket); // Parseamos la fecha en formato dd-MM-yyyy
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // Convertimos a java.sql.Date

            // Establecer los valores para la inserción del ticket
            stmtTicket.setString(1, idTicket);
            stmtTicket.setDate(2, sqlDate); // Usamos java.sql.Date en lugar de String
            stmtTicket.setDouble(3, precioTotal);
            stmtTicket.setInt(4, numeroArticulos.size()); // Cantidad de artículos en el ticket
            
            // Ejecutar la inserción del ticket
            int filasAfectadas = stmtTicket.executeUpdate();
            if (filasAfectadas > 0) {
                // Obtener el ID del ticket recién insertado
                ResultSet rs = stmtTicket.getGeneratedKeys();
                if (rs.next()) {
                    int idTicketGenerado = rs.getInt(1);

                    // Ahora insertamos las relaciones en la tabla Compras
                    String sqlCompras = "INSERT INTO Compras (idArticulosFK, idTicketsFK) VALUES (?, ?)";
                    try (PreparedStatement stmtCompras = conn.prepareStatement(sqlCompras)) {
                        for (int idArticulo : numeroArticulos) {
                            System.out.println("Insertando artículo con ID: " + idArticulo);
                            stmtCompras.setInt(1, idArticulo);
                            stmtCompras.setInt(2, idTicketGenerado);
                            stmtCompras.addBatch(); // Agregar a la batch
                        }

                        stmtCompras.executeBatch(); // Ejecutar todas las inserciones en Compras
                    }

                    return true; // Ticket y compras relacionadas insertados correctamente
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar el ticket: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al procesar la fecha: " + e.getMessage());
        }

        return false; // Si llegamos aquí, algo salió mal
    }

    
    public static List<String> obtenerTickets() {
        List<String> tickets = new ArrayList<>();
        String sql = "SELECT idTickets, fechaTicket, precioTotalTicket, articulosTicket FROM Tickets";

        // Formato de fecha europeo
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Recorrer el resultado y agregar los tickets a la lista
            while (rs.next()) {
                int id = rs.getInt("idTickets");
                Date fecha = rs.getDate("fechaTicket"); // Obtenemos la fecha
                double precioTotal = rs.getDouble("precioTotalTicket");
                int articulos = rs.getInt("articulosTicket");

                // Formateamos la fecha al formato europeo
                String fechaFormateada = sdf.format(fecha);
                
                String ticket = "ID: " + id + ", Fecha: " + fechaFormateada + ", Precio: " + precioTotal + "€" + ", Nº Artículos: " + articulos;
                tickets.add(ticket);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener los tickets: " + e.getMessage());
        }

        return tickets;
    }


	
    
    
}


