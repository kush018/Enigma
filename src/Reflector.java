public class Reflector {
    //a class for all reflector objects

    //one dimensional int array that stores the positions the pins are wired to in sequential order
    private int[] wiring;

    //finds wired position of given pin
    public int getPosition(int pos) {
        return wiring[pos];
    }
}
