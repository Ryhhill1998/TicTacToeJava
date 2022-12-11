import java.util.Arrays;

public class Computer extends Player {

    private static final int EXCLUSIVE_MAX = 3;
    private static final int INCLUSIVE_MAX = 2;
    private static final int INCLUSIVE_MIN = 0;
    private static final int DEFAULT_COORDINATE = -1;
    private static final int EASY_LEVEL = 0;
    private static final int MEDIUM_LEVEL = 1;
    private static final String MARKER_X = "X";
    private static final String MARKER_O = "O";
    int level;

    public Computer(String marker, int level) {
        super(marker);
        this.level = level;
    }

    public String getLevelDescription() {
        if (level == EASY_LEVEL) {
            return "easy";
        }

        return "medium";
    }

    public int[] getCoordinates(Board board) {
        if (level == EASY_LEVEL) {
            System.out.println("EASY LEVEL");
            return getCoordinatesEasy(board);
        }

        return getCoordinatesMedium(board);
    }

    public int[] getCoordinatesEasy(Board board) {
        int[] coordinates = {DEFAULT_COORDINATE, DEFAULT_COORDINATE};

        while (coordinates[0] < INCLUSIVE_MIN || coordinates[0] > INCLUSIVE_MAX
                || coordinates[1] < INCLUSIVE_MIN || coordinates[1] > INCLUSIVE_MAX
                || !board.positionIsFree(coordinates)) {
            coordinates = new int[]{getRandomCoordinate(), getRandomCoordinate()};
        }

        return coordinates;
    }

    private static int getRandomCoordinate() {
        return (int) (Math.random() * EXCLUSIVE_MAX);
    }

    public int[] getCoordinatesMedium(Board board) {
        int[] coordinates = board.findCoordinatesToWin(getMarker());

        if (coordinates != null) {
            System.out.println(Arrays.toString(coordinates));
            return coordinates;
        }

        if (getMarker().equals(MARKER_X)) {
            coordinates = board.findCoordinatesToWin(MARKER_O);
        } else {
            coordinates = board.findCoordinatesToWin(MARKER_X);
        }

        if (coordinates != null) {
            System.out.println(Arrays.toString(coordinates));
            return coordinates;
        }

        return getCoordinatesEasy(board);
    }
}
