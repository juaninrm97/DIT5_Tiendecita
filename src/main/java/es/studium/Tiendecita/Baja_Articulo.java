package es.studium.Tiendecita;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.sql.*;
import java.awt.Choice;

public class Baja_Articulo extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    
    // Agregar un Choice para la selección de artículo
    private Choice choice;

    /**
     * Create the dialog.
     */
    public Baja_Articulo() {
        setForeground(Color.BLACK);
        setTitle("TiendecitaJRM - Artículo - Baja");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        JButton btnMenuPrincipal = new JButton("Menú principal");
        btnMenuPrincipal.setBounds(0, 0, 121, 23);
        contentPanel.add(btnMenuPrincipal);
        
        JLabel lblNewLabel = new JLabel("Seleccione Artículo para baja:");
        lblNewLabel.setBounds(26, 78, 203, 14);
        contentPanel.add(lblNewLabel);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(110, 123, 89, 23);
        contentPanel.add(btnCancelar);
        
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(234, 123, 108, 23);
        contentPanel.add(btnConfirmar);
        
        // Crear el Choice
        choice = new Choice();
        choice.setBounds(237, 78, 136, 20);
        contentPanel.add(choice);
        
        // Llamar al método para cargar los artículos
        cargarArticulos();

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        // Acción para cerrar el diálogo y volver al menú principal (JFrame) al pulsar "Menú principal"
        btnMenuPrincipal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar este diálogo
                dispose();
            }
        });
        
        // Acción para cerrar el diálogo al pulsar "Cancelar"
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar este diálogo
                dispose();
            }
        });
        
        btnConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener el artículo seleccionado
                String articuloSeleccionado = choice.getSelectedItem();
                
                if (articuloSeleccionado != null && !articuloSeleccionado.isEmpty()) {
                    // Llamar al diálogo de seguridad para confirmar la baja
                    Seguridad_Baja_Articulo seguridadDialog = new Seguridad_Baja_Articulo(Baja_Articulo.this, articuloSeleccionado);
                    seguridadDialog.setVisible(true);
                    dispose(); // Cerrar el diálogo actual
                } else {
                    // Avisar si no se ha seleccionado ningún artículo
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un artículo.");
                }
            }
        });
    }

    /**
     * Método para cargar los artículos en el Choice
     */
    private void cargarArticulos() {
        String query = "SELECT descripcionArticulo FROM Articulos";
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tiendecitaJMRM", "root", "studium2024;");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            // Limpiar el Choice antes de agregar nuevos artículos
            choice.removeAll();
            
            // Añadir los artículos al Choice
            while (rs.next()) {
                choice.add(rs.getString("descripcionArticulo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launch the application (for testing purposes).
     */
    public static void main(String[] args) {
        try {
            Baja_Articulo dialog = new Baja_Articulo();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
