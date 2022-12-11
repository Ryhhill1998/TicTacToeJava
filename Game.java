public abstract class Game {
    public Board board;

    public Game() {
        board = new Board();
    }

    public abstract void playGame();

    public abstract int[] getCoordinates(Player player);

    public boolean checkGameOver(String marker) {
        if (board.markerHasWon(marker)) {
            System.out.println(marker + " wins");
            return true;
        }

        if (board.boardIsFull()) {
            System.out.println("Draw");
            return true;
        }

        return false;
    }
}
