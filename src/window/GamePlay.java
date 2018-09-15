package window;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import objects.Player;
import objects.Block;
import framework.GameObject;
import framework.KeyInput;
import framework.ObjectId;
import framework.Texture;
/**
 * Name: Chanchev Mahendran & & Praven Selvakamalan
 * Date: May 23, 2017
 * Purpose: The purpose of this class is to define the game object
 */
public class GamePlay extends Canvas implements Runnable, ActionListener, ItemListener {
	
	
		
		private boolean running= false;
		public static boolean gameMode = true; //easy = true. hard = false
		private Thread thread;//allows a program to start several executions at the same time
		public static int WIDTH, HEIGHT;
		//creates handler
		Handler handler;
		//creates camera
		Camera cam;
		//used to create texture class
		static Texture tex; 
		//used to make images
		private BufferedImage level=null, bg= null;
		
		Random rand= new Random();
		
		
		//GUI
		
		JLabel label, logoL; //images for gui
		JFrame frame = new JFrame("Aster Blaster"); //main menu frame
		JPanel panel= new JPanel();
		JButton start = new JButton("Start");
		JButton instr = new JButton("Instructions");
		
		JRadioButton easy= new JRadioButton("easy");
		JRadioButton hard= new JRadioButton("hard");
		ButtonGroup mode = new ButtonGroup();
		
		public GamePlay(){
			
		}
		//constructor for gameplay object
		public GamePlay(int width, int height){
			
		    //get images for the UI
			BufferedImageLoader loader = new BufferedImageLoader(); //create loader from buffered image loader
			BufferedImage login = loader.loadImage("/background.gif"); // load background for menu
			BufferedImage logo = loader.loadImage("/logo.png");
			//FRAME 1 (MAIN MENU)
	
			
			logoL = new JLabel (new ImageIcon(logo));

			start.addActionListener(this);
			instr.addActionListener(this);
			
			label = new JLabel(new ImageIcon(login));
			frame.setSize(width, height);
			
			
			//sets the background as the label
			frame.setContentPane(label);
			
			//set bounds for each UI component
			logoL.setBounds(120, 50, 400, 113);
			start.setBounds(250, 175, 150, 60);
			instr.setBounds(250, 245, 150, 60);
			//radio buttons
			mode.add(easy);
			easy.setSelected(true);
			mode.add(hard);
			
			easy.addItemListener(this);
			hard.addItemListener(this);
			
			easy.setBounds(250, 300, 70, 30);
			hard.setBounds(320, 300, 70, 30);
			
			//add components to the main menu frame
			frame.add(start);
			frame.add(instr);
			frame.add(logoL);
			
			frame.add(easy);
			frame.add(hard);
			frame.add(panel);
			frame.setVisible(true); //make the main frame visible
			
			
			
		}	
		
		/**Purpose: Called once at the start of the game to set the game up
		 * Pre: n/a
		 * Pros: no return
		 */
		private void init(){
			//dimensuions of screen
			WIDTH=getWidth();
			HEIGHT=getHeight();
			
			tex= new Texture();
			
			BufferedImageLoader loader =new BufferedImageLoader();
			level=loader.loadImage("/background.jpg");//loads level
			
			handler = new Handler();
			//adds player object to the handler linked list
			handler.addObject(new Player(100,100, handler, cam, ObjectId.Player));
			//adds the level to handler
			handler.createLevel();
			this.addKeyListener(new KeyInput(handler) );
			//sets position of camera
			cam= new Camera(0,0);
			bg=loader.loadImage("/background.jpg");
		}
		
		
		
		/**Purpose: Cused for the thread and runs the game at ticks/second
		 * Pre: n/a
		 * Pros: no return
		 */
		public void run(){
			init();
			this.requestFocus();//request resources to run
			long lastTime=System.nanoTime();
			double amountOfTicks=60.0;//amount of times it updates graphically per second
			double ns= 1000000000/amountOfTicks;
			double delta=0;
			long timer= System.currentTimeMillis();
			int updates = 0;
			int frames= 0;
			while(running){
				long now =System.nanoTime();
				delta= delta+ (now-lastTime)/ns;
				lastTime=now;
				while(delta>=1){
					tick();
					updates++;
					delta--;//updates 60 times computationally per second
				}
				render();//renders at speed of computer 
				frames++;
				
				if(System.currentTimeMillis()-timer>1000){
					timer= timer+1000;
					System.out.println("Fps:"+frames+" Ticks: "+ updates);
					frames=0;
					updates=0;
				}
			}
		}
		
		
		/**Purpose: used to start the game and prevent thread interference
		 * Pre: n/a
		 * Pros: no return
		 */
		public synchronized void start(){
			//shows that thread is initialized, for error trapping
			if(running){
				return;
			}
			running =true;
			thread=new Thread(this);
			thread.start();
		}
		/**Purpose: updates game at 60 ticks/second
		 * Pre: n/a
		 * Pros: no return
		 */
		private void tick(){
			handler.tick();
			for (int x=0; x<handler.object.size();x++){
				if (handler.object.get(x).getId()== ObjectId.Player){
					//used to move camera
					cam.tick(handler.object.get(x));

				}
			}
		}
		
