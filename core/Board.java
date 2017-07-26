/*
 * Application by: Michael J. Maffia
 * michaelmaffia@gmail.com
 * http://www.michaelmaffia.com
 */
package core;
//Imports

import static core.IDie.NUMBER_OF_SIDES;
import java.util.ArrayList;
import java.util.Random;

public class Board implements IBoard {

    //Member Variables
    private ArrayList<String> diceData; // Dice Data
    private ArrayList<String> dictData; //Dictionary Data
    private ArrayList<String> gameData;
    private ArrayList<Die> dice; //Game Dice

    //Custom Constructor Taking in two ArrayLists of Type String.
    public Board(ArrayList<String> boggleData, ArrayList<String> dictData) {
        this.diceData = boggleData;
        this.dictData = dictData;
        //Instantiates a new arraylist of Dice
        dice = new ArrayList<>();
    }

    @Override
    public void populateDice() {
        //Declared Variables
        Die die;
        int counter = 0;
        //Loops through the set of dice
        for (int j = 0; j < NUMBER_OF_DICE; j++) {
            //Instantiates a new Die for each set of sides
            die = new Die();
            //Loops through each die and adds the letter to each side.
            for (int i = 0; i < NUMBER_OF_SIDES; i++) {
                die.addLetter(diceData.get(counter));
                //increments the index of the dataFile
                counter++;
            }
            //Prints the die number, and the letters on each die.    
            System.out.print("Die " + j + ": ");
            die.displayLetters();
            System.out.println("");
            //Adds the die to the set of dice.
            dice.add(die);
        }
    }

    @Override
    //Implemented from Interface
    public ArrayList shakeDice() {
        //Instantiate a new Random Instance.
        Random rand = new Random();
        //Declare local variables.
        Die selectedDie;
        int randIndex;
        //Instantiate a new instance of Game Data Array Lists.
        gameData = new ArrayList<>();
        //Declare and instantiate a new arraylist to save the index number
        //of each die used.
        ArrayList<Integer> usedDie = new ArrayList<>();
        //Loop through the 16 Dice
        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            //Randomly pick a die to use.
            randIndex = rand.nextInt(NUMBER_OF_DICE);
            //Check if die has been used, if not, proceed.
            if (usedDie.contains(randIndex)) {
                i--;
                continue;
            }
            //Add the index found to the used die.
            usedDie.add(randIndex);
            //Get's the Die in the set of dice that has been picked.
            selectedDie = dice.get(randIndex);
            //Adds the randomly selected side of the Selected Die to Game Data.
            gameData.add(selectedDie.rollDie());
        }
        //Returns ArrayList containing the game Data.
        return gameData;
    }

    //Getter for Game Data
    public ArrayList<String> getGameData() {
        return gameData;
    }

    //Setter the Game Data
    public void setGameData(ArrayList<String> gameData) {
        this.gameData = gameData;
    }

    public void displayGameData() {
        System.out.print("\nBoggle Board");
        //Loops through each of the Letters Presented in the Game Data
        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            //Adds a line Break every 4 letters.
            if (i % 4 == 0) {
                System.out.println("");
            }
            //Prints each letter of the board Data.
            System.out.print(gameData.get(i) + " ");

        }
        //Adds a line break to end of board.
        System.out.println("");
    }

    /**
     * @return the dictData
     */
    public ArrayList<String> getDictData() {
        return dictData;
    }

    /**
     * @param dictData the dictData to set
     */
    public void setDictData(ArrayList<String> dictData) {
        this.dictData = dictData;
    }

}
