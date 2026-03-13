package rebuilt_scouting;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.time.LocalDate;

import javax.swing.*;
import rebuilt_scouting.*;


public class HomeTab {
    public HomeTab(){
        JFrame homeFrame = new JFrame("REBUILT Scouting: 6101 Edition");
        homeFrame.setLayout(new GridLayout(2,1));
        //HomeFrame.add(new ImageIcon("WPILibLogo"));
        JPanel topPanel = new JPanel();
        String fancyString = "TODO: Add stuff here";
        //TODO: Add stuff for this

        JLabel dayLabel= new JLabel(fancyString);
        topPanel.add(dayLabel);









        JPanel menuPanel = new JPanel(new GridLayout(0,2));

        JButton pitScoutingButton = new JButton("Pit Scouting");
        JButton matchScoutingButton = new JButton("Match Scouting");
        JButton dataViewButton = new JButton("View Data");
        JButton teamRankingButton = new JButton("Adjust Rankings");
        JButton allianceButton = new JButton("Alliances");

        menuPanel.add(pitScoutingButton);
        menuPanel.add(matchScoutingButton);
        menuPanel.add(dataViewButton);
        menuPanel.add(teamRankingButton);
        menuPanel.add(allianceButton);

        
        homeFrame.add(topPanel);
        homeFrame.add(menuPanel);

        homeFrame.setVisible(true);
        homeFrame.setSize(1200, 600);

        pitScoutingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //// new PitScoutingTab();
               new UpdatedPitQuestonareTab();
            }
        });
    }
    
}
