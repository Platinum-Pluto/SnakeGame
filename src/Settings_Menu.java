import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Settings_Menu implements ActionListener {
    String location = "C:";
    JFrame characterMenu = new JFrame("Choose Character");;
    JLabel backgroundImage,snake1,snake2,l;
    JButton c1,c2;
    ImageIcon i1 = new ImageIcon(location+"\\SnakeGame\\src\\icons\\ezgif.com-gif-maker.gif");
    Image green = i1.getImage().getScaledInstance(250,50,Image.SCALE_DEFAULT);
    ImageIcon stored1 =  new ImageIcon(green);
    ImageIcon ic = new ImageIcon(location+"\\SnakeGame\\src\\icons\\FullBody1-removebg-preview.png");
    Image red = ic.getImage().getScaledInstance(250,50,Image.SCALE_DEFAULT);
    ImageIcon stored2 =  new ImageIcon(red);
    Settings_Menu(){
        //Make new frame and such here
        backgroundImage = new JLabel();
        backgroundImage.setBounds(250,250,500,270);
        backgroundImage.setLayout(null);
        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("icons/blueframe2.png"));
        Image i2 = img.getImage().getScaledInstance(500,270,Image.SCALE_DEFAULT);
        ImageIcon i3 =  new ImageIcon(i2);
        backgroundImage.setIcon(i3);
        characterMenu.add(backgroundImage);

        l=new JLabel("Choose");
        l.setVisible(true);
        l.setBounds(205,1,220,20);
        l.setForeground(Color.cyan);
        Font F1=new Font("serif",Font.BOLD,25);
        l.setFont(F1);
        backgroundImage.add(l);
        characterMenu.add(backgroundImage);

        c1 = new JButton(stored1);
        c1.setBounds(120,80,250,50);
        c1.addActionListener(this);
        backgroundImage.add(c1);

        c2 = new JButton(stored2);
        c2.setBounds(120,150,250,50);
        c2.addActionListener(this);
        backgroundImage.add(c2);

        characterMenu.setSize(500,270);
        characterMenu.setLocation(450,250);
        characterMenu.setVisible(true);
        characterMenu.setResizable(false);
        characterMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        File file = new File(location+"\\SnakeGame\\src\\Data\\Character_Data.txt");
        PrintWriter write = null;
        try {
            write = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Add methods and functions below
        if(ae.getSource() == c1){
            write.println("UpHead.gif");
            write.println("DownHead.gif");
            write.println("LeftHead.gif");
            write.println("RightHead.gif");
            write.println("rsz_1b1-removebg-preview.png");
            write.flush();
            write.close();
            characterMenu.setVisible(false);
            try {
                new Main_Menu();
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(ae.getSource() == c2){
            write.println("BUp.png");
            write.println("BDown.png");
            write.println("BLeft.png");
            write.println("BRight.png");
            write.println("BBody.png");
            write.flush();
            write.close();
            characterMenu.setVisible(false);
            try {
                new Main_Menu();
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
