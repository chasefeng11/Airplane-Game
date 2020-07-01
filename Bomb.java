
/** Creates a bomb object
 *  Can be displayed in two forms: falling bomb or explosion cloud
 */

import java.lang.Math;

public class Bomb {
    private final int bombchangeY = 4; //constant for how many pixels the bomb should fall for each iteration of paintComponent
    private int bomblocx; //keeps track of bombs x coordinate
    private int bomblocy; //keeps track of the bombs y coordinate
    private boolean hitSomething; //keeps track of if the bomb has hit a structure

    public Bomb(int x, int y){
        bomblocx = x;
        bomblocy = y;
        hitSomething = false;
    }

    //returns if bomb has reached ground level or not
    public boolean hasHitGround(){
        return (bomblocy >= 650);
    }

    //returns if bomb has hit something
    public boolean hasHitSomething(){
        return hitSomething;
    }
    //returns if bomb has hit a building or has hit ground
    public boolean hasExploded(){
        return ((!hitSomething) && bomblocy >= 650);
    }

    //Returns bombs current x coordinate
    public int getX(){
        return bomblocx;
    }

    //Returns bombs current y coordinate
    public int getY(){
        return bomblocy;
    }

    //Mutator for bomb’s x coordinate
    public void setX(int x){
        bomblocx = x;
    }

    //Mutator for bomb’s y coordinate
    public void setY(int y){
        bomblocy = y;
    }

    //if bomb hasn’t hit building or ground, bomb moves down vertically
    public void update(){
        if (!hasHitGround() && !hasHitSomething()){
            bomblocy += bombchangeY;
        }
    }

    //Takes a Structures object and returns if structure "was hit"
    public boolean hitTarget(Structures s){
        //Sets horizontal and vertical “margin of error”
        final double x_interval = 150.0; 
        final double y_interval = 70.0;

        //Since bombs xy graphical coordinates dont reflect its center of mass, they are adjusted here to estimate the location of the center of mass
        int x = s.getX() - 110; 
        int y = s.getY() + 100; 

        //Make sure bombs COM is within a certain horizontal and vertical distance of building
        double deltaX = Math.sqrt((x - bomblocx)*(x - bomblocx));
        double deltaY = Math.sqrt((y - bomblocy) * (y - bomblocy));
        if (deltaX <= x_interval && deltaY <= y_interval){
            hitSomething = true;
            return true;
        }
        return false;    
    }
}



