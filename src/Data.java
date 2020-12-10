public class Data {
    /*
    Stores all machine configurations such as rotor and reflector wiring

    Each wiring configuration is stored in groups of 2D String arrays such as rotorWiring or reflectorWiring.
    Each element of these arrays is an array for Strings which represents a rotor/reflector
    The first element of each of these arrays representing a rotor is a String which has the rotor name
    The second element is also a String which represents the wiring configuration the rotor

    All wiring configurations are in the order "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    For example: The letter A would associate with the first character of the wiring B would associate with the second
    and so on...
     */

    //stores wiring of rotors
    public static final String[][] rotorWiring = {
            {"I", "EKMFLGDQVZNTOWYHXUSPAIBRCJ"},
            {"II", "AJDKSIRUXBLHWTMCQGZNPYFVOE"},
            {"III", "BDFHJLCPRTXVZNYEIWGAKMUSQO"},
            {"IV", "ESOVPZJAYQUIRHXLNFTGKDCMWB"},
            {"V", "VZBRGITYUPSDNHLXAWMJQOFECK"},
            {"VI", "JPGVOUMFYQBENHZRDKASXLICTW"},
            {"VII", "NZJHGRCXMYSWBOUFAIVLPEKQDT"},
            {"VIII", "FKQHTLXOCBJSPDZRAMEWNIUYGV"}};

    //stores wiring of reflectors
    public static final String[][] reflectorWiring = {
            {"A", "EJMZALYXVBWFCRQUONTSPIKHGD"},
            {"B", "YRUHQSLDPXNGOKMIEBFZCWVJAT"},
            {"C", "FVPJIAOYEDRZXWGCTKUQSBNMHL"},
            {"B_THIN", "ENKQAUYWJICOPBLMDXZVFTHRGS"},
            {"C_THIN", "RDOBJNTKVEHMLFCWZAXGYIPSUQ"}};

    //stores wiring of unmovable rotors
    public static final String[][] unmovableRotorWiring = {
            {"BETA", "LEYJVCNIXWPBQMDRTAKZGFUHOS"},
            {"GAMMA", "FSOKANUERHMBTIYCWLQPZXVGJD"}};
}
