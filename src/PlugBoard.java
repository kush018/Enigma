public class PlugBoard {
    //a class representing PlugBoards

    //a two dimensional array containing information about plug board wirings
    private int[][] plugBoardWiring;

    //constructor
    public PlugBoard(String wiringInfo) {
        String[] wiringAsString = stringArraySplit(wiringInfo, " ");
    }

    private static String[] stringArraySplit(String str, String splitter) {
        //this part in particular could be improved
        String[] splittedArray = new String[str.length()];
        String temp = "";
        for (int i = 0, j = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                temp += str.charAt(i);
            } else {
                splittedArray[j] = temp;
                temp = "";
                j++;
            }
        }
        return splittedArray;
    }
}
