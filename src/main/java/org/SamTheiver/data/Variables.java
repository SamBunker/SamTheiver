package org.SamTheiver.data;

import org.powbot.api.event.NpcActionEvent;

import java.util.ArrayList;

public class Variables {

    public Variables() {
        super();
    }

    public ArrayList<NpcActionEvent> npcAction;
    public NpcActionEvent npcEvent;


    public void selectedNPC(ArrayList<NpcActionEvent> nae) {
        npcAction = nae;
        npcEvent = nae.get(0);
        return;
    }


//    ArrayList<NpcActionEvent> npcAction = (getOption("npc"));
//    NpcActionEvent npcEvent = npcAction.get(0);
}
