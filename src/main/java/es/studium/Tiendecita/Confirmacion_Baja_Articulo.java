package es.studium.Tiendecita;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Confirmacion_Baja_Articulo extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private Baja_Articulo bajaArticuloDialog; // Variable para la instancia de Baja_Articulo

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            Confirmacion_Baja_Articulo dialog = new Confirmacion_Baja_Articulo(new Baja_Articulo());
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public Confirmacion_Baja_Articulo(Baja_Articulo bajaArticulo) {
        bajaArticuloDialog = bajaArticulo; // Guardamos la instancia de Baja_Articulo

        setTitle("TiendecitaJRM - Artículo - Baja");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Baja realizada correctamente.");
        lblNewLabel.setBounds(136, 94, 183, 14);
        contentPanel.add(lblNewLabel);

        // Botón Volver
        JButton btnNewButton = new JButton("Volver");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar este diálogo
                dispose();

                // Abrir nuevamente el diálogo de Baja_Articulo
                bajaArticuloDialog.setVisible(true);
            }
        });
        btnNewButton.setBounds(166, 146, 89, 23);
        contentPanel.add(btnNewButton);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
    }
}
