package es.studium.Tiendecita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class Panel_Consulta_Articulo extends JDialog {

    private static final long serialVersionUID = 1L;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            Panel_Consulta_Articulo dialog = new Panel_Consulta_Articulo();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public Panel_Consulta_Articulo() {
        setTitle("TiendecitaJRM_Consulta Artículo");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());

        // Crear el panel principal y añadirlo a un JScrollPane
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Layout vertical
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Recuperar los artículos desde la base de datos
        List<String> articulos = Metodos_Articulos.consultarArticulos();

        // Agregar los datos al panel principal
        if (articulos.isEmpty()) {
            mainPanel.add(new JLabel("No hay artículos disponibles."));
        } else {
            for (String articulo : articulos) {
                mainPanel.add(new JLabel(articulo));
                mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre los elementos
            }
        }

        // Crear el botón de menú principal
        JButton btnMenuPrincipal = new JButton("Menú Principal");
        btnMenuPrincipal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acción para volver al menú principal
                dispose(); // Cerrar este diálogo
            }
        });

        // Añadir el botón al panel inferior
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPane.add(btnMenuPrincipal);
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
    }

}
