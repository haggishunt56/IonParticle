package IonParticle;

import ParticleType.*;
import Listeners.*;
import javax.swing.*;
import java.awt.*;

/**
 * ~~~~ TEST LINE FOR GIT ~~~~
 * ~~~~ GIT TEST LINE 2 ~~~~
 *
 * The particle game is a strategy game where the player must escape enemy
 * particles which chase them around the board. It uses a simple grid layout
 * to display the game layout and counters. The Particle20 class extends JFrame
 * and is the main window for the game
 *
 * @author Douglas Pollock
 * @version 2.0
 * @since 2020-01-21
 */
public class GameWindow extends JFrame {

    public static final int GRID_SIZE = 30;
    private Player player;
    public SimpleNPC[] NPCs;
    private int difficulty; //1-Beginner, 2-Novice, 3-Intermediate, 4-Advanced, 5-Expert, 6-Master
    private int availableTeles;
    private int availableAttacks;
    public JLabel teleInfo;
    public JLabel attackInfo;

    /**
     * Constructor for objects of class GameWindow; called by the main method
     * when the game is opened.
     */
    public GameWindow(){
        //Create frame
        setTitle("Particle");
        setSize(GRID_SIZE*12+27,GRID_SIZE*20+195);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);
        difficulty = 1;

        //display Game Board grid
        getContentPane().add(new GameBoard(new Rectangle(0, 0, 13*GRID_SIZE, 24*GRID_SIZE), GRID_SIZE));

        //create game buttons and container JPanel
        JPanel buttonPanel = new JPanel();
//        buttonPanel.setBackground(Color.red);
        buttonPanel.setBounds(1, GRID_SIZE*20+10, GRID_SIZE*12+9, 77);
        JButton teleButton = new JButton("Teleport");
        JButton attackButton = new JButton("Attack");
        teleInfo = new JLabel();
        attackInfo = new JLabel();
        buttonPanel.add(teleButton);
        buttonPanel.add(attackButton);
        buttonPanel.add(teleInfo);
        buttonPanel.add(attackInfo);
        teleButton.addActionListener(new TeleButtonListener(this));
        attackButton.addActionListener(new AttackButtonListener(this, GRID_SIZE));


        //create control buttons and container JPanel
        JPanel controlPanel = new JPanel();
//        controlPanel.setBackground(Color.blue);
        controlPanel.setBounds(1, GRID_SIZE*20+87, GRID_SIZE*12+9, 67);
        JButton resetButton = new JButton("Reset game");
        JButton infoButton = new JButton("Instructions");
        JButton aboutButton = new JButton("About");
        controlPanel.add(resetButton);
        controlPanel.add(infoButton);
        controlPanel.add(aboutButton);
        resetButton.addActionListener(new ResetButtonListener(this));
        infoButton.addActionListener(new InfoButtonListener());
        aboutButton.addActionListener(new AboutButtonListener());

        add(buttonPanel);
        add(controlPanel);

        this.availableAttacks = 5;
        this.availableTeles = 5;
        teleInfo.setText("Teleports remaining: " + availableTeles);
        attackInfo.setText("Attacks remaining: " + availableAttacks);

        //add player to grid
        player = new Player(GRID_SIZE);
        getContentPane().add(player);

