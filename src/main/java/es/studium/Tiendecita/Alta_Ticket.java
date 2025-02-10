package es.studium.Tiendecita;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Choice;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Alta_Ticket extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    public JTextField textFieldID;
    private JTextField textFieldFecha;
    private JTextField textFieldPrecio;
    private JTextField textFieldArticulos;
    private JTextArea textAreaSeleccionados;
    private Choice choiceArticulos;
    private double precioTotal = 0; // Almacena el precio total calculado
    private int numeroArticulos = 0; // Almacena el número de artículos seleccionados
    public List<Integer> listaArticulosSeleccionados = new ArrayList<>(); // Lista para almacenar IDs de artículos

    public Alta_Ticket() {
        setForeground(Color.BLACK);
        setTitle("TiendecitaJRM - Ticket - Alta");
        setBounds(100, 100, 500, 400);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        // Botón para volver al menú principal
        JButton btnMenuPrincipal = new JButton("Menú principal");
        btnMenuPrincipal.setBounds(0, 0, 121, 23);
        contentPanel.add(btnMenuPrincipal);

        // Etiqueta y campo de texto para el ID del ticket
        JLabel lblID = new JLabel("ID Ticket:");
        lblID.setBounds(33, 20, 177, 14);
        contentPanel.add(lblID);

        textFieldID = new JTextField();
        textFieldID.setBounds(217, 17, 168, 20);
        contentPanel.add(textFieldID);
        textFieldID.setColumns(10);

        // Etiqueta y campo de texto para la fecha
        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(33, 50, 177, 14);
        contentPanel.add(lblFecha);

        textFieldFecha = new JTextField();
        textFieldFecha.setBounds(217, 47, 168, 20);
        contentPanel.add(textFieldFecha);
        textFieldFecha.setColumns(10);

        // Etiqueta y campo de texto para el precio total
        JLabel lblPrecioTotal = new JLabel("Precio total:");
        lblPrecioTotal.setBounds(33, 81, 177, 14);
        contentPanel.add(lblPrecioTotal);

        textFieldPrecio = new JTextField();
        textFieldPrecio.setBounds(217, 78, 168, 20);
        textFieldPrecio.setEditable(false); // Solo lectura
        contentPanel.add(textFieldPrecio);
        textFieldPrecio.setColumns(10);

        // Etiqueta y campo de texto para el número de artículos
        JLabel lblArticulos = new JLabel("Nº Artículos:");
        lblArticulos.setBounds(33, 112, 177, 14);
        contentPanel.add(lblArticulos);

        textFieldArticulos = new JTextField();
        textFieldArticulos.setBounds(217, 109, 168, 20);
        textFieldArticulos.setEditable(false); // Solo lectura
        contentPanel.add(textFieldArticulos);
        textFieldArticulos.setColumns(10);

        // Etiqueta y Choice para seleccionar artículos
        JLabel lblSeleccionarArticulo = new JLabel("Seleccionar artículo:");
        lblSeleccionarArticulo.setBounds(33, 143, 177, 14);
        contentPanel.add(lblSeleccionarArticulo);

        choiceArticulos = new Choice();
        choiceArticulos.setBounds(217, 140, 168, 20);
        contentPanel.add(choiceArticulos);

        // Cargar los artículos en el Choice
        cargarArticulosEnChoice();

        // Área de texto para mostrar los artículos seleccionados
        JLabel lblSeleccionados = new JLabel("Artículos añadidos:");
        lblSeleccionados.setBounds(33, 173, 177, 14);
        contentPanel.add(lblSeleccionados);

        textAreaSeleccionados = new JTextArea();
        textAreaSeleccionados.setBounds(33, 200, 400, 80);
        textAreaSeleccionados.setEditable(false); // Solo lectura
        contentPanel.add(textAreaSeleccionados);

        // Botón para confirmar la adición de un artículo
        JButton btnAgregarArticulo = new JButton("Agregar artículo");
        btnAgregarArticulo.setBounds(33, 300, 150, 23);
        contentPanel.add(btnAgregarArticulo);

        // Botón para confirmar el ticket
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(300, 300, 108, 23);
        contentPanel.add(btnConfirmar);

        // Acción para volver al menú principal
        btnMenuPrincipal.addActionListener(e -> dispose());

        // Acción para agregar un artículo al ticket
        btnAgregarArticulo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String seleccionado = choiceArticulos.getSelectedItem();
                try {
                    // Procesar el formato "Descripción: X, Precio: Y, ID: Z"
                    String[] partes = seleccionado.split(", "); // Dividir por las comas
                    String descripcion = partes[0].split(": ")[1].trim(); // Extraer descripción
                    double precio = Double.parseDouble(partes[1].split(": ")[1].trim()); // Extraer precio
                    int idArticulo = Integer.parseInt(partes[2].split(": ")[1].trim()); // Extraer ID
                    
                    // Actualizar lista de artículos seleccionados
                    listaArticulosSeleccionados.add(idArticulo);
                    
                    // Actualizar precios y número de artículos
                    precioTotal += precio;
                    numeroArticulos++;
                    textFieldPrecio.setText(String.valueOf(precioTotal));
                    textFieldArticulos.setText(String.valueOf(numeroArticulos));
                    
                    // Agregar el artículo al área de texto
                    textAreaSeleccionados.append(descripcion + " - " + precio + "€\n");
                } catch (Exception ex) {
                    System.err.println("Error al procesar el artículo seleccionado: " + ex.getMessage());
                }
            }
        });

        // Acción para confirmar e insertar el ticket
        btnConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idTicket = textFieldID.getText();
                String fechaTicket = textFieldFecha.getText(); // Obtener la fecha introducida

                // Validar si la fecha está vacía
                if (fechaTicket.isEmpty()) {
                    System.err.println("Debe ingresar una fecha.");
                    return;
                }

                // Formato europeo de fecha (DD-MM-YYYY)
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

                try {
                    // Convertir la fecha introducida (si es válida) al formato europeo
                    Date fecha = sdf.parse(fechaTicket);

                    // Si la fecha es válida, convertirla a cadena en formato europeo
                    String fechaFormateada = sdf.format(fecha);
                    
                    // Utilizar la fecha formateada
                    double precio = precioTotal;  // Precio total calculado
                    int articulos = numeroArticulos;  // Número de artículos seleccionados

                    // Validar campos obligatorios
                    if (idTicket.isEmpty() || fechaFormateada.isEmpty()) {
                        System.err.println("Debe completar todos los campos obligatorios.");
                        return;
                    }

                    // Pasar los datos al diálogo de seguridad
                    Seguridad_Alta_Ticket seguridadDialog = new Seguridad_Alta_Ticket(Alta_Ticket.this, fechaFormateada, precio, articulos);
                    seguridadDialog.setVisible(true);
                } catch (Exception ex) {
                    System.err.println("Error al procesar la fecha: " + ex.getMessage());
                }
            }
        });
    }

    private void cargarArticulosEnChoice() {
        // Obtener los artículos desde la base de datos
        List<String> articulos = Metodos_Articulos.consultarArticulos();
        for (String articulo : articulos) {
            choiceArticulos.add(articulo); // Asegúrate de que el formato incluya el ID del artículo
        }
    }

    public static void main(String[] args) {
        try {
            Alta_Ticket dialog = new Alta_Ticket();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
