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
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Choose_Character implements ActionListener {
    String difficulties [] = {"Easy","Medium","Hard"}, data = "Easy";
    String location = "C:";
    JFrame characterMenu = new JFrame("Settings");;
    JLabel backgroundImage,l;
    JButton c1,c2,l1,l2;
    JComboBox cb;

    Choose_Character(){
       //Make new frame and such here
        backgroundImage = new JLabel();
        backgroundImage.setBounds(250,250,500,400);
        backgroundImage.setLayout(null);
        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("icons/checkthismode_surf.png"));
        Image i2 = img.getImage().getScaledInstance(500,400,Image.SCALE_DEFAULT);
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



        c1 = new JButton();
        c1.setBounds(120,80,250,50);
        c1.setIcon(iconImage(250,50,"ezgif.com-gif-maker.gif",c1));
        backgroundImage.add(c1);

        c2 = new JButton();
        c2.setBounds(120,150,250,50);
        c2.setIcon(iconImage(250,50,"FullBody1-removebg-preview.png",c2));
        backgroundImage.add(c2);

        cb = new JComboBox(difficulties);
        cb.setBounds(120,250,100,25);
        backgroundImage.add(cb);
        selector();

        l1 = new JButton();
        l1.setBounds(220,155,500,250);
        l1.setIcon(iconImage(500,250,data+"_noBG.gif",l1));
        backgroundImage.add(l1);

        l2 = new JButton();
        l2.setBounds(120,300,100,25);
        l2.setIcon(iconImage(100,25,"text-1660885753444.png",l2));
        backgroundImage.add(l2);

        characterMenu.setSize(500,400);
        characterMenu.setLocation(450,250);
        characterMenu.setVisible(true);
        characterMenu.setResizable(false);
        characterMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

   public ImageIcon iconImage(int width, int height, String f1, JButton d){
        d.setFocusable(false);
        d.addActionListener(this);
        d.setOpaque(false);
        d.setBorderPainted(false);
        d.setContentAreaFilled(false);
        d.setBackground(new Color(1f,0f,0f,0f));
        ImageIcon i = new ImageIcon(location+"\\SnakeGame\\src\\icons\\"+f1);
        Image i0 = i.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT);
        return new ImageIcon(i0);
    }

    ImageIcon iconImage1(int width, int height, String f1, JButton d){
        d.setFocusable(false);
        d.addActionListener(this);
        d.setOpaque(false);
        d.setBorderPainted(false);
        d.setContentAreaFilled(false);
        d.setBackground(new Color(1f,0f,0f,0f));
        ImageIcon i = new ImageIcon(location+"\\SnakeGame\\src\\icons\\"+f1);
        Image i0 = i.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT);
        return new ImageIcon(i0);
    }

   public void selector(){
        data = (String) cb.getItemAt(cb.getSelectedIndex());
        File file = new File (location+"\\SnakeGame\\src\\Data\\Difficulty.txt");
        PrintWriter write = null;
        try {
            write = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        write.print(data);
        write.flush();
        write.close();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if(ae.getSource() == l2){
            selector();
            l1.setIcon(iconImage(500,250,data+"_noBG.gif",l1));
            JOptionPane.showMessageDialog(null,"Difficulty has been set to "+data);
        }

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


