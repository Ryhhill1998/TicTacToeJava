import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        String[][] board = getBoardState();
        printBoard(board);
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

        stringBoard.append("---------\n");

        return stringBoard.toString();
    }

    public static String[][] getBoardState()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the initial state of the board " +
                "- use X and O to represent marked positions and _ to represent an empty space:");

        String[] board = scanner.nextLine().split("");

        while (!boardIsValid(board))
        {
            System.out.println("Invalid board! Please enter 9 symbols separated by a space. " +
                    "Please only use 'X', 'O' or '_' to represent a position on the board:");
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

        System.out.println("Enter coordinates to place your marker " +
                "(enter the row 1-3 and column 1-3, separated by a space:");

        int row = getIntegerInput();
        int col = getIntegerInput();

        while (!positionIsFree(board, row, col))
        {
            System.out.println("That space is occupied! Choose another one!");
            row = getIntegerInput();
            col = getIntegerInput();
        }

        return new int[] {row, col};
    }

    public static boolean positionIsFree(String[][] board, int row, int col)
    {
        return board[row][col].equals("_");
    }

    public static int getIntegerInput()
    {
        Scanner scanner = new Scanner(System.in);

        while (!scanner.hasNextInt())
            System.out.println("Invalid input! Please enter a number in the range 1-3:");

        int value = scanner.nextInt();

        while (value > 3 || value < 0)
        {
            System.out.println("Invalid input! Please enter a number in the range 1-3:");
            value = scanner.nextInt();
        }

        return scanner.nextInt() - 1;
    }

    public static String getMarker(String[][] board)
    {
        int countX = 0;
        int countY = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals("X"))
                    countX++;
                else if (board[i][j].equals("Y"))
                    countY++;
            }
        }

        return countX == countY ? "X" : "O";
    }
}
