public class Reflector {
    //a class for all reflector objects

    //one dimensional int array that stores the positions the pins are wired to in sequential order
    private int[] wiring;

    //constructor
    public Reflector(String wiringInfo) {
        //initialises the wiring array
        wiring = new int[26];
        //wiring info consists of all positions in the form of a compact String
        //based on that information, we will form the int array wiring
        for (int i = 0; i < 26; i++) {
            wiring[i] = wiringInfo.charAt(i) - 65;
        }
    }

    //finds wired position of given pin
    public int getPosition(int pos) {
        return wiring[pos];
    }
}
