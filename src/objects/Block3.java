package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;
/**
 * Name: Chanchev Mahendran & Praven Selvakamalan
 * Date: May 23, 2107
 * Purpose: The purpose of this class is to define a finish line
 */
public class Block3 extends GameObject{

	public Block3(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	/**Purpose: Is required for the class but has no function
	 * Pre: n/a
	 * Pros: no return
	 */
	public void tick(LinkedList<GameObject> object) {
		
		
	}
	/**Purpose: Does not serve a function but is required
	 * Pre: n/a
	 * Pros: no return
	 */
	public void render(Graphics pic) {
		
	}
	/**Purpose: collision detection box
	 * Pre: n/a
	 * Pros: returns rectangle
	 */
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y,32,32);
	}

}
