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

public class Confirmacion_Modificacion_Articulo extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private Modificacion_Articulo modificacionArticuloDialog; // Variable para la instancia de Modificacion_Articulo

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            Confirmacion_Modificacion_Articulo dialog = new Confirmacion_Modificacion_Articulo(new Modificacion_Articulo());
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public Confirmacion_Modificacion_Articulo(Modificacion_Articulo modificacionArticulo) {
        modificacionArticuloDialog = modificacionArticulo; // Guardamos la instancia de Modificacion_Articulo
        setTitle("TiendecitaJRM - Artículo - Modificación");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Artículo modificado correctamente.");
        lblNewLabel.setBounds(115, 91, 217, 14);
        contentPanel.add(lblNewLabel);
        
        JButton btnNewButton = new JButton("Volver");
        btnNewButton.setBounds(157, 147, 89, 23);
        contentPanel.add(btnNewButton);
        
        // Acción para el botón "Volver"
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerramos el diálogo de confirmación
                dispose();
                
                // Volvemos al diálogo de Modificacion_Articulo
                modificacionArticuloDialog.setVisible(true);
            }
        });

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
    }
}
