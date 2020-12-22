import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /*
    The main class
     */

    private static Machine machine;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        machine = Machine.getMachineBySetting("C", new String[] {"I", "VIII", "III", "IV"}, new int[] {7, 11, 15, 21}, new String[] {"AU", "BY", "CO", "DS", "EQ", "FX", "GT", "HW", "KP", "MV"});
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
            } else {
                System.out.println(machine.convertMessage(userInput));
            }
        }
    }

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
        System.out.println("Machine set successfully");
    }

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

    public static boolean checkValidityRotorName(String rotorName) {
        String[] validRotorNames = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII"};

        for (int i = 0; i < validRotorNames.length; i++) {
            if (validRotorNames[i].equals(rotorName)) {
                return true;
            }
        }

        return false;
    }

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
