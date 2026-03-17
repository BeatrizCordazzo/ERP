package com.moderni.erp.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class PaginaPruebaFrame extends JFrame {

    private final ApplicationContext applicationContext;

    public PaginaPruebaFrame(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        inicializarVentana();
    }

    private void inicializarVentana() {
        setTitle("ERP Moderni - Prueba");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 420);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));
        panelPrincipal.setBackground(new Color(245, 240, 231));

        JLabel titulo = new JLabel("Pantalla de prueba", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(new Color(28, 52, 46));
        panelPrincipal.add(titulo, BorderLayout.CENTER);

        JButton cerrarSesionButton = new JButton("Cerrar sesion");
        cerrarSesionButton.addActionListener(event -> volverAlLogin());

        JPanel pie = new JPanel();
        pie.setOpaque(false);
        pie.add(cerrarSesionButton);
        panelPrincipal.add(pie, BorderLayout.SOUTH);

        setContentPane(panelPrincipal);
    }

    public void mostrar() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void volverAlLogin() {
        dispose();
        applicationContext.getBean(LoginFrame.class).setVisible(true);
    }
}
