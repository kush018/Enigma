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
        String message = "MXPEOHIOHTIKSOHRZEZZUDZBIKVQMXXMMXDWBDIBMIIXGWTPXSVVDYBFJMZWWLVZOHYI";
        String cipher = "";
        for (int i = 0; i < message.length(); i++) {
            cipher += machine.getChar(message.charAt(i));
            machine.advance();
        }
        System.out.println(cipher);
    }
}
