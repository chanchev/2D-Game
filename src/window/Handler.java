package window;

import java.awt.Graphics;
import java.util.LinkedList;

import objects.Block;
import objects.Block2;
import objects.Enemy;
import framework.GameObject;
import framework.ObjectId;
/**
 * Name: Chanchev Mahendran
 * Date: May 23, 2107
 * Purpose: The purpose of this class to create a linked list of objects
 */
public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	boolean mode;
	
	//handler constructor
	public Handler() {
	}
	/**Purpose: Goes through the entire linked list
	 * Pre: n/a
	 * Pros: no return
	 */
	//goes through entire linked list
	public void tick(){
		for(int i = 0; i< object.size(); i++){
			tempObject=object.get(i);
			tempObject.tick(object);
		}
	}
	/**Purpose: draws all the bjects on the screen
	 * Pre: n/a
	 * Pros: no return
	 */
	public void render(Graphics pic){
		for(int i=0; i<object.size(); i++){
			tempObject=object.get(i);
			tempObject.render(pic);
		}
	}
	/**Purpose: adds objects to the handler linked list
	 * Pre: GameObject object
	 * Pros: no return
	 */
	//adds object to list
	public void addObject(GameObject object){
		this.object.add(object);//specific object from list
	}
	/**Purpose: removes objects from the handler linked list
	 * Pre: GameObject object
	 * Pros: no return
	 */
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	
	/**Purpose: creates a random number used in the create level method
	 * Pre: int a, int b (range of numbers)
	 * Pros: returns an integer value
	 */
	private int randNum(int a, int b){
		return (int)(Math.random()*(b-a+1) + a);
	}
	
	/**Purpose: creates the game level with enemies, obstacles blocks and collision detection blocks
	 * Pre: n/a
	 * Pros: no return
	 */
	public void createLevel(){
		boolean mode = GamePlay.gameMode;//sets the mode of the game to easy or hard
		int difficulty = 5;//easy
		if (!mode){
			difficulty = 4;//hard smaller number so it's more likely to generate an object
		}
		
		//grid used to spawn the enemies and blocks
		//block 2 class holds the three different types of blocks
		for (int xx = 320; xx < 320 * 10; xx += 32) {//row
			for(int yy=0;yy< 640; yy+= 32){//column
				//creates a block and the frequency of block occuring is dependant on the difficulty
				if(randNum(1,difficulty)==1){
					addObject(new Block2(xx, yy, ObjectId.Block2,randNum(0,2)));
				}
				
				//creates a block and the frequency of block occuring is dependant on the difficulty
				if (randNum(1,difficulty*20) == 1){//multiplied by 20 to make the enemies appear less frequently than the block
					addObject(new Enemy(xx,yy, ObjectId.Enemy));
				}
				
			}
		}
		
		
	
		// can multiply width
		//used to determine the finishing point
		for(int xx = 0; xx < GamePlay.HEIGHT+32; xx += 32){ 
			addObject(new Block(3200, xx, ObjectId.Block3)); 
		}		 
		//left boundary
		for (int xx = 0; xx < GamePlay.HEIGHT + 32; xx += 32) {
			addObject(new Block(0, xx, ObjectId.Block));
		}
		//top boundary
				for (int xx = 0; xx < (GamePlay.WIDTH + 32) * 10; xx += 32) {
					addObject(new Block(xx, (GamePlay.HEIGHT), ObjectId.Block));
				}
		// bottom boundary
				for (int xx = 0; xx < (GamePlay.WIDTH - 32) * 10; xx += 32) {
					addObject(new Block(xx, -32, ObjectId.Block));
				}
				
	
	}
	
	
	
}