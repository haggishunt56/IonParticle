package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class listens for the user clicking on the "About" button on the main
 * game screen and displays the relevant window and information. Information to
 * be displayed in the about window is imported from an external HTML text file.
 *
 * @author Douglas Pollock
 */
public class AboutButtonListener implements ActionListener{

    /**
     * Actions to take when the user clicks on the About button. This method
     * is called when the user clicks on the relevant button and controls the
     * window to be displayed
     *
     * @param e - the event created when the user clicks on the board
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //Create window and objects to be contained within
        JFrame aboutWindow = new JFrame();
        JPanel aboutPanel = new JPanel();
        JLabel aboutLabel = new JLabel();

        aboutLabel.setText("\u00a9"); //add Copyright symbol to text

        //check if instruction file exists
        //TO DO

        //read instruction file
        try {
            File file = new File("C:\\Users\\Dougie\\Documents\\Java\\Particle2.0\\Information\\About.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) aboutLabel.setText(aboutLabel.getText() + sc.nextLine());
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(InfoButtonListener.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(aboutLabel.getText());

        aboutLabel.setText(String.format("<html><div style=\"width:%dpx;\">%s</div></html>", 200, aboutLabel.getText()));

        //display instructions in new window
        aboutWindow.setTitle("About");
        aboutWindow.setSize(305,210);
        aboutWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        aboutWindow.setResizable(false);

        aboutPanel.add(aboutLabel);
        aboutWindow.add(aboutPanel);

        aboutWindow.setVisible(true);
    }
}
