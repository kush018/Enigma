package enigma;

/**
 * A class representing a enigma.Reflector in an Enigma machine
 * @author Kushal Galrani
 */
public class Reflector {
    //a class for all reflector objects

    //one dimensional int array that stores the positions the pins are wired to in sequential order
    private final int[] wiring;

    /**
     *
     * @param wiringInfo String representation of the wiring of the rotor. See enigma.WiringData class's code to understand how the String representation works
     */
    public Reflector(String wiringInfo) {
        //initialises the wiring array
        wiring = new int[26];
        //wiring info consists of all positions in the form of a compact String
        //based on that information, we will form the int array wiring
        for (int i = 0; i < 26; i++) {
            wiring[i] = wiringInfo.charAt(i) - 65;
        }
    }

    /**
     * Finds the position wired to another position.
     * @param pos The position whose wiring is to be found
     * @return The position that is wired
     */
    public int getPosition(int pos) {
        return wiring[pos];
    }

    /**
     * Creates a enigma.Reflector object, given the name of the enigma.Rotor. (the name must be present in the WiringInfo class
     * @param name The name of the reflector as present in the WiringInfo class
     * @return The newly created enigma.Reflector object
     */
    public static Reflector getReflectorByName(String name) {
        for (int i = 0; i < WiringData.reflectorWiringData.length; i++) {
            if (WiringData.reflectorWiringData[i][0].equals(name)) {
                return new Reflector(WiringData.reflectorWiringData[i][1]);
            }
        }
        return new Reflector("");
    }
}
