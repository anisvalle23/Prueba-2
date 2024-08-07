package prueba2;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private PalindromoAir palindromoAir;
    private JFrame marco;
    private JTextArea areaDeTexto;
    private JTextField campoNombre;
    private JLabel etiquetaIngreso;

    public Main() {
        palindromoAir = new PalindromoAir();
        inicializarGUI();
    }

    private void inicializarGUI() {
        marco = new JFrame("Sistema de Tickets PalindromoAir");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setSize(800, 600);
        marco.setLayout(new BorderLayout());
        marco.setLocationRelativeTo(null);

        JPanel panelSuperior = new JPanel(new GridBagLayout());
        panelSuperior.setBackground(new Color(255, 228, 225));
        panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel etiquetaNombre = new JLabel("Nombre del Pasajero:");
        etiquetaNombre.setForeground(Color.DARK_GRAY);
        etiquetaNombre.setFont(new Font("Arial", Font.BOLD, 16));

        campoNombre = new JTextField(20);
        campoNombre.setFont(new Font("Arial", Font.PLAIN, 14));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelSuperior.add(etiquetaNombre, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panelSuperior.add(campoNombre, gbc);

        JPanel panelBotones = new JPanel(new GridLayout(4, 1, 10, 10));
        panelBotones.setBackground(new Color(255, 228, 225));
        panelBotones.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton botonVender = crearBoton("Vender Ticket");
        JButton botonCancelar = crearBoton("Cancelar Ticket");
        JButton botonBuscar = crearBoton("Buscar Pasajero");
        JButton botonDespachar = crearBoton("Despachar");

        panelBotones.add(botonVender);
        panelBotones.add(botonCancelar);
        panelBotones.add(botonBuscar);
        panelBotones.add(botonDespachar);

        areaDeTexto = new JTextArea();
        areaDeTexto.setEditable(false);
        areaDeTexto.setFont(new Font("Arial", Font.PLAIN, 14));
        areaDeTexto.setBorder(BorderFactory.createLineBorder(new Color(255, 182, 193), 2));
        JScrollPane scrollPane = new JScrollPane(areaDeTexto);

        etiquetaIngreso = new JLabel("Ingreso Total: $0.0");
        etiquetaIngreso.setFont(new Font("Arial", Font.BOLD, 18));
        etiquetaIngreso.setForeground(new Color(255, 105, 180));
        etiquetaIngreso.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelCentral.add(scrollPane, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelInferior.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelInferior.add(etiquetaIngreso);

        marco.add(panelSuperior, BorderLayout.NORTH);
        marco.add(panelBotones, BorderLayout.WEST);
        marco.add(panelCentral, BorderLayout.CENTER);
        marco.add(panelInferior, BorderLayout.SOUTH);

        botonVender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                venderTicket();
            }
        });

        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarTicket();
            }
        });

        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPasajero();
            }
        });

        botonDespachar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                despachar();
            }
        });

        marco.setVisible(true);
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBackground(new Color(255, 105, 180));
        boton.setForeground(Color.BLACK); 
        boton.setBorder(BorderFactory.createLineBorder(new Color(255, 20, 147), 2));
        boton.setPreferredSize(new Dimension(180, 40));
        return boton;
    }

    private void venderTicket() {
        String nombre = campoNombre.getText().trim();
        if (!nombre.isEmpty()) {
            palindromoAir.sellTicket(nombre);
            mostrarMensaje("Ticket vendido", "Ticket vendido, nombre pasajero: " + nombre + ", precio $" + obtenerPrecio(nombre), JOptionPane.INFORMATION_MESSAGE);
            actualizarPantalla();
        } else {
            mostrarMensaje("Error", "El nombre del pasajero no puede estar vacío.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancelarTicket() {
        String nombre = campoNombre.getText().trim();
        if (!nombre.isEmpty()) {
            boolean resultado = palindromoAir.cancelTicket(nombre);
            if (resultado) {
                mostrarMensaje("Ticket cancelado", "Ticket cancelado para: " + nombre, JOptionPane.INFORMATION_MESSAGE);
            } else {
                mostrarMensaje("Error", "No se encontró ticket para: " + nombre, JOptionPane.ERROR_MESSAGE);
            }
            actualizarPantalla();
        } else {
            mostrarMensaje("Error", "El nombre del pasajero no puede estar vacío.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarPasajero() {
        String nombre = campoNombre.getText().trim();
        if (!nombre.isEmpty()) {
            int posicion = palindromoAir.searchPassenger(nombre);
            if (posicion != -1) {
                mostrarMensaje("Pasajero encontrado", "Pasajero " + nombre + " encontrado en el asiento: " + posicion, JOptionPane.INFORMATION_MESSAGE);
            } else {
                mostrarMensaje("Error", "Pasajero " + nombre + " no encontrado.", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            mostrarMensaje("Error", "El nombre del pasajero no puede estar vacío.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void despachar() {
        double ingresoTotal = palindromoAir.income();
        mostrarMensaje("Ingreso total", "Ingreso total generado: $" + ingresoTotal, JOptionPane.INFORMATION_MESSAGE);
        palindromoAir.dispatch();
        mostrarMensaje("Asientos reseteados", "Los asientos del avión han sido reseteados.", JOptionPane.INFORMATION_MESSAGE);
        actualizarPantalla();
    }

    private void actualizarPantalla() {
        areaDeTexto.setText("");
        for (int index = 0; index < 30; index++) {
            if (palindromoAir.asiento[index] != null) {
                areaDeTexto.append(palindromoAir.asiento[index].getNombrePasajero() + " - $" + palindromoAir.asiento[index].getTotalPagado() + "\n");
            }
        }
        etiquetaIngreso.setText("Ingreso Total: $" + palindromoAir.income());
    }

    private void mostrarMensaje(String titulo, String mensaje, int tipo) {
        UIManager.put("OptionPane.background", new Color(255, 228, 225));
        UIManager.put("Panel.background", new Color(255, 228, 225));
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
        JOptionPane.showMessageDialog(marco, mensaje, titulo, tipo);
    }

    private double obtenerPrecio(String nombre) {
        double precio = 800;
        if (palindromoAir.isPalindromo(nombre)) {
            precio *= 0.8;
        }
        return precio;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
