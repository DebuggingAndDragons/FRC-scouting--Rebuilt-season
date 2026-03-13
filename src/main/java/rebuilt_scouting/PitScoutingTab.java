package rebuilt_scouting;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.border.TitledBorder;

import java.awt.GridLayout;
import java.awt.Window;
//import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PitScoutingTab {
    public PitScoutingTab() {
        // -Creates new window
        JFrame pitScoutFrame = new JFrame("Pit Scouting 2026");
        JPanel dataCollectionPanel = new JPanel(new GridLayout(7, 1));
        pitScoutFrame.add(dataCollectionPanel);

        // Adds the major frame settings
        pitScoutFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pitScoutFrame.setVisible(true);
        pitScoutFrame.setAlwaysOnTop(true);

        // - TEAM DETAILS SECTION
        // Create Jpanel
        JPanel infoPanel = new JPanel(new GridLayout(2, 2));

        // Creating Team Name Area
        JLabel nameAssistLabel = new JLabel("Team Name");
        JTextField teamNameField = new JTextField();

        // Creating Team Number Input Area
        JLabel numAssistLabel = new JLabel("Team Number");
        JSpinner teamNumSpinner = new JSpinner();

        // Adding everything to the JPanel, then adding that JPanel to the
        // dataCollectionPanel
        {
            infoPanel.add(nameAssistLabel);
            infoPanel.add(teamNameField);
            infoPanel.add(numAssistLabel);
            infoPanel.add(teamNumSpinner);
        }

        // Creating a decorative frame
        infoPanel.setBorder(new TitledBorder("Team Info"));

        // -ROBOT ARCHETYPES SECTION
        // Create JPanel
        JPanel archetypePanel = new JPanel(new GridLayout(2, 1));

        // Creates an array of possible archtypes
        String[] robotArchetype = { "Custom", "Kitbot", "Everybot", "Chassis", "Rev CC", "WCP CC" };

        // Creates dropdown menu of archtypes
        @SuppressWarnings("rawtypes")
        JComboBox archetypeBox = new JComboBox<String>(robotArchetype);

        // Creates a JLable to explain what the dropdown is for
        JLabel archetypeLabel = new JLabel("Robot Archetype:");

        // Creates a new JPanel to keep the lable and the dropdown together,
        JPanel archtypeSelectionPanel = new JPanel(new GridLayout(1, 2));
        archtypeSelectionPanel.add(archetypeLabel);
        archtypeSelectionPanel.add(archetypeBox);

        // Creates a JCheckbox for if the robot has been modified from it's default
        JCheckBox modifiedBox = new JCheckBox("Modified?");

        archetypePanel.add(archtypeSelectionPanel);
        archetypePanel.add(modifiedBox);

        // adds frame
        archetypePanel.setBorder(new TitledBorder("Robot Archetype"));

        // -ROBOT CAPABILITIES SECTION
        // Creates JPanels
        JPanel robotcapabilitiesPanel = new JPanel(new GridLayout(3, 1));
        JPanel traversalPanel = new JPanel(new GridLayout(1, 2));
        JPanel autoAndDepotPanel = new JPanel(new GridLayout(1, 2));
        JPanel climbPanel = new JPanel(new GridLayout(1, 0));

        // Adds Checkboxes
        JCheckBox BumpBox = new JCheckBox("Can Traverse Bump");
        JCheckBox trenchBox = new JCheckBox("Can Traverse Trench");
        JCheckBox autoBox = new JCheckBox("Has Auto");
        JCheckBox depotBox = new JCheckBox("Can pass over depot");

        JCheckBox l1Climb = new JCheckBox("L1 Endgame Climb");
        JCheckBox l2Climb = new JCheckBox("L2 Endgame Climb");
        JCheckBox l3Climb = new JCheckBox("L3 Endgame Climb");
        JCheckBox autoClimb = new JCheckBox("Auto Climb");

        // Adds components to their respective panels
        traversalPanel.add(BumpBox);
        traversalPanel.add(trenchBox);

        autoAndDepotPanel.add(autoBox);
        autoAndDepotPanel.add(depotBox);

        climbPanel.add(l1Climb);
        climbPanel.add(l2Climb);
        climbPanel.add(l3Climb);
        climbPanel.add(autoClimb);

        // adding all panels to the main section panel
        robotcapabilitiesPanel.add(traversalPanel);
        robotcapabilitiesPanel.add(climbPanel);
        robotcapabilitiesPanel.add(autoAndDepotPanel);

        // adding borders
        traversalPanel.setBorder(new TitledBorder("Traversal"));
        climbPanel.setBorder(new TitledBorder("Climb"));
        autoAndDepotPanel.setBorder(new TitledBorder("Miscellaneous"));

        // -Hopper and Shooter Info
        JPanel hopperAndShooterPanel = new JPanel(new GridLayout(2, 1));

        // -Shooter Information
        JLabel shooterCountGuide = new JLabel("Number of Shooters:");
        JSpinner shooterCount = new JSpinner();
        JPanel shooterPanel = new JPanel(new GridLayout(1, 3));
        JCheckBox areOmniShoot = new JCheckBox("Turret?");
        shooterPanel.add(shooterCountGuide);
        shooterPanel.add(shooterCount);
        shooterCount.setValue(0);
        shooterPanel.add(areOmniShoot);
        shooterPanel.setBorder(new TitledBorder("Shooter Details"));

        // -Hopper Information

        String[] hopperType = { "Fixed", "Extension", "None" };
        @SuppressWarnings({ "rawtypes", "unchecked" })
        JComboBox hopperBox = new JComboBox(hopperType);
        JLabel hopperLabel = new JLabel("Hopper Type");
        JPanel hopperPanel = new JPanel(new GridLayout());
        JLabel fuelCountLabel = new JLabel("Capacity");
        JSpinner fuelcount = new JSpinner();
        JPanel hopperCapacityPanel = new JPanel(new GridLayout());
        hopperPanel.add(hopperLabel);
        hopperPanel.add(hopperBox);
        hopperCapacityPanel.add(fuelCountLabel);
        hopperCapacityPanel.add(fuelcount);
        hopperPanel.add(hopperCapacityPanel);
        hopperPanel.setBorder(new TitledBorder("Hopper Details"));

        hopperAndShooterPanel.add(shooterPanel);
        hopperAndShooterPanel.add(hopperPanel);

        // -INTAKE SECTION
        JPanel intakePanel = new JPanel(new GridLayout(0, 1));
        JCheckBox hasIntake = new JCheckBox("Any Intake?");
        JPanel intakePanelRow2 = new JPanel(new GridLayout(1, 3));
        JCheckBox bumperHole = new JCheckBox("Bumper Hole?");
        JCheckBox overBumperIntake = new JCheckBox("Over Bumper?");
        JCheckBox multipleIntakes = new JCheckBox("Multiple intakes?");

        intakePanelRow2.add(bumperHole);
        intakePanelRow2.add(overBumperIntake);
        intakePanelRow2.add(multipleIntakes);

        JPanel intakeQuanityPanel = new JPanel(new GridLayout());
        JSpinner intakeAmount = new JSpinner();
        JLabel intakeAmountGuide = new JLabel("Amount of Intakes");
        intakeQuanityPanel.add(intakeAmountGuide);
        intakeQuanityPanel.add(intakeAmount);

        intakePanel.add(hasIntake);

        intakePanel.setBorder(new TitledBorder("Intake Details"));

        //-DRIVE TEAM DETAILS SECTION
        //creates Jpanel
        JPanel driveTeamDetailsPanel = new JPanel(new GridLayout(0,1));

        //Creates Driver Section elements
        JPanel driverPanel = new JPanel(new GridLayout(1,0));
        JLabel driverNamLabel = new JLabel("Driver's name (optional)");
        JTextArea driverNameInput = new JTextArea();
        JPanel driverCheckboxPanel = new JPanel(new GridLayout(1,2));
        JCheckBox hasPractice = new JCheckBox("Has practice with bot");
        JCheckBox vetDriver = new JCheckBox("Veteran Driver");

        // binding the driver area into a single panel
        driverPanel.add(driverNamLabel);
        driverPanel.add(driverNameInput);
        driverPanel.add(driverCheckboxPanel);
        driverCheckboxPanel.add(hasPractice);
        driverCheckboxPanel.add(vetDriver);

        //Creates Codriver Section elements
        JPanel codriverPanel = new JPanel(new GridLayout(1,0));
        JLabel codriverNamLabel = new JLabel("Codriver's name (optional)");
        JTextArea codriverNameInput = new JTextArea();
        JPanel codriverCheckboxPanel = new JPanel(new GridLayout(1,2));
        JCheckBox hasPracticeCodriving = new JCheckBox("Has practice with bot");
        JCheckBox vetCodriver = new JCheckBox("Veteran Codriver");

        codriverPanel.add(codriverNamLabel);
        codriverPanel.add(codriverNameInput);
        codriverPanel.add(codriverCheckboxPanel);
        codriverCheckboxPanel.add(hasPracticeCodriving);
        codriverCheckboxPanel.add(vetCodriver);

        //Creates Drive Coach Section elements
        JPanel driveCoachPanel = new JPanel(new GridLayout(1,0));
        JLabel driveCoachNamLabel = new JLabel("Drive Coach's name (optional)");
        JTextArea driveCoachNameInput = new JTextArea();
        JPanel driveCoachCheckboxPanel = new JPanel(new GridLayout(1,2));
        JCheckBox mentorCoach = new JCheckBox("Is mentor?");
        JCheckBox vetCoach = new JCheckBox("Veteran Drive Coach");

        driveCoachPanel.add(driveCoachNamLabel);
        driveCoachPanel.add(driveCoachNameInput);
        driveCoachPanel.add(driveCoachCheckboxPanel);
        driveCoachCheckboxPanel.add(mentorCoach);
        driveCoachCheckboxPanel.add(vetCoach);

        //Adds everything to the panel
        driveTeamDetailsPanel.add(driverPanel);
        driveTeamDetailsPanel.add(codriverPanel);
        driveTeamDetailsPanel.add(driveCoachPanel);

        //Adds borders

        //driveTeamDetailsPanel.setBorder(new TitledBorder("Drive team details"));
        driverPanel.setBorder(new TitledBorder("Driver"));
        codriverPanel.setBorder(new TitledBorder("Codriver"));
        driveCoachPanel.setBorder(new TitledBorder("Drive Coach"));



        //- SUBMIT BUTTON SECTION
        //creates JPanel
        JPanel buttonPanel = new JPanel(new GridLayout(2,1));

        //creates buttons
        JButton submitButton=new JButton("Save Data");
        JButton cancelButton = new JButton("Cancel");

        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);



        // - Adds Actionlisteners
        //TODO: fancy stuff
        ////archetypeBox.addActionListener(new ActionListener() {
        ////    @Override
        ////    public void actionPerformed(ActionEvent e) {
        ////        switch (robotArchetype[archetypeBox.getSelectedIndex()]) {
        ////            case ("Custom"):
        ////                modifiedBox.setEnabled(false);
        ////                modifiedBox.setSelected(false);
