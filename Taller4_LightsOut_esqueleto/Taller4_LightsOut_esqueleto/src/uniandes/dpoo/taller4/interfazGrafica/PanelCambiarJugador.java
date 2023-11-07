package uniandes.dpoo.taller4.interfazGrafica;

import javax.swing.*;
import java.awt.event.*;

public class PanelCambiarJugador extends JPanel {

    private JTextField campoTexto;
    private String valor;

    public PanelCambiarJugador() {
        JLabel etiqueta = new JLabel("Introduce una cadena de 3 caracteres:");
        campoTexto = new JTextField(3);
        JButton boton = new JButton("Guardar");

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = campoTexto.getText().toUpperCase();
                if (texto.length() == 3) {
                    valor = texto;
                    JOptionPane.showMessageDialog(PanelCambiarJugador.this, "Valor guardado correctamente");
                } else {
                    JOptionPane.showMessageDialog(PanelCambiarJugador.this, "La cadena debe contener exactamente 3 caracteres");
                }
            }
        });

        add(etiqueta);
        add(campoTexto);
        add(boton);
    }

    public String getValor() {
        return valor;
    }

}