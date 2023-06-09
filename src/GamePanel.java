import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class GamePanel extends JPanel implements ActionListener {
    BufferedImage map;
    int flag;
    ImageIcon head, head1, body;
    String locationFile = "C:";
    String up,down,left,right,snakeBody,fileData;          //Chosen Snake Location;
    ArrayList<Integer> list = new ArrayList<>();
    static final int screenWidth = 600;
    static final int screenHeight = 600;
    static final int unitSize = 25;
    static final int gameUnits = (screenWidth*screenHeight)/unitSize;

    int varMaxLength;
    static int Delay;
    static final int x[] = new int[gameUnits];
    static final int y[] = new int[gameUnits];
    int bodyParts = 1;
    int applesEaten, appleX,appleY,flag1 = 0;
    static char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;
    SFX eat,die;

    GamePanel(SFX eat, SFX die, String u, String d, String hL, String hR, String mB)  {
        this.up = u;
        this.down = d;
        this.left = hL;
        this.right = hR;
        this.snakeBody = mB;
        this.eat = eat;
        this.die = die;
        random = new Random();
        File read = new File(locationFile+"\\SnakeGame\\src\\Data\\Difficulty.txt");
        Scanner in1 = null;
        try {
            in1 = new Scanner(read);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(in1.hasNextLine()){
           fileData = in1.nextLine();
        }
            if(Objects.equals(fileData, "Medium")){
                this.Delay = 95;
                varMaxLength = 50;
            }
            else if(Objects.equals(fileData, "Hard")){
                this.Delay = 70;
                varMaxLength = 100;
            }
            else{
                this.Delay = 120;
                varMaxLength = 25;
            }

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
        mapping();
    }

    public void mapping(){
        try {
            map = ImageIO.read(new File(locationFile+"\\SnakeGame\\src\\icons\\grass.png"));
        } catch (IOException ex) {
            System.out.println("Error");
        }
    }



    public void startGame(){
        newApple();
        running = true;
        timer = new Timer(Delay,this);
        timer.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(map,0,0,this);
        draw(g);
    }

    public void draw(Graphics g){
        if(running){
            ImageIcon image = new ImageIcon(locationFile+"\\SnakeGame\\src\\icons\\apple.png");
            g.drawImage(image.getImage(), appleX,appleY,unitSize,unitSize,this);
            for(int i = 0; i<bodyParts; i++){
                    if(i == 0){
                        switch(direction){
                            case 'U':
                                head = new ImageIcon(locationFile+"\\SnakeGame\\src\\icons\\"+up);
                                head.paintIcon(this, g, x[0], y[0]);
                                break;
                            case 'D':
                                head = new ImageIcon(locationFile+"\\SnakeGame\\src\\icons\\"+down);
                                head.paintIcon(this, g, x[0], y[0]);
                                break;
                            case 'L':
                                head = new ImageIcon(locationFile+"\\SnakeGame\\src\\icons\\"+left);
                                head.paintIcon(this, g, x[0], y[0]);
                                break;
                            case 'R':
                                head = new ImageIcon(locationFile+"\\SnakeGame\\src\\icons\\"+right);
                                head.paintIcon(this, g, x[0], y[0]);
                                break;
                        }
                    }

                    else{
                        body = new ImageIcon(locationFile+"\\SnakeGame\\src\\icons\\"+snakeBody);
                        body.paintIcon(this, g, x[i], y[i]);
                    }
            }




            g.setColor(Color.RED);
            g.setFont(new Font("Ink Free",Font.BOLD,30));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: "+applesEaten,(screenWidth - metrics.stringWidth("Score: "+applesEaten))/2,g.getFont().getSize());

        }
        else{
            File dataRead = new File(locationFile+"\\SnakeGame\\src\\Data\\Data.txt");
            Scanner in = null;
            try {
                in = new Scanner(dataRead);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while(in.hasNextLine()){
                list.add(in.nextInt());
            }

            if(list.get(0)<applesEaten){
                list.remove(0);
                list.add(0,applesEaten);
                File dataFile = new File(locationFile+"\\SnakeGame\\src\\Data\\Data.txt");
                PrintWriter write = null;
                try {
                    write = new PrintWriter(dataFile);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                write.print("");
                write.print(list.get(0));
                write.flush();
                write.close();
                flag = 1;
                gameOver(g);
            }
            else{
                flag = 0;
                gameOver(g);
            }
        }

    }

    public void newApple(){
        appleX = random.nextInt((int)(screenWidth/unitSize))*unitSize;
        appleY = random.nextInt((int)(screenHeight/unitSize))*unitSize;
    }


    public void move(){
        for(int i = bodyParts; i>0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch(direction){
            case 'U':
                y[0] = y[0] - unitSize;
                break;
            case 'D':
                y[0] = y[0] + unitSize;
                break;
            case 'L':
                x[0] = x[0] - unitSize;
                break;
            case 'R':
                x[0] = x[0] + unitSize;
                break;
        }
    }

    public void checkApple(){

        if ((x[0] == appleX) && (y[0] == appleY) && flag1 == 0) {
            eat.clip.setMicrosecondPosition(0);
            eat.clip.start();
            bodyParts++;
            applesEaten++;
            newApple();
        }
        if((x[0] == appleX) && (y[0] == appleY) && flag1 == 1){
            eat.clip.setMicrosecondPosition(0);
            eat.clip.start();
            bodyParts--;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollision(){
        //checking if head collides with body
        for(int i = bodyParts; i>0; i--){
            if((x[0] == x[i]) && (y[0] == y[i])){
                die.clip.start();
                running = false;
            }
        }
        //checks if head touches left border
        if(x[0] < 0){
            die.clip.start();
            running = false;
        }
        // checks if head touches right border
        if(x[0] > screenWidth){
            die.clip.start();
            running = false;
        }
        //checks if head touches top border
        if(y[0]<0){
            die.clip.start();
            running = false;
        }
        //checks if head touches bottom border
        if(y[0]>screenHeight){
            die.clip.start();
            running = false;
        }

        if(!running){
            timer.stop();
        }
    }

    public void gameOver(Graphics g){

        //game over text
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free",Font.BOLD,75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game over",(screenWidth - metrics.stringWidth("Game over"))/2,screenHeight/2);
        head1 = new ImageIcon(locationFile+"\\SnakeGame\\src\\icons\\V2.gif");
        head1.paintIcon(this, g, 480, 230);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            if(bodyParts > varMaxLength){
                flag1 = 1;
            }
            else{
                if(bodyParts == 1)
                    flag1 = 0;
            }
            move();
            checkApple();
            checkCollision();
        }
        repaint();
    }

    public static class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
                switch(e.getKeyCode()){
                    case KeyEvent.VK_LEFT:
                        if(direction != 'R'){
                            direction = 'L';
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(direction != 'L'){
                            direction = 'R';
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if(direction != 'D'){
                            direction = 'U';
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if(direction != 'U'){
                            direction = 'D';
                        }
                        break;
                }
        }
    }
}

