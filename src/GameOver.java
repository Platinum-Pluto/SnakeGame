import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameOver implements ActionListener {
    Label l1,l2;
    JButton b;
    JFrame gameOver = new JFrame("Game Over!!!");

    GameOver(int flag, int value){
        if(flag == 1){
            l1 = new Label("NEW HIGH SCORE!!!");
            l1.setBounds(100, 20, 200, 40);
            l1.setFocusable(false);
            l1.setFont(new Font("Ink Free",Font.BOLD,20));
            gameOver.add(l1);

            l2 = new Label("Your Score: "+value);
            l2.setBounds(120, 80, 200, 30);
            l2.setFocusable(false);
            l2.setFont(new Font("Ink Free",Font.BOLD,20));
            gameOver.add(l2);

            b = new JButton("<<< Back to main menu");
            b.setBounds(100, 150, 200, 30);
            b.setFocusable(false);
            b.addActionListener(this);
            gameOver.add(b);
        }
        else{
            l1 = new Label("Game Over!!!");
            l1.setBounds(120, 20, 200, 40);
            l1.setFocusable(false);
            l1.setFont(new Font("Ink Free",Font.BOLD,25));
            gameOver.add(l1);

            l2 = new Label("Your Score: "+value);
            l2.setBounds(120, 80, 200, 30);
            l2.setFocusable(false);
            l2.setFont(new Font("Ink Free",Font.BOLD,20));
            gameOver.add(l2);

            b = new JButton("<<< Back to main menu");
            b.setBounds(100, 150, 200, 30);
            b.setFocusable(false);
            b.addActionListener(this);
            gameOver.add(b);
        }


        gameOver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOver.setSize(600,600);
        gameOver.setLayout(null);
        gameOver.setVisible(true);
        gameOver.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b){
            gameOver.setVisible(false);

            try {
                new Main_Menu();
            } catch (UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
