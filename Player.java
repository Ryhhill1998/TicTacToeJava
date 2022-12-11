import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
    private String marker;

    public Player(String marker) {
        this.marker = marker;
    }

    public String getMarker() {
        return marker;
    }

    public int[] getCoordinates(Board board) {
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