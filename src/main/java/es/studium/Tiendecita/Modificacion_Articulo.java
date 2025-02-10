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
import java.awt.Choice;

public class Modificacion_Articulo extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private Choice choice; // Hacemos que Choice sea un atributo de clase para usarlo con getters

    /**
     * Create the dialog.
     */
    public Modificacion_Articulo() {
        setForeground(Color.BLACK);
        setTitle("TiendecitaJRM - Artículo - Modificacion");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JButton btnMenuPrincipal = new JButton("Menú principal");
        btnMenuPrincipal.setBounds(0, 0, 121, 23);
        contentPanel.add(btnMenuPrincipal);

        JLabel lblNewLabel = new JLabel("Introduzca ID Artículo a modificar:");
        lblNewLabel.setBounds(23, 50, 214, 14);
        contentPanel.add(lblNewLabel);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(89, 179, 89, 23);
        contentPanel.add(btnCancelar);

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(243, 179, 108, 23);
        contentPanel.add(btnConfirmar);

        choice = new Choice(); // Inicializamos el Choice
        choice.setBounds(243, 50, 165, 20);
        contentPanel.add(choice);
        Metodos_Articulos.cargarIdsArticulos(choice);

        JLabel lblNuevaDescripcin = new JLabel("Nueva descripción:");
        lblNuevaDescripcin.setBounds(23, 81, 189, 14);
        contentPanel.add(lblNuevaDescripcin);

        JLabel lblNuevoPrecio = new JLabel("Nuevo precio:");
        lblNuevoPrecio.setBounds(23, 112, 189, 14);
        contentPanel.add(lblNuevoPrecio);

        JLabel lblNuevoStock = new JLabel("Nuevo stock:");
        lblNuevoStock.setBounds(23, 143, 189, 14);
        contentPanel.add(lblNuevoStock);

        textField = new JTextField();
        textField.setBounds(243, 78, 165, 20);
        contentPanel.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(243, 109, 165, 20);
        contentPanel.add(textField_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(243, 140, 165, 20);
        contentPanel.add(textField_2);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        // Acción para cerrar el diálogo y volver al menú principal (JFrame) al pulsar "Menú principal"
        btnMenuPrincipal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra este diálogo
            }
        });

        // Acción para cerrar el diálogo al pulsar "Cancelar"
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra este diálogo
            }
        });

        // Acción del botón Confirmar
        btnConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra este diálogo

                // Crear e invocar el diálogo de seguridad, pasando la instancia actual
                Seguridad_Modificacion_Articulo seguridadDialog = new Seguridad_Modificacion_Articulo(Modificacion_Articulo.this);
                seguridadDialog.setVisible(true);
            }
        });
    }

    // Métodos getters para acceder a los campos desde otras clases
    public Choice getChoice() {
        return choice;
    }

    public String getDescripcion() {
        return textField.getText();
    }

    public String getPrecio() {
        return textField_1.getText();
    }

    public String getStock() {
        return textField_2.getText();
    }

    /**
     * Launch the application (for testing purposes).
     */
    public static void main(String[] args) {
        try {
            Modificacion_Articulo dialog = new Modificacion_Articulo();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
