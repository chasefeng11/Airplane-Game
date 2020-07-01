
/** This class will be the create all building objects
 */
public class Structures {
    private int x; //Tracks x coordinate of the building relative to the screen (decreases until scroled off the page)
    private int y; //Tracks y coordinate of the building (does not change)
    private int type; //Tracks type of building, where 1 is an enemy base, 2 is factory, 3 refers to civilian home
    private boolean destroyed; //Tracks if the building object has been destroyed
    

    public Structures(int a, int b, int t){
        x = a;
        y = b;
        type = t;
        destroyed = false;
    }
    
    //Accessor for buildings x coordinate
    public int getX(){ 
        return x;
    }
    
    //Accessor for buildings y coordinate
    public int getY(){
        return y;
    }
    
    //Mutator for buildings x coordinate
    public void setX(int i){
        x = i;
    }
    
    //Accessor for buildings type
    public int getType(){
        return type;
    }
    
    //Accessor for whether or not the building is destroyed
    public boolean isDestroyed(){
        return destroyed;
    }
    
    //Mutator for setting the building to be destroyed or not destroyed
    public void setDestroyed(boolean b){
        destroyed = b;
    }
}
        
    
    
    


