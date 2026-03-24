package rebuilt_scouting.MicroClasses;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorMessage {
    public ErrorMessage(String errorMessage) {
        if(!errorMessage.startsWith("Error: ")){
            errorMessage=("Error: "+errorMessage);
        }
        JFrame error = new JFrame("ERROR!");
        error.setLayout(new GridLayout(2, 1));

        JLabel errorLabel = new JLabel(errorMessage);
        JButton okButton = new JButton("Ok");

        error.add(errorLabel);
        error.add(okButton);
        
        error.setSize(300, 100);
        error.setVisible(true);
        error.setAlwaysOnTop(true);
                
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                error.dispose();
            }
        });
    }
}
