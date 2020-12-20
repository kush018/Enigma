import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /*
    The main class
     */
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter rotor names: ");
        String rotorNames = reader.readLine();
        System.out.print("Enter initial rotor settings: ");
        String rotorSettingsStr = reader.readLine();
        String[] rotorSettingsArrStr = rotorSettingsStr.split(" ");
        int[] rotorSettingsArrInt = new int[rotorSettingsArrStr.length];
        for (int i = 0; i < rotorSettingsArrInt.length; i++) {
            rotorSettingsArrInt[i] = Integer.parseInt(rotorSettingsArrStr[i]);
        }
        RotorGroup rotorGroup = getRotorGroupByNames(rotorNames.split(" "), rotorSettingsArrInt);
        System.out.print("Enter reflector name: ");
        String reflectorName = reader.readLine();
        Reflector reflector = getReflectorByName(reflectorName);
        System.out.print("Enter plug board settings: ");
        String plugBoardSettings = reader.readLine();
        PlugBoard plugBoard = getPlugBoard(plugBoardSettings.split(" "));
        Machine machine = new Machine(plugBoard, reflector, rotorGroup);
        System.out.print("Enter message: ");
        String message = reader.readLine();
        StringBuilder cipher = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            cipher.append(machine.getChar(message.charAt(i)));
            machine.advance();
        }
        System.out.println("Cipher: " + cipher);
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

    public static boolean checkValidityRotorNames(String[] rotorNames) {
        String[] validRotorNames = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII"};
        for (int i = 0; i < rotorNames.length; i++) {
            for (int j = 0; j < validRotorNames.length; j++) {
                if (validRotorNames[j].equals(rotorNames[i])) {
                    return true;
                }
            }
        }
        return true;
    }

    public static Reflector getReflectorByName(String name) {
        for (int i = 0; i < WiringData.reflectorWiringData.length; i++) {
            if (WiringData.reflectorWiringData[i][0].equals(name)) {
                return new Reflector(WiringData.reflectorWiringData[i][1]);
            }
        }
        return new Reflector("");
    }

    public static PlugBoard getPlugBoard(String[] plugBoardWiring) {
        return new PlugBoard(plugBoardWiring);
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
}
