package rebuilt_scouting;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.BoxLayout;

import java.nio.file.*;

public class UpdatedPitQuestonareTab {
    public UpdatedPitQuestonareTab() {
        // Creates the initaial JFrame that allows for the team name, number, and
        // archtype to be entered.
        JFrame initWindow = new JFrame("New Team");
        initWindow.setLayout(new GridLayout(4, 1));

        // Creates a JPanel for the team name
        JPanel namePanel = new JPanel(new GridLayout(1, 2));

        // Creates a JLabel and input area for the team's name
        JLabel nameAssistLabel = new JLabel("Team Name");
        JTextField teamNameField = new JTextField();

        // Bind the elements for the team's name to their JPanel
        namePanel.add(nameAssistLabel);
        namePanel.add(teamNameField);

        // Creates a new JPanel for the team's number
        JPanel numPanel = new JPanel(new GridLayout(1, 2));

        // Creates a JLabel and a JSpinner to allow for the input of the team's number
        JLabel numAssistLabel = new JLabel("Team Number");
        JSpinner teamNumSpinner = new JSpinner();

        // Binds the Spinner and JLable to the same panel
        numPanel.add(numAssistLabel);
        numPanel.add(teamNumSpinner);

        // Creates a JPanel for the robot archetype
        JPanel archetypePanel = new JPanel(new GridLayout(1, 2));

        // Creates a new array for the possible robot archtypes
        String[] robotArchetype = {"Custom", "Kitbot", "Everybot", "Chassis", "Rev CC", "WCP CC","---" };

        // Creates a new JLabel to explain what the soon-to-follow combo box is for
        JLabel archetypeLabel = new JLabel("Robot Archetype:");

        // Creating a combo box to provide a dropdown of all possible archtypes
        @SuppressWarnings("rawtypes")
        JComboBox archetypeBox = new JComboBox<String>(robotArchetype);

        // Creates a checkbox for if the robot has been modified from it's archtype (Ex:
        // a kitbot with a L1 climb)
        JCheckBox modifiedBox = new JCheckBox("Modified?");

        // Adds everything to the archtype JPanel
        archetypePanel.add(archetypeLabel);
        archetypePanel.add(archetypeBox);
        archetypePanel.add(modifiedBox);

        // Creates a JPanel contaioning two buttons: One to cancel, one to create
        JPanel buttonPannel = new JPanel(new GridLayout(1, 2));
        JButton createButton = new JButton("Create Team");
        JButton cancelButton = new JButton("Cancel");
        buttonPannel.add(createButton);
        buttonPannel.add(cancelButton);

        // Adding everything to the window
        initWindow.add(namePanel);
        initWindow.add(numPanel);
        initWindow.add(archetypePanel);
        initWindow.add(buttonPannel);

        // Basic Window config
        initWindow.setSize(400, 150);
        initWindow.setVisible(true);
        initWindow.setLocationRelativeTo(null);
        modifiedBox.setEnabled(false);

        archetypeBox.setSelectedIndex(6);

        // Setting up actionlistiners to see if team creation is allowed
        createButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(teamNameField.getText().isEmpty() || ((int) teamNumSpinner.getValue()) < 1
                    || archetypeBox.getSelectedIndex() == 6)) {
                    question(teamNameField.getText(), (int) teamNumSpinner.getValue(),
                            (String) archetypeBox.getSelectedItem(), ((archetypeBox.getSelectedIndex()==0)||(archetypeBox.getSelectedIndex()==3))? true:modifiedBox.isSelected());
                    initWindow.dispose();
                } else {
                    //displays messages if the inputs are invalid
                    if (((int) teamNumSpinner.getValue()) < 1) {
                        throwInvalidTeamNumError();
                    }
                    if (teamNameField.getText().isEmpty()){
                        throwEmptyFieldError();
                    }
                }
            }

        });

        //Sets up an actionListiner to dispose of the window if told to
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initWindow.dispose();
            }

        });

        //Sets up an acionListiner for if the modifiedBox should be enabled
        archetypeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e ){
                if(archetypeBox.getSelectedIndex()==0|| archetypeBox.getSelectedIndex()==3){
                    modifiedBox.setEnabled(false);
                }else if(archetypeBox.equals(null)){
                    throwEmptyFieldError();
                }else{
                    modifiedBox.setEnabled(true);
                }
            }
        });
        


            


    }
    //- "error messages"
    public void throwEmptyFieldError() {

        JFrame error = new JFrame("ERROR!");
        error.setLayout(new GridLayout(2, 1));
        JLabel errorLabel = new JLabel("Error: Fields cannot be left empty.");
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

    public void throwInvalidTeamNumError() {

        JFrame error = new JFrame("ERROR!");
        error.setLayout(new GridLayout(2, 1));
        JLabel errorLabel = new JLabel("Error: Team number must be greater than zero");
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

    public void question(String teamName, int teamNum, String archetype, boolean modified) {
        JFrame pitScoutFrame = new JFrame("Pit Scouting: " + teamName);
        JPanel dataCollectionPanel = new JPanel(new GridLayout(7, 1));
        pitScoutFrame.add(dataCollectionPanel);

        // Adds the major frame settings
        pitScoutFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pitScoutFrame.setVisible(true);
        pitScoutFrame.setAlwaysOnTop(true);

        // -CLIMB DETAILS SECTION

        // creates JPanel for Climb
        JPanel climbPanel = new JPanel(new GridLayout(2, 1));

        // Adds Button that will determine if the robot can climb.
        JCheckBox canClimb = new JCheckBox("Can Climb");

        // Creates JPanel that will appear if the robot can climb
        JPanel secondaryClimbPanel = new JPanel(new GridLayout(2, 2));

        // Creates an array for the possible climbs and an array for clomber archetypes
        String[] climbTiers = { "L1", "L2", "L3" };
        String[] climberTypes = { "Pen State", "Boxed", "Bumper Flip", "Custom" };

        // Creates a label and a dropdown for the possible climbs
        JLabel climbLabel = new JLabel("Climb:");
        JComboBox climbTierBox = new JComboBox<>(climbTiers);

        // Creates a label and ComboBox for types of climbers (Pen state,
        // climber-in-a-box, etc)
        JLabel climbStyle = new JLabel("Climb Type");
        JComboBox climbTypeBox = new JComboBox<>(climberTypes);

        // Adds the checkbox to see if the robot can climb to the climbPanel
        climbPanel.add(canClimb);

        // Adds everything else to the secondaryClimbPanel, which is kept out of
        // climbpanel unless the ActionListiners on canClimb get triggered
        secondaryClimbPanel.add(climbLabel);
        secondaryClimbPanel.add(climbTierBox);
        secondaryClimbPanel.add(climbStyle);
        secondaryClimbPanel.add(climbTypeBox);
        climbPanel.setBorder(new TitledBorder("Climb"));

        // -INTAKE DETAILS
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

        // -Shooter Stuff
        JLabel shooterCountGuide = new JLabel("Number of Shooters:");
        JSpinner shooterCount = new JSpinner();
        JPanel shooterPanel = new JPanel(new GridLayout(1, 3));
        JCheckBox areOmniShoot = new JCheckBox("Turret?");
        shooterPanel.add(shooterCountGuide);
        shooterPanel.add(shooterCount);
        shooterCount.setValue(0);
        shooterPanel.add(areOmniShoot);
        shooterPanel.setBorder(new TitledBorder("Shooter Details"));

        JPanel hopperAndShooterPanel = new JPanel(new GridLayout(2, 1));

        hopperAndShooterPanel.add(hopperPanel);
        hopperAndShooterPanel.add(shooterPanel);

        //-DRIVETRAIN SECTION
        JPanel drivetrainPanel = new JPanel(new GridLayout(1,3));
        String[] drivetrains ={"Tankdrive", "Mechanum", "Swervedrive", "Omniwheels", "Raptordrive", "Other"};
        JLabel driveLabel = new JLabel("Drivetrain:");
        JComboBox driveBox=new JComboBox<>(drivetrains);
        JCheckBox fullyWorkingDrive = new JCheckBox("Fully works");

        drivetrainPanel.add(fullyWorkingDrive);
        drivetrainPanel.add(driveLabel);
        drivetrainPanel.add(driveBox);

        drivetrainPanel.setBorder(new TitledBorder("Drivetrain Details"));


        //-TRAVERSAL SECTION
        JPanel traversalPanel = new JPanel(new GridLayout(1,2));
        JCheckBox trenchBox = new JCheckBox("Can traverse trench?");
        JCheckBox bumpBox = new JCheckBox("Can traverse bump?");

        traversalPanel.add(trenchBox);
        traversalPanel.add(bumpBox);
        traversalPanel.setBorder(new TitledBorder("Traversal"));

        //- Human Player section (unused)
        JPanel humanPlayerPanel = new JPanel(new GridLayout(1, 0));
        JLabel humanPlayerNameLabel = new JLabel("Human Player's name (optional)");
        JTextArea humanPlayerNameInput = new JTextArea();
        JCheckBox hasPracticeHuman = new JCheckBox("Has practice with robot");

        humanPlayerPanel.add(humanPlayerNameLabel);
        humanPlayerPanel.add(humanPlayerNameInput);
        humanPlayerPanel.add(hasPracticeHuman); 
        humanPlayerPanel.setBorder(new TitledBorder("Human Player"));

        //-Creating a JPanel to hold traversal, human player, and drivetrain information
        JPanel traversalAndDrivetrainPanel= new JPanel(new GridLayout(0,1));
        traversalAndDrivetrainPanel.add(drivetrainPanel);
        traversalAndDrivetrainPanel.add(traversalPanel);
        //traversalHumanPlayerAndDrivetrainPanel.add(humanPlayerPanel);

        // -DRIVE TEAM DETAILS SECTION
        JPanel driveTeamDetailsPanel = new JPanel(new GridLayout(0, 1));

        JPanel driverPanel = new JPanel(new GridLayout(1, 0));
        JLabel driverNamLabel = new JLabel("Driver's name (optional)");
        JTextArea driverNameInput = new JTextArea();
        JCheckBox hasPractice = new JCheckBox("Has practice with robot");

        driverPanel.add(driverNamLabel);
        driverPanel.add(driverNameInput);
        driverPanel.add(hasPractice);

        JPanel codriverPanel = new JPanel(new GridLayout(1, 0));
        JLabel codriverNamLabel = new JLabel("Codriver's name (optional)");
        JTextArea codriverNameInput = new JTextArea();
        JCheckBox hasPracticeCodriving = new JCheckBox("Has practice with robot");

        codriverPanel.add(codriverNamLabel);
        codriverPanel.add(codriverNameInput);
        codriverPanel.add(hasPracticeCodriving);

        JPanel driveCoachPanel = new JPanel(new GridLayout(1, 0));
        JLabel driveCoachNamLabel = new JLabel("Drive Coach's name (optional)");
        JTextArea driveCoachNameInput = new JTextArea();
        JPanel driveCoachCheckboxPanel = new JPanel(new GridLayout(1, 2));
        JCheckBox mentorCoach = new JCheckBox("Is mentor?");

        driveCoachPanel.add(driveCoachNamLabel);
        driveCoachPanel.add(driveCoachNameInput);
        driveCoachPanel.add(driveCoachCheckboxPanel);
        driveCoachCheckboxPanel.add(mentorCoach);

        driveTeamDetailsPanel.add(driverPanel);
        driveTeamDetailsPanel.add(codriverPanel);
        driveTeamDetailsPanel.add(driveCoachPanel);

        // Adds borders

        // driveTeamDetailsPanel.setBorder(new TitledBorder("Drive team details"));
        driverPanel.setBorder(new TitledBorder("Driver"));
        codriverPanel.setBorder(new TitledBorder("Codriver"));
        driveCoachPanel.setBorder(new TitledBorder("Drive Coach"));


        //-AUTO SECTION

        JPanel autoPanel = new JPanel(new GridLayout(2,1));
        JPanel autoPanel2=new JPanel(new GridLayout(0,3));

        JCheckBox hasAuto = new JCheckBox("Has auto");
        JCheckBox hasComplexAuto = new JCheckBox("Has multipart autos");
        JCheckBox hasMultipleAutos = new JCheckBox("Has multiple autos");
        JCheckBox hasVision = new JCheckBox("Vision integraded with auto?");
        JCheckBox usesArchetypeAuto = new JCheckBox("Uses provided autos");
        JCheckBox autoClimb = new JCheckBox("Auto Climb?");

        autoPanel.add(hasAuto);
        autoPanel.add(autoPanel2);
        autoPanel.setBorder(new TitledBorder("Auto"));


        
        
        //-BUTTON SECTION
        JPanel buttonPanel = new JPanel(new GridLayout(2,1));
        JButton submitButton = new JButton("Submit?");
        JButton cancelButton = new JButton("Cancel");

        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);


        //- ADDING EVERYTHING TO THE WINDOW
        dataCollectionPanel.add(climbPanel);
        dataCollectionPanel.add(intakePanel);
        dataCollectionPanel.add(hopperAndShooterPanel);
        dataCollectionPanel.add(traversalAndDrivetrainPanel);
        dataCollectionPanel.add(driveTeamDetailsPanel);
        dataCollectionPanel.add(autoPanel);
        dataCollectionPanel.add(buttonPanel);


        pitScoutFrame.setSize(620, 800);

        if(!modified){
            climbPanel.setEnabled(false);
            canClimb.setEnabled(false);
            climbLabel.setEnabled(false);
            climbTierBox.setEnabled(false);
            climbStyle.setEnabled(false);
            climbTypeBox.setEnabled(false);

            bumperHole.setEnabled(false);
            overBumperIntake.setEnabled(false);
            multipleIntakes.setEnabled(false);
            intakeAmount.setEnabled(false);
            intakeAmountGuide.setEnabled(false);
            hasIntake.setEnabled(false);

            hopperBox.setEnabled(false);
            hopperPanel.setEnabled(false);
            fuelCountLabel.setEnabled(false);
            fuelcount.setEnabled(false);
            hopperLabel.setEnabled(false);
            intakeAmount.setEnabled(false);
            intakeAmountGuide.setEnabled(false);

            shooterPanel.setEnabled(false);
            shooterCount.setEnabled(false);
            shooterCountGuide.setEnabled(false);
            areOmniShoot.setEnabled(false);


            intakePanel.setEnabled(false);
            hopperAndShooterPanel.setEnabled(false);
            traversalAndDrivetrainPanel.setEnabled(false);

            ////drivetrainPanel.setEnabled(false);
            driveLabel.setEnabled(false);
            driveBox.setEnabled(false);

            traversalPanel.setEnabled(false);
            bumpBox.setEnabled(false);
            trenchBox.setEnabled(false);


        }
        //!Archetype initalization: Probbably problematic
        switch (archetype) {
            case "Custom":
                break;
            case "Kitbot":
                canClimb.setSelected(false);
                climbTierBox.setSelectedIndex(0);
                climbTypeBox.setSelectedIndex(0);
                bumperHole.setSelected(true);
                overBumperIntake.setSelected(false);
                multipleIntakes.setSelected(false);
                intakeAmount.setValue(1);
                hasIntake.setSelected(true);
                hopperBox.setSelectedIndex(0);
                fuelcount.setValue(20);
                shooterCount.setValue(1);
                areOmniShoot.setSelected(false);
                driveBox.setSelectedIndex(0);
                fullyWorkingDrive.setSelected(false);
                bumpBox.setSelected(true);
                trenchBox.setSelected(true);

                intakePanel.add(intakePanelRow2);
                bumperHole.setEnabled(false);
                multipleIntakes.setEnabled(false);
                overBumperIntake.setEnabled(false);

            break;
            case "Everybot": 
                canClimb.setSelected(true);
                climbTierBox.setSelectedIndex(0);
                climbTypeBox.setSelectedIndex(1);
                bumperHole.setSelected(true);
                overBumperIntake.setSelected(false);
                multipleIntakes.setSelected(false);
                intakeAmount.setValue(1);
                hasIntake.setSelected(true);
                hopperBox.setSelectedIndex(0);
                fuelcount.setValue(20);
                shooterCount.setValue(1);
                areOmniShoot.setSelected(false);
                driveBox.setSelectedIndex(0);
                fullyWorkingDrive.setSelected(false);
                bumpBox.setSelected(true);
                trenchBox.setSelected(true);

                intakePanel.add(intakePanelRow2);
                bumperHole.setEnabled(false);
                multipleIntakes.setEnabled(false);
                overBumperIntake.setEnabled(false);
                climbPanel.add(secondaryClimbPanel);

                break;
            case "Chassis":
                //The thing that sets this case apart is that it will also lock down most information about the robot, simaler to if (!modified)
                canClimb.setSelected(false);
                climbTierBox.setSelectedIndex(0);
                climbTypeBox.setSelectedIndex(0);
                bumperHole.setSelected(false);
                overBumperIntake.setSelected(false);
                multipleIntakes.setSelected(false);
                intakeAmount.setValue(0);
                hasIntake.setSelected(false);
                hopperBox.setSelectedIndex(2);
                fuelcount.setValue(0);
                shooterCount.setValue(0);
                areOmniShoot.setSelected(false);
                driveBox.setSelectedIndex(0);
                fullyWorkingDrive.setSelected(false);
                bumpBox.setSelected(true);
                trenchBox.setSelected(true);

                climbPanel.setEnabled(false);
                canClimb.setEnabled(false);
                climbLabel.setEnabled(false);
                climbTierBox.setEnabled(false);
                climbStyle.setEnabled(false);
                climbTypeBox.setEnabled(false);

                bumperHole.setEnabled(false);
                overBumperIntake.setEnabled(false);
                multipleIntakes.setEnabled(false);
                intakeAmount.setEnabled(false);
                intakeAmountGuide.setEnabled(false);
                hasIntake.setEnabled(false);

                hopperBox.setEnabled(false);
                hopperPanel.setEnabled(false);
                fuelCountLabel.setEnabled(false);
                fuelcount.setEnabled(false);
                hopperLabel.setEnabled(false);
                intakeAmount.setEnabled(false);
                intakeAmountGuide.setEnabled(false);

                shooterPanel.setEnabled(false);
                shooterCount.setEnabled(false);
                shooterCountGuide.setEnabled(false);
                areOmniShoot.setEnabled(false);

                intakePanel.setEnabled(false);
                hopperAndShooterPanel.setEnabled(false);
                traversalAndDrivetrainPanel.setEnabled(false);
                break; 
            case "Rev CC": 
                canClimb.setSelected(false);
                climbTierBox.setSelectedIndex(0);
                climbTypeBox.setSelectedIndex(0);
                bumperHole.setSelected(false);
                overBumperIntake.setSelected(true);
                multipleIntakes.setSelected(false);
                intakeAmount.setValue(1);
                hasIntake.setSelected(true);
                hopperBox.setSelectedIndex(0);
                fuelcount.setValue(40);
                shooterCount.setValue(1);
                areOmniShoot.setSelected(false);
                driveBox.setSelectedIndex(2);
                fullyWorkingDrive.setSelected(false);
                bumpBox.setSelected(true);
                trenchBox.setSelected(false);

                intakePanel.add(intakePanelRow2);
                bumperHole.setEnabled(false);
                multipleIntakes.setEnabled(false);
                overBumperIntake.setEnabled(false);
                break;
            case "WCP CC":
                canClimb.setSelected(true);
                climbTierBox.setSelectedIndex(0);
                climbTypeBox.setSelectedIndex(1);
                bumperHole.setSelected(false);
                overBumperIntake.setSelected(true);
                multipleIntakes.setSelected(false);
                intakeAmount.setValue(1);
                hasIntake.setSelected(true);
                hopperBox.setSelectedIndex(1);
                fuelcount.setValue(48);
                shooterCount.setValue(3);
                areOmniShoot.setSelected(false);
                driveBox.setSelectedIndex(2);
                fullyWorkingDrive.setSelected(false);
                bumpBox.setSelected(true);
                trenchBox.setSelected(false);

                intakePanel.add(intakePanelRow2);
                bumperHole.setEnabled(false);
                multipleIntakes.setEnabled(false);
                overBumperIntake.setEnabled(false);
                climbPanel.add(secondaryClimbPanel);
            default:
                break;
        }



        //! ActionListiners configured below! Likely bugs!
        //Climb
        canClimb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (canClimb.isSelected()) {
                    climbPanel.add(secondaryClimbPanel);
                    if(hasAuto.isSelected()){
                        autoPanel2.add(autoClimb);
                    }
                } else {
                    climbPanel.remove(secondaryClimbPanel);
                    if(hasAuto.isSelected()){
                        autoPanel2.remove(autoClimb);
                    }
                }
                climbPanel.repaint();
                climbPanel.revalidate();
                autoPanel2.repaint();
                autoPanel2.revalidate();
                
            }
        });
        // This is for hiding everything about the intake untill we know it exists
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
        // This is just for the fancy thing where if it has multiple intakes it'll have
        // a spinner appear
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
        //auto actionlistiner
        hasAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(hasAuto.isSelected()){
                    autoPanel2.add(hasComplexAuto);
                    autoPanel2.add(hasMultipleAutos);
                    autoPanel2.add(hasVision);
                    if(archetype.equals("WCP CC")|| archetype.equals("Kitbot")||archetype.equals("Everybot")){
                        autoPanel2.add(usesArchetypeAuto);
                    }
                    if(canClimb.isSelected()){
                        autoPanel2.add(autoClimb);
                    }
                }else{
                    autoPanel2.remove(hasComplexAuto);
                    autoPanel2.remove(hasMultipleAutos);
                    autoPanel2.remove(hasVision);
                    autoPanel2.remove(usesArchetypeAuto);
                    autoPanel2.remove(autoClimb);
                }
            autoPanel2.repaint();
            autoPanel2.revalidate();

            }
        });

        //! SAVER CODE BELOW! LIKELY BUGGY!

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = ("Pit Scouting data, Team Number:"+teamNum+"; "+teamName);
                File SaveFile = new File("src/main/java/rebuilt_scouting/Data/Data Files/PitScoutingData/"+fileName);

                try {
                    FileWriter writer = new FileWriter(SaveFile);
                    writer.append("Team Name: "+teamName);
                    writer.append("\nTeam Number: "+ teamNum);
                    writer.append("\nRobot Archetype: "+archetype);
                    writer.append("\nModified from archetype (returns true for custom robots by default): "+modified);
                    writer.append("\nCan Climb: "+(canClimb.isSelected()));
                    if(canClimb.isSelected()){
                        writer.append("\nMaximum Climb: "+ climbTierBox.getSelectedItem());
                        writer.append("\nClimber Type: "+(climbTypeBox.getSelectedItem()));
                    }
                    writer.append("\nHas Intake: "+ hasIntake.isSelected());
                    if(hasIntake.isSelected()){
                        writer.append("\nBumper hole used: "+ bumperHole.isSelected());
                        writer.append("\nOver bmper intake used: "+ overBumperIntake.isSelected());
                        writer.append("\nIntake Amount: "+ (multipleIntakes.isSelected()?intakeAmount.getValue():"1").toString());
                    }
                    writer.close();
                    System.out.println(SaveFile.exists()); 
                } catch (IOException e1) {
                    System.out.println("Error!");
                }

            }
            
        });

        
    }
    public static String niceTrueFalse(boolean Input){
        if (Input){
            return "Yes";
        }else{
            return "No";
        }
    }
    
}
