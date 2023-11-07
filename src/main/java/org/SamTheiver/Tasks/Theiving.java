package org.SamTheiver.Tasks;

import com.google.errorprone.annotations.Var;
import org.SamTheiver.SamTheiver;
import org.SamTheiver.Task;
import org.SamTheiver.data.Constants;
import org.SamTheiver.data.Variables;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

import java.util.Random;

public class Theiving extends Task {
    SamTheiver main;
    private final Constants cons;
    private final Variables vars;

    public Theiving(SamTheiver main, Constants cons, Variables vars) {
        super();
        super.name = "Theiving";
        this.main = main;
        this.cons = cons;
        this.vars = vars;
    }


    @Override
    public boolean activate() {
        return Inventory.stream().name(cons.coinPouch).count() < vars.randomCoinPouch
                && Npcs.stream().name(vars.npcEvent.getStrippedName()).nearest().first().inViewport()
                && vars.healCheck;
        // return if user is within theiving area selected, and the selected npc is within viewport, if hp is > input variable, it'll take it and add a range to it
    }

    @Override
    public void execute() {
        main.setTask("Thieving");
        Npc selectedNPC = Npcs.stream().name(vars.npcEvent.getStrippedName()).nearest().first();
        if (selectedNPC.inViewport()) {
            selectedNPC.interact("Pickpocket");
            Condition.wait(()-> Players.local().animation() != -1, 300, 10);
        }
    }
}

