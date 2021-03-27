/**
 * A class representing an enigma machine plugboard.
 *
 * @author Kushal Galrani
 */
public class PlugBoard {

    //a two dimensional array containing information about plug board wirings
    private final int[][] plugBoardWiring;

    /**
     *
     * @param connections A String array representation of the plugboard connections. Each element in this array represents a plugboard connection. Eg: If the letter A needs to be connected to F then an element "AF" should be there. Case does not matter. There may be upto 15 connections (elements)
     */
    public PlugBoard(String[] connections) {
        plugBoardWiring = new int[connections.length][2];
        int i = 0;
        for (;i < connections.length; i++) {
            connections[i] = connections[i].toUpperCase();
            plugBoardWiring[i][0] = connections[i].charAt(0) - 65;
            plugBoardWiring[i][1] = connections[i].charAt(1) - 65;
        }
    }

    /**
     * Used to find the "position" that a given position is wired to in the plugboard
     * @param pos The position whose wiring we need to find
     * @return The position that is connected to
     */
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
