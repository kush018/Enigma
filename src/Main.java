import enigma.Machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The main class
 *
 * @author Kushal Galrani
 */
public class Main {

    private static Machine machine;

    /**
     * The main method
     * @param args Command-line arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        machine = Machine.getMachineBySetting("C", new String[] {"I", "VIII", "III", "IV"}, new int[] {7, 11, 15, 21}, new String[] {"AU", "BY", "CO", "DS", "EQ", "FX", "GT", "HW", "KP", "MV"});
        System.out.println("Enter \"help;\" to see the help menu");
        while (true) {
            System.out.print("> ");
            String userInput = reader.readLine();
            if (userInput.equals("set;")) {
                set();
            } else if (userInput.equals("show;")) {
                System.out.println("Current rotor settings are:");
                System.out.println(machine.getRotorSettingsStr());
            } else if (userInput.equals("exit;")) {
                System.out.println("Byeee!");
                System.exit(0);
            } else if (userInput.equals("settingset;")) {
                settingset();
            } else if (userInput.equals("help;")) {
                displayHelp();
            } else {
                System.out.println(machine.convertMessage(userInput));
            }
        }
    }

    /**
     * Displays the help menu on the console
     */
    public static void displayHelp() {
        System.out.println("Valid commands are:");
        System.out.println("show; - shows the current settings of the rotors");
        System.out.println("sets; - reconfigures the machine settings completely");
        System.out.println("settingset; - reconfigures the rotor settings only");
        System.out.println("help; - shows this help menu");
        System.out.println("exit; - exits the program");
        System.out.println("NOTE: when whatever you type into the prompt is none of the above commands, the program simply encrypts whatever you have typed and prints out the ciphertext");
        System.out.println("The default settings of the machine are:");
        System.out.println("enigma.Reflector: C");
        System.out.println("Rotors: I VIII III IV");
        System.out.println("enigma.Rotor settings: 7 11 15 21");
        System.out.println("Plugboard settings are: AU BY CO DS EQ FX GT HW KP MV");
        System.out.println("NOTE: Valid rotor settings are between 0 and 25 ONLY");
        System.out.println("Valid rotor names are I, II, III, IV, V, VI, VII, VIII ONLY");
        System.out.println("Valid reflector names are A, B, C, B_THIN, C_THIN ONLY");
    }

    /**
     * Sets the settings of the rotors of the Enigma machine based on user input
     * @throws IOException
     */
    public static void settingset() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter new rotor settings:");
        String settingsStr = reader.readLine();

        machine.setRotorSettings(settingsStr);
    }

    /**
     * Sets all the machine settings of the Enigma machine based on user input
     * @throws IOException
     */
    public static void set() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter reflector name: ");
        String reflectorName = reader.readLine();
        if (!(checkValidityReflectorName(reflectorName))) {
            System.out.println("Invalid reflector name");
            return;
        }

        System.out.print("Enter rotor names: ");
        String rotorNames = reader.readLine();
        if (!(checkValidityRotorNames(Machine.convertStringToArray(rotorNames)))) {
            System.out.println("Invalid rotor names entered");
        }

        System.out.print("Enter rotor settings: ");
        String rotorSettings = reader.readLine();

        System.out.print("Enter plugboard settings: ");
        String plugBoardSettings = reader.readLine();
        if (plugBoardSettings.equals("")) {
            System.out.println("Plugboard is going to be empty.");
            plugBoardSettings = "AA";
        }

        String[] settingsArr = rotorSettings.split(" ");
        int[] settingsArrInt = new int[settingsArr.length];
        for (int i = 0; i < settingsArr.length; i++) {
            settingsArrInt[i] = Integer.parseInt(settingsArr[i]);
        }

        machine = Machine.getMachineBySetting(reflectorName, Machine.convertStringToArray(rotorNames), settingsArrInt, Machine.convertStringToArray(plugBoardSettings));
        System.out.println("enigma.Machine set successfully");
    }

    /**
     * Checks the validity of a number of rotor names
     * The validity of each rotor name is checked using the function checkValidityRotorName()
     * @param rotorNames The String array containing all the rotor names whose validity needs to be checked
     * @return true if and only if all the rotor names are valid
     */
    public static boolean checkValidityRotorNames(String[] rotorNames) {
        for (int i = 0; i < rotorNames.length; i++) {
            if (!(checkValidityRotorName(rotorNames[i]))) {
                //if rotor name is not valid
                return false;
            }
        }
        //if all rotors passed the validity test
        return true;
    }

    /**
     * Checks the validity of a rotor name (a rotor name is valid if there is an entry of that rotor name in the enigma.WiringData class)
     * @param rotorName The name of the rotor whose validity needs to be checked.
     * @return true if and only if the name of the rotor is valid.
     */
    public static boolean checkValidityRotorName(String rotorName) {
        String[] validRotorNames = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII"};

        for (int i = 0; i < validRotorNames.length; i++) {
            if (validRotorNames[i].equals(rotorName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks the validity of a reflector name (a reflector name is valid if there is an entry of that reflector name in the enigma.WiringData class)
     * @param reflectorName The name of the reflector whose validity needs to be checked.
     * @return true if and only if the name of the reflector is valid.
     */
    public static boolean checkValidityReflectorName(String reflectorName) {
        String[] validReflectorNames = {"A", "B", "C", "B_THIN", "C_THIN"};

        for (int i = 0; i < validReflectorNames.length; i++) {
            if (validReflectorNames[i].equals(reflectorName)) {
                return true;
            }
        }

        return false;
    }
}
