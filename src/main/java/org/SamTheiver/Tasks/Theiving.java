package org.SamTheiver.Tasks;

import org.SamTheiver.SamTheiver;
import org.SamTheiver.Task;
import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.*;

public class Theiving extends Task {
    SamTheiver main;
    public String task = "";

    public Theiving(SamTheiver main) {
        super();
        super.name = "Theiving";
        this.main = main;
    }

    @Override
    public boolean activate() {
        return Game.loggedIn();
//        return Inventory.stream().name(main.itemName).isNotEmpty()
//                && Magic.Spell.HIGH_ALCHEMY.canCast();
        // return if user is within theiving area selected, and the selected npc is within viewport, if hp is > input variable, it'll take it and add a range to it
    }

    @Override
    public void execute() {
        main.setTask("Theiving");
    }
}

