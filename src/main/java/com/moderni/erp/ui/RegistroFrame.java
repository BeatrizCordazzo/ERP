package com.moderni.erp.ui;

import com.moderni.erp.controller.RegistroController;
import com.moderni.erp.model.Rol;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class RegistroFrame extends JFrame {
    private final ApplicationContext applicationContext;
    private final RegistroController registroController;
    private final JTextField nombreField = new JTextField(20);
    private final JTextField apellidoField = new JTextField(20);
    private final JPasswordField passwordField = new JPasswordField(20);
    private final JPasswordField passwordConfirmacionField = new JPasswordField(20);
    private final JTextField telefonoField = new JTextField(20);
    private final JTextField correoField = new JTextField(20);
    public RegistroFrame(RegistroController registroController, ApplicationContext applicationContext) {
        this.registroController = registroController;
        this.applicationContext = applicationContext;
        inicializarVentana();
    }

    private void inicializarVentana() {
        setTitle("ERP Moderni - Registro de Usuarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(540, 420);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel(new BorderLayout(0, 18));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));
        panelPrincipal.setBackground(new Color(245, 240, 231));

        JLabel titulo = new JLabel("Registro de Usuarios", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(new Color(28, 52, 46));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBackground(new Color(255, 251, 244));
        formulario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(214, 202, 180)),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        agregarCampo(formulario, gbc, 0, "Nombre", nombreField);
        agregarCampo(formulario, gbc, 1, "Apellido", apellidoField);
        agregarCampo(formulario, gbc, 2, "Contraña", passwordField);
        agregarCampo(formulario, gbc, 3, "Confirmar Contraseña", passwordConfirmacionField);
        agregarCampo(formulario, gbc, 4, "Correo", correoField);
        agregarCampo(formulario, gbc, 5, "Telefono", telefonoField);


        JButton registrarButton = new JButton("Registrar usuario");
        registrarButton.setFocusPainted(false);
        registrarButton.setBackground(new Color(31, 92, 74));
        registrarButton.setForeground(Color.WHITE);
        registrarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        registrarButton.addActionListener(event -> registrarUsuario());

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(16, 8, 0, 8);
        formulario.add(registrarButton, gbc);

        panelPrincipal.add(formulario, BorderLayout.CENTER);
        setContentPane(panelPrincipal);
    }

    

    private void agregarCampo(JPanel panel, GridBagConstraints gbc, int fila, String etiqueta, java.awt.Component campo) {
        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        panel.add(new JLabel(etiqueta), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(campo, gbc);
    }

    private void registrarUsuario() {
        String nombre = nombreField.getText().trim();
        String apellido = apellidoField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String passwordConfirmacion = new String(passwordConfirmacionField.getPassword()).trim();
        String telefono = telefonoField.getText().trim();
        String correo = correoField.getText().trim();

        if (nombre.isBlank() || apellido.isBlank() || password.isBlank() || passwordConfirmacion.isBlank() || telefono.isBlank() || correo.isBlank()  ) {
            JOptionPane.showMessageDialog(
                this,
                "Todos los campos son obligatorios.",
                "Validacion",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (!password.equals(passwordConfirmacion)) {
            JOptionPane.showMessageDialog(
                this,
                "Las contraseñas no coinciden.",
                "Validacion",
                JOptionPane.WARNING_MESSAGE
            );
            abrirLogin();
            return;
        }

        try {
            registroController.registrarUsuario(nombre, apellido, password, telefono, Rol.EMPLEADO, correo);
            limpiarFormulario();
            JOptionPane.showMessageDialog(
                this,
                "Usuario registrado correctamente.",
                "Registro completado",
                JOptionPane.INFORMATION_MESSAGE
                
            );
            abrirLogin();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(
                this,
                "No se pudo registrar el usuario: " + exception.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void abrirLogin() {
        applicationContext.getBean(LoginFrame.class).mostrar();
        dispose();
    }

    private void limpiarFormulario() {
        nombreField.setText("");
        apellidoField.setText("");
        passwordField.setText("");
        passwordConfirmacionField.setText("");
        telefonoField.setText("");
        correoField.setText("");
    }
    public void mostrar() {
        setVisible(true);
    }
}
