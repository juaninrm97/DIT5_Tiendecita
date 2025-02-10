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

public class Seguridad_Modificacion_Articulo extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private Modificacion_Articulo modificacionArticuloDialog;

    /**
     * Create the dialog.
     */
    public Seguridad_Modificacion_Articulo(Modificacion_Articulo modificacionArticulo) {
        modificacionArticuloDialog = modificacionArticulo; // Guardamos la instancia de Modificacion_Articulo

        setTitle("Confirmación - Modificación Artículo");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblMensaje = new JLabel("Artículo modificado correctamente.");
        lblMensaje.setBounds(115, 60, 219, 14);
        contentPanel.add(lblMensaje);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Recoger los datos del diálogo Modificacion_Articulo
                int idArticulo = Integer.parseInt(modificacionArticuloDialog.getChoice().getSelectedItem());
                String nuevaDescripcion = modificacionArticuloDialog.getDescripcion();
                double nuevoPrecio = Double.parseDouble(modificacionArticuloDialog.getPrecio());
                int nuevoStock = Integer.parseInt(modificacionArticuloDialog.getStock());

                // Llamar al método de actualización
                Metodos_Articulos.modificarArticulo(idArticulo, nuevaDescripcion, nuevoPrecio, nuevoStock);

                // Cerrar el diálogo actual
                dispose();
            }
        });

        btnAceptar.setBounds(160, 120, 89, 23);
        contentPanel.add(btnAceptar);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
    }

    /**
     * Launch the application (for testing purposes).
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
}
