package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.GamePlay;
/**
 * Name: Chanchev Mahendran & Praven Selvakamalan
 * Date: May 23, 2107
 * Purpose: The purpose of this class is to define each of the block obstacles in the game
 */
public class Block2 extends GameObject{

	
	Texture tex= GamePlay.getInstance();
	int blockType;//randomly generated in the addObject call in the handler
	
	public Block2(float x, float y, ObjectId id, int type) {
		super(x, y, id);
		blockType = type;//chooses between the window brick and cynder
		healthMax = type+1;//makes it more difficult to shoot a certain block, if the block has more health you get more points
		health = type+1;//changes whenever block is hit
		
	}

	/**Purpose: Is required for the class but has no function
	 * Pre: n/a
	 * Pros: no return
	 */
	public void tick(LinkedList<GameObject> object) {
		
		
	}

	/**Purpose: Draws the bricks, cynders and windows
	 * Pre: n/a
	 * Pros: no return
	 */
	public void render(Graphics pic) {
		//draws the specific block
		pic.drawImage(tex.brick[blockType],(int)x,(int)y,32,32,  null);
	}
	/**Purpose: collision detection box
	 * Pre: n/a
	 * Pros: returns rectangle
	 */
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y,32,32);
	}

}