////
        ////                BumpBox.setEnabled(true);
        ////                trenchBox.setEnabled(true);
        ////                l1Climb.setEnabled(true);
        ////                l2Climb.setEnabled(true);
        ////                l3Climb.setEnabled(true);
        ////                autoClimb.setEnabled(true);
        ////                depotBox.setEnabled(true);
        ////                shooterCount.setEnabled(true);
        ////                areOmniShoot.setEnabled(true);
        ////                hopperBox.setEnabled(true);
        ////                hasIntake.setEnabled(true);
        ////                bumperHole.setEnabled(true);
        ////                overBumperIntake.setEnabled(true);
        ////                multipleIntakes.setEnabled(true);
        ////                intakeAmount.setEnabled(true);
        ////                fuelcount.setEnabled(true);
        ////                break;
        ////            case ("Kitbot"):// kitbot
        ////                modifiedBox.setEnabled(true);
////
        ////                BumpBox.setSelected(true);
        ////                trenchBox.setSelected(true);
        ////                autoBox.setSelected(true);
        ////                depotBox.setSelected(true);
////
        ////                shooterCount.setValue(1);
////
        ////                hopperBox.setSelectedIndex(0);
        ////                fuelcount.setValue(Constants.KITBOT_HOPPER_CAPACITY);
