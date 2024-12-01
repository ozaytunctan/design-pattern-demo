package tr.com.otunctan.dp.behavioural.chainOfResp.methodChain;

public class Creature {

    public String name;
    public int attack, defense;

    public Creature(String name, int attack, int defense) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "name='" + name + '\'' +
                ", attack=" + attack +
                ", defense=" + defense +
                '}';
    }

}

class CreatureModifier {
    protected Creature creature;
    public CreatureModifier next;

    public CreatureModifier(Creature creature) {
        this.creature = creature;
    }

    public void add(CreatureModifier cm) {
        if (next == null) {
            next = cm;
        } else next.add(cm);
    }

    public void handle() {
        if (next != null) {
            next.handle();
        }
    }
}

class DoubleAttackModifier extends CreatureModifier {

    public DoubleAttackModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("Doubling " + creature.name + " attack" + creature.attack);
        creature.attack *= 2;
        super.handle();
    }
}

class IncreaseDefenseModifier extends CreatureModifier {
    public IncreaseDefenseModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("Increasing " + creature.name + "'s defense");
        creature.defense += 3;
        super.handle();
    }
}

class NoBonusesModifier extends CreatureModifier {
    public NoBonusesModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("No bonuses for you!");
//        super.handle();
    }
}

class Demo {
    public static void main(String[] args) {
        Creature gobling = new Creature("Gobling", 2, 3);
        System.out.println(gobling);

        CreatureModifier root = new CreatureModifier(gobling);
        System.out.println("Let's doubling gobling's attack ...");
        root.add(new DoubleAttackModifier(gobling));

        System.out.println("Let's  increase gobling's defense ");
        root.add(new IncreaseDefenseModifier(gobling));

        root.add(new NoBonusesModifier(gobling));
        root.handle();

        System.out.println("Gobling Last value:" + gobling);

    }
}

