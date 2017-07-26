/*
 * Application by: Michael J. Maffia
 * michaelmaffia@gmail.com
 * http://www.michaelmaffia.com
 */
package core;
//Imports

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author mmaffia
 */
public class Die implements IDie {

    //Member Variables
    private ArrayList<String> diceData;

    //Custom Constructor instantiates a new arraylist
    public Die() {
        this.diceData = new ArrayList<>();
    }

    //Implemented from Interface
    @Override
    public String rollDie() {
        Random rand = new Random();
        String side;
        //Set side equal to a random side of the selected dice.
        side = diceData.get(rand.nextInt(NUMBER_OF_SIDES));
        return side;
    }

    //Implemented from Interface
    @Override
    public void addLetter(String letter) {
        diceData.add(letter);
    }

    //Method uses Enhanced For Loop to print out the letters for each die.
    @Override
    public void displayLetters() {
        for (String n : diceData) {
            System.out.print(n + " ");
        }
    }

}
