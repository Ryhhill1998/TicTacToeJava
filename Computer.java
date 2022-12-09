public class Computer extends Player {

    public Computer(String marker) {
        super(marker);
    }

    @Override
    public int[] getCoordinates() {
        int row = (int) (Math.random() * 3);
        int col = (int) (Math.random() * 3);

        return new int[]{row, col};
    }
}
