package ParticleType;

import IonParticle.*;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * This class controls the Attacker NPC type for the Particle game
 *
 * @author Douglas Pollock
 */
public class AttackerNPC extends SimpleNPC {

    GameWindow w;

    /**
     * Constructor for objects of class AttackerNPC
     *
     * @param window - the frame on which the AttackAnim object is displayed
     * @param gridSize - the size of each square in the GameBoard grid. Used to determine how large the counter should be
     */
    public AttackerNPC(GameWindow window, int gridSize){
        setLayout(null);
        setBounds(5, 5, gridSize*12+27, gridSize*20+80);
        setOpaque(false);
        this.color = new Color(199, 151, 28);
        this.alive = true;
        this.present = true;
        this.gridSize = gridSize;
        w = window;

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
     * Method called when resetting the game. Sets the colour of the counter
     * and resets variables.
     */
    @Override
    public void reanimateNPC(){
        alive = true;
        present = true;
        color = Color.getHSBColor((float) 0.08431, 1, (float) 0.78);
        repaint();
    }

    /**
     * Method controls the movement of the AttackerNPC towards the player.
     * As it is, the counter moves one square towards the player every time the
     * player moves and attacks. The attack always comes before collisions are
     * calculated so the attackerNPC always attacks after it moves, even if it
     * is killed by another NPC afterwards.
     *
     * @param playerXLocation - the position on the x-axis the NPC needs to move
     * towards
     * @param playerYLocation - the position on the y-axis the NPC needs to move
     * towards
     */
    @Override
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

            //attack
            int[] paintFrom = {currentSquare[0] - gridSize, currentSquare[1] - gridSize};
            int[] paintTo = {currentSquare[0] + gridSize*2, currentSquare[1] + gridSize*2};
            Timer t;

            //paint squares
            AttackAnim attack = new AttackAnim(new Rectangle(
                    paintFrom[0]+3, paintFrom[1]+3, paintTo[0] - paintFrom[0] + 2,
                    paintTo[1] - paintFrom[1] + 2), gridSize);
            w.getContentPane().add(attack);
            w.repaint();

            //kill player if attacked
            if(playerSquare[0] >= currentSquare[0] - gridSize &&
                    playerSquare[0] <= currentSquare[0] + gridSize &&
                    playerSquare[1] >= currentSquare[1] - gridSize &&
                    playerSquare[1] <= currentSquare[1] + gridSize){
                w.getPlayer().killPlayer();
            }

            //code in this block is executed after a delay
            ActionListener taskPerformer = evt -> {
                //remove attack animation
                w.remove(attack);

                //check if any NPCs have touched
                w.calculateCollisions();

                w.repaint();
            };

            //Timer t controls above delay
            t = new Timer(750, taskPerformer);
            t.setRepeats(false);
            t.start();

            repaint();
        }
        //else do nothing if not alive or not present
    }
}
