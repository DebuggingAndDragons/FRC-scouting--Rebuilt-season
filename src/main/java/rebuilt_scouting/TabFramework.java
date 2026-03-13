package rebuilt_scouting;

import javax.swing.*;

public abstract class TabFramework {
    public static void addMultipleComponents(JComponent baseComponent, JComponent innerComponents[]){
        int run = 0;
        while(run>=innerComponents.length){
            baseComponent.add(innerComponents[run]);
            run=run+1;
        }
    }
}
