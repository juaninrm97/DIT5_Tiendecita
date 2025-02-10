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

public class Confirmacion_Alta_Ticket extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private Alta_Ticket altaTicketDialog; // Variable para guardar la instancia del diálogo de alta

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            Confirmacion_Alta_Ticket dialog = new Confirmacion_Alta_Ticket(new Alta_Ticket());
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public Confirmacion_Alta_Ticket(Alta_Ticket altaTicket) {
        altaTicketDialog = altaTicket; // Guardamos la instancia del diálogo de alta
        setTitle("TiendecitaJRM - Ticket - Alta");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Ticket introducido correctamente.");
        lblNewLabel.setBounds(113, 86, 215, 14);
        contentPanel.add(lblNewLabel);
        
        JButton btnNewButton = new JButton("Volver");
        btnNewButton.setBounds(157, 142, 89, 23);
        // Acción del botón Volver
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar este diálogo
                dispose();
                
                // Volver al diálogo de alta de ticket
                altaTicketDialog.setVisible(true);
            }
        });
        contentPanel.add(btnNewButton);
        
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
    }
}
