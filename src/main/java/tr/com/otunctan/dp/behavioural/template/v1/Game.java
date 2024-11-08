package tr.com.otunctan.dp.behavioural.template.v1;

public abstract class Game {

    protected int currentPlayer;
    protected final int numberOfPlayer;

    protected Game(int numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
    }

    public void run() {
        start();
        while (!haveWinner()) {
            takeTurn();
        }
        System.out.println("Player " + getWinningPlayer() + " wins");
    }

    protected abstract int getWinningPlayer();

    protected abstract void takeTurn();

    protected abstract void start();

    protected abstract boolean haveWinner();


}
