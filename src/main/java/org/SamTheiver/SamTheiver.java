package org.SamTheiver;
import org.SamTheiver.Tasks.CoinPouch;
import org.SamTheiver.Tasks.Heal;
import org.SamTheiver.Tasks.MoveTo;
import org.SamTheiver.Tasks.Theiving;
import org.SamTheiver.data.Constants;
import org.SamTheiver.data.Variables;
import org.powbot.api.Notifications;
import org.powbot.api.event.NpcActionEvent;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.*;
import org.powbot.api.script.paint.Paint;
import org.powbot.api.script.paint.PaintBuilder;
import org.powbot.mobile.script.ScriptManager;
import org.powbot.mobile.service.ScriptUploader;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@ScriptConfiguration(
        name = "npc",
        description = "Pickpocket the NPC you want to automate.",
        optionType = OptionType.NPC_ACTIONS
)
@ScriptConfiguration(
        name = "inventory",
        description = "Record your inventory layout.",
        optionType = OptionType.INVENTORY
//        Allowed Values -> Which item is food in your inventory?
)
@ScriptConfiguration(
        name = "healing",
        description = "Around what health do you want to stay above?",
        optionType = OptionType.INTEGER
)

@ScriptManifest(name = "SamTheiver", description = "An enhanced theiving script for PowBot.", author="Sam", version = "1", category = ScriptCategory.Magic)
public class SamTheiver extends AbstractScript {
    private Constants cons = new Constants();
    private Variables vars = new Variables();
    private ArrayList<Task> taskList = new ArrayList<Task>();
    private String task;

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public void onStart() {
        vars.healConfiguration = getOption("healing");
        ArrayList<NpcActionEvent> npcAction = (getOption("npc"));
        LinkedHashMap<String, String> inv = (getOption("inventory"));
//        Check to see if inventory is valid, then send it off to variables in a void function to return

        if (!vars.inventoryCheck(getOption("inventory"))) {
            Notifications.showNotification("By not selecting your inventory, you will not eat when your hp is low.");
        }

        if (npcAction.isEmpty()) {
            Notifications.showNotification("You did not select an NPC!");
        } else {
            if (npcAction.size() > 1) {
                Notifications.showNotification("You selected too many NPCs!");
            } else {
                vars.selectedNPC(getOption("npc"));
                if (vars.npcEvent.getInteraction().toLowerCase().contains("pickpocket")) {
                    pBuilder();
                    Notifications.showNotification(vars.npcEvent.getStrippedName() + ", " + vars.npcEvent.getInteraction());
                    taskList.add(new Theiving(this, cons, vars));
                    taskList.add(new CoinPouch(this, cons, vars));
                    taskList.add(new MoveTo(this, vars));
                    taskList.add(new Heal(this, vars));
                } else {
                    Notifications.showNotification("Interaction Selected: "+vars.npcEvent.getInteraction()+". You must Pickpocket them!");
                }
            }
        }
    }

    @Override
    public void poll() {
        for (Task task : taskList) {
            if (task.activate()) {
                task.execute();
                if (ScriptManager.INSTANCE.isStopping()) {
                    Notifications.showNotification("Script Stopping!");
                    break;
                }
            }
        }
    }

    private void pBuilder() {
        Paint p = new PaintBuilder()
                .trackSkill(Skill.Thieving)
                .addString(()->"Current Task: "+task)
                .x(30)
                .y(65)
                .build();
        addPaint(p);
    }

    public static void main(String[] args) {
        new ScriptUploader().uploadAndStart("SamTheiver", "", "R52T90A6VCM", true, false);
    }
}