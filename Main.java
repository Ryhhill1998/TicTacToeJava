import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Board board = new Board();

        System.out.print("Enter the marker that you would like to play as (X/O): ");

        String playerMarker, computerMarker;

        Scanner scanner = new Scanner(System.in);

        if (scanner.nextLine().toUpperCase().equals("X"))
        {
            playerMarker = "X";
            computerMarker = "O";
        }
        else
        {
            playerMarker = "O";
            computerMarker = "X";
        }

        Player player = new Player(playerMarker);
        Computer computer = new Computer(computerMarker);

        Player turn;

        if (playerMarker.equals("X"))
            turn = player;
        else
            turn = computer;

        board.printGameBoard();

        while (true)
        {
            int[] coordinates = getPlayerCoordinates(board, turn);
            board.placeMarker(turn.getMarker(), coordinates[0], coordinates[1]);
            board.printGameBoard();

            if (checkGameOver(board, turn.getMarker()))
                break;

            if (turn == player)
                turn = computer;
            else
                turn = player;
        }
    }

    public static int[] getPlayerCoordinates(Board board, Player player)
    {

        int[] coordinates = {-1, -1};

        boolean playerIsComputer = false;

        if (player instanceof Computer)
        {
            System.out.println("Making move level \"easy\"");
            playerIsComputer = true;
        }

        while (coordinates[0] == -1 || coordinates[1] == -1 || !board.positionIsFree(coordinates))
        {
            if (playerIsComputer)
            {
                Computer computer = (Computer) player;
                coordinates = computer.getCoordinates();
            }
            else
            {
                coordinates = player.getCoordinates();

                if (!board.positionIsFree(coordinates))
                    System.out.println("This cell is occupied! Choose another one!");
            }
        }

        return coordinates;
    }

    public static boolean checkGameOver(Board board, String marker)
    {
        if (board.markerHasWon(marker))
        {
            System.out.println(marker + " wins");
            return true;
        }

        if (board.boardIsFull())
        {
            System.out.println("Draw");
            return true;
        }

        return false;
    }
}
