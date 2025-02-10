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

public class Seguridad_Alta_Articulo extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private Alta_Articulo altaArticuloDialog; // Variable para guardar la instancia del diálogo de alta

    /**
     * Create the dialog.
     */
    public Seguridad_Alta_Articulo(Alta_Articulo altaArticulo) {
        altaArticuloDialog = altaArticulo; // Guardamos la instancia del diálogo de alta
        setTitle("TiendecitaJRM - Artículos - Alta");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblNewLabel = new JLabel("¿Quiere confirmar esta alta?");
        lblNewLabel.setBounds(130, 61, 219, 14);
        contentPanel.add(lblNewLabel);

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(244, 124, 105, 23);
        contentPanel.add(btnConfirmar);

        // Acción del botón Confirmar
        btnConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener los datos del formulario desde la ventana Alta_Articulo
                String descripcion = altaArticuloDialog.textField.getText();
                double precio = Double.parseDouble(altaArticuloDialog.textField_1.getText());
                int stock = Integer.parseInt(altaArticuloDialog.textField_2.getText());

                // Llamar al método insertarArticulo para agregar el artículo a la base de datos
                boolean resultado = Metodos_Articulos.insertarArticulo(descripcion, precio, stock);

                if (resultado) {
                    // Si la inserción fue exitosa, cerrar el diálogo de seguridad
                    dispose();
                    
                    // Mostrar la ventana de confirmación
                    Confirmacion_Alta_Articulo confirmacionDialog = new Confirmacion_Alta_Articulo(altaArticuloDialog);
                    confirmacionDialog.setVisible(true);
                } else {
                    // Si hubo un error, mostrar un mensaje de error
                    System.out.println("Error al insertar el artículo.");
                }
            }
        });
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar este diálogo
                dispose();

                // Abrir nuevamente el diálogo de alta de artículo
                altaArticuloDialog.setVisible(true);
            }
        });
        btnCancelar.setBounds(78, 124, 89, 23);
        contentPanel.add(btnCancelar);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
    }

    /**
     * Launch the application (for testing purposes).
     */
    public static void main(String[] args) {
        try {
            Seguridad_Alta_Articulo dialog = new Seguridad_Alta_Articulo(new Alta_Articulo());
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
