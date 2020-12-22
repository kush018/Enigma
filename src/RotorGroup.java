public class RotorGroup {
    //a class for objects representing a group of Rotor objects

    //a one dimensional array representing all the rotors present in the RotorGroup object
    private final Rotor[] rotors;

    //a constructor for all RotorGroup objects
    public RotorGroup(Rotor[] rotors) {
        this.rotors = rotors;
    }

    //returns the position which forms a circuit on the leftmost end for a given position on the rightmost end
    public int getPosition(int pos) {
        for (int i = rotors.length - 1; i >= 0; i--) {
            pos = rotors[i].getWiredPosition(pos);
        }
        return pos;
    }

    public int getPositionInverse(int pos) {
        for (Rotor rotor : rotors) {
            pos = rotor.getWiredPositionInverse(pos);
        }
        return pos;
    }

    //advance the left rotor by one. if required, advance rotors to the right as well
    public void advance() {
        for (int i = rotors.length - 1; i >= 0; i--) {
            if (rotors[i].advance()) {
                continue;
            }
            break;
        }
    }

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

    public int[] getChildRotorSettings() {
        int[] settings = new int[rotors.length];
        for (int i = 0; i < rotors.length; i++) {
            settings[i] = rotors[i].getSetting();
        }
        return settings;
    }

    public void setChildRotorSettings(int[] settings) {
        for (int i = 0; i < settings.length; i++) {
            rotors[i].setSetting(settings[i]);
        }
    }
}
