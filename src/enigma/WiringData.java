package enigma;

/**
 * Class that has data related to the wiring of rotors and reflectors.
 * 
 * @author Kushal Galrani
 */
public class WiringData {

    //two dimensional String array that stores rotor wiring information
    //the first element in each array in the below array represents the rotor name
    //the second element represents the wiring
    public static String[][] rotorWiringData = new String[][] {
            {"I", "EKMFLGDQVZNTOWYHXUSPAIBRCJ"},
            {"II", "AJDKSIRUXBLHWTMCQGZNPYFVOE"},
            {"III", "BDFHJLCPRTXVZNYEIWGAKMUSQO"},
            {"IV", "ESOVPZJAYQUIRHXLNFTGKDCMWB"},
            {"V", "VZBRGITYUPSDNHLXAWMJQOFECK"},
            {"VI", "JPGVOUMFYQBENHZRDKASXLICTW"},
            {"VII", "NZJHGRCXMYSWBOUFAIVLPEKQDT"},
            {"VIII", "FKQHTLXOCBJSPDZRAMEWNIUYGV"}
    };

    //two dimensional String array that stores reflector wiring information
    public static String[][] reflectorWiringData = new String[][] {
            {"A", "EJMZALYXVBWFCRQUONTSPIKHGD"},
            {"B", "YRUHQSLDPXNGOKMIEBFZCWVJAT"},
            {"C", "FVPJIAOYEDRZXWGCTKUQSBNMHL"},
            {"B_THIN", "ENKQAUYWJICOPBLMDXZVFTHRGS"},
            {"C_THIN", "RDOBJNTKVEHMLFCWZAXGYIPSUQ"}
    };
}
