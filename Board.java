public class Board
{
    String[][] gameBoard;

    public Board()
    {
        initialiseGameBoard();
    }

    public void initialiseGameBoard()
    {
        gameBoard = new String[][]{
                {"_", "_", "_"},
                {"_", "_", "_"},
                {"_", "_", "_"}
        };
    }

    public String[][] getGameBoard()
    {
        return gameBoard;
    }

    public void printGameBoard()
    {
        System.out.println(stringifyBoard());
    }

    public String stringifyBoard()
    {
        StringBuilder stringBoard = new StringBuilder();
        stringBoard.append("---------\n");

        for (int i = 0; i < gameBoard.length; i++)
        {
            stringBoard.append("|");
            for (int j = 0; j < gameBoard[i].length; j++) {
                stringBoard.append(" ");
                String symbol = gameBoard[i][j];

                if (symbol.equals("_"))
                    stringBoard.append(" ");
                else
                    stringBoard.append(symbol);
            }
            stringBoard.append(" |\n");
        }

        stringBoard.append("---------");

        return stringBoard.toString();
    }

    public boolean positionIsFree(int[] coordinates)
    {
        return gameBoard[coordinates[0]][coordinates[1]].equals("_");
    }

    public void placeMarker(String marker, int row, int col)
    {
        gameBoard[row][col] = marker;
    }

    public boolean boardIsFull()
    {
        for (int i = 0; i < gameBoard.length; i++)
        {
            for (int j = 0; j < gameBoard[i].length; j++)
                if (gameBoard[i][j].equals("_"))
                    return false;
        }
        return true;
    }

    public boolean markerHasWon(String marker)
    {
        // check rows
        for (int i = 0; i < gameBoard.length; i++)
        {
            int count = 0;

            for (int j = 0; j < gameBoard[i].length; j++)
                if (gameBoard[i][j].equals(marker))
                    count++;

            if (count == 3)
                return true;
        }

        // check columns
        for (int i = 0; i < gameBoard.length; i++)
        {
            int count = 0;

            for (int j = 0; j < gameBoard[i].length; j++)
                if (gameBoard[j][i].equals(marker))
                    count++;

            if (count == 3)
                return true;
        }

        // check diagonals
        int diag1Count = 0;
        int diag2Count = 0;

        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[i][i].equals(marker))
                diag1Count++;

            if (gameBoard[i][gameBoard.length - 1 - i].equals(marker))
                diag2Count++;
        }

        return diag1Count == 3 || diag2Count == 3;
    }
}