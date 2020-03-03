package Listeners;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * This class listens for the user clicking on the "Instructions" button on the
 * main game screen. When clicked, the listener displays a new JFrame with tabs
 * explaining details on how to play and what each particle type does .
 *
 * @author Douglas Pollock
 */
public class InfoButtonListener implements ActionListener {

    /**
     * Actions to take when user clicks the "Instructions" button - pulls
     * details from relevant .txt documents and displays in a JFrame.
     *
     * @param e - the event created when the user clicks on the board
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //Create window and objects to be contained within
        JFrame infoWindow = new JFrame();
        JTabbedPane tabbedPane = new JTabbedPane();
        JLabel[] tabLabel = new JLabel[8];
        JPanel[] tabPanel = new JPanel[8];
        File[] tabInfo = new File[8];
        ImageIcon[] tabIcon = new ImageIcon[8];
        JScrollPane scrollPane;

        for (int i=0;i<=7;i++) {
            tabLabel[i] = new JLabel ();
            tabLabel[i].setFont(new Font("Verdana", Font.PLAIN, 11));
            tabPanel[i] =  new JPanel();
        }

        //check if instruction file exists
        //TO DO

        //read tab0 file
        try {
            tabInfo[0] = new File("C:\\Users\\Dougie\\Documents\\Java\\Particle2.0\\Information\\About the game.txt");
            Scanner sc = new Scanner(tabInfo[0]);
            while (sc.hasNextLine()) tabLabel[0].setText(tabLabel[0].getText() + sc.nextLine());
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(InfoButtonListener.class.getName()).log(Level.SEVERE, null, ex);
        }

        //read tab1 file
        try {
            tabInfo[1] = new File("C:\\Users\\Dougie\\Documents\\Java\\Particle2.0\\Information\\How to play.txt");
            Scanner sc = new Scanner(tabInfo[1]);
            while (sc.hasNextLine()) tabLabel[1].setText(tabLabel[1].getText() + sc.nextLine());
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(InfoButtonListener.class.getName()).log(Level.SEVERE, null, ex);
        }

        //read remainder of files
        for(int i=2;i<=7;i++){
            try {
                tabInfo[i] = new File("C:\\Users\\Dougie\\Documents\\Java\\Particle2.0\\Information\\" + i + ".txt");
                Scanner sc = new Scanner(tabInfo[i]);
                while (sc.hasNextLine()) tabLabel[i].setText(tabLabel[i].getText() + sc.nextLine());
            }
            catch (FileNotFoundException ex) {
                Logger.getLogger(InfoButtonListener.class.getName()).log(Level.SEVERE, null, ex);
            }
            tabIcon[i] = new ImageIcon("C:\\Users\\Dougie\\Documents\\Java\\Particle2.0\\Information\\" + i + ".png");
        }

        //format label widths
        for(int i=2;i<=7;i++){
            tabLabel[i].setText(String.format("<html><div style=\"width:%dpx;\">%s</div></html>", 260, tabLabel[i].getText()));
        }
        tabLabel[0].setText(String.format("<html><div style=\"width:%dpx;\">%s</div></html>", 260, tabLabel[0].getText()));
        tabLabel[1].setText(String.format("<html><div style=\"width:%dpx;\">%s</div></html>", 246, tabLabel[1].getText()));

        scrollPane = new JScrollPane(tabLabel[1]);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setSize(new Dimension(337, 317));
        scrollPane.setPreferredSize(new Dimension(337, 317));

        //add instructions to frame
        tabbedPane.addTab("About the game", tabPanel[0]);
        tabPanel[0].add(tabLabel[0]);
        tabbedPane.addTab("How to play", tabPanel[1]);
        tabPanel[1].add(scrollPane);
        for(int i=2;i<=7;i++){
            tabbedPane.addTab("", tabIcon[i], tabPanel[i]);
            tabPanel[i].add(tabLabel[i]);
        }

        //prep and display frame
        infoWindow.setTitle("Instructions");
        infoWindow.setSize(365,415);
        infoWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        infoWindow.setResizable(false);
        infoWindow.add(tabbedPane);
        infoWindow.setVisible(true);

    }

}
