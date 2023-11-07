package org.SamTheiver.Tasks;

import org.SamTheiver.SamTheiver;
import org.SamTheiver.Task;
import org.SamTheiver.data.Constants;
import org.powbot.api.rt4.*;

import java.util.Random;

public class Theiving extends Task {
    SamTheiver main;
    private final Constants cons;
    Random random = new Random();

    public Theiving(SamTheiver main, Constants cons) {
        super();
        super.name = "Theiving";
        this.main = main;
        this.cons = cons;
    }

    @Override
    public boolean activate() {
        return Inventory.stream().name(cons.coinPouch).count() < (cons.maxPouches - random.nextInt(7));
//        return Inventory.stream().name(main.itemName).isNotEmpty()
//                && Magic.Spell.HIGH_ALCHEMY.canCast();
        // return if user is within theiving area selected, and the selected npc is within viewport, if hp is > input variable, it'll take it and add a range to it
    }

    @Override
    public void execute() {
        main.setTask("Theiving");
    }
}

