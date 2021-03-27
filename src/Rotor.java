public class Rotor {
    /*
    A class that represents a rotor in an enigma machine
     */

    //one dimensional int array that contains data about the wiring of the pins of the rotor for its "zero" setting
    //the wiring is in sequential order i.e. the first int in the array tells which pin the first pin is wired to
    private final int[] wiring;

    //int that stores the "setting" of the Rotor object (between 0 and 25)
    private int setting;

    //constructor for all Rotor objects
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

    //method that advances the rotor's setting by one.
    //must be called after each character gets translated by the machine
    //returns - true - if the rotor to it's left needs to be advanced as well
    //false - if the rotor to it's left need not be advanced by one step
    public boolean advance() {
        setting++;
        if (setting == 26) {
            setting = 0;
            return true;
        }
        return false;
    }

    //returns which position pos is wired to
    public int getWiredPosition(int pos) {
        int wiredPos = wiring[(pos + setting) % 26] - setting;
        if (wiredPos < 0) {
            return 26 + wiredPos;
        } else {
            return wiredPos;
        }
    }

    //returns a position such that if that position is used as an argument
    //for the method getWiredPosition(int pos) then it will return the argument entered
    public int getWiredPositionInverse(int pos) {
        for (int i = 0; i < 26; i++) {
            if (getWiredPosition(i) == pos) {
                return i;
            }
        }
        return 0;
    }

    public int getSetting() {
        return this.setting;
    }

    public void setSetting(int setting) {
        if (0 <= setting && setting <= 25) {
            this.setting = setting;
        } else {
            this.setting = 0;
        }
    }
}
