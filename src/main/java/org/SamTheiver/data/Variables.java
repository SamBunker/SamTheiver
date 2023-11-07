package org.SamTheiver.data;
import org.powbot.api.event.NpcActionEvent;
import org.powbot.api.rt4.Players;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

public class Variables {

    public Variables() {
        super();
    }

    private Constants cons;
    Random random = new Random();
    public ArrayList<NpcActionEvent> npcAction;
    public LinkedHashMap<String, String> inv;
    public NpcActionEvent npcEvent;
    public int randomCoinPouch = (cons.MAX_POUCHES - random.nextInt(7));
    public int healConfiguration;
    public boolean healCheck = Players.local().healthPercent() > (healConfiguration - random.nextInt(7));

    public void selectedNPC(ArrayList<NpcActionEvent> nae) {
        npcAction = nae;
        npcEvent = nae.get(0);
        return;
    }

    public boolean inventoryCheck(LinkedHashMap inventory) {
        if (inventory.keySet().isEmpty()) {
            return false;
        } else {
            inv = inventory;
            return true;
        }
    }
}
