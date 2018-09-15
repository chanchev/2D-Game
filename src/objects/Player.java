package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import window.Animation;
import window.Camera;
import window.Handler;
import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.GamePlay;
/**
 * Name: Chanchev Mahendran 
 * Date: May 23, 2107
 * Purpose: The purpose of this class is to define the player
 */
public class Player extends GameObject{
	//size of the player
	private float width=64, height=48;
	//access handler class
	private Handler handler;
	Camera cam;
	
	//health of player
	private int health;
	private int maxHealth;
	
	

	
	//used for character flashing
	private boolean isHit;
	int hitTimer = 0;//length of flash
	
	static int score = 0;//player score
	//every object needs it
	Texture tex= GamePlay.getInstance();
	
	
	
	
	private Animation playerWalk;
	
	public Player(float x, float y,Handler handler, Camera cam, ObjectId id) {
		super(x, y, id);
		this.handler=handler;
		this.cam = cam;
		//sprite animation of player
		playerWalk= new Animation(2, tex.player[1], tex.player[2], tex.player[3],tex.player[4],tex.player[5],tex.player[6],tex.player[7]);
		
		health= maxHealth=8;
		
	}
	/**Purpose: updates player functions at 60 ticks/second
	 * Pre: n/a
	 * Pros: no return
	 */
	public void tick(LinkedList<GameObject> object) {
		//make the player flash when it's hit
		//go through 50 tick so less than 60 ticks per second
		if (isHit){
			hitTimer++;
			if (hitTimer>50){
				isHit = false;
				hitTimer = 0;
			}
		}
		//movement of the player
		x+=vX;
		y+=vY;
		
		//stops player from moving off screen
		if(-x > cam.getX()){
			x = -cam.getX();
		}
		if (x>=(-cam.getX()+GamePlay.WIDTH-60)){
			x= -cam.getX()+GamePlay.WIDTH-80;
		}
		//calls the collison method
		Collision(object);
		
		//player moving
		playerWalk.runAnimation();
		
	}

	/**Purpose: draws the player
	 * Pre: n/a
	 * Pros: no return
	 */
	public void render(Graphics pic) {
		//if the character is hit, it will draw the image every other second
		if (!isHit || (!(hitTimer%2==0))) {
			if (vX != 0 || vY != 0) {
				playerWalk.drawAnimation(pic, (int) x, (int) y, 90, 60);//normal animation
			} else {
				pic.drawImage(tex.player[0], (int) x, (int) y, 90, 60, null);//flash
			}
		}
		
		
		//draws health bar
		healthBar(pic);
		//draws the score
		displayScore(pic);
	}
	
	
	 
	/**Purpose: collision method
	 * Pre: handler linked list
	 * Pros: no return
	 */
	public void Collision(LinkedList<GameObject> object) {
	//goes through the linked list of the handler 
		for (int i=0;i<handler.object.size();i++){
			//stores the object as a temporary object
			GameObject tempObject =handler.object.get(i);
			
			//block collision to stop the player from moving off screen 
			if(tempObject.getId()==ObjectId.Block){
				if (getBoundsTop().intersects(tempObject.getBounds())){
					y=tempObject.getY()+32;
					vY=0;
				}
				
				if (getBounds().intersects(tempObject.getBounds())){
					y=tempObject.getY()-height;
					vY=0;
				}
				//right
				if (getBoundsRight().intersects(tempObject.getBounds())){
					x=tempObject.getX()-width;
				}
				//left
				if (getBoundsLeft().intersects(tempObject.getBounds())){
					x=tempObject.getX()+32;
				}
			}
			
			//collision with cynder, brick, window and enemy
			if ((tempObject.getId() == ObjectId.Block2) || (tempObject.getId() == ObjectId.Enemy)) {
				
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject); //remove blocks on hit
					isHit = true;//turns on hit variable for flashing
					health-=1;//decreases player health by one
					//losing
					//checks if player is dead
					if (health <= 0){
						//ouputs losing message with the high score
						try {
							JOptionPane.showMessageDialog(null, "YOU LOSE!\nScore: " + score + "\nHighest Score: " + getHighScore(), "", JOptionPane.PLAIN_MESSAGE);
						} catch (HeadlessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						System.exit(0);//closes the games prevents glitch
					}
				}
			}
			//winnning
			//collsion with the finish line
			if ((tempObject.getId() == ObjectId.Block3)) {
				if (getBounds().intersects(tempObject.getBounds())) {
					//obtained from the internet
					try{
				        //Specify the file name and path here
				    	File file =new File("Scores.txt");

				    	//Here true is to append the content to file
				    	FileWriter fw = new FileWriter(file,true);
				    	//BufferedWriter writer give better performance
				    	BufferedWriter bw = new BufferedWriter(fw);
				    	bw.write("\n"+score);//add the current score onto the list
				    	//Closing BufferedWriter Stream
				    	bw.close();


				      }catch(IOException ioe){
				         System.out.println("Exception occurred:");
				    	 ioe.printStackTrace();
				       }
						//output the winning message and high score
						try {
							JOptionPane.showMessageDialog(null, "YOU WIN!\nScore: " + score + "\nHighest Score: " + getHighScore() , "", JOptionPane.PLAIN_MESSAGE);
						} catch (HeadlessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				
					System.exit(0);//close game
				}
			}
		}
	}
	
