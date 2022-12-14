import java.util.Scanner;

public class SinglePlayer extends Game {

    private String type;

    public SinglePlayer(String type) {
        super();
        this.type = type;
    }

    public SinglePlayer() {
        super();
    }

    @Override
    public void playGame() {
        String playerMarker = PlayerIO.getPlayerMarker();
        Player player = new Player(playerMarker);

        Computer computer;
        Player turn;

        int level = ComputerIO.getComputerLevel();

        if (playerMarker.equals("X")) {
            computer = new Computer("O", level);
            turn = player;
        } else {
            computer = new Computer("X", level);
            turn = computer;
        }

        System.out.println();

        board.printGameBoard();

        while (true) {
            getReasonForComputerMove(computer, player, turn, board);
            int[] coordinates = getCoordinates(turn);
            board.placeMarker(turn.getMarker(), coordinates[0], coordinates[1]);
            board.printGameBoard();

            if (checkGameOver(turn.getMarker())) {
                break;
            }

            turn = changeTurn(turn, player, computer);
        }

        turn = changeTurn(turn, player, computer);
        getReasonForComputerMove(computer, player, turn, board);
    }

    @Override
    public int[] getCoordinates(Player player) {
        int[] coordinates;

        if (player instanceof Computer) {
            Computer computer = (Computer) player;
            ComputerIO.printComputerLevel(computer);
            coordinates = computer.getCoordinates(board);
        } else {
            coordinates = PlayerIO.getCoordinates(board);
        }

        return coordinates;
    }

    public void getReasonForComputerMove(Computer computer, Player player, Player turn, Board board) {
        if (type == null) return;

        if (turn == player && !board.isEmpty()) {
            System.out.print("Enter Y to get reason for the computer's move or any other key to continue: ");
            Scanner scan = new Scanner(System.in);
            if (scan.nextLine().equalsIgnoreCase("Y")) {
                ComputerIO.displayReasonForMove(computer);
            }
        }
    }

    public Player changeTurn(Player turn, Player player, Computer computer) {
        if (turn == player) {
            return computer;
        } else {
            return player;
        }
    }
}
