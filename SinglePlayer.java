import java.util.Scanner;

public class SinglePlayer extends Game {

    @Override
    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        String playerMarker = "";

        while (!playerMarker.equals("X") && !playerMarker.equals("O")) {
            System.out.print("Enter the marker that you would like to play as (X/O): ");
            playerMarker = scanner.nextLine().toUpperCase();
        }

        Player player = new Player(playerMarker);
        Computer computer;
        Player turn;

        if (playerMarker.equals("X")) {
            computer = new Computer("O");
            turn = player;
        }
        else {
            computer = new Computer("X");
            turn = computer;
        }

        System.out.println();

        board.printGameBoard();

        while (true) {
            int[] coordinates = getPlayerCoordinates(turn);
            board.placeMarker(turn.getMarker(), coordinates[0], coordinates[1]);
            board.printGameBoard();

            if (checkGameOver(turn.getMarker())) {
                break;
            }

            if (turn == player) {
                turn = computer;
            }
            else {
                turn = player;
            }
        }
    }

    @Override
    public int[] getPlayerCoordinates(Player player) {

        int[] coordinates = {-1, -1};

        boolean playerIsComputer = false;

        if (player instanceof Computer) {
            System.out.println("Making move level \"easy\"");
            playerIsComputer = true;
        }

        while (coordinates[0] == -1 || coordinates[1] == -1 || !board.positionIsFree(coordinates)) {
            if (playerIsComputer) {
                Computer computer = (Computer) player;
                coordinates = computer.getCoordinates();
            }
            else {
                coordinates = player.getCoordinates();

                if (!board.positionIsFree(coordinates)) {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            }
        }

        return coordinates;
    }
}
