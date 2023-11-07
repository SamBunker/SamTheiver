package org.SamTheiver.data;

import org.powbot.api.event.NpcActionEvent;

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
    public int randomCoinPouch;


    public void selectedNPC(ArrayList<NpcActionEvent> nae) {
        npcAction = nae;
        npcEvent = nae.get(0);
        return;
    }

    public void randomLootPouch(Integer i) {
        randomCoinPouch = (cons.maxPouches - random.nextInt(7));
        return;
    }


//    ArrayList<NpcActionEvent> npcAction = (getOption("npc"));
//    NpcActionEvent npcEvent = npcAction.get(0);
}
