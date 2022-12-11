import java.util.InputMismatchException;
import java.util.Scanner;

public class SinglePlayer extends Game {
    @Override
    public void playGame() {
        String playerMarker = getPlayerMarker();
        Player player = new Player(playerMarker);

        Computer computer;
        Player turn;

        int level = getComputerLevel();

        if (playerMarker.equals("X")) {
            computer = new Computer("O", level);
            turn = player;
        } else {
            computer = new Computer("X", level);
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

        return player.getCoordinates(board);
    }
}
