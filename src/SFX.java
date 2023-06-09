import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SFX {
    Clip clip;
    String f;
    SFX(String f1) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.f = f1;
        File file = new File(f);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
    }

    public void setF(String f) {
        this.f = f;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }
}
