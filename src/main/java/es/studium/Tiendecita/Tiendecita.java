package es.studium.Tiendecita;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tiendecita extends JFrame {
    private JPanel panelArticulos;
    private JPanel panelTickets;

    public Tiendecita() {
        setTitle("TiendecitaJRM");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
        // Botón Artículos
        JButton btnArticulos = new JButton("Artículos");
        btnArticulos.setBounds(0, 0, 100, 30);
        getContentPane().add(btnArticulos);
        
        // Panel que contiene las opciones para Artículos
        panelArticulos = new JPanel();
        panelArticulos.setBounds(42, 32, 129, 140);
        panelArticulos.setLayout(null);

        // Botones dentro del panel de Artículos
        JButton btnAlta = new JButton("Alta");
        btnAlta.setHorizontalAlignment(SwingConstants.LEFT);
        btnAlta.setBounds(0, 0, 107, 23);
        
        JButton btnBaja = new JButton("Baja");
        btnBaja.setHorizontalAlignment(SwingConstants.LEFT);
        btnBaja.setBounds(0, 23, 107, 23);
        
        JButton btnModificacion = new JButton("Modificación");
        btnModificacion.setHorizontalAlignment(SwingConstants.LEFT);
        btnModificacion.setBounds(0, 46, 107, 23);
        
        JButton btnConsulta = new JButton("Consulta");
        btnConsulta.setHorizontalAlignment(SwingConstants.LEFT);
        btnConsulta.setBounds(0, 69, 107, 23);
        
        JButton btnReporte = new JButton("Reporte");
        btnReporte.setHorizontalAlignment(SwingConstants.LEFT);
        btnReporte.setBounds(0, 92, 107, 23);

        // Añadir botones al panel de Artículos
        panelArticulos.add(btnAlta);
        panelArticulos.add(btnBaja);
        panelArticulos.add(btnModificacion);
        panelArticulos.add(btnConsulta);
        panelArticulos.add(btnReporte);

        // Panel de Artículos inicialmente no visible
        panelArticulos.setVisible(false);
        getContentPane().add(panelArticulos);
        
        // Botón Tickets
        JButton btnTickets = new JButton("Tickets");
        btnTickets.setBounds(99, 0, 100, 30);
        getContentPane().add(btnTickets);
        
        // Panel que contiene las opciones para Tickets
        panelTickets = new JPanel();
        panelTickets.setBounds(134, 32, 129, 100);
        panelTickets.setLayout(null);

        // Botones dentro del panel de Tickets
        JButton btnAltaTicket = new JButton("Alta");
        btnAltaTicket.setHorizontalAlignment(SwingConstants.LEFT);
        btnAltaTicket.setBounds(0, 0, 107, 23);
        
        JButton btnConsultaTicket = new JButton("Consulta");
        btnConsultaTicket.setHorizontalAlignment(SwingConstants.LEFT);
        btnConsultaTicket.setBounds(0, 23, 107, 23);
        
        JButton btnReporteTicket = new JButton("Reporte"); // Nuevo botón
        btnReporteTicket.setHorizontalAlignment(SwingConstants.LEFT);
        btnReporteTicket.setBounds(0, 46, 107, 23);

        // Añadir botones al panel de Tickets
        panelTickets.add(btnAltaTicket);
        panelTickets.add(btnConsultaTicket);
        panelTickets.add(btnReporteTicket); // Agregado el nuevo botón de reporte

        // Panel de Tickets inicialmente no visible
        panelTickets.setVisible(false);
        getContentPane().add(panelTickets);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 484, 30);
        getContentPane().add(menuBar);
        
        // Acción del botón Artículos
        btnArticulos.addActionListener(e -> {
            panelArticulos.setVisible(!panelArticulos.isVisible());
            panelTickets.setVisible(false);
        });

        // Acción del botón Tickets
        btnTickets.addActionListener(e -> {
            panelTickets.setVisible(!panelTickets.isVisible());
            panelArticulos.setVisible(false);
        });

        // Acciones de los botones de Artículos
        btnAlta.addActionListener(e -> mostrarAltaArticuloDialog());
        btnBaja.addActionListener(e -> mostrarBajaArticuloDialog());
        btnModificacion.addActionListener(e -> mostrarModificacionArticuloDialog());
        btnConsulta.addActionListener(e -> mostrarConsultaArticuloDialog());
        btnReporte.addActionListener(e -> mostrarReporteArticuloDialog());

        // Acciones de los botones de Tickets
        btnAltaTicket.addActionListener(e -> mostrarAltaTicketDialog());
        btnConsultaTicket.addActionListener(e -> mostrarConsultaTicketDialog());
        btnReporteTicket.addActionListener(e -> mostrarConsultaFechaTicketDialog()); // Nueva acción para Reporte de Tickets
    }

    // Métodos para mostrar los diálogos correspondientes
    private void mostrarAltaArticuloDialog() {
        Alta_Articulo dialog = new Alta_Articulo();
        dialog.setVisible(true);
    }

    private void mostrarBajaArticuloDialog() {
        Baja_Articulo dialog = new Baja_Articulo();
        dialog.setVisible(true);
    }

    private void mostrarModificacionArticuloDialog() {
        Modificacion_Articulo dialog = new Modificacion_Articulo();
        dialog.setVisible(true);
    }

    private void mostrarConsultaArticuloDialog() {
        Consulta_Articulo dialog = new Consulta_Articulo();
        dialog.setVisible(true);
    }

    private void mostrarReporteArticuloDialog() {
        Reporte_Articulo dialog = new Reporte_Articulo();
        dialog.setVisible(true);
    }

    private void mostrarAltaTicketDialog() {
        Alta_Ticket dialog = new Alta_Ticket();
        dialog.setVisible(true);
    }

    private void mostrarConsultaTicketDialog() {
        Consulta_Ticket dialog = new Consulta_Ticket();
        dialog.setVisible(true);
    }

    private void mostrarConsultaFechaTicketDialog() {
        Consulta_Fecha_Ticket dialog = new Consulta_Fecha_Ticket();
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        Tiendecita frame = new Tiendecita();
        frame.setVisible(true);
    }
}
