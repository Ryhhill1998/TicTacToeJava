import java.util.InputMismatchException;
import java.util.Scanner;

public class Simulation extends Game {
    @Override
    public void playGame() {
        int computer1Level = getComputerLevel(1);
        int computer2Level = getComputerLevel(2);

        Computer computer1 = new Computer("X", computer1Level);
        Computer computer2 = new Computer("O", computer2Level);

        Computer turn = computer1;

        System.out.println();

        board.printGameBoard();

        while (true) {
            int[] coordinates = getCoordinates(turn);
            System.out.println("Making move level \"" + turn.getLevelDescription() + "\"");
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

    public static int getComputerLevel(int computerNumber) {
        int level = -1;

        while (level != 0 && level != 1) {
            try {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Which level computer would you like computer " + computerNumber +
                        " to be? Enter 0 for easy or 1 for medium: ");

                level = scanner.nextInt();

                if (level != 0 && level != 1) {
                    System.out.println();
                    System.out.print("Invalid entry! Please enter 0 or 1: ");
                    level = scanner.nextInt();
                }
            } catch (InputMismatchException e) {
                System.out.println("That is not a number!");
            }
        }


        System.out.println();

        return level;
    }

    @Override
    public int[] getCoordinates(Player player) {
        Computer computer = (Computer) player;
        int[] coordinates = computer.getCoordinates(board);

        while (!board.positionIsFree(coordinates)) {
            coordinates = computer.getCoordinates(board);
        }

        return coordinates;
    }
}