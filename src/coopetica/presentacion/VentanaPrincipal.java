package coopetica.presentacion;

import coopetica.logica.AsociadoServicio;
import coopetica.modelo.Asociado;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.*;

public class VentanaPrincipal extends JFrame {

    private AsociadoServicio servicio;

    private JTextField campoCedula = new JTextField(15);
    private JTextField campoNombre = new JTextField(15);
    private JTextField campoSaldo = new JTextField(15);
    private JButton botonRegistrar = new JButton("Registrar");
    private JButton botonListar = new JButton("Listar");

    private JTextArea areaResultados = new JTextArea(12, 40);

    private JTextField campoBuscar = new JTextField(12);
    private JButton botonBuscar = new JButton("Buscar");
    private JButton botonResumen = new JButton("Resumen");

    public VentanaPrincipal() {

        setTitle("CoopeTica · Camila Valeria Cabrera Arce");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formulario = new JPanel();
        formulario.setLayout(new GridLayout(4, 2));
        formulario.add(new JLabel("Cedula:"));
        formulario.add(campoCedula);
        formulario.add(new JLabel("Nombre:"));
        formulario.add(campoNombre);
        formulario.add(new JLabel("Saldo de aportes:"));
        formulario.add(campoSaldo);
        formulario.add(botonRegistrar);
        formulario.add(botonListar);

        areaResultados.setEditable(false);

        JPanel barraInferior = new JPanel();
        barraInferior.add(new JLabel("Cedula a buscar:"));
        barraInferior.add(campoBuscar);
        barraInferior.add(botonBuscar);
        barraInferior.add(botonResumen);

        add(formulario, BorderLayout.NORTH);
        add(new JScrollPane(areaResultados), BorderLayout.CENTER);
        add(barraInferior, BorderLayout.SOUTH);

        botonRegistrar.addActionListener(e -> registrar());
        botonListar.addActionListener(e -> listar());
        botonBuscar.addActionListener(e -> buscar());
        botonResumen.addActionListener(e -> mostrarResumen());

        pack();
        setLocationRelativeTo(null);

        try {
            servicio = new AsociadoServicio();
            listar();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo abrir el archivo de datos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listar() {
        try {
            areaResultados.setText("");
            for (Asociado a : servicio.listar()) {
                areaResultados.append(a + "\n");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo leer el archivo de datos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrar() {
        try {
            String mensaje = servicio.registrarDesdeTexto(campoCedula.getText(),
                    campoNombre.getText(), campoSaldo.getText());

            if (mensaje.equals("OK")) {
                JOptionPane.showMessageDialog(this, "Asociado registrado correctamente.");
                campoCedula.setText("");
                campoNombre.setText("");
                campoSaldo.setText("");
                listar();
            } else {
                JOptionPane.showMessageDialog(this, mensaje,
                        "No se pudo registrar", JOptionPane.WARNING_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo guardar en el archivo de datos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscar() {
        try {
            Asociado encontrado = servicio.buscarPorCedula(campoBuscar.getText());

            if (encontrado == null) {
                JOptionPane.showMessageDialog(this, "No existe un asociado con esa cedula.",
                        "Busqueda", JOptionPane.INFORMATION_MESSAGE);
            } else {
                areaResultados.setText("Asociado encontrado:\n" + encontrado);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo leer el archivo de datos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarResumen() {
        try {
            int cantidad = servicio.contarAsociados();
            double total = servicio.calcularTotalAportes();

            JOptionPane.showMessageDialog(this,
                    "Asociados registrados: " + cantidad + "\n"
                    + "Total de aportes: " + total,
                    "Resumen", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo leer el archivo de datos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
