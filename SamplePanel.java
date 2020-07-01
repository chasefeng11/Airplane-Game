/* This class will contain all images inside it
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.io.*;

public class SamplePanel extends JPanel implements ActionListener, KeyListener {
    private int scrollX = 0; //keeps track of amount scrolled (resets to 0 after full width of frame has been scrolled through
    private int scrollFactor; //amount of pixels scrolled for each iteration of the loop, determines how fast the background scrolls
    private int changeY = 0;

    final private int locx = 300; //planes x  coordinate on the screen, does not change
    private int locy = -100; //planes y coordinate on the screen, changes as the user goes up and down

    private int cycle; //keeps track of where the enemy plane is in its routine (i.e. is it going up or going down)
    private ArrayList<Bomb> bombs; //list of bomb objects in the air, where the size cannot exceed 5
    private ArrayList<Bomb> explosions; //list of explosions (bomb objects displayed as explosions) that are still on the screen
    private ArrayList<Structures> buildings; //list of Structures objects that are on the screen or soon upcoming

    private Plane user; //Creates the users plane
    private EnemyPlane enemy; //Creates the enemy plane, which may or may not be on the screen at any given moment
    private ArrayList<EnemyBullet> bullets; //list of bullets fired by the enemy plane that are still on the screen
    private Timer time = new Timer (5,this);

    //Instances of all the images to be displayed
    private Image explosion;
    private Image bomb;
    private Image background;
    private Image plane;
    private Image enemy_plane;
    private Image enemy_bullet;

    private Image base;
    private Image burningBase;
    private Image factory;
    private Image burningFactory;
    private Image home;
    private Image burningHome;
    private Image destroyedplane;
    
    private boolean added; //Keeps track if score has been added to txt file yet

    public SamplePanel(){        
        // Starts Timer.
        time.start();

        // Adds a KeyListener.
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        scrollFactor = 3;
        //creates objects or arraylists
        user = new Plane();
        bombs = new ArrayList<Bomb>();
        explosions = new ArrayList<Bomb>();
        buildings = new ArrayList<Structures>();

        enemy = new EnemyPlane();
        cycle = 0;
        bullets = new ArrayList<EnemyBullet>();

        added = false;
    }
    // Draws the panel.
    public void paintComponent (Graphics g){
        super.paintComponent(g);

        //Paints scrolling background image if plane is alive, if not background freezes
        background = Toolkit.getDefaultToolkit().getImage("NewBackground.png");
        if(user.alive())
        {
            g.drawImage(background, -scrollX, 0, 1800, 960, this);
            g.drawImage(background, 1800 - scrollX, 0,1800, 960, this);
            scrollX += scrollFactor;
            if (scrollX == 1800){
                scrollX = 0;
                newBuildings(); //randomly generates new buildings ahead
            }
        }
        else
        {
            g.drawImage(background, -scrollX, 0, 1800, 960, this); 
            g.drawImage(background, 1800 - scrollX, 0,1800, 960, this);

        }

        //Paints scoreboard and health 
        //Paints plane, if plane is dead paints destroyed plane
        plane = Toolkit.getDefaultToolkit().getImage("Allied_Plane.png");
        destroyedplane = Toolkit.getDefaultToolkit().getImage("DestroyedPlane.png");
        if(user.alive())
        {
            g.drawImage(plane, locx, locy, 1200, 850, this);
        }
        else
        {
            g.drawImage(destroyedplane, locx+400, locy+275, 400, 275, this);
        }
        //if plane is alive, score and health on top right, if not says you lose and score big and middle of screen
        if(user.alive())
        {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 45)); 
            g.setColor(Color.RED);
            g.drawString("Score: " + user.getScore(), 1300, 75);
            g.drawString("Health: " + user.getSurvival(), 1300, 200);
        }
        else
        {
            if (!added){ //If users score hasnt been added to the txt file yet, it is added here
                appendScore2Txt(user.getScore());
                added = true;
            }
            g.setFont(new Font("TimesRoman", Font.PLAIN, 150)); 
            g.setColor(Color.RED);
            g.drawString("YOU LOSE! ", 150, 200);
            g.drawString("Score: " + user.getScore(), 150, 350);

            int highScore = getHighScore();
            if (highScore != -500){ //If there is an error reading the text file, high score is simply not printed
                g.drawString("High Score: " + highScore, 150, 500);
            }
        }

        //Paints enemy plane and bullets
        enemy_plane = Toolkit.getDefaultToolkit().getImage("Enemy_Plane.png");
        enemy_bullet = Toolkit.getDefaultToolkit().getImage("EnemyBullet.png");

        //Paints building 
        base = Toolkit.getDefaultToolkit().getImage("Enemy_Base.png");
        burningBase = Toolkit.getDefaultToolkit().getImage("BurningEnemyBase.png");
        factory = Toolkit.getDefaultToolkit().getImage("Factory.png");
        burningFactory = Toolkit.getDefaultToolkit().getImage("ExplodingFactory.png");
        home = Toolkit.getDefaultToolkit().getImage("CivilianHouse.png");
        burningHome = Toolkit.getDefaultToolkit().getImage("BurningCivilianHome.png");

        //Draws each structure in the ArrayList, either as a undamaged building or burning building
        for (Structures s: buildings){
            //Determines the type of building in order to draw it
            if (s.getType() == 1){
                if (s.isDestroyed()){          
                    g.drawImage(burningBase, s.getX()-145, s.getY()-145, 510, 710, this);
                } else {            
                    g.drawImage(base, s.getX(), s.getY(), 250, 450, this);
                }
            }
            if (s.getType() == 2){
                if(user.alive())
                {
                    if (s.isDestroyed()){           
                        g.drawImage(burningFactory, s.getX(), s.getY() - 85, 230, 430, this);
                    } else {            
                        g.drawImage(factory, s.getX(), s.getY(), 250, 450, this);
                    }
                }
                else
                {

                }
            }
            if (s.getType() == 3){
                if (s.isDestroyed()){           
                    g.drawImage(burningHome, s.getX(), s.getY()+65, 350, 350, this);
                } else {            
                    g.drawImage(home, s.getX()+10, s.getY()+165, 250, 250, this);
                }
            }
            if(user.alive())
            {
                s.setX(s.getX() - scrollFactor);
            }
            else
            {

            }
        }

        //Paints bomb
        bomb = Toolkit.getDefaultToolkit().getImage("Bomb.png");
        explosion = Toolkit.getDefaultToolkit().getImage("Explosion.png");

        //Draws each bomb in the ArrayList, then moves it down by a certain amount
        //Also checks to see if each bomb has hit a structure, and what type of structure it hit
        for (Bomb b: bombs){
            if(user.alive())
            {
                g.drawImage(bomb, b.getX(), b.getY(), 600, 250, this);
                b.update();
                for (Structures s: buildings){
                    if (b.hitTarget(s)){
                        s.setDestroyed(true);
                        if (s.getType() == 1){
                            user.bombedBase();
                        } else if (s.getType() == 2){
                            user.bombedFactory();
                        } else if (s.getType() == 3){
                            user.bombedCivilian();
                        }
                    } 
                }
            }
        }
        clearBombsList(); //If there are any bombs that have exploded, takes them off bombs ArrayList and puts them on explosions ArrayList

        //Draws each explosion in the ArrayList, then moves it left by a scrollFactor 
        //This gives the impression that the plane is getting farther away from it
        for (Bomb e: explosions){
            if(user.alive())
            {
                g.drawImage(explosion, e.getX() + 150, 720, 200, 85, this);
                e.setX(e.getX() - scrollFactor);
            }
        }
        clearExplosionList(); //Takes any explosions no longer on the screen off of the explosions ArrayList

        for (EnemyBullet bullet: bullets){
            if (bullet.hitPlane(locx, locy)){
                user.hitByBullet();
            }
            else {
                g.drawImage(enemy_bullet, bullet.getX(), bullet.getY(), 40, 20, this);
                bullet.addX(15);
            }
        }

        if (enemy.getLocation() >= 0){
            g.drawImage(enemy_plane, 100, enemy.getLocation(), 240, 150, this);
            if(user.alive())
            {
                if (cycle < 80){ //at first, the enemy plane will be moving up
                    enemy.setLocation(enemy.getLocation() - 1); 
                } else { //midway through its time on the screen, the enemy plane will start moving down
                    enemy.setLocation(enemy.getLocation() + 1);
                }
                if (cycle % 25 == 0){
                    fireBullet(); //Every so often while enemy plane is on the screen, a bullet will be fired
                }
            }
            else
            {

            }
            cycle++;
        }

        //Every so often, adds a point to the user's score for simply staying alive
        if (user.getSurvival() >= 0 && scrollX % 15 == 0){
            user.addScore();
        }
        clearBuildings(); //Takes any buildings no longer on the screen off of the buildings ArrayList

        //Every so often, randomly generates an enemy plane on the screen
        if (scrollX % 1800 == 0){
            buildEnemyPlane();
        }

        if (scrollX >= 800 && scrollX <= 900){
            enemy.resetLocation();
        }

        clearBullets();
    }
    //Makes sure plane is within certain Y coordinates, if so it can move
    public void actionPerformed (ActionEvent e){
        if(locy + changeY >=130 || locy + changeY <= -260)
        {

        }
        else
        {
            locy += changeY;
        }
        repaint();
    }
    //checks what key is pressed so plane goes up or down or bomb drops
    public void keyPressed (KeyEvent e)
    {
        int c = e.getKeyCode();
        if(user.alive())
        {
            if (c == KeyEvent.VK_UP){
                changeY = -5;
            }
            if (c == KeyEvent.VK_DOWN ){
                changeY = 5;
            }
            if(c == KeyEvent.VK_SPACE){
                if (bombs.size() <= 4){
                    bombs.add(new Bomb(locx + 295, locy + 270));
                }
            }
            else
            {

            }
        }
    }
    //When you release a key the motion of the plane stops
    public void keyReleased (KeyEvent e)
    {
        int c = e.getKeyCode();
        if(user.alive())
        {
            if (c == KeyEvent.VK_UP || c == KeyEvent.VK_DOWN){
                changeY = 0;
            }
        }
    }

    public void keyTyped (KeyEvent e)
    {

    }

    //Removes any bomb that has hit the ground or hit a structure from the buildings ArrayList and puts it into the explosions ArrayList
    public void clearBombsList(){
        Iterator itr = bombs.iterator(); 
        while (itr.hasNext()) 
        { 
            Bomb b = (Bomb)itr.next(); 

            if (b.hasHitGround() || b.hasHitSomething()){ 
                explosions.add(b);
                itr.remove();
            }
        }

    }

    //Removes explosion clouds a few moments after they detonate
    public void clearExplosionList(){
        Iterator itr = explosions.iterator(); 
        while (itr.hasNext()) 
        { 
            Bomb e = (Bomb)itr.next(); 
            if (e.getX() <= 300){ 
                itr.remove();
            }
        }
    }

    //When called, randomly generates types and locations of new buildings and puts them in the buildings ArrayList to be displayed ahead
    public void newBuildings(){
        Random rand = new Random();
        for (int yloc = 0; yloc < 6; yloc++){
            int upper1 = 8;
            int rand1 = rand.nextInt(upper1);
            if (rand1 <= 6){
                int upper2 = 7;
                int rand2 = rand.nextInt(upper2);
                if (rand2 == 0){                    
                    buildings.add(new Structures(1800 + yloc*300, 375, 1));
                } else if (rand2 <= 3){
                    buildings.add(new Structures(1800 + yloc*300, 500, 2));
                } else {
                    buildings.add(new Structures(1800 + yloc*300, 450, 3));
                }    
            }
        }
    }

    //Removes any building from buildings ArrayList that has gone off the screen
    public void clearBuildings(){
        Iterator itr = buildings.iterator(); 
        while (itr.hasNext()) 
        { 
            Structures s = (Structures)itr.next(); 
            if (s.getX() <= -100){ 
                itr.remove();
            }
        }

    }

    //When called, moves EnemyPlane onto the screen and starts its routine
    public void buildEnemyPlane(){
        Random rand = new Random();
        int locy = rand.nextInt(300)+80;
        enemy.setLocation(locy);
        cycle = 0;
    }

    //Adds new bullet object to bullets ArrayList when called
    public void fireBullet(){
        EnemyBullet bullet = new EnemyBullet(150, enemy.getLocation() + 70);
        bullets.add(bullet);
    }

    //Removes any enemy bullet that has either hit something or gone off the screen from the bullets ArrayList
    public void clearBullets(){
        Iterator itr = bullets.iterator(); 
        while (itr.hasNext()) 
        { 
            EnemyBullet b = (EnemyBullet)itr.next(); 
            if (!b.shouldBeVisible()){ 
                itr.remove();
            }
        }
    }

    //Reads each line in txt file and returns high score 
    public int getHighScore(){
        int highScore = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));
            String line = reader.readLine();
            while (line != null)                 // read the score file line by line
            {
                try {
                    int score = Integer.parseInt(line.trim());   // parse each line as an int
                    if (score > highScore)                       // and keep track of the largest
                    { 
                        highScore = score; 
                    }
                } catch (NumberFormatException e1) {
                    // ignores invalid scores
                }
                line = reader.readLine();
            }
            reader.close();
            return highScore;

        } catch (IOException ex) {
            return -500; //In the event txt file cannot be read
        }

    }

    //Adds users score to txt file as a new line at the bottom 
    public void appendScore2Txt(int score){
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter("scores.txt", true));
            output.newLine();
            output.append("" + score);
            output.close();

        } catch (IOException ex1) {
            System.out.printf("ERROR writing score to file: %s\n", ex1);
        }

    }
}
