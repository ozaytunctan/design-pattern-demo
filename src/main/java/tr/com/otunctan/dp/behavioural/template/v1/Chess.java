package tr.com.otunctan.dp.behavioural.template.v1;

public class Chess extends Game {

    private int maxTurns = 10;
    private int turn = 1;

    protected Chess() {
        super(2);
    }

    @Override
    protected int getWinningPlayer() {
        return currentPlayer;
    }

    @Override
    protected void takeTurn() {
        System.out.println("Turn " + (turn++) + " taken by player " + currentPlayer);
        currentPlayer = (currentPlayer + 1) % numberOfPlayer;
    }

    @Override
    protected void start() {
        System.out.println("Starting a game of chess ");
    }

    @Override
    protected boolean haveWinner() {
        return turn == maxTurns;
    }
}
