public class PlugBoard {
    //a class representing PlugBoards

    //a two dimensional array containing information about plug board wirings
    private final int[][] plugBoardWiring;

    //constructor
    public PlugBoard(String[] connections) {
        plugBoardWiring = new int[connections.length][2];
        int i = 0;
        for (;i < connections.length; i++) {
            connections[i] = connections[i].toUpperCase();
            plugBoardWiring[i][0] = connections[i].charAt(0) - 65;
            plugBoardWiring[i][1] = connections[i].charAt(1) - 65;
        }
    }

    public int findConnection(int pos) {
        for (int[] ints : plugBoardWiring) {
            if (ints[0] == pos) {
                return ints[1];
            } else if (ints[1] == pos) {
                return ints[0];
            }
        }
        return pos;
    }
}
