/* Tic Tac Toe Game Application
 * The AudioCore Class has core functionality for Playing Sounds and Music.
*/

package core;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


public class AudioCore {
    private static File musicFile;
    private static File soundFile;
    private static Media music;
    private static Media sound;
    
    private static MediaPlayer musicPlayer;
    private static MediaPlayer soundPlayer;

    //Plays BGM
    public static void playBGM(String path) {
        System.out.println(resourceLoader.getAsset(path));
        musicFile = new File(resourceLoader.getAsset(path));
        music = new Media(musicFile.toURI().toString());
        musicPlayer = new MediaPlayer(music);
        musicPlayer.setVolume(0.2);
        musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        musicPlayer.play();
    }

    //Plays Sound
    public static void playSound(String path) {
        soundFile = new File(path);
        sound = new Media(soundFile.toURI().toString());
        soundPlayer = new MediaPlayer(sound);
        soundPlayer.stop();
        soundPlayer.seek(Duration.ZERO);
        soundPlayer.play();
    }
}
