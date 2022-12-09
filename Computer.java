public class Computer extends Player
{
    int level;

    public Computer(String marker, int level)
    {
        super(marker);
        this.level = level;
    }

    public String getLevelDescription() {
        if (level == 0) {
            return "easy";
        }

        return "medium";
    }

    public int[] getCoordinates(Board board) {
        if (level == 0) {
            return getCoordinatesEasy();
        }

        if (level == 1) {
            int[] coordinates = getCoordinatesMedium(marker, board);
            if (coordinates != null) {
                return coordinates;
            }

            if (marker.equals("X")) {
                coordinates = getCoordinatesMedium("O", board);
            } else {
                coordinates = getCoordinatesMedium("X", board);
            }

            if (coordinates != null) {
                return coordinates;
            }

            return getCoordinatesEasy();
        }

        return null;
    }

    public int[] getCoordinatesEasy() {
        int row = (int) (Math.random() * 3);
        int col = (int) (Math.random() * 3);

        return new int[]{row, col};
    }

    public int[] getCoordinatesMedium(String marker, Board board) {
        String[][] gameBoard = board.getGameBoard();

        // check rows for 2 of marker
        for (int i = 0; i < gameBoard.length; i++) {
            int[] freeSpace = null;
            int count = 0;

            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j].equals(marker)) {
                    count++;
                } else {
                    int[] space = {i, j};
                    if (board.positionIsFree(space)) {
                        freeSpace = space;
                    }
                }
            }

            if (count == 2) {
                return freeSpace;
            }
        }

        // check cols for 2 of marker
        for (int i = 0; i < gameBoard.length; i++) {
            int[] freeSpace = null;
            int count = 0;

            for (int j = 0; j < gameBoard[i].length; j++) {
                int[] space = {j, i};
                if (board.positionIsFree(space)) {
                    freeSpace = space;
                }
                if (gameBoard[j][i].equals(marker)) {
                    count++;
                }
            }

            if (count == 2) {
                return freeSpace;
            }
        }

        // check diag for 2 of marker
        int[] freeSpace = null;
        int count = 0;

        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[i][i].equals(marker)) {
                count++;
            } else {
                int[] space = {i, i};

                if (board.positionIsFree(space)) {
                    freeSpace = space;
                }
            }
        }

        if (count == 2) {
            return freeSpace;
        }

        count = 0;

        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[i][gameBoard.length - 1 - i].equals(marker)) {
                count++;
            } else {
                int[] space = {i, gameBoard.length - 1 - i};

                if (board.positionIsFree(space)) {
                    freeSpace = space;
                }
            }
        }

        if (count == 2) {
            return freeSpace;
        }

        return null;
    }
}
