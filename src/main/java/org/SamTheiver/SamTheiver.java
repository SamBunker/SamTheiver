package org.SamTheiver;
import org.SamTheiver.Tasks.Theiving;
import org.powbot.api.Notifications;
import org.powbot.api.event.NpcActionEvent;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.*;
import org.powbot.api.script.paint.Paint;
import org.powbot.api.script.paint.PaintBuilder;
import org.powbot.mobile.script.ScriptManager;
import org.powbot.mobile.service.ScriptUploader;
import java.util.ArrayList;

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
        description = "What health do you want to stay above?",
        optionType = OptionType.INTEGER
)

@ScriptManifest(name = "SamTheiver", description = "An enhanced theiving script for PowBot.", author="Sam", version = "1", category = ScriptCategory.Magic)
public class SamTheiver extends AbstractScript {
//    private Constants c = new Constants();
    private ArrayList<Task> taskList = new ArrayList<Task>();
    private String task;

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public void onStart() {
        ArrayList<NpcActionEvent> npcAction = (getOption("npc"));
        NpcActionEvent npcEvent = npcAction.get(0);

        if (npcAction.isEmpty()) {
            Notifications.showNotification("You did not select an NPC!");
        } else {
            if (npcAction.size() > 1) {
                Notifications.showNotification("You selected too many NPCs!");
            } else {
                if (npcEvent.getInteraction().toLowerCase().contains("pickpocket")) {
                    pBuilder();
                    Notifications.showNotification(npcEvent.getStrippedName() + ", " + npcEvent.getInteraction());
                    taskList.add(new Theiving(this));
                } else {
                    Notifications.showNotification("Interaction Selected: "+npcEvent.getInteraction()+". You must Pickpocket them!");
//                    Kill the script
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
            } else {
                ScriptManager.INSTANCE.stop();
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