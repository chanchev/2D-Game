package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.Animation;
import window.GamePlay;
/**
 * Name: Praven Selvakamalan
 * Date: May 23, 2107
 * Purpose: The purpose of this class is to define the enemies that will appaer on the screen
 */
public class Enemy extends GameObject{

	
	Texture tex= GamePlay.getInstance();
	//accesses the animation class
	private Animation move;
	
	public Enemy(float x, float y, ObjectId id) {
		super(x, y, id);
		healthMax = 4;//max health of the enemy
		health = 4;//changing health of the health
		move = new Animation(20, tex.enemy[0],tex.enemy[1],tex.enemy[2],tex.enemy[3],tex.enemy[4]);//used to create the sprite movement
	}

	/**Purpose: updates player functions at 60 ticks/second
	 * Pre: n/a
	 * Pros: no return
	 */
	public void tick(LinkedList<GameObject> object) {
		x=(float) (x-0.5);
		move.runAnimation();
	}

	/**Purpose: draws the enemies
	 * Pre: n/a
	 * Pros: no return
	 */
	public void render(Graphics pic) {
		
		move.drawAnimation(pic,(int)x, (int)y,  84, 64);
	}
	/**Purpose: collision detection box
	 * Pre: n/a
	 * Pros: returns rectangle
	 */
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y,84,64);
	}

}
