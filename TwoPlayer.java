public class TwoPlayer extends Game {
    @Override
    public void playGame() {
        Player player1 = new Player("X");
        Player player2 = new Player("O");
        Player turn = player1;

        board.printGameBoard();

        while (true) {
            int[] coordinates = getCoordinates(turn);
            board.placeMarker(turn.getMarker(), coordinates[0], coordinates[1]);
            board.printGameBoard();

            if (checkGameOver(turn.getMarker())) {
                break;
            }

            if (turn == player1) {
                turn = player2;
            } else {
                turn = player1;
            }
        }
    }

    @Override
    public int[] getCoordinates(Player player) {
        return PlayerIO.getCoordinates(board);
    }
}