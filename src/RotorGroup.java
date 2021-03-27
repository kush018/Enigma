/**
 * A class representing a group of rotor objects
 *
 * @author Kushal Galrani
 */
public class RotorGroup {

    //a one dimensional array representing all the rotors present in the RotorGroup object
    private final Rotor[] rotors;

    /**
     *
     * @param rotors An array of Rotor objects that need to be a part of this RotorGroup
     */
    public RotorGroup(Rotor[] rotors) {
        this.rotors = rotors;
    }

    /**
     * Returns the position which forms a circuit on the leftmost end for a given position on the rightmost end
     * @param pos given position on the rightmost end
     * @return the position which forms a circuit on the leftmost end for a given position on the rightmost end
     */
    public int getPosition(int pos) {
        for (int i = rotors.length - 1; i >= 0; i--) {
            pos = rotors[i].getWiredPosition(pos);
        }
        return pos;
    }

    /**
     * Returns the position which forms a circuit on the rightmost end for a given position on the leftmost end.
     * @param pos given position on the leftmost end
     * @return the position which forms a circuit on the rightmost end for a given position on the leftmost end.
     */
    public int getPositionInverse(int pos) {
        for (Rotor rotor : rotors) {
            pos = rotor.getWiredPositionInverse(pos);
        }
        return pos;
    }

    /**
     * advance the left rotor by one. if required, advance rotors to the right as well
     */
    public void advance() {
        for (int i = rotors.length - 1; i >= 0; i--) {
            if (rotors[i].advance()) {
                continue;
            }
            break;
        }
    }

    /**
     * Useful for creating a RotorGroup object, given the names of each rotor and their corresponding settings (these rotor names must be available in WiringInfo class)
     * @param rotorNames The list of names of the required rotors.
     * @param rotorSettings The initial settings for each of the required rotors.
     * @return The newly prepared RotorGroup object.
     */
    public static RotorGroup getRotorGroupByNames(String[] rotorNames, int[] rotorSettings) {
        Rotor[] rotors = new Rotor[rotorNames.length];
        for (int i = 0; i < rotorNames.length; i++) {
            String currentRotorWiring = "";
            for (int j = 0; j < WiringData.rotorWiringData.length; j++) {
                if (WiringData.rotorWiringData[j][0].equals(rotorNames[i])) {
                    currentRotorWiring = WiringData.rotorWiringData[j][1];
                }
            }
            rotors[i] = new Rotor(rotorSettings[i], currentRotorWiring);
        }
        return new RotorGroup(rotors);
    }

    /**
     * Returns array of settings of all the rotors in this RotorGroup object.
     * @return the array of settings of all the rotors in this RotorGroup object.
     */
    public int[] getChildRotorSettings() {
        int[] settings = new int[rotors.length];
        for (int i = 0; i < rotors.length; i++) {
            settings[i] = rotors[i].getSetting();
        }
        return settings;
    }

    /**
     * Sets the settings of all the rotors in this RotorGroup object.
     * @param settings the array of new settings for all the rotors in this RotorGroup object.
     */
    public void setChildRotorSettings(int[] settings) {
        for (int i = 0; i < settings.length; i++) {
            rotors[i].setSetting(settings[i]);
        }
    }
}