////
        ////                hasIntake.setSelected(true);
        ////                bumperHole.setSelected(true);
        ////                intakePanel.add(intakePanelRow2);
        ////                multipleIntakes.setSelected(false);
        ////                intakePanel.remove(intakeQuanityPanel);
////
        ////                if (!modifiedBox.isSelected()) {
        ////                    l1Climb.setSelected(false);
        ////                    l2Climb.setSelected(false);
        ////                    l3Climb.setSelected(false);
        ////                    autoClimb.setSelected(false);
        ////                    areOmniShoot.setSelected(false);
        ////                    overBumperIntake.setSelected(false);
        ////                    multipleIntakes.setSelected(false);
////
        ////                    BumpBox.setEnabled(false);
        ////                    trenchBox.setEnabled(false);
        ////                    l1Climb.setEnabled(false);
        ////                    l2Climb.setEnabled(false);
        ////                    l3Climb.setEnabled(false);
        ////                    autoClimb.setEnabled(false);
        ////                    depotBox.setEnabled(false);
        ////                    shooterCount.setEnabled(false);
        ////                    areOmniShoot.setEnabled(false);
        ////                    hopperBox.setEnabled(false);
        ////                    hasIntake.setEnabled(false);
        ////                    bumperHole.setEnabled(false);
        ////                    overBumperIntake.setEnabled(false);
        ////                    multipleIntakes.setEnabled(false);
        ////                    intakeAmount.setEnabled(false);
        ////                    fuelcount.setEnabled(false);
        ////                }
