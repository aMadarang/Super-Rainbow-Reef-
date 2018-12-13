import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class ReefWorld extends JPanel {

    private int score; //player score -> collects after hitting the bricks
    private int lives; //limit lives -> game over
    private Shell Star;
    private JFrame windowSize;
    public static final int sHeight = 500, sWidth = 640; //set screen size

    //Images
    private Image background, ShellImg, StarImg, Octopus;
    private Image Brick1, Brick2, Brick3, Brick4, Brick5, Brick6, Brick7;

    private GameEvents gameEvents;
    private BufferedImage img;
    private Graphics2D g2;

    //array img hold
    ArrayList<Star> Stars = new ArrayList<>();
    ArrayList<Brick> Bricks = new ArrayList<>();
    ArrayList<Brick> OctoArray = new ArrayList<>();

    private void init()
    {
        //load resources
        try
        {
            //Set Background Image of game
            background = ImageIO.read(new File("C:\\Users\\TEMP\\Desktop\\csc413-secondgame-angelomadarang\\Super-Rainbow-Reef-Master\\Resources\\Background2.bmp"));

            //Shell and Star Image
            ShellImg = ImageIO.read(new File("C:\\Users\\TEMP\\Desktop\\csc413-secondgame-angelomadarang\\Super-Rainbow-Reef-Master\\Resources\\Katch.png"));
            StarImg = ImageIO.read(new File("C:\\Users\\TEMP\\Desktop\\csc413-secondgame-angelomadarang\\Super-Rainbow-Reef-Master\\Resources\\Pop.png"));

            //Color Bricks
            Brick1 = ImageIO.read(new File("C:\\Users\\TEMP\\Desktop\\csc413-secondgame-angelomadarang\\Super-Rainbow-Reef-Master\\Resources\\Block1.gif"));
            Brick2 = ImageIO.read(new File("C:\\Users\\TEMP\\Desktop\\csc413-secondgame-angelomadarang\\Super-Rainbow-Reef-Master\\Resources\\Block2.gif"));
            Brick3 = ImageIO.read(new File("C:\\Users\\TEMP\\Desktop\\csc413-secondgame-angelomadarang\\Super-Rainbow-Reef-Master\\Resources\\Block3.gif"));
            Brick4 = ImageIO.read(new File("C:\\Users\\TEMP\\Desktop\\csc413-secondgame-angelomadarang\\Super-Rainbow-Reef-Master\\Resources\\Block4.gif"));
            Brick5 = ImageIO.read(new File("C:\\Users\\TEMP\\Desktop\\csc413-secondgame-angelomadarang\\Super-Rainbow-Reef-Master\\Resources\\Block5.gif"));
            Brick6 = ImageIO.read(new File("C:\\Users\\TEMP\\Desktop\\csc413-secondgame-angelomadarang\\Super-Rainbow-Reef-Master\\Resources\\Block6.gif"));
            Brick7 = ImageIO.read(new File("C:\\Users\\TEMP\\Desktop\\csc413-secondgame-angelomadarang\\Super-Rainbow-Reef-Master\\Resources\\Block7.gif"));

            //Boss Octopus img
            Octopus = ImageIO.read(new File("C:\\Users\\TEMP\\Desktop\\csc413-secondgame-angelomadarang\\Super-Rainbow-Reef-Master\\Resources\\Webp.net-resizeimage.png"));

        }
        catch (Exception e)
        {
            System.out.print(e.getStackTrace() +"No resources are found");
        }

        windowSize = new JFrame();
        windowSize.addWindowListener(new WindowAdapter() {});
        windowSize.add(this);
        windowSize.pack();
        windowSize.setVisible(true);
        windowSize.setTitle("Super-Rainbow-Reef!");
        windowSize.setSize(sWidth, sHeight);
        windowSize.setResizable(false); //fixed
        windowSize.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowSize.getContentPane().setFocusable(true);

    }

    //level 1 update
    private void LevelOne()
    {
        //shell location and key controls
        Star = new Shell(ShellImg, 280, 400, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);

        //tracking
        this.score = 0;
        this.lives = 3;

        gameEvents = new GameEvents();
        gameEvents.addObserver(Star);
        Controls playerControls = new Controls(gameEvents);

        //fix size
        windowSize.getContentPane().requestFocusInWindow();
        windowSize.getContentPane().addKeyListener(playerControls);

        //octopus location image boss, score
        OctoArray.add(new Brick(Octopus, 280, 0, 500));

        //top bricks location
        for(int i = 0; i < 15; i ++)
        {
            //x location plus spacing per brick - space y(20)
            Bricks.add(new Brick(Brick1, 20 + 40 * i, 80, 40));
        }

        //2nd brick
        for(int i = 4; i < 10; i ++)
        {
            Bricks.add(new Brick(Brick2, 20 + 40 * i, 100, 30));
        }

        //3rd brick
        for(int i = 6; i < 8; i ++)
        {
            Bricks.add(new Brick(Brick3, 20 + 40 * i, 120, 20));
        }


        //star bouncing location
        Stars.add(new Star(StarImg, 200, 240));
    }

    private void LevelTwo()
    {

        Star = new Shell(ShellImg, 280, 400, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);

        gameEvents = new GameEvents();
        gameEvents.addObserver(Star);
        Controls playerKeys = new Controls(gameEvents);

        windowSize.getContentPane().requestFocusInWindow();
        windowSize.getContentPane().addKeyListener(playerKeys);

        OctoArray.add(new Brick(Octopus, 180, 0, 500));
        OctoArray.add(new Brick(Octopus, 280, 0, 500));
        OctoArray.add(new Brick(Octopus, 380, 0, 500));

                //brick locations
        for(int i = 0; i < 15; i ++) {
            Bricks.add(new Brick(Brick1, 20 + 40 * i, 80, 10));
        }

        for(int i = 0; i < 15; i ++) {
            Bricks.add(new Brick(Brick4, 20 + 40 * i, 100, 20));
        }

        for(int i = 0; i < 15; i ++) {
            Bricks.add(new Brick(Brick5, 20 + 40 * i, 120, 30));
        }

        Stars.add(new Star(StarImg, 200, 240));
//      Stars.add(new Star(StarImg, 320, 240)); //testing

    }

    @Override
    public void paint(Graphics g)
    {
        //paint screen size
        if (img == null)
        {
            img = (BufferedImage) createImage(sWidth, sHeight);
        }

        Graphics2D gt = (Graphics2D) g;
        g2 = img.createGraphics();
        super.paint(gt);

        //background water img
        g2.drawImage(background, 0, 0, this);


        for (int i = 0; i < OctoArray.size(); i++)
        {
            g2.drawImage(OctoArray.get(i).getImg(), OctoArray.get(i).getX(), OctoArray.get(i).getY(), this);
        }

        for (int i = 0; i < Stars.size(); i++)
        {
            g2.drawImage(Stars.get(i).getImg(), Stars.get(i).getX(), Stars.get(i).getY(), this);
        }

        for (int i = 0; i < Bricks.size(); i++)
        {
            g2.drawImage(Bricks.get(i).getImg(), Bricks.get(i).getX(), Bricks.get(i).getY(), this);
        }

        g2.drawImage(Star.getImg(), Star.getX(), Star.getY(), this);

        gt.drawImage(img, 0, 0, this);

        String Temp;

        //print out Score and Lives --> add img
        Temp = "SCORE: " + this.score;
        gt.setColor(Color.BLACK);
        gt.drawString(Temp, 10, 450); //location

        Temp = "LIVES: " + this.lives;
        gt.setColor(Color.BLACK);
        gt.drawString(Temp, 10, 425);

        //Game Over sign
        if (lives == 0)
        {
            gt.setFont((new Font("Black", Font.BOLD, 30)));
            gt.setColor(Color.RED);
            gt.drawString("~~GAME OVER~~", 210, 250); //print center
        }

        gt.dispose(); //clean up
    }

    private void start()
    {
        this.LevelOne();
        this.run();

        if(OctoArray.isEmpty())
        {
            //play level two
            this.LevelTwo();
            this.run();
        }
    }



    //the shells and stars update
    private void GameUpdate()
    {
        Star.move();

        //spawn new stars if empty
        if(Stars.isEmpty())
        {
            Stars.add(new Star(StarImg, 200, 250));
        }

        //collision
        for(int i = 0; i < Stars.size(); i++)
        {
            collisionDetector();
            Stars.get(i).update();

            if(Stars.get(i).getY() > sHeight-35)
            {
                Stars.remove(i);
                i--;
                if(Stars.isEmpty())
                    lives--;
            }
        }
    }

    //check Rectangle what collides
    public void collisionDetector()
    {

        for(int i = 0; i < Stars.size(); i++)
        {
            Rectangle starRect = Stars.get(i).getRect();
            Rectangle paddleRect = Star.getRect();

            //collision between star and paddle
            if(starRect.intersects(paddleRect))
            {
                //speed up
                Stars.get(i).setVerticalSpeed(-Stars.get(i).getVSpeed());
                System.out.println("Star -> Paddle");//->debug
            }

            //breakable walls (star and wall collision)
            for(int k = 0; k < Bricks.size(); k++)
            {
                //pass
                Rectangle brickRect = Bricks.get(k).getRect();

                //if collision
                if(starRect.intersects(brickRect))
                {
                    //speed up
                    Stars.get(i).setVerticalSpeed(-Stars.get(i).getVSpeed());
                    this.score += Bricks.get(k).getPoints(); //update score

                    //breakable wall/brick
                    Bricks.remove(k);
                    System.out.println("Star -> Brick");
                }
            }

            //star and octopus collision
            for(int k = 0; k < OctoArray.size(); k++)
            {
                //pass
                Rectangle octoRect = OctoArray.get(k).getRect();

                //if collision
                if(starRect.intersects(octoRect))
                {
                    //speed up
                    Stars.get(i).setVerticalSpeed(-Stars.get(i).getVSpeed());

                    //Delete
                    OctoArray.remove(k);
                    k--; //size update

                    //if boss is dead go next lvl
                    if(OctoArray.isEmpty())
                    {
                        this.LevelTwo();
                        Stars.remove(i);
                    }
                    System.out.println("Star -> Octopus");
                }
            }

        }

    }

    private void run()
    {
        long Time;
        long Refresh = 1000000000 / 80;

        while ((lives > 0))
        {
            Time = System.nanoTime();

            GameUpdate();
            repaint();

            //speed
            if ((Time - System.nanoTime() + Refresh) > 0) {
                try {
                    Thread.sleep((Time - System.nanoTime() + Refresh) / 1000000);
                }
                catch (InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args)
    {
        ReefWorld game = new ReefWorld();
        game.init();
        game.start();
    }
}
