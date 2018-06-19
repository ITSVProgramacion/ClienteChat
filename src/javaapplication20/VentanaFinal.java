
package javaapplication20;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author nicolas.fanin
 */
public class VentanaFinal extends JFrame{
    
    private String texto = "";
    
    public VentanaFinal(JFrame copiarTexto, String texto){
               
        JFrame frame = new JFrame("Agregar Texto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        
        JLabel label = new JLabel("Su texto final: \n" + texto);        
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setAlignmentY(Component.CENTER_ALIGNMENT);
        
        JButton btnVolver = new JButton("<< Volver");
        btnVolver.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.setVisible(false);
                frame.dispose();
                copiarTexto.setVisible(true);
            }
        });
        
        listPane.add(label);
        listPane.add(btnVolver); 
        
        frame.add(listPane);
        
        frame.setVisible(true);
    }
    
}
