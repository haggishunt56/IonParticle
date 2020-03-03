package ParticleType;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

/**
 * This class controls the SimpleNPC type for the Particle game. All other NPC types
 * inherit from SimpleNPC.
 *
 * @author Douglas Pollock
 */
public class SimpleNPC extends JPanel {

    protected int[] xGridLocations = new int[13];
    protected int[] yGridLocations = new int[21];
    protected int[] currentSquare = {1,1};
    protected int[] playerSquare = {1,1};
    protected boolean alive;
    protected boolean present;
    protected Color color;
    int gridSize;
    public boolean moved = false; //only used for FastNPC
    public boolean secondMove = false; //only used for FastNPC

//    protected int[] startPosition = currentSquare; // will eventually be used for animating moves
//    protected int[] endPosition = {1,1}; // will eventually be used for animating moves

    /**
     * Default constructor for objects of class SimpleNPC. Required to allow other
     * NPC types to be constructed.
     *
     */
    protected SimpleNPC()
    {

    }

    /**
     * Constructor for objects of class SimpleNPC
     *
     * @param gridSize - the size of each square in the GameBoard grid. Used to determine
     *                 how large the counter should be
     */
    public SimpleNPC(int gridSize)
    {
        setLayout(null);
        setBounds(5, 5, gridSize*12+27, gridSize*20+80);
        setOpaque(false);
        this.color = new Color(240, 250, 0);
        this.alive = true;
        this.present = true;
        this.gridSize = gridSize;

        for(int i=0;i<=11;i++){
            xGridLocations[i]=gridSize*i+1;
        }
        xGridLocations[12] = 50000;

        for(int i=0;i<=19;i++){
            yGridLocations[i]=gridSize*i+1;
        }
        yGridLocations[20] = 50000;

        this.teleport();
    }

    /**
     * Controls how the counter is displayed on the screen
     *
     * @param g - the graphics object created when the object is initialised
     */
    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(color);
        g2D.fill(new Ellipse2D.Double(currentSquare[0], currentSquare[1], gridSize-2, gridSize-2));
    }

    /**
     * Generates a random location in the xGridLocations array
     *
     * @return the randomly generated x-coordinate
     */
    public int randomXLocation(){
        return xGridLocations[(int)(12*Math.random())];
    }

    /**
     * Generates a random location in the yGridLocations array
     *
     * @return the randomly generated y-coordinate
     */
    public int randomYLocation(){
        return yGridLocations[(int)(20*Math.random())];
    }

    /**
     * Checks if the NPC is alive and, if so, moves it to a random location on the board.
     * Does nothing if the NPC is dead.
     */
    public void teleport(){
        if(alive){
            currentSquare[0] = randomXLocation();
            currentSquare[1] = randomYLocation();
            this.repaint();
        }
        //else do nothing
    }

    /**
     * Enables other objects to access the location on both the x and y planes of the
     * NPC object
     *
     * @return an array containing the x coordinate as its first entry and the y-coordinate
     * as its second entry.
     */
    public int[] getNPCLocation(){
        return currentSquare;
    }

    /**
     * Sets the alive parameter to false and changes the colour to black. Generally this
     * prevents the NPC from making any further moves until the game is reset.
     */
    public void killNPC(){
        alive = false;
        color = Color.black;
        repaint();
    }

    /**
     * Checks if the NPC is alive
     *
     * @return value of the alive boolean. True means the NPC is still alive, false means it
     * has been killed.
     */
    public boolean checkPulse(){
        return alive;
    }

    /**
     * Removes the NPC entirely from the game board by moving it 50,000 pixels in each direction.
     * This prevents it from interacting any further with the player or NPCs in the game.
     *
     * This method only operates if the NPC is alive.
     */
    public void removeNPC(){
        if(alive){
            present = false;
            currentSquare[0] = xGridLocations[12];
            currentSquare[1] = yGridLocations[20];
        }
    }

    /**
     * Removes the NPC entirely from the game board by moving it 50,000 pixels in each direction.
     * This prevents it from interacting any further with the player or NPCs in the game.
     *
     * This method operates whether the NPC is alive or not
     */
    public void forceRemoveNPC(){
        present = false;
        currentSquare[0] = xGridLocations[12];
        currentSquare[1] = yGridLocations[20];
    }

    /**
     * Allows other objects to bring the NPC back to life. Sets the alive parameter to true
     * and changes the colour back to yellow.
     */
    public void reanimateNPC(){
        alive = true;
        present = true;
        color = Color.getHSBColor((float) 0.1722222, 1, (float) 0.9804);
        repaint();
    }

    /**
     * Controls the movement of the NPC object towards the player. The SimpleNPC moves one
     * square in any direction towards the player each turn.
     *
     * @param playerXLocation the coordinate on the x-plane the NPC is moving towards
     * @param playerYLocation the coordinate on the y-plane the NPC is moving towards
     */
    public void moveToPlayer(int playerXLocation, int playerYLocation){

        if(alive && present){
            playerSquare[0] = playerXLocation;
            playerSquare[1] = playerYLocation;

            //move towards player's X coordinate
            if(playerSquare[0] < currentSquare[0]){
                currentSquare[0] = currentSquare[0] - gridSize;
            }
            else if(playerSquare[0] > currentSquare[0]){
                currentSquare[0] = currentSquare[0] + gridSize;
            }

            //move towards player's Y coordinate
            if(playerSquare[1] < currentSquare[1]){
                currentSquare[1] = currentSquare[1] - gridSize;
            }
            else if(playerSquare[1] > currentSquare[1]){
                currentSquare[1] = currentSquare[1] + gridSize;
            }

            repaint();
        }
        //else do nothing if not alive or not present

    }

}