		/**Purpose: draws all images and backgrounds on the screen
		 * Pre: n/a
		 * Pros: no return
		 */
		private void render(){
			//this refers to canvas class
			//buffer strategy: used to load next images
			BufferStrategy strategy =this.getBufferStrategy();//equals null because it's called so many times
			if (strategy==null){
				this.createBufferStrategy(3);//triple buffering
				return;
			}
			
			Graphics pic =strategy.getDrawGraphics();
			Graphics2D pic2D= (Graphics2D)pic;
			///
			
			pic.setColor(Color.BLACK);
			pic.fillRect(0,0,getWidth(),getHeight());
			
		
			//moves what you see
			pic2D.translate(cam.getX(), cam.getY());//begin of cam
			//draws the background
			for (int x=0;x<bg.getWidth()*3;x+=bg.getWidth()){
				
				pic.drawImage(bg,x,0,this);
			}
			handler.render(pic);//draws everything
				
			
			pic2D.translate(-cam.getX(), -cam.getY());//end of cam
			
			///
			
			pic.dispose();//gets rid of graphics
			strategy.show();
		}
		
	
		/**Purpose: texture is needed for every class, method allows for any instance to be returned
		 * Pre: n/a
		 * Pros: no return
		 */
		public static Texture getInstance(){
			return tex;
		}
		/**Purpose:creates the window for the menu
		 * Pre: n/a
		 * Pros: no return
		 */
		public static void main(String args[]){
			new GamePlay(640, 480);
		}




		/**Purpose:creates a response to buttons on the menus
		 * Pre: ActionEvent event (the event that occurs)
		 * Pros: no return
		 */
		public void actionPerformed(ActionEvent event) {
			
			if (event.getSource() == start) {
				frame.setVisible(false); //hide main menu window
				new Window(680, 480, "Aster Blaster", new GamePlay()); //create game window
			}
			if (event.getSource() == instr){
				JOptionPane.showMessageDialog(null, "The objective of the game is for the charizard to"
						+ "\n destroy as many obstacles and enemies as possible and to reach the end of the game. "
						+ "\n The player can destroy blocks by pressing the space bar which will shoot a fire ball. "
						+ "\n A window is worth 50 points, a brick is worth 100 points, a cinder block is worth 150 points"
						+ "\n  and an enemy is worth 200 points. To destroy a window one blast is required, to destroy a brick"
						+ "\n  2 blasts are required, to destroy a cinder block 3 blasts "
						+ "\n are required and to destroy an enemy 4 blasts are require. "
						+ "\n There is a bar on top of the player which is used to show the score of the player. "
						+ "\n The health bar is also shown above the player. If the player collides with an object, the health will decrease."
						+ "\n  A total of 8 hits can be made between the player and an obstacle before the player loses the game. "
						+ "\n The will not obtain a high score by just trying to reach the end of the game. "
						+ "\n The player must try to destroy as many obstacles as possible before reaching "
						+"\n  the end of the game"
						+ "\n Up Arrow Key- move player up"
						+ "\n Down Arrow Key- move player down"
						+ "\n Right Arrow Key- move player forward"
						+ "\n Left Arrow Key- move player backward"
						+ "\n SpaceBar- Shoot fireball"
						," ",JOptionPane.PLAIN_MESSAGE);
			}
		}

		/**Purpose:creates a response to radio buttons on the menus
		 * Pre: ItemEvent event (the event that occurs)
		 * Pros: no return
		 */
		public void itemStateChanged(ItemEvent event) {
			//easy setting of the game
			if (event.getItemSelectable() == easy){
				gameMode = true;
				
			}
			//hard setting of the game
			if (event.getItemSelectable() == hard){
				
				gameMode = false;
				
			}
			
		}
		
		
		
}
