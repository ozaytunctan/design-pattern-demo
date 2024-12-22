package tr.com.otunctan.dp.behavioural.observer.exercise.game;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Game {
    List<Rat> rats = new ArrayList<>();


    public void join(Rat rat) {
        rats.add(rat);
        informRatSize();
    }

    public void leave(Rat rat) {
        rats.remove(rat);
        informRatSize();
    }

    private void informRatSize() {
        for (Rat ratItem : rats) {
            ratItem.informRatSize(rats.size());
        }
    }

}

class Rat implements Closeable {
    private Game game;
    public int attack = 1;

    public Rat(Game game) {
        this.game = game;
        game.join(this);
    }

    public void informRatSize(int ratSize) {
        this.attack = 1 * ratSize;
    }

    @Override
    public void close() throws IOException {
        game.leave(this);
    }
}

class Demo{

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        Rat rat1 = new Rat(game);
        Rat rat2 = new Rat(game);

        rat1.close();
    }
}