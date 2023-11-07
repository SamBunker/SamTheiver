package org.SamTheiver.Tasks;
import org.SamTheiver.SamTheiver;
import org.SamTheiver.data.Constants;
import org.SamTheiver.Task;
import org.SamTheiver.data.Variables;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

public class CoinPouch extends Task {
    SamTheiver main;
    private final Constants cons;
    private final Variables vars;

    public CoinPouch(SamTheiver main, Constants cons, Variables vars) {
        super();
        super.name = "CoinPouch";
        this.main = main;
        this.cons = cons;
        this.vars = vars;
    }

    @Override
    public boolean activate() {
        return Inventory.stream().name(cons.COIN_POUCH).count() > vars.randomCoinPouch;
    }

    @Override
    public void execute() {
        main.setTask("Looting Coin Pouches");
        Inventory.stream().name(cons.COIN_POUCH).first().interact("Open-all");
        Condition.wait(()-> Inventory.stream().name(cons.COIN_POUCH).isEmpty(), 150, 30);
    }
}

