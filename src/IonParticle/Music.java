package IonParticle;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The music class controls the song playing in the background of the game.
 * The song playing is Battle of the Arps by Eduard Popescu. Used with permission.
 */
public class Music extends Thread{
    private AudioPlayer p;

    public Music() {
    }

    /**
     * this method defines that the song to be played, as defined in the
     * FILEPATH variable, should be played on a continuous loop.
     */
    public void playMusic(){
        boolean playback = true;

        try {
            String FILEPATH = "C:\\Users\\Dougie\\Documents\\Java\\IonParticle\\src\\reference\\BotA.wav";
            System.out.println("Now Playing: " + FILEPATH);
            AudioStream as = new AudioStream(new FileInputStream(FILEPATH));

            p.player.start(as);
            do {
            } while (as.available() > 0 && playback);
            if (playback) {
                playMusic();
            }
        }
        catch (IOException ex) {
            Logger.getLogger(Music.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void run() {
        playMusic();
    }
}
