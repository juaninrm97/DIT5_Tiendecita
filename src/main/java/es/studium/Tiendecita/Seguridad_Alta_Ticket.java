package es.studium.Tiendecita;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class Seguridad_Alta_Ticket extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private Alta_Ticket altaTicketDialog;  // Instancia del diálogo de alta
    private static String fechaTicket;
    private static double precioTotal;
    private static int articulosTicket;

    // Constructor de la clase
    public Seguridad_Alta_Ticket(Alta_Ticket altaTicket, String fechaTicket, double precioTotal, int articulosTicket) {
        this.altaTicketDialog = altaTicket;  // Guardamos la instancia del diálogo de alta
        this.fechaTicket = fechaTicket;
        this.precioTotal = precioTotal;
        this.articulosTicket = articulosTicket;

        setTitle("TiendecitaJRM - Confirmación de Ticket");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblConfirmacion = new JLabel("¿Quiere confirmar esta alta?");
        lblConfirmacion.setBounds(130, 61, 219, 14);
        contentPanel.add(lblConfirmacion);

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(244, 124, 105, 23);
        contentPanel.add(btnConfirmar);

        // Acción del botón Confirmar
        btnConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ahora insertamos los datos en la base de datos
                String idTicket = altaTicketDialog.textFieldID.getText(); // Obtenemos el ID del ticket
                List<Integer> articulosSeleccionados = altaTicketDialog.listaArticulosSeleccionados; // Obtenemos la lista de artículos seleccionados

                // Insertar el ticket en la base de datos con todos los parámetros
                boolean exito = Metodos_Ticket.insertarTicket(idTicket, fechaTicket, precioTotal, articulosSeleccionados);

                if (exito) {
                    System.out.println("Ticket insertado correctamente.");
                } else {
                    System.err.println("Error al insertar el ticket.");
                }

                // Cerrar este diálogo
                dispose();

                // Abrir el nuevo diálogo de confirmación pasando la instancia de Alta_Ticket
                Confirmacion_Alta_Ticket confirmacionDialog = new Confirmacion_Alta_Ticket(altaTicketDialog);
                confirmacionDialog.setVisible(true);
            }
        });

        // Acción del botón Cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar el diálogo de seguridad y volver al diálogo de alta
                dispose();
                altaTicketDialog.setVisible(true);
            }
        });
        btnCancelar.setBounds(78, 124, 89, 23);
        contentPanel.add(btnCancelar);
    }

    public static void main(String[] args) {
        try {
            Seguridad_Alta_Ticket dialog = new Seguridad_Alta_Ticket(new Alta_Ticket(), fechaTicket, precioTotal, articulosTicket);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
