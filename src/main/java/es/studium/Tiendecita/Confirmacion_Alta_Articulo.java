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

public class Confirmacion_Alta_Articulo extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private Alta_Articulo altaArticuloDialog; // Guardamos la instancia de Alta_Articulo

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            Confirmacion_Alta_Articulo dialog = new Confirmacion_Alta_Articulo(new Alta_Articulo());
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public Confirmacion_Alta_Articulo(Alta_Articulo altaArticulo) {
        altaArticuloDialog = altaArticulo; // Guardamos la instancia de Alta_Articulo

        setTitle("TiendecitaJRM - Artículo - Alta");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Alta realizada correctamente.");
        lblNewLabel.setBounds(134, 83, 183, 14);
        contentPanel.add(lblNewLabel);

        // Botón Volver
        JButton btnNewButton = new JButton("Volver");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar este diálogo
                dispose();

                // Abrir el diálogo de Alta_Articulo
                altaArticuloDialog.setVisible(true);
            }
        });
        btnNewButton.setBounds(162, 146, 89, 23);
        contentPanel.add(btnNewButton);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
    }
}
