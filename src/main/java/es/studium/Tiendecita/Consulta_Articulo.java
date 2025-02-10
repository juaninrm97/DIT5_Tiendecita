package es.studium.Tiendecita;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Consulta_Articulo extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            Consulta_Articulo dialog = new Consulta_Articulo();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public Consulta_Articulo() {
        setTitle("TiendecitaJRM - Artículos - Consulta");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        // Botón para volver al menú principal
        JButton btnMenuPrincipal = new JButton("Menú principal");
        btnMenuPrincipal.setBounds(0, 0, 121, 23);
        contentPanel.add(btnMenuPrincipal);
        
        btnMenuPrincipal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar este diálogo
                dispose();
            }
        });

        // Botón para realizar la consulta
        JButton btnConsulta = new JButton("Consulta");
        btnConsulta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar este diálogo
                dispose(); // Cerrar el diálogo actual

                // Abrir el diálogo de consulta de artículo
                Panel_Consulta_Articulo panelConsultaArticulo = new Panel_Consulta_Articulo();
                panelConsultaArticulo.setVisible(true); // Mostrar el nuevo diálogo
            }
        });
        btnConsulta.setBounds(159, 101, 89, 23);
        contentPanel.add(btnConsulta);
        
        // Panel de botones en la parte inferior (opcional)
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
    }
}
