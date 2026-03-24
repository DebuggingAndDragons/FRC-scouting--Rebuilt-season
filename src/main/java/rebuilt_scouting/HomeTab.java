package rebuilt_scouting;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import rebuilt_scouting.MicroClasses.ErrorMessage;


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
        JButton dataViewButton = new JButton("View Teams");
        JButton teamRankingButton = new JButton("Adjust Rankings");
        JButton allianceButton = new JButton("Alliances");
        JButton importEventButton = new JButton("<html>"+"Import Event (Statbotics CVS) <br> WARNING: Buggy and unsafe!"+"</html>");

        menuPanel.add(pitScoutingButton);
        menuPanel.add(matchScoutingButton);
        menuPanel.add(dataViewButton);
        menuPanel.add(teamRankingButton);
        menuPanel.add(allianceButton);
        menuPanel.add(importEventButton);

        
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

        dataViewButton.addActionListener(new ActionListener(){
            @ Override
            public void actionPerformed(ActionEvent e){

            }
        });

        importEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JFrame fileFrame = new JFrame("Select a file to import");
                JFileChooser fileChose = new JFileChooser("Downloads");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV File", "CSV");
                fileFrame.add(fileChose);
                fileChose.setFileFilter(filter);
                fileChose.setFileSelectionMode(JFileChooser.FILES_ONLY);

                if(fileChose.showOpenDialog(fileFrame)==JFileChooser.APPROVE_OPTION&&(fileChose.getSelectedFile().getAbsolutePath().toUpperCase().endsWith(".CSV"))){
                    File file = fileChose.getSelectedFile();
                    Path sourcePath = file.toPath();
                    Path endPath = Paths.get("src/main/java/rebuilt_scouting/Data/Data Files/Imported Data/");

                    if(!Files.exists(endPath)){
                        try{
                        Files.createDirectories(endPath);
                        }catch(Exception  e2){
                            new ErrorMessage("Something has gone very, very wrong. Please contact the developer.");
                        }
                    }

                    try {
                        Files.move(sourcePath,(endPath.resolve(sourcePath.getFileName())),StandardCopyOption.REPLACE_EXISTING);
                        //file.renameTo(sourcePath.getFileName());
                    } catch (IOException e1) {
                       new ErrorMessage("Something went wrong. Please try again.");
                 }
                
                }else{
                    if(!fileChose.getSelectedFile().getAbsolutePath().toUpperCase().endsWith(".CSV")){
                        new ErrorMessage("Invalid file type selected");
                    }
                    return;
                }

            }
        });
    }
    
}
