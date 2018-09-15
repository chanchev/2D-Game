package framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
/**
 * Name: Chanchev Mahendran
 * Date: May 23, 2017
 * Purpose: The purpose of this class is to create an abstract class with the basic functions of all objects
 */
public abstract class GameObject {


	//baisc functions every object needs
	protected float x,y;
	protected float vX=0, vY=0;
	protected ObjectId id;
	protected boolean falling =true;
	protected boolean jumping =false;
	public int health;
	public int healthMax;

	public GameObject(float x, float y, ObjectId id ){
		this.x=x;
		this.y=y;
		this.id=id;
	}
	


	public abstract void tick (LinkedList<GameObject> object);//abstract method of the linked list
	public abstract void render(Graphics pic);//abstract method of the render method of each object
	public abstract Rectangle getBounds();//collision bounding
	
	/**Purpose: obtain general x value
	 * Pre: n/a
	 * Pros: returns x
	 */
	public  float getX(){
		return x;
	}
	/**Purpose: obtain general y value
	 * Pre: n/a
	 * Pros: returns y
	 */
	public  float getY(){
		return y;
	}
	/**Purpose: set general x value
	 * Pre: float x
	 * Pros: no return
	 */
	public  void setX(float x){
		this.x=x;
	}
	
	/**Purpose: set general y value
	 * Pre: float y
	 * Pros: no return
	 */
	public  void setY(float y){
		this.y=y;
	}
	/**Purpose: obtain general vX value
	 * Pre: n/a
	 * Pros: returns vX
	 */
	public  float getVX(){
		return vX;
	}
	/**Purpose: obtain general vY value
	 * Pre: n/a
	 * Pros: returns vY
	 */
	public  float getVY(){
		return vY;
	}
	/**Purpose: set general vX value
	 * Pre: float vX
	 * Pros: no return
	 */
	public  void setVX(float vX){
		this.vX=vX;
	}
	/**Purpose: set general vY value
	 * Pre: float vY
	 * Pros: no return
	 */
	public  void setVY(float vY){
		this.vY=vY;
	}
	/**Purpose: obtain general identification of an object
	 * Pre: n/a
	 * Pros: returns id
	 */
	public  ObjectId getId(){
		return id;
	}

	
	
}
