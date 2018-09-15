package window;

import framework.GameObject;
/**
 * Name: Chanchev Mahendran & Praven Selvakamalan
 * Date: May 23, 2107
 * Purpose: The purpose of this class is to create an automatically scrolling camera
 */
public class Camera {
	private static float x;
	private float y;
	double difficulty = 1;//for easy mode
	//moves the screen
	public Camera(float x, float y){
		this.x=x;
		this.y=y;
		if (!GamePlay.gameMode){
			difficulty = 1.5;//for hard mode
		}
	}
	/**Purpose: Updates the camera for movement
	 * Pre: n/a
	 * Pros: no return
	 */
	public void tick(GameObject player){
		if (x > -3000) {//stops the camera at the end of the game
			x = (float) (x - difficulty);//moves the actual camera
		}
		//System.out.println(x);
	}
	
	/**Purpose: used to set the x value of the camera
	 * Pre: float x
	 * Pros: no return
	 */
	public void setX(float x){
		this.x=x;
	}
	/**Purpose: used to set the y value of the camera
	 * Pre:float y
	 * Pros: no return
	 */
	public void setY(float y){
		this.y=y;
	}
	/**Purpose: used to get the x value of the camera
	 * Pre: n/a
	 * Pros: returns x
	 */
	public static float getX(){
		return x;
	}
	/**Purpose: used to get the y value of the camera
	 * Pre: n/a
	 * Pros: returns x
	 */
	public float getY(){
		return y;
	}
}
