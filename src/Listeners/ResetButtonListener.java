package Listeners;

import IonParticle.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Listens for clicks on the "Reset" button. Displays a window when clicked, allowing
 * the player to select the difficulty of the game to be played. Currently the hardest
 * difficulty, Master, is not available as the NPC types have not yet been designed.
 *
 * When the difficulty is selected, all current counters will be removed from the board
 * and new ones placed according to the difficulty. There is a random number of counters
 * which differs depending on the difficulty level, and they will be placed in random
 * locations.
 *
 * @author Douglas Pollock
 */
public class ResetButtonListener implements ActionListener {

    GameWindow w;

    /**
     * Constructor for objects of class ResetButtonListener
     *
     * @param w The GameWindow object on which the new counters should be displayed.
     */
    public ResetButtonListener(GameWindow w){
        this.w = w;
    }

    /**
     * This method describes the window to be displayed containing the options
     * for difficulty levels and allows the user to set the difficulty of their
     * next game. It then calls the resetAction method to remove all counters
     * from the board.
     *
     * @param e The event object created when the user clicks the button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //ask for difficulty
        JFrame difficultySelector = new JFrame();
        JPanel difficultyPanel = new JPanel();
        difficultyPanel.setLayout(new GridLayout(7,0));
        JLabel difficultyLabel = new JLabel("Select Difficulty:", SwingConstants.CENTER);
        JButton beginnerButton = new JButton("Beginner");
        JButton noviceButton = new JButton("Novice");
        JButton intermediateButton = new JButton("Intermediate");
        JButton advancedButton = new JButton("Advanced");
        JButton expertButton = new JButton("Expert");
        JButton masterButton = new JButton("Master - coming soon");

        masterButton.setEnabled(false);

        difficultyPanel.add(difficultyLabel);
        difficultyPanel.add(beginnerButton);
        difficultyPanel.add(noviceButton);
        difficultyPanel.add(intermediateButton);
        difficultyPanel.add(advancedButton);
        difficultyPanel.add(expertButton);
        difficultyPanel.add(masterButton);

        beginnerButton.addActionListener(e1 -> {
            w.setDifficulty(1);
            resetAction();
            difficultySelector.dispose();
        });

        noviceButton.addActionListener(e12 -> {
            w.setDifficulty(2);
            resetAction();
            difficultySelector.dispose();
        });

        intermediateButton.addActionListener(e13 -> {
            w.setDifficulty(3);
            resetAction();
            difficultySelector.dispose();
        });

        advancedButton.addActionListener(e14 -> {
            w.setDifficulty(4);
            resetAction();
            difficultySelector.dispose();
        });

        expertButton.addActionListener(e15 -> {
            w.setDifficulty(5);
            resetAction();
            difficultySelector.dispose();
        });

        masterButton.addActionListener(e16 -> {
            w.setDifficulty(6);
            resetAction();
            difficultySelector.dispose();
        });

        difficultySelector.add(difficultyPanel);
        difficultySelector.setTitle("Select difficulty");
        difficultySelector.setSize(167,255);
        difficultySelector.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        difficultySelector.setResizable(false);

        difficultySelector.setVisible(true);


    }

     /**
     * The resetAction method removes the player and NPC counters, then calls the
     * gameSetup method of the GameWindow object which sets up new counters
     */
    private void resetAction(){
        //reset & randomise player counter
        w.getPlayer().reanimatePlayer();
        w.getPlayer().teleport();

        //reset & randomise NPC counters
        for(int i=0;i<w.getNPCs().length;i++){
            if(w.getNPCs()[i] != null){
                w.getNPCs()[i].forceRemoveNPC();
            }
            //else do nothing if null
        }

        //reset available attacks and teleports
        w.setAvailableAttacks(1);
        w.setAvailableTeles(1);
        w.attackInfo.setText("Attacks remaining: " + w.getAvailableAttacks());
        w.teleInfo.setText("Teleports remaining: " + w.getAvailableTeles()); //update label

        w.gameSetup(w);
        w.repaint();
    }

}
