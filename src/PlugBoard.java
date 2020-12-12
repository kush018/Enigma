public class PlugBoard {
    //a class representing PlugBoards

    //a two dimensional array containing information about plug board wirings
    private int[][] plugBoardWiring;

    //constructor
    public PlugBoard(String[] connections) {
        plugBoardWiring = new int[13][2];
        int i = 0;
        for (;i < connections.length; i++) {
            plugBoardWiring[i][0] = connections[i].charAt(0) - 65;
            plugBoardWiring[i][1] = connections[i].charAt(1) - 65;
        }
        for (; i < plugBoardWiring.length; i++) {
            plugBoardWiring[i] = new int[] {-1, -1};
        }
    }

    public int findConnection(int pos) {
        for (int i = 0; i < plugBoardWiring.length; i++) {
            if (plugBoardWiring[i][0] == pos) {
                return plugBoardWiring[i][1];
            } else if (plugBoardWiring[i][1] == pos) {
                return plugBoardWiring[i][0];
            }
        }
        return pos;
    }
}
