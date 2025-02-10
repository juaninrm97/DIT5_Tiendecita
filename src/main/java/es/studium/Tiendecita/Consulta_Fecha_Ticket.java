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
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class Consulta_Fecha_Ticket extends JDialog {
	private JTextField fechaDesdeField;
	private JTextField fechaHastaField;
	private JButton generarReporteButton;

	public Consulta_Fecha_Ticket() {
		setTitle("Consulta Tickets por Fecha");
		setSize(400, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2));

		JLabel labelDesde = new JLabel("Fecha Desde (YYYY-MM-DD):");
		JLabel labelHasta = new JLabel("Fecha Hasta (YYYY-MM-DD):");

		fechaDesdeField = new JTextField();
		fechaHastaField = new JTextField();

		generarReporteButton = new JButton("Generar Reporte");

		panel.add(labelDesde);
		panel.add(fechaDesdeField);
		panel.add(labelHasta);
		panel.add(fechaHastaField);
		panel.add(new JLabel()); // Espacio vacío
		panel.add(generarReporteButton);

		getContentPane().add(panel, BorderLayout.CENTER);

		generarReporteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				generarReporte();
			}
		});
	}

	private void generarReporte() {



		String fechaDesdeStr = fechaDesdeField.getText();
		String fechaHastaStr = fechaHastaField.getText();

		if (fechaDesdeStr.isEmpty() || fechaHastaStr.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor ingrese ambas fechas.");
			return;
		}

		Date fechaDesde = Date.valueOf(fechaDesdeStr);
		Date fechaHasta = Date.valueOf(fechaHastaStr);

		try {
			Connection conexion = tiendecita_BD.conectar();
			if (conexion == null) {
				JOptionPane.showMessageDialog(this, "Error: No se pudo conectar a la base de datos.");
				return;
			}

			// Cargar reporte desde recursos
			InputStream jasperStream = getClass().getResourceAsStream("/tickets1.jasper");
			if (jasperStream == null) {
				JOptionPane.showMessageDialog(this, "Error: No se encontró el archivo tickets1.jasper.");
				return;
			}

			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			String fechaDesdeFormatted = sdf.format(fechaDesde);
			String fechaHastaFormatted = sdf.format(fechaHasta);

			// Pasar las fechas formateadas a los parámetros del reporte
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("FechaDesde", fechaDesdeFormatted);
			parametros.put("FechaHasta", fechaHastaFormatted);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conexion);

			// Exportar el reporte a PDF
			String pdfPath = "Reporte_Tickets_" + System.currentTimeMillis() + ".pdf";
			JasperExportManager.exportReportToPdfFile(jasperPrint, pdfPath);

			JasperViewer.viewReport(jasperPrint, false);

			// Abrir el archivo PDF
			File path = new File(pdfPath);
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(path);
			} else {
				JOptionPane.showMessageDialog(this, "El sistema no soporta apertura de archivos.");
			}

			JOptionPane.showMessageDialog(this, "Reporte generado y guardado como PDF: " + pdfPath);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Error al generar el reporte: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Consulta_Fecha_Ticket dialog = new Consulta_Fecha_Ticket();
		dialog.setVisible(true);
	}
}
