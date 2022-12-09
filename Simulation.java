public class Simulation extends Game {

    @Override
    public void playGame() {
        Computer computer1 = new Computer("X");
        Computer computer2 = new Computer("O");

        Computer turn = computer1;

        System.out.println();

        board.printGameBoard();

        while (true) {
            int[] coordinates = getPlayerCoordinates(turn);
            board.placeMarker(turn.getMarker(), coordinates[0], coordinates[1]);
            board.printGameBoard();

            if (checkGameOver(turn.getMarker())) {
                break;
            }

            if (turn == computer1) {
                turn = computer2;
            }
            else {
                turn = computer1;
            }
        }
    }

    @Override
    public int[] getPlayerCoordinates(Player player) {
        Computer computer = (Computer) player;
        int[] coordinates = computer.getCoordinates();

        while (!board.positionIsFree(coordinates)) {
            coordinates = computer.getCoordinates();
        }

        return coordinates;
    }
}
