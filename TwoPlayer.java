import java.util.InputMismatchException;
import java.util.Scanner;

public class TwoPlayer extends Game {
    @Override
    public void playGame() {
        Player player1 = new Player("X");
        Player player2 = new Player("O");
        Player turn = player1;

        System.out.println();

        board.printGameBoard();

        while (true) {
            int[] coordinates = getCoordinates(turn);
            board.placeMarker(turn.getMarker(), coordinates[0], coordinates[1]);
            board.printGameBoard();

            if (checkGameOver(turn.getMarker())) {
                turn = player1;
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

        System.out.print("Enter the coordinates: ");

        Scanner scanner = new Scanner(System.in);

        int[] coordinates = {-1, -1};

        System.out.println();

        while (coordinates[0] < 1 || coordinates[0] > 3 || coordinates[1] < 1 || coordinates[1] > 3
                || !board.positionIsFree(coordinates)) {
            try {
                coordinates = new int[]{scanner.nextInt() - 1, scanner.nextInt() - 1};

                if (coordinates[0] < 1 || coordinates[0] > 3 || coordinates[1] < 1 || coordinates[1] > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (!board.positionIsFree(coordinates)) {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            }
        }

        return coordinates;
    }
}