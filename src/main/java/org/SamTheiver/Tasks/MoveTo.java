package org.SamTheiver.Tasks;
import org.SamTheiver.SamTheiver;
import org.SamTheiver.Task;
import org.SamTheiver.data.Variables;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

public class MoveTo extends Task {
    SamTheiver main;
    private final Variables vars;

    public MoveTo(SamTheiver main, Variables vars) {
        super();
        super.name = "LocationCheck";
        this.main = main;
        this.vars = vars;
    }


    @Override
    public boolean activate() {
        return !Npcs.stream().name(vars.npcEvent.getStrippedName()).nearest().first().inViewport();
    }

    @Override
    public void execute() {
        main.setTask("Finding NPC");
        Npc selectedNPC = Npcs.stream().name(vars.npcEvent.getStrippedName()).nearest().first();
        Movement.moveTo(selectedNPC);
        Condition.wait(()-> Players.local().movementAnimation() != -1, 150, 10);
        if (selectedNPC.inViewport()) Camera.turnTo(selectedNPC);
    }
}

