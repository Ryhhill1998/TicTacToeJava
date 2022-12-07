import java.util.InputMismatchException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        String[][] board = getBoardState();
        printBoard(board);
        String marker = getMarker(board);
        int[] position = getCoordinates(board);
        placeMarker(board, marker, position[0], position[1]);
        printBoard(board);
        System.out.println(getResult(board, marker));
    }

    public static void printBoard(String[][] board)
    {
        System.out.println(stringifyBoard(board));
    }

    public static String stringifyBoard(String[][] board)
    {
        StringBuilder stringBoard = new StringBuilder();
        stringBoard.append("---------\n");

        for (int i = 0; i < board.length; i++)
        {
            stringBoard.append("|");
            for (int j = 0; j < board[i].length; j++) {
                stringBoard.append(" ");
                String symbol = board[i][j];

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

    public static String[][] getBoardState()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the cells:");

        String[] board = scanner.nextLine().split("");

        while (!boardIsValid(board))
        {
            System.out.println("Invalid board!");
            board = scanner.nextLine().split(" ");
        }

        return formatBoard(board);
    }

    public static String[][] formatBoard(String[] board)
    {
        String[][] formattedBoard = new String[3][3];

        for (int i = 0; i < board.length; i++)
        {
            int row = i / 3;
            int col = i % 3;
            formattedBoard[row][col] = board[i];
        }

        return formattedBoard;
    }

    public static boolean boardIsValid(String[] board)
    {
        if (board.length != 9)
            return false;

        for (String symbol : board)
            if (!symbol.equals("X") && !symbol.equals("O") && !symbol.equals("_"))
                return false;

        return true;
    }

    public static int[] getCoordinates(String[][] board)
    {

        System.out.println("Enter the coordinates:");

        Scanner scanner = new Scanner(System.in);

        int row = -1;
        int col = -1;

        while (row < 0 || row > 2 || col < 0 || col > 2 || !positionIsFree(board, row, col))
        {
            try
            {
                row = scanner.nextInt() - 1;
                col = scanner.nextInt() - 1;

                if (row < 0 || row > 2 || col < 0 || col > 2)
                {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (!positionIsFree(board, row, col))
                    System.out.println("This cell is occupied! Choose another one!");
            }
            catch (InputMismatchException e)
            {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            }
        }

        return new int[] {row, col};
    }

    public static boolean positionIsFree(String[][] board, int row, int col)
    {
        return board[row][col].equals("_");
    }

    public static String getMarker(String[][] board)
    {
        int countX = 0;
        int countO = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals("X"))
                    countX++;
                else if (board[i][j].equals("O"))
                    countO++;
            }
        }

        return countX == countO ? "X" : "O";
    }

    public static void placeMarker(String[][] board, String marker, int row, int col)
    {
        board[row][col] = marker;
    }

    public static boolean boardIsFull(String[][] board)
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
                if (board[i][j].equals("_"))
                    return false;
        }
        return true;
    }

    public static boolean markerHasWon(String[][] board, String marker)
    {
        // check rows
        for (int i = 0; i < board.length; i++)
        {
            int count = 0;

            for (int j = 0; j < board[i].length; j++)
                if (board[i][j].equals(marker))
                    count++;

            if (count == 3)
                return true;
        }

        // check columns
        for (int i = 0; i < board.length; i++)
        {
            int count = 0;

            for (int j = 0; j < board[i].length; j++)
                if (board[j][i].equals(marker))
                    count++;

            if (count == 3)
                return true;
        }

        // check diagonals
        int diag1Count = 0;
        int diag2Count = 0;

        for (int i = 0; i < board.length; i++) {
            if (board[i][i].equals(marker))
                diag1Count++;

            if (board[i][board.length - 1 - i].equals(marker))
                diag2Count++;
        }

        return diag1Count == 3 || diag2Count == 3;
    }

    public static String getResult(String[][] board, String marker)
    {
        if (markerHasWon(board, marker))
            return marker + " wins";

        if (boardIsFull(board))
            return "Draw";

        return "Game not finished";
    }
}
