/* This class create the enemyPlane object in SamplePanel
 */

public class EnemyPlane {
    private int ylocation; //tracks planes current y location

    public EnemyPlane(){ //by default, plane is not on the screen
        ylocation = -500;
    }

    //When called, plane will move onto screen to default point
    public void setLocation(int y){ 
        ylocation = y;
    }

    //When called, plane will move back off the screen
    public void resetLocation(){
        ylocation = -500;
    }

    //Accessor for planes current y coordinate
    public int getLocation(){
        return ylocation;
    }


}

