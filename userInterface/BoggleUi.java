/*
 * Application by: Michael J. Maffia
 * michaelmaffia@gmail.com
 * http://www.michaelmaffia.com
 */
package userInterface;

import core.Board;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

/**
 *
 * @author mmaffia
 */
public final class BoggleUi {

    //Member Variables for UI components.
    private JFrame frame; //Frame
    private Board board; //Local Variable for Board
    private JButton[][] buttons; //Array of Buttons
    private JMenuBar menuBar; //Menu Bar
    private JMenu menu1; //Boggle Menu
    private JMenuItem item1; //Used to Start Game
    private JMenuItem item2; //Used to Exit Game
    private JPanel contentPane; //Container used to store UI Panels
    private JPanel panel0; //Panel Containing Current Word
    private JLabel currWordLbl; //Label to display the Current Word
    private JButton submitWord; //Button to Submit Word
    private JLabel scoreLbl; //Label that Contains the Score
    private JPanel panel1; //Second Panel used to hold Boggle Board.
    private JPanel panel2; //Third Panel used to hold the Words Found Text Field and Time
    private JTextPane textPane; //TextPane that holds Words Found
    private JScrollPane jsp; //ScrollPane added to TextPane.
    private JLabel timeLbl; //Shows Time Remaining
    private JButton shakeBtn; //Button to Shake Dice.
    private Timer timer; //timer
    private int minutes = 3; //minutes for timer
    private int seconds = 0; //seconds for timer
    private ResetGame reset; //new ResetGame
    private int pscore; //Player Score
    private ArrayList< String> foundWords; //User Found Words

    //Custom Constructor. Calls method initComponents();
    public BoggleUi(Board board) {
        this.board = board;
        initComponents();
        reset = new ResetGame();
        foundWords = new ArrayList<>();

    }

    private class ResetGame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //Reset with New Letters
            resetDice();

