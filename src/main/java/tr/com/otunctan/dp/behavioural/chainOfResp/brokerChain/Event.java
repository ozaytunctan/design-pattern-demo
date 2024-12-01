package tr.com.otunctan.dp.behavioural.chainOfResp.brokerChain;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

//CQS
public class Event<T> {
    private Map<Integer, Consumer<T>>
            handlers = new HashMap<>();
    private int index = 0;

    public int subscribe(Consumer<T> handler) {
        int i = index;
        handlers.put(index++, handler);
        return i;
    }

    public void unsubscribe(int key) {
        handlers.remove(key);
    }

    public void fire(T args) {
        for (Consumer<T> handler : handlers.values()) {
            handler.accept(args);
        }
    }
}


class Query {

    public String creatureName;

    enum Argument {
        ATTACK, DEFENSE;
    }

    public Argument argument;
    public int result;

    public Query(String creatureName, Argument argument, int result) {
        this.creatureName = creatureName;
        this.argument = argument;
        this.result = result;
    }
}

class Game {
    public Event<Query> queries = new Event<>();

}

class Creature {
    private Game game;
    protected String name;

    public int baseAttack, baseDefense;

    public Creature(Game game, String name, int baseAttack, int baseDefense) {
        this.game = game;
        this.name = name;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
    }

    int getAttack() {
        Query q = new Query(name, Query.Argument.ATTACK, baseAttack);
        game.queries.fire(q);
        return q.result;
    }

    int getDefense() {
        Query q = new Query(name, Query.Argument.DEFENSE, baseDefense);
        game.queries.fire(q);
        return q.result;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "  name='" + name + '\'' +
                ", attack=" + getAttack() +
                ", defence=" + getDefense() +
                '}';
    }
}

class CreatureModifier {
    protected Game game;
    private Creature creature;

    public CreatureModifier(Game game, Creature creature) {
        this.game = game;
        this.creature = creature;
    }
}

class DoubleAttackModifier extends CreatureModifier implements AutoCloseable {

    private final int token;

    public DoubleAttackModifier(Game game, Creature creature) {
        super(game, creature);
        token = game.queries.subscribe(q -> {
                    if (q.creatureName.equals(creature.name) &&
                            q.argument == Query.Argument.ATTACK) {
                        q.result *= 2;
                    }
                }
        );
    }


    @Override
    public void close() throws Exception {
        game.queries.unsubscribe(token);
    }

}

class IncreaseModifier extends CreatureModifier implements AutoCloseable {

    private final int token;

    public IncreaseModifier(Game game, Creature creature) {
        super(game, creature);

        token = game.queries.subscribe(q -> {
                    if (q.creatureName.equals(creature.name) &&
                            q.argument == Query.Argument.DEFENSE) {
                        q.result += 3;
                    }
                }
        );
    }

    @Override
    public void close() throws Exception {
        game.queries.unsubscribe(token);
    }
}

class Demo {
    public static void main(String[] args) {

        Game game = new Game();
        Creature goblin = new Creature(game, "Strong Goblin", 2, 2);
        System.out.println(goblin);
        IncreaseModifier icm = new IncreaseModifier(game, goblin);
        DoubleAttackModifier dam = new DoubleAttackModifier(game, goblin);
//        try (icm) {
        try (dam) {
            System.out.println(goblin);
        } catch (Exception e) {
        }

        System.out.println(goblin);

    }
}






