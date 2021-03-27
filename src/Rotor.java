/**
 * A class that represents a rotor in an enigma machine.
 *
 * @author Kushal Galrani
 */
public class Rotor {

    //one dimensional int array that contains data about the wiring of the pins of the rotor for its "zero" setting
    //the wiring is in sequential order i.e. the first int in the array tells which pin the first pin is wired to
    private final int[] wiring;

    //int that stores the "setting" of the Rotor object (between 0 and 25)
    private int setting;

    /**
     *
     * @param setting The intial setting of the rotor
     * @param wiringInfo The information regarding the wiring of the rotor in String representation. See WiringData class's code to understand how this String representation works
     */
    public Rotor(int setting, String wiringInfo) {
        if (0 <= setting && setting <= 25) {
            this.setting = setting;
        } else {
            this.setting = 0;
        }
        //initialises the wiring array
        wiring = new int[26];
        //wiring info consists of all positions in the form of a compact String
        //based on that information, we will form the int array wiring
        for (int i = 0; i < 26; i++) {
            wiring[i] = wiringInfo.charAt(i) - 65;
        }
    }

    /**
     * Method that advances the rotor's setting by one.
     * Must be called after each character gets encrypted/decrypted by the machine
     * @return true if the rotor to it's left needs to be advanced as well
     */
    public boolean advance() {
        setting++;
        if (setting == 26) {
            setting = 0;
            return true;
        }
        return false;
    }

    /**
     * Returns which position pos is wired to
     * @param pos The position whose wiring needs to be found
     * @return The position that it is wired to.
     */
    public int getWiredPosition(int pos) {
        int wiredPos = wiring[(pos + setting) % 26] - setting;
        if (wiredPos < 0) {
            return 26 + wiredPos;
        } else {
            return wiredPos;
        }
    }

    /**
     * Is an "inverse function" of getWiredPosition
     * @param pos The position whose wiring needs to be found.
     * @return The position such that if it is used as an argument to getWiredPosition(), then it returns pos
     */
    public int getWiredPositionInverse(int pos) {
        for (int i = 0; i < 26; i++) {
            if (getWiredPosition(i) == pos) {
                return i;
            }
        }
        return 0;
    }

    /**
     *
     * @return the current setting of the rotor
     */
    public int getSetting() {
        return this.setting;
    }

    /**
     * Changes the setting of the rotor.
     * @param setting The new setting
     */
    public void setSetting(int setting) {
        if (0 <= setting && setting <= 25) {
            this.setting = setting;
        } else {
            this.setting = 0;
        }
    }
}
