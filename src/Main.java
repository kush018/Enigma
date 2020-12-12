public class Main {
    /*
    The main class
     */

    public static void main(String[] args) {
        Rotor rotor1 = new Rotor(12, "EKMFLGDQVZNTOWYHXUSPAIBRCJ");
        Rotor rotor2 = new Rotor(23, "AJDKSIRUXBLHWTMCQGZNPYFVOE");
        Rotor rotor3 = new Rotor(9, "BDFHJLCPRTXVZNYEIWGAKMUSQO");
        RotorGroup group = new RotorGroup(new Rotor[] {rotor1, rotor2, rotor3});
        Reflector reflector = new Reflector("ZYXWVUTSRQPONMLKJIHGFEDCBA");
        PlugBoard plugBoard = new PlugBoard(new String[] {"AB", "CD", "EF", "GH", "IJ", "KL", "MN", "OP", "QR", "ST"});
        Machine machine = new Machine(plugBoard, reflector, group);
        String message = "PFXLOGXOXCISZ";
        String cipher = "";
        for (int i = 0; i < message.length(); i++) {
            cipher += machine.getChar(message.charAt(i));
            machine.advance();
        }
        System.out.println(cipher);
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
}
