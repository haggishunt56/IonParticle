package Listeners;

import IonParticle.AttackAnim;
import IonParticle.GameWindow;
import ParticleType.*;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * This class listens for the user clicking on the "Attack" button on the main
 * game screen. When clicked, the listener displays the attack animation and
 * ensures that the relevant counters are removed from the board depending on
 * collision rules.
 *
 * @author Douglas Pollock
 */
public class AttackButtonListener implements ActionListener {

    IonParticle.GameWindow w;
    int gridSize;

    /**
     * Constructor for objects of Class AttackButtonListener
     *
     * @param w - The frame on which the AttackAnim should be displayed
     * @param gridSize - The size of each square in the game grid
     */
    public AttackButtonListener(GameWindow w, int gridSize){
        this.w = w;
        this.gridSize = gridSize;
    }

    /**
     * Actions to take when the user clicks on the Attack button. This method
     * is called when the user clicks on the relevant button and controls the
     * objects to be displayed on screen and any associated timings
     *
     * @param e - the event created when the user clicks on the board
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(w.getAvailableAttacks()>0){
            if(!w.getPlayer().checkPulse()){
                //do nothing if player is dead
                System.out.println("Oh dear, you are dead!");
            }
            else{
                int[] paintFrom = {w.getPlayer().getPlayerLocation()[0] - gridSize,
                        w.getPlayer().getPlayerLocation()[1] - gridSize};
                int[] paintTo = {w.getPlayer().getPlayerLocation()[0] + gridSize*2,
                        w.getPlayer().getPlayerLocation()[1] + gridSize*2};
                Timer t;

                //paint squares
                AttackAnim attack = new AttackAnim(new Rectangle(paintFrom[0]+3,
                        paintFrom[1]+3, paintTo[0] - paintFrom[0] + 2,
                        paintTo[1] - paintFrom[1] + 2), gridSize);
                w.getContentPane().add(attack);
                w.repaint();

                //delete NPCs in range (not kill)
                for (int i=0;i<w.getNPCs().length;i++){ //loop through each NPC in array
                    if(w.getNPCs()[i] != null && w.getNPCs()[i].checkPulse()){
                        boolean remove = false;
                        for(int j=0;j<gridSize*3;j=j+gridSize){ //check all columns in kill radius
                            for(int k=0;k<gridSize*3;k=k+gridSize){ //check all rows in kill radius
                                if (w.getNPCs()[i].getNPCLocation()[0] == paintFrom[0] + j &&
                                        w.getNPCs()[i].getNPCLocation()[1] == paintFrom[1] + k)
                                { //if present in any checked square
                                    w.removeNPC(i); //remove NPC
                                    remove = true;
                                }
                            }
                        }
                        if (remove){w.getNPCs()[i] = null;}
                    }
                    //else do nothing if NPC at array position i is null or NPC is dead
                }

                //code in this block is executed after a delay
                ActionListener taskPerformer = evt -> {

                    //move each NPC towards player marker
                    for(int i=0;i<w.getNPCs().length;i++){
                        if(w.getNPCs()[i] != null){
                            w.getNPCs()[i].moveToPlayer(w.getPlayer().getPlayerLocation()[0],
                                    w.getPlayer().getPlayerLocation()[1]);
                        }
                        //else do nothing if value at array position i is null
                    }

                    w.calculateCollisions();

                    //move each Fast NPC towards player marker again
                    for(int i=0;i<w.getNPCs().length;i++){
                        if(w.getNPCs()[i] != null){
                            if(w.getNPCs()[i] instanceof FastNPC){
                                w.getNPCs()[i].moveToPlayer(w.getPlayer().getPlayerLocation()[0],
                                        w.getPlayer().getPlayerLocation()[1]);
                            }
                            w.getNPCs()[i].moved = false;
                            w.getNPCs()[i].secondMove = false;
                        }
                        //else do nothing if value at array position i is null
                    }

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

                w.setAvailableAttacks(0); //reduce number of available attacks
                w.attackInfo.setText("Attacks remaining: " + w.getAvailableAttacks()); //update label
            }
        }
        //else do nothing - no attacks remaining

    }
}
