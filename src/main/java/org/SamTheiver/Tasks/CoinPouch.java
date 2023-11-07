package org.SamTheiver.Tasks;
import org.SamTheiver.SamTheiver;
import org.SamTheiver.data.Constants;
import org.SamTheiver.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

import java.util.Random;

public class CoinPouch extends Task {
    SamTheiver main;
    private Constants cons;
    Random random = new Random();

    public CoinPouch(SamTheiver main, Constants cons) {
        super();
        super.name = "CoinPouch";
        this.main = main;
        this.cons = cons;
    }

    @Override
    public boolean activate() {
        return Inventory.stream().name(cons.coinPouch).count() > (cons.maxPouches - random.nextInt(7));
    }

    @Override
    public void execute() {
        main.setTask("Looting Coin Pouches");
        if (Game.tab(Game.Tab.INVENTORY)) {
            Condition.wait(() -> Game.tab() == Game.Tab.INVENTORY, 150, 10);
            Inventory.stream().name(cons.coinPouch).first().interact("Open-all");
            Condition.wait(()-> !Inventory.stream().name(cons.coinPouch).contains(), 150, 10);
        }
    }
}

