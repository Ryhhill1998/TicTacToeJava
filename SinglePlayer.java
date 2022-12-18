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
            int[] coordinates = getCoordinates(turn);
            board.placeMarker(turn.getMarker(), coordinates[0], coordinates[1]);
            board.printGameBoard();

            if (checkGameOver(turn.getMarker())) {
                break;
            }

            turn = changeTurn(turn, player, computer);
        }

        turn = changeTurn(turn, player, computer);
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

    public Player changeTurn(Player turn, Player player, Computer computer) {
        if (turn == player) {
            return computer;
        } else {
            return player;
        }
    }
}
