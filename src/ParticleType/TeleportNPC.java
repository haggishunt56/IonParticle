package ParticleType;

import java.awt.Color;

/**
 * This class controls the TeleportNPC type for the Particle game
 *
 * @author Douglas Pollock
 */
public class TeleportNPC extends SimpleNPC {

    /**
     * Constructor for objects of class TeleportNPC
     *
     * @param gridSize - the size of each square in the GameBoard grid. Used to determine how large the counter should be
     */
    public TeleportNPC(int gridSize)
    {
        setLayout(null);
        setBounds(5, 5, gridSize*12+27, gridSize*20+80);
        setOpaque(false);
        this.color = new Color(0, 196, 36);
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
     * This method is used when resetting the game to return the NPC to life.
     * It has to override the method in the SimpleNPC class as each NPC type
     * has a different colour which must be set in the method.
     */
    @Override
    public void reanimateNPC(){
        alive = true;
        present = true;
        color = Color.getHSBColor((float) 0.364, 1, (float) 0.7686);
        repaint();
    }

    /**
     * This method controls the movement of the TeleportNPC object. When called, it
     * moves the object one square in any direction towards the player counter if it
     * is within 4 squares of the player. If it is outwith this radius, the NPC will
     * teleport to within this radius.
     *
     * @param playerXLocation - the location on the x-plane the NPC is moving towards
     * @param playerYLocation - the location on the y-plane the NPC is moving towards
     */
    @Override
    public void moveToPlayer(int playerXLocation, int playerYLocation){

        if(alive && present){
            playerSquare[0] = playerXLocation;
            playerSquare[1] = playerYLocation;

            //if NPC is more than 4 squares away from player in any direction
            if(currentSquare[0] < playerSquare[0] - (gridSize*4) ||
                    currentSquare[0] > playerSquare[0] + (gridSize*4) ||
                    currentSquare[1] < playerSquare[1] - (gridSize*4) ||
                    currentSquare[1] > playerSquare[1] + (gridSize*4)){
                //teleport to random location
                do{
                    currentSquare[0] = randomXLocation();
                    currentSquare[1] = randomYLocation();
                } while((currentSquare[0] < playerSquare[0] - (gridSize*4) ||
                        currentSquare[0] > playerSquare[0] + (gridSize*4)) ||
                        (currentSquare[1] < playerSquare[1] - (gridSize*4) ||
                                currentSquare[1] > playerSquare[1] + (gridSize*4)));
                //keep teleporting until  within 4 squares of player

                if(currentSquare[0] == playerSquare[0] && currentSquare[1] == playerSquare[1]){
                    //teleport again if landed on same square as player to random location
                    do{
                        currentSquare[0] = randomXLocation();
                        currentSquare[1] = randomYLocation();
                    } while((currentSquare[0] < playerSquare[0] - (gridSize*4) ||
                            currentSquare[0] > playerSquare[0] + (gridSize*4)) ||
                            (currentSquare[1] < playerSquare[1] - (gridSize*4) ||
                                    currentSquare[1] > playerSquare[1] + (gridSize*4)));
                }
            }

            else{
                //move towards player's X coordinate
                if(playerSquare[0] < currentSquare[0]){
                    currentSquare[0] = currentSquare[0] - gridSize;
                }
                else if(playerSquare[0] > currentSquare[0]){
                    currentSquare[0] = currentSquare[0] + gridSize;
                }
                //else do nothing

                //move towards player's Y coordinate
                if(playerSquare[1] < currentSquare[1]){
                    currentSquare[1] = currentSquare[1] - gridSize;
                }
                else if(playerSquare[1] > currentSquare[1]){
                    currentSquare[1] = currentSquare[1] + gridSize;
                }
                //else do nothing
            }

            repaint();
        }
        //else do nothing if NPC not alive or not present
    }
}
