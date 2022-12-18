import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class ComputerIO {

    public static int getComputerLevel() {
        int level = -1;

        while (level != 0 && level != 1 && level != 2) {
            try {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Which level computer would you like to play against? " +
                        "Enter 0 for easy, 1 for medium or 2 for hard: ");

                level = scanner.nextInt();

                if (level != 0 && level != 1 && level != 2) {
                    System.out.println();
                    System.out.print("Invalid entry! Please enter 0, 1 or 2: ");
                    level = scanner.nextInt();
                }
            } catch (InputMismatchException e) {
                System.out.println("That is not a number!");
            }
        }


        System.out.println();

        return level;
    }

    public static void printComputerLevel(Computer computer) {
        System.out.println("Making move level \"" + computer.getLevelDescription() + "\"");
    }
}
