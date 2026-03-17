package com.moderni.erp.ui;

import com.moderni.erp.controller.LoginController;
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
public class LoginFrame extends JFrame {

    private final LoginController loginController;
    private final ApplicationContext applicationContext;
    private final JTextField correoField = new JTextField(20);
    private final JPasswordField passwordField = new JPasswordField(20);

    public LoginFrame(LoginController loginController, ApplicationContext applicationContext) {
        this.loginController = loginController;
        this.applicationContext = applicationContext;
        inicializarVentana();
    }

    private void inicializarVentana() {
        setTitle("ERP Moderni - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(460, 320);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel(new BorderLayout(0, 18));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));
        panelPrincipal.setBackground(new Color(245, 240, 231));

        JLabel titulo = new JLabel("Iniciar sesion", SwingConstants.CENTER);
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

        agregarCampo(formulario, gbc, 0, "correo", correoField);
        agregarCampo(formulario, gbc, 1, "Contrasena", passwordField);

        JButton loginButton = new JButton("Entrar");
        loginButton.setFocusPainted(false);
        loginButton.setBackground(new Color(31, 92, 74));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.addActionListener(event -> iniciarSesion());

        JButton registroButton = new JButton("Ir a registro");
        registroButton.setFocusPainted(false);
        registroButton.setBackground(new Color(210, 198, 175));
        registroButton.setForeground(new Color(42, 36, 29));
        registroButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        registroButton.addActionListener(event -> abrirRegistro());

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(16, 8, 0, 8);
        formulario.add(loginButton, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(8, 8, 0, 8);
        formulario.add(registroButton, gbc);

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

    private void iniciarSesion() {
        String correo = correoField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (correo.isBlank() || password.isBlank()) {
            JOptionPane.showMessageDialog(
                this,
                "Introduce correo y contraseña.",
                "Validacion",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (!loginController.iniciarSesion(correo, password)) {
            JOptionPane.showMessageDialog(
                this,
                "Credenciales incorrectas.",
                "Login",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        applicationContext.getBean(PaginaPruebaFrame.class).mostrar();
        dispose();
    }

    public void mostrar() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void abrirRegistro() {
        applicationContext.getBean(RegistroFrame.class).mostrar();
        setVisible(false);
    }
}
