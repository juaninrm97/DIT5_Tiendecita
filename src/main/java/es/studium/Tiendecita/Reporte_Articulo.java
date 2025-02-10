package es.studium.Tiendecita;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class Reporte_Articulo extends JDialog {
	
	private JButton generarReporteButton;

	public Reporte_Articulo() {
		setTitle("Consulta Reporte Articulos");
		setSize(400, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2));



		generarReporteButton = new JButton("Generar Reporte");

		
		panel.add(generarReporteButton);

		getContentPane().add(panel, BorderLayout.CENTER);

		generarReporteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				generarReporte();
			}
		});
	}


		public void generarReporte() {
	        try {
	            // Compilar el informe generando fichero .jasper

	            // Objeto para guardar parámetros necesarios para el informe
	            HashMap<String, Object> parametros = new HashMap<>();

	            // Cargar el informe compilado
	            JasperReport report = (JasperReport) JRLoader.loadObjectFromFile("./src/main/resources/articulos1.jasper");

	            // Conectar a la base de datos para extraer la información
	            Class.forName("com.mysql.cj.jdbc.Driver"); // Se actualizó el driver
	            String servidor = "jdbc:mysql://localhost:3306/tiendecitaJMRM";
	            String usuarioDB = "root";
	            String passwordDB = "studium2024;";

	            try (Connection conexion = DriverManager.getConnection(servidor, usuarioDB, passwordDB)) {
	                // Completar el informe con los datos de la base de datos
	                JasperPrint print = JasperFillManager.fillReport(report, parametros, conexion);

	                // Mostrar el informe en JasperViewer
	                JasperViewer.viewReport(print, false);

	                // Exportar a PDF
	                String pdfPath = "./src/main/resources/Articulos.pdf";
	                JasperExportManager.exportReportToPdfFile(print, pdfPath);

	                // Abrir el fichero PDF generado
	                File path = new File(pdfPath);
	                Desktop.getDesktop().open(path);
	            }
	        } catch (Exception e) {
	            System.out.println("Error: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
		
		public static void main(String[] args) {
			Reporte_Articulo dialog = new Reporte_Articulo();
			dialog.setVisible(true);
		}
}

