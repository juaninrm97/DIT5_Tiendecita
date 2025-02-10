package es.studium.Tiendecita;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Alta_Articulo extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    protected JTextField textField; // Descripción
    protected JTextField textField_1; // Precio
    protected JTextField textField_2; // Stock
    protected JTextField textField_3; // ID Artículo

    /**
     * Create the dialog.
     */
    public Alta_Articulo() {
        setForeground(Color.BLACK);
        setTitle("TiendecitaJRM - Artículo - Alta");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        JButton btnMenuPrincipal = new JButton("Menú principal");
        btnMenuPrincipal.setBounds(0, 0, 121, 23);
        contentPanel.add(btnMenuPrincipal);
        
        JLabel lblNewLabel = new JLabel("Descripción Artículo");
        lblNewLabel.setBounds(33, 90, 177, 14);
        contentPanel.add(lblNewLabel);
        
        textField = new JTextField();
        textField.setBounds(204, 87, 168, 20);
        contentPanel.add(textField);
        textField.setColumns(10);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(79, 201, 89, 23);
        contentPanel.add(btnCancelar);
        
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(233, 201, 108, 23);
        contentPanel.add(btnConfirmar);
        
        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(33, 127, 177, 14);
        contentPanel.add(lblPrecio);
        
        JLabel lblStock = new JLabel("Stock:");
        lblStock.setBounds(33, 163, 177, 14);
        contentPanel.add(lblStock);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(204, 121, 168, 20);
        contentPanel.add(textField_1);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(204, 160, 168, 20);
        contentPanel.add(textField_2);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(204, 50, 168, 20);
        contentPanel.add(textField_3);
        
        JLabel lblIdArtculo = new JLabel("ID Artículo:");
        lblIdArtculo.setBounds(33, 53, 177, 14);
        contentPanel.add(lblIdArtculo);
        
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
        
        // Acción para manejar el botón "Confirmar"
        btnConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar este diálogo
                dispose();
                
                // Crear e invocar el diálogo de seguridad, pasando la instancia actual de Alta_Articulo
                Seguridad_Alta_Articulo seguridadDialog = new Seguridad_Alta_Articulo(Alta_Articulo.this);
                seguridadDialog.setVisible(true);
            }
        });
    }

    /**
     * Métodos getter para obtener los valores de los campos de texto.
     */
    public String getDescripcion() {
        return textField.getText();
    }

    public double getPrecio() {
        return Double.parseDouble(textField_1.getText());
    }

    public int getStock() {
        return Integer.parseInt(textField_2.getText());
    }

    public String getIdArticulo() {
        return textField_3.getText();
    }

    /**
     * Launch the application (for testing purposes).
     */
    public static void main(String[] args) {
        try {
            Alta_Articulo dialog = new Alta_Articulo();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
