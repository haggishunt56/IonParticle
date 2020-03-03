package IonParticle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

/**
 * This class controls the player counter (red icon) on screen and the behaviours associated with it.
 *
 * @author Dougie
 */
public class Player extends JPanel {

    private int[] xGridLocations = new int[13];
    private int[] yGridLocations = new int[21];
    private int[] currentSquare = {1, 1};
    private Color color;
    private boolean alive;
    private int gridSize;

    /**
     * Constructor for objects of class Player
     *
     * @param gridSize -  the size of each square in the GameBoard grid. Used to determine how large the counter should be
     */
    public Player(int gridSize)
    {
        setLayout(null);
        setBounds(5, 5, gridSize*12+27, gridSize*20+80);
        setOpaque(false);
        this.color = new Color(210, 0, 0);
        this.alive = true;
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
     * This method is used to draw the player object on screen.
     * It has to override the method in the JPanel class to tell the JRE what shape and colour to draw.
     */
    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(color);
        g2D.fill(new Ellipse2D.Double(currentSquare[0], currentSquare[1], gridSize-2, gridSize-2));
    }

    /**
     * Returns a random location from the xGridLocations array
     *
     * @return a randomly selected integer from the possible x-coordinate positions
     */
    public int randomXLocation(){
        return xGridLocations[(int)(12*Math.random())];
    }

    /**
     * Returns a random location from the yGridLocations array
     *
     * @return a randomly selected integer from the possible y-coordinate positions
     */
    public int randomYLocation(){
        return yGridLocations[(int)(20*Math.random())];
    }

    /**
     * Returns an integer array containing the x-coordinate of the player
     * at position 0 in the array and the y-coordinate of the player at
     * position 1 in the array
     * e.g. {141,221}
     *
     * @return an array containing both the x and y position of the player counter
     */
    public int[] getPlayerLocation(){
        return currentSquare;
    }

    /**
     * Tells if the player is alive or has been killed
     *
     * @return true if alive, false if dead
     */
    public boolean checkPulse(){
        return alive;
    }

    /**
     * Moves the player counter to a random location on the board. Only works
     * if the player is alive.
     */
    public void teleport(){ //move player marker to a random location
        if (alive){
            currentSquare[0] = randomXLocation();
            currentSquare[1] = randomYLocation();
            repaint();
        }
    }

    /**
     * Sets the "alive" parameter of the object to false, which generally
     * prevents the player from making any more moves until the game is
     * reset. Also changes the colour of the particle to black to show
     * graphically that it has been killed.
     */
    public void killPlayer(){
        alive = false;
        color = Color.black;
        repaint();
    }

    /**
     * Sets the "alive" parameter of the object to true to enable the
     * player to continue taking turns. Also changes the colour of the
     * particle back to red to show graphically that it has not (yet)
     * been killed.
     */
    public void reanimatePlayer(){
        alive = true;
        color = Color.getHSBColor(0, 1, (float) 0.8235);
        repaint();
    }

    /**
     * Moves the player counter to the location specified. Called by GameClickListener
     * which passes in the point the user clicks on, on the game board. This method
     * works out from that information what square to move to.
     *
     * @param xLocation the coordinate on the x-axis where the user clicked
     * @param yLocation the coordinate on the y-axis where the user clicked
     */
    public void userMove(int xLocation, int yLocation){

        if (alive){
            int temp = xGridLocations[0];

            if (xLocation < currentSquare[0] + gridSize*2 && xLocation > currentSquare[0] - gridSize && yLocation < currentSquare[1] + gridSize*2 && yLocation > currentSquare[1] - gridSize){ //ensures player can only move one square at a time in any direction
                //loop ensures that x-coordinate of player marker is always within a square and does not go over grid lines
                for(int i=0; i<xGridLocations.length-1; i++){
                    if (xGridLocations[i] < xLocation){
                        temp=xGridLocations[i];
                    }
                }
                xLocation = temp;

                //loop ensures that y-coordinate of player marker is always within a square and does not go over grid lines
                for(int i=0; i<yGridLocations.length-1; i++){
                    if (yGridLocations[i] < yLocation){
                        temp=yGridLocations[i];
                    }
                }
                yLocation = temp;

                //record new location of player and repaint in that location
                currentSquare[0] = xLocation;
                currentSquare[1] = yLocation;
                this.repaint(0);

            }
        }
        else{
            //Do nothing
            System.out.println("Oh dear, you are dead!");
        }
    }

}
