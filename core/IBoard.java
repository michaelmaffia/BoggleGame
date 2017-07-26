/*
 * Application by: Michael J. Maffia
 * michaelmaffia@gmail.com
 * http://www.michaelmaffia.com
 */
package core;

import java.util.ArrayList;

/**
 *
 * @author mmaffia
 */
public interface IBoard {
    // Constant Variables
    public static final int NUMBER_OF_DICE = 16;
    public static final int GRID = 4;
    
    // Method Signatures
    public void populateDice();
    public ArrayList shakeDice();
    
}
