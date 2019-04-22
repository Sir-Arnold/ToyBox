import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window
{
   Setting setting;
  
   public boolean menuState;   // controls set to true to start, false if in setting, reset to true when user goes back to menu
  
   public Window(int width, int height, String title)
	{
		frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
      
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
   
   }
  
  
}