public class Machine {
    /*
    A class that represents an Enigma machine
     */

    private final PlugBoard plugBoard;
    private final Reflector reflector;
    private final RotorGroup rotorGroup;

    public Machine(PlugBoard plugBoard, Reflector reflector, RotorGroup rotorGroup) {
        this.plugBoard = plugBoard;
        this.reflector = reflector;
        this.rotorGroup = rotorGroup;
    }

    public static Machine getMachineBySetting(String reflectorName, String[] rotorNames, int[] settings, String[] plugBoardWiring) {
        return new Machine(new PlugBoard(plugBoardWiring), Reflector.getReflectorByName(reflectorName), RotorGroup.getRotorGroupByNames(rotorNames, settings));
    }

    public char getChar(char ch) {
        int pos = ch - 65;
        pos = plugBoard.findConnection(pos);
        pos = rotorGroup.getPosition(pos);
        pos = reflector.getPosition(pos);
        pos = rotorGroup.getPositionInverse(pos);
        pos = plugBoard.findConnection(pos);
        return (char) (pos + 65);
    }

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

    public void advance() {
        rotorGroup.advance();
    }

    public String getRotorSettingsStr() {
        int[] settings = rotorGroup.getChildRotorSettings();
        StringBuilder settingsStr = new StringBuilder();
        for (int i = 0; i < settings.length; i++) {
            settingsStr.append(settings[i]).append(" ");
        }
        return settingsStr.toString();
    }
}
