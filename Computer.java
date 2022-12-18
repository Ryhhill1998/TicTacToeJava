public class Computer extends Player {

    private static final int EXCLUSIVE_MAX = 3;
    private static final int INCLUSIVE_MAX = 2;
    private static final int INCLUSIVE_MIN = 0;
    private static final int DEFAULT_COORDINATE = -1;
    private static final int EASY_LEVEL = 0;
    private static final int MEDIUM_LEVEL = 1;
    private static final int HARD_LEVEL = 2;
    private static final String MARKER_X = "X";
    private static final String MARKER_O = "O";
    private int level;
    private String playerMarker;
    private static final int infinity = (int) Double.POSITIVE_INFINITY;

    public Computer(String marker, int level) {
        super(marker);
        playerMarker = getMarker().equals("X") ? "O" : "X";
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

        if (level == HARD_LEVEL) {
            return getCoordinatesHard(board);
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
            return coordinates;
        }

        if (getMarker().equals(MARKER_X)) {
            coordinates = board.findCoordinatesToWin(MARKER_O);
        } else {
            coordinates = board.findCoordinatesToWin(MARKER_X);
        }

        if (coordinates != null) {
            return coordinates;
        }

        return getCoordinatesEasy(board);
    }

    public int[] getCoordinatesHard(Board board) {
        int[] bestMove = new int[2];
        int bestMoveVal = -infinity;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int[] coordinates = {i, j};
                if (!board.positionIsFree(coordinates)) {
                    continue;
                }

                board.placeMarker(getMarker(), coordinates[0], coordinates[1]);
                int val = findBestMove(board, getMarker(), false, 0, -infinity, infinity);
                board.placeMarker("_", coordinates[0], coordinates[1]);

                if (val > bestMoveVal) {
                    bestMoveVal = val;
                    bestMove[0] = coordinates[0];
                    bestMove[1] = coordinates[1];
                }
            }
        }

        return bestMove;
    }

    private int findBestMove(Board board, String marker, boolean maximising, int numberOfMoves, int alpha, int beta) {
        if (maximising) {
            if (board.markerHasWon(marker)) {
                return -10 + numberOfMoves;
            }
        } else {
            if (board.markerHasWon(marker)) {
                return 10 - numberOfMoves;
            }
        }

        if (board.boardIsFull()) {
            return 0;
        }

        String currentMarker = maximising ? getMarker() : playerMarker;

        if (maximising) {
            int maxEval = -infinity;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int[] position = {i, j};
                    if (!board.positionIsFree(position)) {
                        continue;
                    }

                    board.placeMarker(currentMarker, position[0], position[1]);
                    int eval = findBestMove(board, currentMarker, false, numberOfMoves + 1, alpha, beta);
                    board.placeMarker("_", position[0], position[1]);

                    maxEval = Math.max(maxEval, eval);
                    alpha = Math.max(alpha, eval);

                    if (beta <= alpha) {
                        return maxEval;
                    }
                }
            }

            return maxEval;
        } else {
            int minEval = infinity;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int[] position = {i, j};
                    if (!board.positionIsFree(position)) {
                        continue;
                    }

                    board.placeMarker(currentMarker, position[0], position[1]);
                    int eval = findBestMove(board, currentMarker, true, numberOfMoves + 1, alpha, beta);
                    board.placeMarker("_", position[0], position[1]);

                    minEval = Math.min(minEval, eval);
                    beta = Math.min(beta, eval);

                    if (beta <= alpha) {
                        return minEval;
                    }
                }
            }

            return minEval;
        }
    }
}
