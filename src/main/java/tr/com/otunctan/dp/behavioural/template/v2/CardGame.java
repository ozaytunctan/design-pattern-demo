package tr.com.otunctan.dp.behavioural.template.v2;

public abstract class CardGame {
    public Creature[] creatures;

    public CardGame(Creature[] creatures) {
        this.creatures = creatures;
    }

    // returns -1 if no clear winner (both alive or both dead)
    public int combat(int creature1, int creature2) {
        Creature first = creatures[creature1];
        Creature second = creatures[creature2];
        hit(first, second);
        hit(second, first);
        // todo: determine who won and return either creature1 or creature2
        return 0;
    }

    // attacker hits other creature
    protected abstract void hit(Creature attacker, Creature other);
}