////
        ////                break;
        ////            case ("Everybot"):// Everybot
        ////                modifiedBox.setEnabled(true);
////
        ////                BumpBox.setSelected(true);
        ////                trenchBox.setSelected(true);
        ////                autoBox.setSelected(true);
        ////                depotBox.setSelected(true);
////
        ////                shooterCount.setValue(1);
////
        ////                hopperBox.setSelectedIndex(0);
        ////                fuelcount.setValue(Constants.KITBOT_HOPPER_CAPACITY);
////
        ////                hasIntake.setSelected(true);
        ////                intakePanel.add(intakePanelRow2);
        ////                multipleIntakes.setSelected(false);
        ////                intakePanel.remove(intakeQuanityPanel);
        ////                l1Climb.setSelected(true);
        ////                break;
        ////            case ("Chassis"):// Chassis
        ////                modifiedBox.setEnabled(false);
        ////                break;
        ////            case ("Rev CC"):// Rev CC
        ////                modifiedBox.setEnabled(true);
        ////                break;
        ////            case ("WCP CC"):// WCP CC
        ////                modifiedBox.setEnabled(true);
        ////                break;
////
        ////        }
        ////        archetypePanel.revalidate();
        ////        archetypePanel.repaint();
        ////    }
        ////});

        //This is just for the fancy thing where if it has multipule intakes it'll have a spinner appear
        multipleIntakes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (multipleIntakes.isSelected()) {
                    intakePanel.add(intakeQuanityPanel);
                } else {
                    intakePanel.remove(intakeQuanityPanel);
                }
                intakePanel.revalidate();
                intakePanel.repaint();
            }

        });
        //This is for hiding everything about the intake untill we know it exists
        hasIntake.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hasIntake.isSelected()) {
                    intakePanel.add(intakePanelRow2);
                    if (multipleIntakes.isSelected()) {
                        intakePanel.add(intakeQuanityPanel);
                    }
                } else {
                    intakePanel.remove(intakePanelRow2);
                    intakePanel.remove(intakeQuanityPanel);
                }
                intakePanel.revalidate();
                intakePanel.repaint();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JFrame submitwindow=new JFrame("Save Data");
                submitwindow.setLayout(new GridLayout(2,1));
                submitwindow.add(new JLabel("Are you sure you want to submit?"));
                
                JPanel submitButtonPanel=new JPanel();
                JButton save=new JButton("Save");
                JButton cancel = new JButton("Cancel");
                submitButtonPanel.add(save);
                submitButtonPanel.add(cancel);
                save.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        
                    }
                });

            }
        });
    //TODO: More Fancy stuff   
    ////    modifiedBox.addActionListener(new ActionListener() {
    ////        @Override
    ////        public void actionPerformed(ActionEvent e) {
    ////            boolean lockdown;
    ////            if (modifiedBox.isEnabled() && !modifiedBox.isSelected())
    ////                lockdown = false;
    ////            else {
    ////                lockdown = true;
    ////            }
    ////            BumpBox.setEnabled(lockdown);
    ////            trenchBox.setEnabled(lockdown);
    ////            l1Climb.setEnabled(lockdown);
    ////            l2Climb.setEnabled(lockdown);
    ////            l3Climb.setEnabled(lockdown);
    ////            autoClimb.setEnabled(lockdown);
    ////            depotBox.setEnabled(lockdown);
    ////            shooterCount.setEnabled(lockdown);
    ////            areOmniShoot.setEnabled(lockdown);
    ////            hopperBox.setEnabled(lockdown);
    ////            hasIntake.setEnabled(lockdown);
    ////            bumperHole.setEnabled(lockdown);
    ////            overBumperIntake.setEnabled(lockdown);
    ////            multipleIntakes.setEnabled(lockdown);
    ////            intakeAmount.setEnabled(lockdown);
    ////            fuelcount.setEnabled(lockdown);
    ////        }
////
    ////    });

        dataCollectionPanel.add(infoPanel);
        dataCollectionPanel.add(archetypePanel);
        dataCollectionPanel.add(robotcapabilitiesPanel);
        dataCollectionPanel.add(hopperAndShooterPanel);
        dataCollectionPanel.add(intakePanel);
        dataCollectionPanel.add(driveTeamDetailsPanel);
        dataCollectionPanel.add(buttonPanel);

        pitScoutFrame.setSize(600, 800);// This has a tendancy to break everything if placed elsewhere.

    }
}