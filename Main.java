import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean quit = false;

        while (!quit) {
            printMenu();
            quit = getSelection();
        }
    }

    public static void printMenu() {
        System.out.println("Select the option you would like to play from the list below:" +
                "\n\ta - Two player" +
                "\n\tb - Single player" +
                "\n\tc - Simulation" +
                "\n\tq - Quit\n");
    }

    public static boolean getSelection() {
        Game game;

        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine().toLowerCase();

        switch (option) {
            case "a":
                // two player game
                game = new TwoPlayer();
                break;
            case "b":
                // single player game
                game = new SinglePlayer();
                break;
            case "c":
                // simulation game
                game = new Simulation();
                break;
            case "q":
                // quit
                return true;
            default:
                System.out.println("Invalid option!");
                return false;
        }

        game.playGame();
        return false;
    }
}
