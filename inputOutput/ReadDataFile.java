/*
 * Application by: Michael J. Maffia
 * michaelmaffia@gmail.com
 * http://www.michaelmaffia.com
 */
package inputOutput;
//Imports
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mmaffia
 */
public class ReadDataFile implements IReadDataFile {
    //Member Variables
    private Scanner s; //Scanner Name
    private String fname; //File Name
    private ArrayList<String> fileData;
    
    
    //Custom Constructor
    //Takes in a String for fileName, Instantiates a new arraylist for file data.
    public ReadDataFile(String diceIn) {
        this.fname = diceIn;
        this.fileData = new ArrayList<>();
        
        
        
    }
public void populateDict() {
        try {  
            //Creates URL Variable using the filename passed into constructor.
            URL url = getClass().getResource(fname);
            File file = new File(url.toURI());
            //Instatiates new Scanner Instance
            s = new Scanner(file);
            //Loops through data file until empty.
            while(s.hasNext()){
                //Adds each piece data into ArrayList
                fileData.add(s.nextLine());
            }
          //Catches Exceptions  
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(ReadDataFile.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    @Override
    //Method to populate Data from File to ArrayList.
    public void populateData() {
        try {  
            //Creates URL Variable using the filename passed into constructor.
            URL url = getClass().getResource(fname);
            File file = new File(url.toURI());
            //Instatiates new Scanner Instance
            s = new Scanner(file);
            //Loops through data file until empty.
            while(s.hasNext()){
                //Adds each piece data into ArrayList
                fileData.add(s.next());
            }
          //Catches Exceptions  
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(ReadDataFile.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    //Getters and Setters for File Data.
    /**
     * @return the fileData
     */
    public ArrayList<String> getFileData() {
        return fileData;
    }

    /**
     * @param fileData the fileData to set
     */
    public void setFileData(ArrayList<String> fileData) {
        this.fileData = fileData;
    }
    
}
