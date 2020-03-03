package Listeners;

import IonParticle.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listens for clicks on the teleport button and performs actions accordingly
 * to move the player to a random location on the board.
 *
 * @author Douglas Pollock
 */
public class TeleButtonListener implements ActionListener {

    GameWindow w;

    /**
     * Constructor for objects of class TeleButtonListener
     *
     * @param w the GameBoard object on which to paint the player object.
     */
    public TeleButtonListener(GameWindow w){
        this.w = w;
    }

    /**
     * Method defines actions to take to teleport the player. Checks that player is alive
     * and has teleports remaining. Teleports the player and checks that they have not
     * landed on the same square as an NPC. If they have, teleports the player again.
     *
     * Note that there is still a very small (0.34%) chance that the user will still end
     * up teleporting onto an NPC's square.
     *
     * @param e - the ActionEvent object created when the user clicks the Teleport button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(w.getAvailableTeles() > 0){ //only works if player has enough teleports available
            w.getPlayer().teleport(); //teleport player to random square
            for(int i=0;i<w.getNPCs().length;i++){
                if(w.getNPCs()[i] == null){
                    continue; //prevents nullPointerException if array entry is empty
                }
                if(w.getPlayer().getPlayerLocation()[0] == w.getNPCs()[i].getNPCLocation()[0] && w.getPlayer().getPlayerLocation()[1] == w.getNPCs()[i].getNPCLocation()[1]){
                    w.getPlayer().teleport(); //if player teleports to the same square as an NPC (22.7% chance, based on 5000 tests), teleport again
                    for(int j=0;j<w.getNPCs().length;j++){
                        if(w.getNPCs()[j] == null){
                            continue; //prevents nullPointerException if array entry is empty
                        }
                        if(w.getPlayer().getPlayerLocation()[0] == w.getNPCs()[j].getNPCLocation()[0] && w.getPlayer().getPlayerLocation()[1] == w.getNPCs()[j].getNPCLocation()[1]){
                            w.getPlayer().teleport(); //if player teleports to the same square as an NPC again (5.12% chance, based on 5000 tests), teleport again
                        }
                    }
                }
            }
            //Note - even after these double-checks, there is a 0.34% chance that the player will still teleport to the same square as an NPC

            w.setAvailableTeles(0); //reduce number of available teleports
            w.teleInfo.setText("Teleports remaining: " + w.getAvailableTeles()); //update label
        }
        //else do nothing - no teleports remaining
    }
}
