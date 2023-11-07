package org.SamTheiver.data;

import org.powbot.api.event.NpcActionEvent;
import org.powbot.api.rt4.Players;

import java.util.ArrayList;
import java.util.Random;

public class Variables {

    public Variables() {
        super();
    }

    private Constants cons;
    Random random = new Random();
    public ArrayList<NpcActionEvent> npcAction;
    public NpcActionEvent npcEvent;
    public int randomCoinPouch = (cons.maxPouches - random.nextInt(7));
    public boolean healCheck = Players.local().healthPercent() > (50 - random.nextInt(7));


    public void selectedNPC(ArrayList<NpcActionEvent> nae) {
        npcAction = nae;
        npcEvent = nae.get(0);
        return;
    }


//    ArrayList<NpcActionEvent> npcAction = (getOption("npc"));
//    NpcActionEvent npcEvent = npcAction.get(0);
}
