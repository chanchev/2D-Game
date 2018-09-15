package window;

import java.awt.Dimension;

import javax.swing.JFrame;

//used to create a window
/**
 * Name: Chanchev Mahendran
 * Date: May 26, 2107
 * Purpose: The purpose of this class is to cretae a window
 */
public class Window{
	//constructor of the window
	public Window(int w, int h, String title, GamePlay game){
		game.setSize(new Dimension(w,h));
		game.setMaximumSize(new Dimension(w,h));
		game.setMinimumSize(new Dimension(w,h));

		JFrame frame= new JFrame();
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setTitle(title);
		frame.setVisible(true);
		
		game.start();
}


}