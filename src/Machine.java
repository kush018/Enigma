/**
 * This class represents an Enigma machine
 *
 * @author Kushal Galrani
 */
public class Machine {
    private final PlugBoard plugBoard;
    private final Reflector reflector;
    private final RotorGroup rotorGroup;

    /**
     *
     * @param plugBoard A PlugBoard object that will be the plugboard of this Enigma machine that helps in message encryption
     * @param reflector A Reflector object that will be used as a reflector of this Enigma machine that helps in message encryption
     * @param rotorGroup A RotorGroup object that will be used as a "collection of rotors" for this enigma machine and help in message encryption
     */
    public Machine(PlugBoard plugBoard, Reflector reflector, RotorGroup rotorGroup) {
        this.plugBoard = plugBoard;
        this.reflector = reflector;
        this.rotorGroup = rotorGroup;
    }

    /**
     * A method that allows one to create a machine very easily using String representation of all the required Enigma machine components.
     * This is useful if one wants the user to enter rotor names, reflector name, plugboard settings etc, and it will create an Enigma machine object automatically
     * @param reflectorName The name of the reflector that will be used. The name must be there in the WiringData class
     * @param rotorNames The names of the rotors to be used by the machine. The name must be there in the WiringData class
     * @param settings The settings of the rotors of the Enigma machine. The number of settings given must be same as number of rotors and must be such an order such that each setting is for to the name of the corresponding rotor given in the rotorNames array
     * @param plugBoardWiring The String representation of the plug board
     * @return The created Enigma machine object
     */
    public static Machine getMachineBySetting(String reflectorName, String[] rotorNames, int[] settings, String[] plugBoardWiring) {
        return new Machine(new PlugBoard(plugBoardWiring), Reflector.getReflectorByName(reflectorName), RotorGroup.getRotorGroupByNames(rotorNames, settings));
    }

    /**
     * Takes an UPPERCASE character as an input and returns the encrypted/decrypted character in UPPERCASE
     * @param ch The UPPERCASE character that will be encrypted/decrypted
     * @return The UPPERCASE character that has been encrypted/decrypted
     */
    public char getChar(char ch) {
        int pos = ch - 65;
        pos = plugBoard.findConnection(pos);
        pos = rotorGroup.getPosition(pos);
        pos = reflector.getPosition(pos);
        pos = rotorGroup.getPositionInverse(pos);
        pos = plugBoard.findConnection(pos);
        return (char) (pos + 65);
    }

    /**
     * Takes a message and encrypts/decrypts it. Here, case does not matter as everything is converted to uppercase. Even special characters can be used, however, they will be left as-it-is. They will neither be encrypted not decrypted.
     * @param message The message to be encrypted/decrypted
     * @return The encrypted/decrypted message
     */
    public String convertMessage(String message) {
        StringBuilder convertedMessage = new StringBuilder();
        message = message.toUpperCase();
        for (int i = 0; i < message.length(); i++) {
            if (('A' <= message.charAt(i) && message.charAt(i) <= 'Z')) {
                convertedMessage.append(getChar(message.charAt(i)));
                advance();
            } else {
                convertedMessage.append(message.charAt(i));
            }
        }
        return convertedMessage.toString();
    }

    /**
     * Converts a single String into array, by splitting it using the space character. It gets rid of any special characters and makes everything uppercase.
     * This is useful to convert a String entered by a user into a RotorGroup or a plugboard where everything needs to be uppercase and special characters typed by mistake should be ignored.
     * @param str The String to be converted.
     * @return The resultant array.
     */
    public static String[] convertStringToArray(String str) {
        //converts the given string to an array of substrings based on certain predetermined rules
        //gets rid of special characters
        str = str.toUpperCase();
        for (int i = 0; i < str.length(); i++) {
            if (!(('A' <= str.charAt(i) && str.charAt(i) <= 'Z') || str.charAt(i) == ' ')) {
                str = str.substring(0, i) + str.substring(i + 1);
                i = -1;
            }
        }
        //create array
        String[] array = str.split(" ");
        //clean array (remove all empty elements)
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals("")) {
                //the element is empty and redundant and therefore needs to be removed

                String temp = array[array.length - 1]; //temp is the last element
                array[array.length - 1] = array[i]; //last element becomes i
                array[i] = temp; //i is same as temp which is same as last element

                String[] newArray = new String[array.length - 1];
                for (int j = 0; j < newArray.length; j++) {
                    newArray[j] = array[j];
                }

                array = newArray;
                i = -1;
            }
        }
        return array;
    }

    /**
     * Advances the machine's rotors by one setting
     */
    public void advance() {
        rotorGroup.advance();
    }

    /**
     *
     * @return The settings of all the rotors in the form of a String
     */
    public String getRotorSettingsStr() {
        int[] settings = rotorGroup.getChildRotorSettings();
        StringBuilder settingsStr = new StringBuilder();
        for (int i = 0; i < settings.length; i++) {
            settingsStr.append(settings[i]).append(" ");
        }
        return settingsStr.toString();
    }

    /**
     * Sets the rotor settings
     * @param settingsStr The new settings of the rotors in the form of a Strings
     */
    public void setRotorSettings(String settingsStr) {
        String[] settingsStrArr = settingsStr.split(" ");
        int[] newSettings = new int[settingsStrArr.length];

        for (int i = 0; i < settingsStrArr.length; i++) {
            newSettings[i] = Integer.parseInt(settingsStrArr[i]);
        }

        rotorGroup.setChildRotorSettings(newSettings);
    }
}
