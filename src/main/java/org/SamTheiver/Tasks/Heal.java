package org.SamTheiver.Tasks;
import org.SamTheiver.SamTheiver;
import org.SamTheiver.Task;
import org.SamTheiver.data.Variables;

public class Heal extends Task {
    SamTheiver main;
    private final Variables vars;

    public Heal(SamTheiver main, Variables vars) {
        super();
        super.name = "Heal";
        this.main = main;
        this.vars = vars;
    }


    @Override
    public boolean activate() {
        return !vars.healCheck;
    }

    @Override
    public void execute() {
        main.setTask("Restoring Health");
//        Grab the inventory variable from vars. and get the first item that you can eat or drink with. So also create
//        a function that says "if not eat, drink". Then eat that food.
    }
}

