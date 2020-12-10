public class Rotor {
    /*
    A class that represents an individual rotor, reflector or an immovable rotor
     */

    private int setting;
    private String type;

    public Rotor(String type, int setting) {
        //constructor for Rotor objects
        this.setting = setting;
        this.type = type;
    }

    public int getSetting() {
        return this.setting;
    }

    public char getLetter(char letter) {
        return '\u0000';
    }
}
