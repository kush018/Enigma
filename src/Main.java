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
        PlugBoard plugBoard = getPlugBoard(plugBoardSettings);
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

    public static Reflector getReflectorByName(String name) {
        for (int i = 0; i < WiringData.reflectorWiringData.length; i++) {
            if (WiringData.reflectorWiringData[i][0].equals(name)) {
                return new Reflector(WiringData.reflectorWiringData[i][1]);
            }
        }
        return new Reflector("");
    }

    public static PlugBoard getPlugBoard(String plugBoardWiring) {
        return new PlugBoard(plugBoardWiring.split(" "));
    }
}
