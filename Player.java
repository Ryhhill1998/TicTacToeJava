import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
    String marker;

    public Player(String marker) {
        this.marker = marker;
    }

    public String getMarker() {
        return marker;
    }

    public int[] getCoordinates() {
        System.out.print("Enter the coordinates: ");

        Scanner scanner = new Scanner(System.in);

        int row = -1;
        int col = -1;

        System.out.println();

        while (row < 0 || row > 2 || col < 0 || col > 2) {
            try {
                row = scanner.nextInt() - 1;
                col = scanner.nextInt() - 1;

                if (row < 0 || row > 2 || col < 0 || col > 2) {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            }
        }

        return new int[] {row, col};
    }
}