	/**Purpose: collision box bottom
	 * Pre: n/a
	 * Pros: returns rectangle
	 */
	public Rectangle getBounds() {
		return (new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int) ((int)y+(height/2)), (int)width/2, (int)height/2));
	}
	/**Purpose: collision box at top 
	 * Pre: n/a
	 * Pros: returns rectangle
	 */
	public Rectangle getBoundsTop() {
		return (new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int)y, (int)width/2, (int)height/2));
	}
	/**Purpose: collision box for right
	 * Pre: n/a
	 * Pros: returns rectangle
	 */
	public Rectangle getBoundsRight() {
		return (new Rectangle((int) ((int)x+width-5), (int)y+5, (int)5, (int)height-10));
	}
	/**Purpose: collision box for the left
	 * Pre: n/a
	 * Pros: returns rectangle
	 */
	public Rectangle getBoundsLeft() {
		return( new Rectangle((int)x+1, (int)y+5, (int)5, (int)height-10));
	}
	
	/**Purpose: to get the health of the player
	 * Pre: n/a
	 * Pros: returns health
	 */
	public int getHealth(){
		return health;
	}
	/**Purpose: to get the max health of the player
	 * Pre: n/a
	 * Pros: returns maxHealth
	 */
	public int getMaxHealth(){
		return maxHealth;
	}

	
	
	/** Purpose: Paint the health bar of the monster onto the game window
	 * Pre: Graphics pic
	 * Pros: none
	 */
	private void healthBar (Graphics pic){
		//draw a black bar for the max health of the monster
		pic.setColor(Color.black);
		pic.fillRect ((int)x+15, (int) (y -10), (int) maxHealth*10, 10);
		
		//draw a green bar over the black bar to show remaining health
		pic.setColor(Color.green);
		pic.fillRect ((int)x+15, (int) (y -10), (int) health*10, 10);
	}
	/** Purpose:Display the score on top of the screen
	 * Pre: Graphics pic
	 * Pros: none
	 */
	private void displayScore (Graphics pic){
		pic.setColor(Color.white);
		pic.drawString("Score:" + score, (int)x+15,(int) (y -20));
	}
	
	
	
	
	
	
	
	
	
	
	private int getHighScore() throws FileNotFoundException{
		Scanner in = new Scanner(new FileReader("Scores.txt"));
		List<String> list = new ArrayList<String>();
		while(in.hasNextLine()){
		    list.add(in.nextLine());
		}

		String[] stringArr = list.toArray(new String[0]);
		int [] scores = strArrayToIntArray(stringArr);
		Arrays.sort(scores);
		return scores[scores.length-1];
		
	}
	
	public static int[] strArrayToIntArray(String[] a){
	    int[] b = new int[a.length];
	    for (int i = 0; i < a.length; i++) {
	        b[i] = Integer.parseInt(a[i]);
	    }

	    return b;
	}
	
	
}
