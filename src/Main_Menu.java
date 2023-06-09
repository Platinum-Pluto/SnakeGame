import javax.lang.model.type.NullType;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main_Menu implements ActionListener {
        int tap = 0;
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<String> character = new ArrayList<String>();
        JFrame menu = new JFrame("Menu");
        JButton b1,b2,b4,b5,b;
        JToggleButton b3;
        JLabel backgroundImage,l;
        String fileLocation = "C:"; //Change to only C:
        String up,down,left,right,bod;
        SFX sound = new SFX(fileLocation+"\\SnakeGame\\SFX\\Rising.wav");
        SFX eatSound = new SFX(fileLocation+"\\SnakeGame\\SFX\\Crunch sound effect No copyright_Trim.wav");
        SFX dieSound = new SFX(fileLocation+"\\SnakeGame\\SFX\\GameOver.wav");
        Main_Menu() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

            backgroundImage = new JLabel();
            backgroundImage.setBounds(0,0,420,420);
            backgroundImage.setLayout(null);
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/barren_surf.png"));
            Image i2 = i1.getImage().getScaledInstance(420,420,Image.SCALE_DEFAULT);
            ImageIcon i3 =  new ImageIcon(i2);
            backgroundImage.setIcon(i3);
            menu.add(backgroundImage);

            sound.clip.start();


            b1 = new JButton();
            b1.setBounds(100, 120, 200, 40);
            b1.setIcon(iconImage(200,30,"text-1660794911984",b1));
            backgroundImage.add(b1);

            l = new JLabel();
            l.setBounds(130, 20, 145, 40);
            l.setLayout(null);
            ImageIcon a1 = new ImageIcon(ClassLoader.getSystemResource("icons/Capture-removebg-preview.png"));
            Image a2 = a1.getImage().getScaledInstance(145,40,Image.SCALE_DEFAULT);
            ImageIcon a3 =  new ImageIcon(a2);
            l.setIcon(a3);
            backgroundImage.add(l);

            b2 = new JButton();
            b2.setBounds(100, 170, 200, 40);
            b2.setIcon(iconImage(200,30,"text-1660794987583",b2));
            backgroundImage.add(b2);

            b3 = new JToggleButton();
            b3.setBounds(100, 220, 200, 40);
            b3.setIcon(iconImage1(200,30,"text-1660795016051",b3));
            backgroundImage.add(b3);

            b5 = new JButton("Choose Snake");
            b5.setBounds(100, 270, 200, 40);
            b5.setIcon(iconImage(200,30,"text-1660795044836",b5));
            backgroundImage.add(b5);

            b4 = new JButton();
            b4.setBounds(100, 320, 200, 40);
            b4.setIcon(iconImage(200,30,"text-1660795055941",b4));
            backgroundImage.add(b4);

            menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menu.setSize(420,420);
            menu.setLayout(null);
            menu.setVisible(true);
            menu.setLocationRelativeTo(null);
            menu.setResizable(false);
        }

    ImageIcon iconImage(int width, int height, String f1, JButton b){
        b.setFocusable(false);
        b.addActionListener(this);
        b.setOpaque(false);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setBackground(new Color(1f,0f,0f,0f));
        ImageIcon i = new ImageIcon(ClassLoader.getSystemResource("icons/"+f1+".png"));
        Image i0 = i.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT);
        return new ImageIcon(i0);
    }

    ImageIcon iconImage1(int width, int height, String f1, JToggleButton b){
        b.setFocusable(false);
        b.addActionListener(this);
        b.setOpaque(false);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setBackground(new Color(1f,0f,0f,0f));
        ImageIcon i = new ImageIcon(ClassLoader.getSystemResource("icons/"+f1+".png"));
        Image i0 = i.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT);
        return new ImageIcon(i0);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

            File dataRead = new File(fileLocation+"\\SnakeGame\\src\\Data\\Data.txt");
            Scanner in = null;
            try {
            in = new Scanner(dataRead);
            } catch (FileNotFoundException e) {
            e.printStackTrace();
            }
            while(in.hasNextLine()){
            list.add(in.nextInt());
            }

            if(ae.getSource() == b1) {

                File dataCharacter = new File(fileLocation + "\\SnakeGame\\src\\Data\\Character_Data.txt");
                Scanner sc = null;
                try {
                    sc = new Scanner(dataCharacter);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while (sc.hasNextLine()) {
                    character.add(sc.nextLine());
                }
                sound.clip.close();
                menu.setVisible(false);
                //Assigning bodyparts here from file
                up = character.get(0);
                down = character.get(1);
                left = character.get(2);
                right = character.get(3);
                bod = character.get(4);

                new GameFrame(eatSound, dieSound, up, down, left, right, bod);
            }
            if(ae.getSource() == b2){
                JOptionPane.showMessageDialog(null,"Current Highest Score : "+list.get(0));
            }

            if(b3.isSelected()){
                b3.setIcon(iconImage1(200,30,"text-1660795030011",b3));
                sound.clip.stop();
            }
            else{
                b3.setIcon(iconImage1(200,30,"text-1660795016051",b3));
                sound.clip.start();
            }

             if(ae.getSource() == b4){
            menu.setVisible(false);
            System.exit(0);
        }
           if(ae.getSource() == b5){
            sound.clip.close();
            menu.setVisible(false);
            new Choose_Character();
        }

    }
}
