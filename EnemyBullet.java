
/** Creates bullet objects that fly right and can hit the users plane
 */

public class EnemyBullet {
    private int locx; //tracks bullets current x coordinate
    private int locy; //tracks bullets y location (fixed after being fired)
    private boolean hitPlane; //tracks if a bullet has hit the plane

    public EnemyBullet(int x, int y){
        locx = x;
        locy = y;
        hitPlane = false;
    }

    //checks if bullet is out of frame or has hit plane
    public boolean shouldBeVisible(){
        return ((!hitPlane) && locx < 1800);
    }

    //Accessor for bullets x and y locations
    public int getX(){
        return locx;
    }

    public int getY(){
        return locy;
    }

    //Given planes x and y coordinates, checks if bullet has hit the plane
    public boolean hitPlane(int x, int y){
        //Creates vertical and horizontal margin of error for acceptable distance between a bullet and the users plane
        final double x_interval = 60.0;
        final double y_interval = 40.0;

        //Since planes xy coordinates dont reflect center of mass, we adjust them here to do so
        y += 350;
        x += 490;

        //Computes vertical and horizontal distance between two centers of mass
        double deltaX = Math.sqrt((x - locx)*(x - locx));
        double deltaY = Math.sqrt((y - locy) * (y - locy));
        //Returns true if distances are smaller than allocated margins of error
        if (deltaX <= x_interval && deltaY <= y_interval){
            hitPlane = true;
            return true;
        }
        return false; 
    }

    //Moves the bullet right by n pixels everytime it is caled
    public void addX(int n){
        locx += n;
    }

}

