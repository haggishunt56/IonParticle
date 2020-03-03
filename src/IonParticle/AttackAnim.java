package IonParticle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 * This class is created by the AttackButtonListener to display an orange grid
 * behind the player to show the squares they are attacking.
 *
 * @author Douglas Pollock
 */
public class AttackAnim extends JPanel {

    int gridSize;

    /**
     * Constructor for objects of class AttackAnim
     *
     * @param rectangle - the dimensions of the AttackAnim object to draw on the
     * screen.
     * @param gridSize - the size in pixels of each square in the game grid
     */
    public AttackAnim(Rectangle rectangle, int gridSize)
    {
        setLayout(null);
        setBounds(rectangle);
        setOpaque(true);
        this.gridSize = gridSize;
    }

    /**
     * Method called when object is created. Defines how the object will be
     * drawn on the screen by providing colour and dimensions.
     *
     * @param g - graphics object created when AttackAnim is drawn.
     */
    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.getHSBColor((float) 0.06714, (float) 0.667, (float) 0.76));
        Graphics2D g2D = (Graphics2D) g;
        for(int j=1;j<gridSize*3;j=j+gridSize)
        {
            for(int i=1; i<gridSize*3; i=i+gridSize)
            {
                g2D.fill(new Rectangle2D.Double(i,j,gridSize,gridSize));
            }
        }
    }
}
