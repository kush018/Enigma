public class Machine {
    /*
    A class that represents an Enigma machine
     */

    private PlugBoard plugBoard;
    private Reflector reflector;
    private RotorGroup rotorGroup;

    public Machine(PlugBoard plugBoard, Reflector reflector, RotorGroup rotorGroup) {
        this.plugBoard = plugBoard;
        this.reflector = reflector;
        this.rotorGroup = rotorGroup;
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

    public void advance() {
        rotorGroup.advance();
    }
}