        //create NPC array
        NPCs = new SimpleNPC[60];

    }

    /**
     * Main method - program starts here. Create & show game window,
     * set up counters, handle input and play music
     *
     * @param args - the command line arguments
     */
    public static void main(String[] args){

        //create game window
        GameWindow window = new GameWindow();

        //set up counters
        window.gameSetup(window);
        window.player.teleport();

        //Show window
        window.setVisible(true);

        //handle input
        GameClickListener listener = new GameClickListener(window, GRID_SIZE);
        window.addMouseListener(listener);

        //start music. Has to be called after everything is initialised or the thread stops any further actions
        Music musicPlayer = new Music();
        musicPlayer.playMusic();

    }

    /**
     * Method sets up NPC game counters within the game board.
     * Depending on the difficulty selected, this method will place
     * different numbers and types of game counters on the board.
     *
     * @param window    The frame in which to set up the game counters
     */
    public void gameSetup(GameWindow window){
        switch(difficulty){
            case 1:
                //add 5-12 simpleNPCs
                for (int i=0;i<=randBetween(5,12);i++){
                    NPCs[i] = new SimpleNPC(GRID_SIZE);
                    getContentPane().add(NPCs[i]);
                }
                break;
            case 2:
                //add 5-10 simpleNPCs
                for (int i=0;i<=randBetween(5,10);i++){
                    NPCs[i] = new SimpleNPC(GRID_SIZE);
                    getContentPane().add(NPCs[i]);
                }
                //add 4-8 FastNPCs
                for (int i=12;i<=randBetween(16,20);i++){
                    NPCs[i] = new FastNPC(GRID_SIZE);
                    getContentPane().add(NPCs[i]);
                }
                break;
            case 3:
                //add 4-8 simpleNPCs
                for (int i=0;i<=randBetween(4,8);i++){
                    NPCs[i] = new SimpleNPC(GRID_SIZE);
                    getContentPane().add(NPCs[i]);
                }
                //add 3-6 FastNPCs
                for (int i=12;i<=randBetween(15,18);i++){
                    NPCs[i] = new FastNPC(GRID_SIZE);
                    getContentPane().add(NPCs[i]);
                }
                //add 6-10 KnightNPCs
                for (int i=20;i<=randBetween(25,29);i++){
                    NPCs[i] = new KnightNPC(GRID_SIZE);
                    getContentPane().add(NPCs[i]);
                }
                break;
            case 4:
                //add 3-6 simpleNPCs
                for (int i=0;i<=randBetween(3,6);i++){
                    NPCs[i] = new SimpleNPC(GRID_SIZE);
                    getContentPane().add(NPCs[i]);
                }
                //add 3-6 FastNPCs
                for (int i=12;i<=randBetween(15,18);i++){
                    NPCs[i] = new FastNPC(GRID_SIZE);
                    getContentPane().add(NPCs[i]);
                }
                //add 4-8 KnightNPCs
                for (int i=20;i<=randBetween(23,27);i++){
                    NPCs[i] = new KnightNPC(GRID_SIZE);
                    getContentPane().add(NPCs[i]);
                }
                //add 3-5 TeleportNPCs
                for(int i=30;i<=randBetween(32,34);i++){ //
                    NPCs[i] = new TeleportNPC(GRID_SIZE);
                    getContentPane().add(NPCs[i]);
                }
                break;
            case 5:
                //add 5-6 simpleNPCs
                for (int i=0;i<=randBetween(5,6);i++){
                    NPCs[i] = new SimpleNPC(GRID_SIZE);
                    getContentPane().add(NPCs[i]);
                }
                //add 3-8 FastNPCs
                for (int i=12;i<=randBetween(15,20);i++){
                    NPCs[i] = new FastNPC(GRID_SIZE);
                    getContentPane().add(NPCs[i]);
                }
                //add 5-8 KnightNPCs
                for (int i=20;i<=randBetween(24,27);i++){
                    NPCs[i] = new KnightNPC(GRID_SIZE);
                    getContentPane().add(NPCs[i]);
                }
                //add 3-5 TeleportNPCs
                for(int i=30;i<=randBetween(32,34);i++){
                    NPCs[i] = new TeleportNPC(GRID_SIZE);
                    getContentPane().add(NPCs[i]);
                }
                //add 4-6 AttackerNPCs
                for(int i=36;i<=randBetween(39,41);i++){
                    NPCs[i] = new AttackerNPC(window, GRID_SIZE);
                    getContentPane().add(NPCs[i]);
                }
                break;
            case 6:
                System.out.println("Not yet supported");
                break;
            default:
                System.out.println("Error. Please try again");
        }
    }

    /**
     * Allows other objects to access the player variable of the game window
     *
     * @return player variable of object
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * Allows other objects to determine the number of teleports remaining to the player
     *
     * @return the number of remaining teleports
     */
    public int getAvailableTeles(){
        return availableTeles;
    }

    /**
     * Allows other objects to determine how many teleports are available to
     * the player.
     *
     * @param direction - 0 reduces the number of available teleports by 1. 1
     * returns the number of available teleports to the default of 5.
     */
    public void setAvailableTeles(int direction){
        if(direction == 0){
            availableTeles--;
        }
        else if(direction == 1){
            availableTeles = 5;
        }
    }

    /**
     * Returns the number of attacks remaining to the player
     *
     * @return the remaining number of attacks which can be made in the current game
     */
    public int getAvailableAttacks(){
        return availableAttacks;
    }

    /**
     * Allows the game to change or reset the number of available attacks if the player uses an attack or resets the game.
     *
     * @param direction 0 to reduce the number of attacks by 1, or 1 to reset the number of attacks to 5.
     */
    public void setAvailableAttacks(int direction){
        if(direction == 0){
            availableAttacks--;
        }
        else if(direction == 1){
            availableAttacks = 5;
        }
    }

    /**
     * Returns all NPC objects in the game as an array of objects
     *
     * @return array of NPCs in the game. Note that some of the entries in the array may be null
     */
    public SimpleNPC[] getNPCs(){
        return NPCs;
    }

    /**
     * Prevents NPC from being displayed when attacked. Only works if NPC is alive
     *
     * @param arrayPos The position of the NPC to be removed in the NPCs array variable of the GameWindow.
     */
    public void removeNPC(int arrayPos){
        if(NPCs[arrayPos].checkPulse()){
            NPCs[arrayPos].removeNPC();
        }
    }

    /**
     * Returns a random number between two integers using the Math.random method.
     *
     * @param min the minimum value the number generated may take
     * @param max the maximum value the number generated may take
     * @return the randomly generated number
     */
    public int randBetween(int min, int max){
        return (int)(Math.random() * ((max-min)+1)) + min;
    }

    /**
     * Allows objects to set the difficulty of the game
     *
     * @param difficulty the difficulty of the game to be played. 1-Beginner, 2-Novice, 3-Intermediate, 4-Advanced, 5-Expert, 6-Master.
     */
    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }

    /**
     * Determine whether two game counters are on the same square as each
     * other. Kills player if they land on the same square as an NPC by calling
     * the relevant method of the player object, and kills NPCs if they land on
     * the same square as each other by calling the relevant methods of that
     * object.
     */
    public void calculateCollisions(){
        //if any NPC caught player, game over
        for(int i=0;i<getNPCs().length;i++){
            if(getNPCs()[i] != null) {
                if (getNPCs()[i].getNPCLocation()[0] == getPlayer().getPlayerLocation()[0]
                        && getNPCs()[i].getNPCLocation()[1] == getPlayer().getPlayerLocation()[1]){
                    getPlayer().killPlayer();
                }
            }
            //else do nothing if entry at position i is null
        }

        //if any two NPC locations are the same, change state to dead (!alive)
        for(int i=0;i<getNPCs().length;i++){
            for(int j=i+1;j<getNPCs().length;j++){
                if(getNPCs()[i] != null && getNPCs()[j] != null){
                    if (getNPCs()[i].getNPCLocation()[0] == getNPCs()[j].getNPCLocation()[0]
                            && getNPCs()[i].getNPCLocation()[1] == getNPCs()[j].getNPCLocation()[1]){
                        getNPCs()[i].killNPC();
                        getNPCs()[j].killNPC();
                    }
                }
                //else do nothing if entry at positions i & j are null
            }
        }

        //If NPC out of bounds, remove NPC
        for(int i=0;i<getNPCs().length;i++){
            if(getNPCs()[i] != null){
                if (getNPCs()[i].getNPCLocation()[0] > GRID_SIZE*11+1 ||
                        getNPCs()[i].getNPCLocation()[1] > GRID_SIZE*19+1 ||
                        getNPCs()[i].getNPCLocation()[0] < 1 ||
                        getNPCs()[i].getNPCLocation()[1] < 1){
                    getNPCs()[i].removeNPC();
                }
            }
            //else do nothing if entry at position i is null
        }
    }
}


