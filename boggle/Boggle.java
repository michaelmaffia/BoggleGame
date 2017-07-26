/*
 * Application by: Michael J. Maffia
 * michaelmaffia@gmail.com
 * http://www.michaelmaffia.com
 */
package boggle;
//Imports

import core.Board;
import inputOutput.ReadDataFile;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import userInterface.BoggleUi;

/*
 * @author mmaffia
 */
public class Boggle {

    //Member Variables
    //Variables for BoggleData file containing Data.
    // Array list to store data value of each die
    private static ArrayList<String> boggleData;
    private static String diceIn = "/data/BoggleData.txt";
    //Variables for Dictionary.txt file containing Dictionary Words.
    private ArrayList<String> dictData;
    private static String dictIn = "/data/Dictionary.txt";

    //Main Method
    public static void main(String[] args) {
        //Prints Welcome Message
        System.out.println("Welcome to Boggle!");
        //Opens a Message Dialog
        JOptionPane.showMessageDialog(null, "Let's Play Boggle!");

        //Instantiate an instance of ReadDataFile Class for Dice
        ReadDataFile diceData = new ReadDataFile(diceIn);
        //Populate the DiceData
        diceData.populateData();
        //Instantiate an instance of ReadDataFile Class for Dictionary Data
        ReadDataFile dictData = new ReadDataFile(dictIn);
        //Populate the Dictionary Data
        dictData.populateDict();
        //Instantiate an instance of Board for board Data.
        Board board = new Board(diceData.getFileData(), dictData.getFileData());
        //Populates dice data.
        board.populateDice();
        //Prints the number of entries in the Dictionary. 
        System.out.println("There are " + dictData.getFileData().size() + " entries in the dictionary");
        //Sets member variable BoggleData to the returned ArrayList from shakeDice.
        boggleData = board.shakeDice();
        //Displays the Game Data using implemented method in class Board.
        board.displayGameData();
        //Instantiate a new Boggle UI
        BoggleUi ui = new BoggleUi(board);
    }
}
