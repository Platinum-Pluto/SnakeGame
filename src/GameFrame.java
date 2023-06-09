import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameFrame extends JFrame {
    SFX eat,die;
    int flag = 0;
    GameFrame(SFX eat, SFX die, String u, String d, String hL, String hR, String mB) {
        this.eat = eat;
        this.die = die;
        GamePanel a = new GamePanel(eat,die,u,d,hL,hR,mB);
        this.add(a);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}


