/* This class will create the user's plane object
 * Note that this class is completely unrelated to enemyPlane.java 
 */
public class Plane {
    private int survival; //tracks amount of health (out of 1000) the user currently has left
    private int score; //tracks the number of points the user has
    private boolean alive = true;
    private final int count = 1; //points earned every two seconds for staying aline
    private final int pointsFactory = 100; //points gained each time a factory is hit
    private final int pointsBase = 300; //points gained each time an enemy base is bombed
    private final int pointsHome = -400; //points lost each time a civilian home is hit
    private final int damageBullet = 200; //amount of survival lost when hit by regular bullet 
    private final int damageMissile = 300; //amount of survival lost when hit by missile (from boss plane)

    //Constructor: initializes plane's score and health 
    public Plane(){ 
        score = 0;
        survival = 1000;
    }

    //Accessor for score variable
    public int getScore(){ 
        return score;
    }

    //Accessor for survival variable
    public int getSurvival(){ 
        return survival;
    }

    //If plane is hit by a bullet, 200 is subtracted from user's health
    // Returns whether or not health is still a positive integer (if user is still alive)
    public boolean hitByBullet(){ 
        survival -= damageBullet;
        if (survival < 0){
            survival = 0;
            return false;
        }
        return true;
    }

    public boolean hitByMissile(){
        survival -= damageMissile;
        return (survival == 0);
    }

    //If plane successfully bombs a base, 300 is added to the user's points 
    public void bombedBase(){
        score += pointsBase;
    }

    //If plane successfully bombs a factory, 100 is added to the user's points
    public void bombedFactory(){
        score += pointsFactory;
    }

    //If plane successfully bombs a factory, 400 is subtracted from the user's points
    //Users score cannot go below 0, however
    public void bombedCivilian(){
        score += pointsHome;
        if (score < 0){
            score = 0;
        }
    }

    //Adds 1 to the user's points for every fraction of time he/she remains alive
    public void addScore(){
        score += count;
    }
    
    //Returns whether or not the plane is alive 
    public boolean alive()
    {
        if(survival == 0)
        {
            alive = false;
        }
        return alive;
    }
}




