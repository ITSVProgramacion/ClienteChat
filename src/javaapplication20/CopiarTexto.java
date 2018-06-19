/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication20;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import main.ClienteChat;

/**
 *
 * @author nicolas.fanin
 */
public class CopiarTexto {

    private JFrame frame;
    private JTextArea textArea;
    private JTextField textField;
    private ClienteChat clienteChat;

    public CopiarTexto() {
        //Inicializo el Frame.
        frame = new JFrame("Agregar Texto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setResizable(false);

        //Seteo los componentes
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 20, 460, 350);

        textField = new JTextField();
        textField.setToolTipText("Ingrese texto");
        textField.setBounds(20, 385, 460, 25);
        
        clienteChat = new ClienteChat("127.0.0.1", "2000", textArea);
        clienteChat.conectar();

        JButton btnEnviar = new JButton("Enviar");
        btnEnviar.setBounds(360, 420, 120, 30);

        JButton btnBorrar = new JButton("Finalizar");
        btnBorrar.setBounds(220, 420, 120, 30);

        //Agrego los actionListeners.
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                copiarTexto();
                textField.requestFocus();
            }
        };
        btnEnviar.addActionListener(al);

        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int respuesta;
                respuesta = JOptionPane.showConfirmDialog(null,
                        "Está seguro que desea finalizar?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    frame.setVisible(false);
                    finalizar();                    
                    textArea.setText("");
                    textField.setText("");
                }                
            }
        });

        //Agrego key event para tomar el enter.
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    copiarTexto();
                }
            }
        });
                
        //Agrego los componentes a la pantalla.
        frame.add(scrollPane);
        frame.add(textField);
        frame.add(btnEnviar);
        frame.add(btnBorrar);

        //Hago la ventana visible.
        frame.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CopiarTexto chat = new CopiarTexto();
    }

    //Metodo que agrega el texto de textField a textArea.
    public void copiarTexto() {
        /*if (!textField.getText().equals("")) {
            if (textArea.getText().equals("")) {
                textArea.setText(textField.getText());
            } else {
                textArea.setText(textArea.getText() + "\n" + textField.getText());
            }
            textField.setText("");
        }*/
        clienteChat.EnviarMensaje("Nicolas",textField.getText());
        textField.setText("");
    }

    public void finalizar() {
        VentanaFinal ventanaFinal = new VentanaFinal(frame, textArea.getText());        
    }

}
