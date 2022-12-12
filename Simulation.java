public class Simulation extends Game {
    @Override
    public void playGame() {
        int computer1Level = ComputerIO.getComputerLevel();
        int computer2Level = ComputerIO.getComputerLevel();

        Computer computer1 = new Computer("X", computer1Level);
        Computer computer2 = new Computer("O", computer2Level);

        Computer turn = computer1;

        System.out.println();
        board.printGameBoard();

        while (true) {
            int[] coordinates = getCoordinates(turn);

            ComputerIO.printComputerLevel(turn);
            board.placeMarker(turn.getMarker(), coordinates[0], coordinates[1]);
            board.printGameBoard();

            if (checkGameOver(turn.getMarker())) {
                break;
            }

            if (turn == computer1) {
                turn = computer2;
            } else {
                turn = computer1;
            }
        }
    }

    @Override
    public int[] getCoordinates(Player player) {
        return ((Computer) player).getCoordinates(board);
    }
}