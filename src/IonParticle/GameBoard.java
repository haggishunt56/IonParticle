package IonParticle;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;


/**
 * The GameBoard class draws the game grid on the game window.
 *
 * @author Douglas Pollock
 */
public class GameBoard extends JPanel {

    private int gridSize;

    /**
     * Constructor for objects of class GameBoard
     *
     * @param rectangle - dimensions of the GameBoard object to be drawn
     * @param gridSize  - the size in pixels of each square in the game grid
     */
    public GameBoard(Rectangle rectangle, int gridSize)
    {
        setLayout(null);
        setBounds(rectangle);
        setOpaque(false);
        this.gridSize = gridSize;
    }

    /**
     * Method called when object is created. Defines how the object will be
     * drawn on the screen by providing the number of rows, columns, and the
     * size of each square on the grid.
     *
     * @param g - graphics object created when GameBoard is drawn.
     */
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2D = (Graphics2D) g;
        for(int j=5;j<=gridSize*20;j=j+gridSize)
        {
            for(int i=5;i<=gridSize*12;i=i+gridSize)
            {
                g2D.draw(new Rectangle2D.Double(i,j,gridSize,gridSize));
            }
        }
    }
}
