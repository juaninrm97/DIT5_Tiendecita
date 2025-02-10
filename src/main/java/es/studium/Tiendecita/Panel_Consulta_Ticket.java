package es.studium.Tiendecita;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Panel_Consulta_Ticket extends JDialog {

    private static final long serialVersionUID = 1L;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            Panel_Consulta_Ticket dialog = new Panel_Consulta_Ticket();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public Panel_Consulta_Ticket() {
        setTitle("TiendecitaJRM - Consulta Ticket");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());

        // Crear el panel principal y añadirlo a un JScrollPane
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Layout vertical
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Obtener la lista de tickets de la base de datos
        List<String> tickets = Metodos_Ticket.obtenerTickets();

        // Agregar los tickets al panel
        for (String ticket : tickets) {
            mainPanel.add(new JLabel(ticket.toString())); // Mostrar la información del ticket
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre los elementos
        }

        // Crear el botón de menú principal
        JButton btnMenuPrincipal = new JButton("Menú Principal");
        btnMenuPrincipal.addActionListener(e -> {
            dispose(); // Cerrar el diálogo
        });

        // Añadir el botón al panel inferior
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPane.add(btnMenuPrincipal);
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
    }
}
