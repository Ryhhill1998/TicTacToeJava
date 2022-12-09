import java.util.InputMismatchException;
import java.util.Scanner;

public class SinglePlayer extends Game {
    @Override
    public void playGame() {
        String playerMarker = getPlayerMarker();
        Player player = new Player(playerMarker);

        String computerMarker = (playerMarker.equals("X") ? "O" : "X");
        int level = getComputerLevel();
        Computer computer = new Computer(computerMarker, level);

        Player turn;

        if (playerMarker.equals("X")) {
            turn = player;
        } else {
            turn = computer;
        }

        System.out.println();

        board.printGameBoard();

        while (true) {
            int[] coordinates = getCoordinates(turn);
            board.placeMarker(turn.getMarker(), coordinates[0], coordinates[1]);
            board.printGameBoard();

            if (checkGameOver(turn.getMarker()))
                break;

            if (turn == player) {
                turn = computer;
            } else {
                turn = player;
            }
        }
    }

    public static String getPlayerMarker() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Would you like to play as X or O? ");

        String marker = scanner.nextLine().toUpperCase();

        while (!marker.equals("X") && !marker.equals("O")) {
            System.out.println();
            System.out.print("Invalid entry! Please enter X or O: ");
            marker = scanner.nextLine().toUpperCase();
        }

        System.out.println();

        return marker;
    }

    public static int getComputerLevel() {
        int level = -1;

        while (level != 0 && level != 1) {
            try {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Which level computer would you like to play against? " +
                        "Enter 0 for easy or 1 for medium: ");

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
        if (player instanceof Computer) {
            System.out.println("Making move level \"" + ((Computer) player).getLevelDescription() + "\"");
            Computer computer = (Computer) player;
            return computer.getCoordinates(board);
        }

        return getPlayerCoordinates();
    }

    public int[] getPlayerCoordinates() {
        System.out.print("Enter the coordinates: ");

        Scanner scanner = new Scanner(System.in);

        int[] coordinates = {-1, -1};

        System.out.println();

        while (coordinates[0] < 0 || coordinates[0] > 2 || coordinates[1] < 0 || coordinates[1] > 2
                || !board.positionIsFree(coordinates)) {
            try {
                coordinates = new int[]{scanner.nextInt() - 1, scanner.nextInt() - 1};

                if (coordinates[0] < 0 || coordinates[0] > 2 || coordinates[1] < 0 || coordinates[1] > 2) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (!board.positionIsFree(coordinates)) {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            }
        }

        return coordinates;
    }
}
