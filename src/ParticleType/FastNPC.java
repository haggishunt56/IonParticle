package ParticleType;

import java.awt.Color;

/**
 * This class controls the Fast NPC type for the Particle game
 *
 * @author Douglas Pollock
 */
public class FastNPC extends SimpleNPC {

    /**
     * Constructor for objects of class FastNPC
     *
     * @param gridSize - the size of each square in the GameBoard grid. Used to determine how large the counter should be
     */
    public FastNPC(int gridSize)
    {
        setLayout(null);
        setBounds(5, 5, gridSize*12+27, gridSize*20+80);
        setOpaque(false);
        this.color = new Color(21, 5, 255);
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
        color = Color.getHSBColor((float) 0.678, (float) 0.9816, 1);
        repaint();
    }

    /**
     * This method controls the movement of the FastNPC object. When called, it
     * moves the object towards the player counter. The FastNPC moves one square
     * diagonally each turn OR two squares up, down, left or right. This method
     * is set up so that the object moves once. IN order to move twice, the
     * method must be called a second time. The GameClickListener manages this
     * by calling moveToPlayer, checking for collisions, then calling
     * moveToPlayer again.
     *
     * @param playerXLocation - the location on the x-plane the NPC needs to
     * move towards
     * @param playerYLocation - the location on the y-plane the NPC needs to
     * move towards
     */
    @Override
    public void moveToPlayer(int playerXLocation, int playerYLocation){

        if(alive && present){
            playerSquare[0] = playerXLocation;
            playerSquare[1] = playerYLocation;

            if(!secondMove && !moved){
                //if player X coordinate = NPC X coordinate, move in Y direction
                if(currentSquare[0] == playerSquare[0]){
                    if(currentSquare[1] < playerSquare[1]){
                        currentSquare[1] = currentSquare[1] + gridSize;
                    }
                    else if(currentSquare[1] > playerSquare[1]){
                        currentSquare[1] = currentSquare[1] - gridSize;
                    }
                    secondMove = true;
                }
                //if player Y coordinate = NPC Y coordinate, move in X direction
                else if(currentSquare[1] == playerSquare[1]){
                    if(currentSquare[0] < playerSquare[0]){
                        currentSquare[0] = currentSquare[0] + gridSize;
                    }
                    else if(currentSquare[0] > playerSquare[0]){
                        currentSquare[0] = currentSquare[0] - gridSize;
                    }
                    secondMove = true;
                }
                //if neither is true, move once in both directions
                else{
                    //move towards player's X coordinate
                    if(playerSquare[0] < currentSquare[0]){
                        currentSquare[0] = currentSquare[0] - gridSize;
                    }
                    else if(playerSquare[0] > currentSquare[0]){
                        currentSquare[0] = currentSquare[0] + gridSize;
                    }

                    //move towards player's Y coordinate (once only)
                    if(playerSquare[1] < currentSquare[1]){
                        currentSquare[1] = currentSquare[1] - gridSize;
                    }
                    else if(playerSquare[1] > currentSquare[1]){
                        currentSquare[1] = currentSquare[1] + gridSize;
                    }
                    moved = true;
                }
            }
            else if(secondMove && !moved){
                //if player X coordinate = NPC X coordinate, move in Y direction
                if(currentSquare[0] == playerSquare[0]){
                    if(currentSquare[1] < playerSquare[1]){
                        currentSquare[1] = currentSquare[1] + gridSize;
                    }
                    else if(currentSquare[1] > playerSquare[1]){
                        currentSquare[1] = currentSquare[1] - gridSize;
                    }
                }
                //if player Y coordinate = NPC Y coordinate, move in X direction
                else if(currentSquare[1] == playerSquare[1]){
                    if(currentSquare[0] < playerSquare[0]){
                        currentSquare[0] = currentSquare[0] + gridSize;
                    }
                    else if(currentSquare[0] > playerSquare[0]){
                        currentSquare[0] = currentSquare[0] - gridSize;
                    }
                }
            }
            //else do nothing

            repaint();
        }
        //else do nothing if NPC not alive or not present
    }
}
