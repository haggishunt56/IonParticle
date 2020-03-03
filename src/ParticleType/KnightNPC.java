package ParticleType;

import java.awt.Color;

/**
 * This class controls the Fast NPC type for the Particle game
 *
 * @author Douglas Pollock
 */
public class KnightNPC extends SimpleNPC {

    /**
     * Constructor for objects of type KnightNPC
     *
     * @param gridSize - the size of each square in the GameBoard grid. Used to determine how large the counter should be
     */
    public KnightNPC(int gridSize)
    {
        setLayout(null);
        setBounds(5, 5, gridSize*12+27, gridSize*20+80);
        setOpaque(false);
        this.color = new Color(214, 0, 178);
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
        color = Color.getHSBColor((float) 0.8611, 1, (float) 0.84);
        repaint();
    }

    /**
     * This method controls the movement of the KnightNPC object. When called, it moves the object towards the player
     * counter. The KnightNPC moves two squares on the X or Y plane, then one square on the other. The result is that
     * it moves in an "L" shape like a knight in a game of chess.
     *
     * @param playerXLocation - the location on the x-plane the NPC needs to move towards
     * @param playerYLocation - the location on the y-plane the NPC needs to move towards
     */
    @Override
    public void moveToPlayer(int playerXLocation, int playerYLocation){

        if(alive && present){
            playerSquare[0] = playerXLocation;
            playerSquare[1] = playerYLocation;

            if(currentSquare[0] != playerSquare[0] && currentSquare[1] != playerSquare[1]){
                for(int i=1;i<=23;i++){
                    //if player X coordinate is less than NPC X coordinate, move twice in Y direction & once in X direction
                    if(currentSquare[0] == playerSquare[0] - (gridSize*i)){
                        if(currentSquare[1] < playerSquare[1]){
                            currentSquare[1] = currentSquare[1] + gridSize*2;
                            currentSquare[0] = currentSquare[0] + gridSize;
                        }
                        else if(currentSquare[1] > playerSquare[1]){
                            currentSquare[1] = currentSquare[1] - gridSize*2;
                            currentSquare[0] = currentSquare[0] + gridSize;
                        }
                        //else do nothing

                        break;
                    }
                    //if player X coordinate is more than NPC X coordinate, move twice in Y direction & once in X direction
                    else if(currentSquare[0] == playerSquare[0] + (gridSize*i)){
                        if(currentSquare[1] < playerSquare[1]){
                            currentSquare[1] = currentSquare[1] + gridSize*2;
                            currentSquare[0] = currentSquare[0] - gridSize;
                        }
                        else if(currentSquare[1] > playerSquare[1]){
                            currentSquare[1] = currentSquare[1] - gridSize*2;
                            currentSquare[0] = currentSquare[0] - gridSize;
                        }
                        //else do nothing
                        break;
                    }
                    //if player Y coordinate is less than NPC Y coordinate, move twice in X direction & once in Y direction
                    else if(currentSquare[1] == playerSquare[1] - (gridSize*i)){
                        if(currentSquare[0] < playerSquare[0]){
                            currentSquare[0] = currentSquare[0] + gridSize*2;
                            currentSquare[1] = currentSquare[1] + gridSize;
                        }
                        else if(currentSquare[0] > playerSquare[0]){
                            currentSquare[1] = currentSquare[1] + gridSize;
                            currentSquare[0] = currentSquare[0] - gridSize*2;
                        }
                        //else do nothing
                        break;
                    }
                    //if player Y coordinate is more than NPC Y coordinate, move twice in X direction & once in Y direction
                    else if(currentSquare[1] == playerSquare[1] + (gridSize*i)){
                        if(currentSquare[0] < playerSquare[0]){
                            currentSquare[0] = currentSquare[0] + gridSize*2;
                            currentSquare[1] = currentSquare[1] - gridSize;
                        }
                        else if(currentSquare[0] > playerSquare[0]){
                            currentSquare[0] = currentSquare[0] - gridSize*2;
                            currentSquare[1] = currentSquare[1] - gridSize;
                        }
                        //else do nothing
                        break;
                    }
                }
            }
            //if player X coordinate = NPC X coordinate, move twice in Y direction & once in X direction
            else if(currentSquare[0] == playerSquare[0]){
                if(currentSquare[1] < playerSquare[1]){
                    currentSquare[1] = currentSquare[1] + gridSize*2;
                    currentSquare[0] = currentSquare[0] + gridSize;
                }
                else if(currentSquare[1] > playerSquare[1]){
                    currentSquare[1] = currentSquare[1] - gridSize*2;
                    currentSquare[0] = currentSquare[0] - gridSize;
                }
                //else do nothing
            }
            //if player Y coordinate = NPC Y coordinate, move twice in X direction & once in Y direction
            else if(currentSquare[1] == playerSquare[1]){
                if(currentSquare[0] < playerSquare[0]){
                    currentSquare[0] = currentSquare[0] + gridSize*2;
                    currentSquare[1] = currentSquare[1] + gridSize;
                }
                else if(currentSquare[0] > playerSquare[0]){
                    currentSquare[0] = currentSquare[0] - gridSize*2;
                    currentSquare[1] = currentSquare[1] - gridSize;
                }
                //else do nothing
            }
            repaint();
        }
    }
}
