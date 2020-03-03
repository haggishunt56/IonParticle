package Listeners;

import ParticleType.*;
import IonParticle.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Listens for clicks on the game board and moves the player accordingly.
 *
 * @author Douglas Pollock
 */
public class GameClickListener extends MouseAdapter{

    GameWindow w;
    int gridSize;

    /**
     * Constructor for objects of class GameClickListener
     *
     * @param w - the frame in which the object is listening for actions
     * @param gridSize - this size in pixels of each square on the grid
     */
    public GameClickListener(GameWindow w, int gridSize){
        this.w = w;
        this.gridSize = gridSize;

    }

    /**
     * Actions to take when the user clicks on the game board. This method
     * is called when the user clicks on the board and ensures that the player
     * object moves to the selected grid square and that all NPC objects move
     * towards the player afterwards.
     *
     * @param e - the MouseEvent created when the user clicks on the board
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        //variables account for different positions on grid and on frame.
        int minClickableX = w.getPlayer().getPlayerLocation()[0] - gridSize;
        int maxClickableX = w.getPlayer().getPlayerLocation()[0] + gridSize*2;
        int minClickableY = w.getPlayer().getPlayerLocation()[1] - gridSize;
        int maxClickableY = w.getPlayer().getPlayerLocation()[1] + gridSize*2;

        //clicks are slightly offset from where player is located. These lines of code aligns them.
        int clickedX = e.getX()-8;
        int clickedY = e.getY()-30;

        if(clickedX < minClickableX || clickedX > maxClickableX || clickedY < minClickableY || clickedY > maxClickableY){
            //if clicked more than 1 square away in any direction, do nothing.
            System.out.println("Can't move. Too far away");
        }
        else{
            if(e.getButton() == MouseEvent.BUTTON1) { //if left-clicked
                if(clickedY < 1 || clickedY > gridSize*20) { //if out of bounds (y-coordinate)
                    //do nothing
                }
                else{
                    if(clickedX < 1 || clickedX > gridSize*12) { //if out of bounds (x-coordinate)
                        //do nothing
                    }
                    else{

                        //move player to square clicked on
                        w.getPlayer().userMove(clickedX, clickedY);

                        //move each NPC towards player marker
                        for(int i=0;i<w.getNPCs().length;i++){
                            if(w.getNPCs()[i] == null){
                            }
                            else{
                                w.getNPCs()[i].moveToPlayer(w.getPlayer().getPlayerLocation()[0], w.getPlayer().getPlayerLocation()[1]);
                            }
                        }

                        w.calculateCollisions();

                        //move each Fast NPC towards player marker
                        for(int i=0;i<w.getNPCs().length;i++){
                            if(w.getNPCs()[i] == null){
                            }
                            else{
                                if(w.getNPCs()[i] instanceof FastNPC){
                                    w.getNPCs()[i].moveToPlayer(w.getPlayer().getPlayerLocation()[0], w.getPlayer().getPlayerLocation()[1]);
                                }
                                w.getNPCs()[i].moved = false;
                                w.getNPCs()[i].secondMove = false;
                            }
                        }

                        w.calculateCollisions();

                        w.repaint();
                    }
                }
            }
        }
        //if right-clicked
        if(e.getButton() == MouseEvent.BUTTON3) {
            System.out.println("X- coordinate: " + e.getX() + ", Y- coordinate: " + e.getY());
        }

    }

}