            //resets text areas to be ready for a new game
            textPane.setText(""); //Words found panel is cleared
            scoreLbl.setText("0"); //Reset Score
            currWordLbl.setText(""); //currentLabel is reset
            timeLbl.setText("3:00"); //timer label reset
            panel1.revalidate();//Update UI
            panel1.repaint();//
            timer.stop();//Restart Timer
            minutes = 3;//
            seconds = 0;//
            timer.start();//Start New Timer
        }
    }

    public void resetDice() {
        board.shakeDice();
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //ADD EACH SHAKE DICE TO EACH BUTTON.
                buttons[i][j].setText(board.getGameData().get(count));
                count++;
            }

        }
        //Upon Reset, Enable all buttons.
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j].setEnabled(true);
            }
        }
    }

    //Listener class for Submit Button
    private class SubmitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String currWord;
            currWord = toLowerCase(currWordLbl.getText());
            //Upon Submit, Enable all Buttons.
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    buttons[i][j].setEnabled(true);
                }
            }
            //Check if word is in dictionary
            if (board.getDictData().contains(currWord) && currWord.length() > 2) {
                //Add word to textPane
                textPane.setText(textPane.getText() + currWord + "\n");
                currWordLbl.setText("");
                foundWords.add(currWord);
                //Scoring Rules, based on Word Length.
                if (currWord.length() == 3) {
                    pscore += 1;
                }
                if (currWord.length() == 4) {
                    pscore += 1;
                }
                if (currWord.length() == 5) {
                    pscore += 2;
                }
                if (currWord.length() == 6) {
                    pscore += 3;
                }
                if (currWord.length() == 7) {
                    pscore += 5;
                }
                if (currWord.length() == 8) {
                    pscore += 11;
                }
                scoreLbl.setText(Integer.toString(pscore));
                //IF no word is found, or if word is too small, reset.
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Word, Try Again!");
                currWordLbl.setText("");

            }

        }
    }

    //Button Handler
    public class ButtonActionHandler implements ActionListener {

        //Member Vars
        private final JButton tempButton;
        private int ivar;
        private int jvar;

        //Constructor
        public ButtonActionHandler(int ivar, int jvar) {
            this.tempButton = buttons[ivar][jvar];
            this.ivar = ivar;
            this.jvar = jvar;
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    //Disables all Letters.
                    buttons[i][j].setEnabled(false);
                }
            }
            //Will Enable only Surrounding Letters of selected button, within range.
            try {
                buttons[ivar - 1][jvar].setEnabled(true);
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                buttons[ivar][jvar - 1].setEnabled(true);
            } catch (ArrayIndexOutOfBoundsException ignored) {

            }
            try {
                buttons[ivar - 1][jvar - 1].setEnabled(true);
            } catch (ArrayIndexOutOfBoundsException ignored) {

            }
            try {
                buttons[ivar + 1][jvar].setEnabled(true);
            } catch (ArrayIndexOutOfBoundsException ignored) {

            }
            try {
                buttons[ivar][jvar + 1].setEnabled(true);
            } catch (ArrayIndexOutOfBoundsException ignored) {

            }
            try {
                buttons[ivar + 1][jvar + 1].setEnabled(true);
            } catch (ArrayIndexOutOfBoundsException ignored) {

            }
            try {
                buttons[ivar + 1][jvar - 1].setEnabled(true);
            } catch (ArrayIndexOutOfBoundsException ignored) {

            }
            try {
                buttons[ivar - 1][jvar + 1].setEnabled(true);
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            //Adds each letter to the Current Word Label
            currWordLbl.setText(currWordLbl.getText() + this.tempButton.getText());

        }
    }

    //Exit Listener
    private class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //Popup Dialog
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit " + "boggle?",
                    "Exit?", JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }

    }
    //Listener Clas for the Timer
    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //End of Game
            if (seconds == 0 && minutes == 0) {
                //Initiate End of Game
                JOptionPane.showMessageDialog(null, "The computer is Comparing words!");
                Random randomGen = new Random();
                //AI Simulate
                //Randdomly generate Computer Words Found.
                int cpuNumWords;
                cpuNumWords = randomGen.nextInt(foundWords.size() + 1);
                textPane.setText("");        
                //Format AI Found Words.
                //Add StrikeThrough Font Styling to cpu Found Words.
                Font font = new Font("arial", Font.BOLD, 12);
                Map fontAttr = font.getAttributes();
                fontAttr.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
                Font myFont = new Font(fontAttr);
                textPane.setFont(myFont);
                //Alert user how many Words the Computer Found.
                JOptionPane.showMessageDialog(null, "The computer found " + cpuNumWords + " words out of the player's " + foundWords.size() + " words!");
                for (int i = 0; i < cpuNumWords; i++) {
                    int cpuInd = randomGen.nextInt(cpuNumWords + 1 - i);
                    textPane.setText(textPane.getText() + foundWords.get(cpuInd) + "\n");
                    foundWords.remove(cpuInd);
                }
                //Determine Winner
                if (foundWords.size() > cpuNumWords) {
                    currWordLbl.setText("<You Won!>");
                }
                else if (foundWords.size() == cpuNumWords) {
                    currWordLbl.setText("<It's a Tie!>");
                }
                //Stop the Timer.
                timer.stop();
            } else {
                if (seconds == 0) {
                    seconds = 59;
                    minutes--;
                } else {
                    seconds--;
                }
            }
            if (seconds < 10) {
                String strSeconds = "0" + String.valueOf(seconds);
                timeLbl.setText(String.valueOf(minutes) + ":" + strSeconds);
            } else {
                timeLbl.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));
            }
        }
    }

    public void initComponents() {

        //Instantiate a new JFrame
        frame = new JFrame();
        //Add Properities to the Frame
        frame.setPreferredSize(new Dimension(1280, 720));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 800, 600);
        //Instantiate and Set a new Menu Bar
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        //Add a Menu
        menu1 = new JMenu("Boggle");
        menuBar.add(menu1);
        //Add Start Game to Menu
        item1 = new JMenuItem("New Game");
        menu1.add(item1);
        item1.addActionListener(new ResetGame()); //Add Exit to Menu
        item2 = new JMenuItem("Exit");
        menu1.add(item2);
        item2.addActionListener(new ExitListener());
        //Instantiate a new Container to hold UI Panels
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        //Add ContentPane to the Frame
        frame.setContentPane(contentPane);
        //Instantiate a new Panel to Hold Current Word w/ Properties
        panel0 = new JPanel();
        panel0.setBorder(new TitledBorder(null, "Current Word", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        //Add Panel to the ContentPane.
        contentPane.add(panel0, BorderLayout.SOUTH);
        //Create a new Label for Current Word w/ Properties.
        currWordLbl = new JLabel("");
        currWordLbl.setPreferredSize(new Dimension(200, 75));
        currWordLbl.setBorder(new TitledBorder(null, "Current Word", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        currWordLbl.setHorizontalAlignment(SwingConstants.LEFT);
        //Add Label to Panel.
        panel0.add(currWordLbl);
        //Instantiate new Button to Submit Word w/ Properties.
        submitWord = new JButton("Submit");
        submitWord.setPreferredSize(new Dimension(200, 75));
        submitWord.addActionListener(new SubmitListener());
        //Add button to First Panel.
        panel0.add(submitWord);
        //Instantiate new label Storing the Score w/ Properties.
        scoreLbl = new JLabel("");
        scoreLbl.setBorder(new TitledBorder(null, "Score", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        scoreLbl.setPreferredSize(new Dimension(200, 75));
        scoreLbl.setHorizontalAlignment(SwingConstants.LEFT);
        scoreLbl.setFont(new Font("Serif", Font.BOLD, 24));
        //Add label to Panel
        panel0.add(scoreLbl);
        //Create new Panel Object Storing the Boggle Board
        panel1 = new JPanel();
        panel1.setBorder(new TitledBorder(null, "Boggle Board", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        //Add the Panel to the Content Pane.
        contentPane.add(panel1, BorderLayout.CENTER);
        //Set the GridLayout
        panel1.setLayout(new GridLayout(4, 4, 0, 0));
        //Instantiate new array of Buttons
        buttons = new JButton[4][4];
        //Loop that adds 16 buttons to the grid.
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                buttons[i][j] = new JButton();
                //ADD EACH SHAKE DICE TO EACH BUTTON.
                buttons[i][j].setText(board.getGameData().get(count));
                buttons[i][j].setFont(new Font("Serif", Font.BOLD, 24));
                buttons[i][j].addActionListener(new ButtonActionHandler(i, j));
                panel1.add(buttons[i][j]);
                count++;
            }
        }
        //Instantiate a new Panel to hold the Words Found.
        panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(200, 350));
        panel2.setBorder(new TitledBorder(null, "Enter Words Found", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        //Adds the Panel to the Content Pane.
        contentPane.add(panel2, BorderLayout.EAST);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        //Creates a new textPane, adds it to the Panel.
        textPane = new JTextPane();
        panel2.add(textPane);
        //Creates a new JScrollPane and adds it to the textPane and panel2.
        jsp = new JScrollPane(textPane);
        panel2.add(jsp);
        //Create new Label for the time clock. w/ Properties
        timeLbl = new JLabel("3:00", SwingConstants.CENTER);
        timeLbl.setFont(new Font("Serif", Font.BOLD, 36));
        timeLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        timeLbl.setMaximumSize(new Dimension(200, 50));
        timeLbl.setMinimumSize(new Dimension(250, 100));
        timeLbl.setPreferredSize(new Dimension(250, 100));
        timeLbl.setBorder(new TitledBorder(null, "Time Left", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        //Adds the time Label to the Panel.
        panel2.add(timeLbl);
        //Instantiate new Shake Dice Button w/ Properties.
        shakeBtn = new JButton("Shake Dice");
        shakeBtn.setPreferredSize(new Dimension(200, 100));
        shakeBtn.setMaximumSize(new Dimension(200, 50));
        shakeBtn.setMinimumSize(new Dimension(250, 100));
        shakeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        shakeBtn.addActionListener(new ResetGame());
        //Add Shake Dice Button to the Panel.
        panel2.add(shakeBtn);
        //Set Frame Visibility.
        frame.setVisible(true);
        //Start the Timer
        timer = new Timer(1000, new TimerListener());
        timer.start();
    }
}
