package rebuilt_scouting.Data.Writers;

import java.io.FileWriter;
import java.io.IOException;

public class PitScoutSaver {
    public PitScoutSaver(String teamName,int teamNum,String archetype,boolean modified,Boolean bump,Boolean trench,Boolean l1Endgame,
        Boolean l2endgame, Boolean l3endgame,boolean autoclimb,Boolean hasAuto,boolean depot,int shooterCount,boolean shooterAreOmni,
        short hopperType,int hopperCapacity,boolean hasIntake,boolean bumperHole,boolean overBumperIntake, int intakeAmount,String drivername, 
        boolean driverHasPractice, boolean driverIsVet,String CodriverName, boolean codriverHasPractice,boolean vetCodriver,String DriveCoachName, 
        boolean driveCoachIsMentor,boolean vetCoach) throws IOException{
            FileWriter writer = new FileWriter(((String)(teamName+":"+teamNum+"Pit Scouting data")));
            //TODO: Acual saving stuff
    }
}
