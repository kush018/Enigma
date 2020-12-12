public class RotorGroup {
    //a class for objects representing a group of Rotor objects

    //a one dimensional array representing all the rotors present in the RotorGroup object
    private Rotor[] rotors;

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
        for (int i = 0; i < rotors.length; i++) {
            pos = rotors[i].getWiredPositionInverse(pos);
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
}
