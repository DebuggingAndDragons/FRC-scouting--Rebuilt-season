package rebuilt_scouting;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class TeamViewTab {
    public TeamViewTab(){
        Path configPath = Paths.get("src/main/java/rebuilt_scouting/Data Saved/Config");
        if (!Files.exists(configPath)){
            File configFile = new File(configPath.toString());
            
        }
    }
}
