public class TwoPlayer extends Game {
    @Override
    public void playGame() {
        Player player1 = new Player("X");
        Player player2 = new Player("O");
        Player turn = player1;

        System.out.println();

        board.printGameBoard();

        while (true) {
            int[] coordinates = getPlayerCoordinates(turn);
            board.placeMarker(turn.getMarker(), coordinates[0], coordinates[1]);
            board.printGameBoard();

            if (checkGameOver(turn.getMarker())) {
                break;
            }

            if (turn == player1) {
                turn = player2;
            }
            else {
                turn = player1;
            }
        }
    }

    @Override
    public int[] getPlayerCoordinates(Player player) {

        int[] coordinates = {-1, -1};

        while (coordinates[0] == -1 || coordinates[1] == -1 || !board.positionIsFree(coordinates)) {
            coordinates = player.getCoordinates();

            if (!board.positionIsFree(coordinates)) {
                System.out.println("This cell is occupied! Choose another one!");
            }
        }

        return coordinates;
    }
}
