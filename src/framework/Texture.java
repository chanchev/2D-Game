package framework;

import java.awt.image.BufferedImage;

import window.BufferedImageLoader;
/**
 * Name:  Praven Selvakamalan
 * Date: May 23, 2107
 * Purpose: The purpose of this class is to load to sprite sheet and load sprites into different arrays for different movements/objects
 */
public class Texture {
	//the sprite sheet of each object
	SpriteSheet ps;
	SpriteSheet bulletSheet;
	SpriteSheet brickSheet;
	SpriteSheet cynderSheet;
	SpriteSheet windowSheet;
	SpriteSheet enemySheet;
	
	//the number of sprite for each movement and 
	public BufferedImage[] player = new BufferedImage[8];
	public BufferedImage[] playerShoot= new BufferedImage[4];
	public BufferedImage[] bullet= new BufferedImage[1];
	public BufferedImage[] brick= new BufferedImage[3];
	public BufferedImage[] enemy = new BufferedImage[5];
	
	//loads the sprite sheet into a variable
	private BufferedImage playerS=null;
	private BufferedImage bulletS=null;
	private BufferedImage brickS = null;
	private BufferedImage cynderS = null;
	private BufferedImage windowS = null;
	private BufferedImage enemyS = null;
	
	public Texture(){
		
		//loads the sheet
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			//load any sprite sheets with this code
			playerS= loader.loadImage("/pokemon.png");
			bulletS= loader.loadImage("/bullet.png");
			brickS = loader.loadImage("/brick.png");
			cynderS = loader.loadImage("/cynder.png");
			windowS = loader.loadImage("/window.png");
			enemyS = loader.loadImage("/gastly.png");
		}catch (Exception e){
			e.printStackTrace();
		}
		//loads to variable
		ps= new SpriteSheet(playerS);
		bulletSheet= new SpriteSheet(bulletS);
		brickSheet = new SpriteSheet(brickS);
		windowSheet = new SpriteSheet(windowS);
		cynderSheet = new SpriteSheet(cynderS);
		enemySheet = new SpriteSheet(enemyS);
		getTextures(); 
		
	}
	
	private void getTextures(){
		//player movement
		player [0]= ps.grabImage(1,1,64,48);
		player [1]= ps.grabImage(2,1,64,48);
		player [2]= ps.grabImage(3,1,64,48);
		player [3]= ps.grabImage(4,1,64,48);
		player [4]= ps.grabImage(5,1,64,48);
		player [5]= ps.grabImage(6,1,64,48);
		player [6]= ps.grabImage(7,1,64,48);
		player [7]= ps.grabImage(8,1,64,48);
		//shoot
		playerShoot [0]= ps.grabImage(1,2,64,48);
		playerShoot [1]= ps.grabImage(2,2,64,48);
		playerShoot [2]= ps.grabImage(3,2,64,48);
		playerShoot [3]= ps.grabImage(4,2,64,48);
		//bullet
		bullet[0]=bulletSheet.grabImage(1,2,16,16);
		//brick
		brick[1] = brickSheet.grabImage(1, 1, 32, 32);
		brick[2] = cynderSheet.grabImage(1, 1, 32, 32);
		brick[0] = windowSheet.grabImage(1, 1, 32, 32);
		//enemy
		enemy[0] = enemySheet.grabImage(1, 1, 84, 64);
		enemy[1] = enemySheet.grabImage(2, 1, 84, 64);
		enemy[2] = enemySheet.grabImage(3, 1, 84, 64);
		enemy[3] = enemySheet.grabImage(4, 1, 84, 64);
		enemy[4] = enemySheet.grabImage(5, 1, 84, 64);
	}
}
