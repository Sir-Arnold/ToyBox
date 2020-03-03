import java.awt.*;
import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import java.awt.image.BufferStrategy;

// The Runner is both the manager of what is happening within the program and the canvas which is painted within the window
public class Runner extends Canvas implements Runnable
{
   
   // what the program runs
   public static void main(String[] args)
   {
      javax.swing.SwingUtilities.invokeLater(new Runnable() {

			public void run()                         // run() is initially processed here but after done processing, run() is overridden by runner.run()
			{
				Runner runner = new Runner();
				
				Runner.initVelX = 1;
				Runner.initVelY = 1;
				
				
				runner.createGUI();
			}
		});
   }
   
   public void createGUI()
   {
      window = new Window(options.WIDTH, options.HEIGHT, "Toy Box", this);
      //keyInput = new KeyInput(window);
      addKeyListener(new KeyInput(window, setting));
      window.setSetting(setting);
   }
   
   public synchronized void start()
   {
      thread = new Thread(this);
      thread.start();
      running = true;
   }
   
   public synchronized void stop()
   {
      try{
         thread.join();
         running = false;
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }
   
   public Runner()
   {
      setting = new Setting(this);
      options = new Options();
      
      
      //this.addKeyListener(new KeyInput(window));
      //this.addMouseListener(new MyAdapter(window));
      
      
      //System.out.println(Options.getWIDTH());
   }

   public void run()
   {
      pause = false;
	   
	  boolean running = true;
      int time = 0;
      long passedTime = 0;
      long lastTime = System.nanoTime();
      double amountOfTicks = 60.0;                 // ticks per second - same as UPDATE_CAP
      double amountOfFrames = 60.0;                // frames per second - same as UPDATE_CAP
      double nsPerSec = 1000000000.0;              // nanoSeconds per second
      float delta = 0f;                            // explained later
      timer = 0;				   // precise timer for timing how long the program has been running, saved to the runner and can be accessed elsewhere in the program
      int frames = 0;
      
      int iterator = 0;
      
      
      while(running)
      {
         long now = System.nanoTime();                          // each time the program reaches the top of the loop again
         passedTime = now - lastTime;
         delta += ((passedTime) / nsPerSec) * UPDATE_CAP;       // each iteration adds the passed time (converted to seconds) multiplied by the update cap (60 updates / 1 second) -> dimensional analysis - each iteration adds a fraction of an update
         lastTime = now;
         
         while(delta >= 1)                                      // delta is representing the amount of updates that should be processed, where if it is more than or equal to 1 it processes an update
         {
            frames += 1;
                                                                  
            if(pause == false)                                      // (delta / UPDATE_CAP) represents the real time in seconds since last updated (tick and render)
            	tick(delta / UPDATE_CAP);                           // tells runner to tick, passing the time in seconds since last update, as of 5/19 the average value of this is 
            render();
            delta--;
            //System.out.println(frames);
         }
         
         if(frames >= amountOfTicks) 				// indicates the number of seconds that have passed since the program started
         {
        	 frames = 0;
             time += 1;
             System.out.println(">>> " + time + " <<<"); 
         }
         
         iterator++;
         //System.out.println("Iteration: " + iterator + " complete at " + timer + " seconds.");
         
      }
   }
   
   public void tick(double delta)
   {
      setting.tick(delta);
   }
   
   public void render()
   {
      BufferStrategy bs = this.getBufferStrategy();		// A buffer stragetgy is important because without a buffer, the computer won't have anything to show while it is computing what it is supposed to render
      if(bs == null)
      {
         this.createBufferStrategy(3);				// creates a buffer strategy for the Runner object and applies it to the Runner
         return;
      }
      
      g = bs.getDrawGraphics();					// Graphics g is set to the graphics of the buffer
      
      Graphics2D g2 = (Graphics2D) g;
      RenderingHints rh = new RenderingHints(                  // antialiasing makes things smooth looking
             RenderingHints.KEY_ANTIALIASING,
             RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setRenderingHints(rh);
      
      g.setColor(Color.WHITE);
      g.fillRect(0,0, options.WIDTH, options.HEIGHT);
      
      g2.setColor(Color.BLUE);
      setting.render(g2);
      
      g.dispose();
      bs.show();
   }
   
   public void resetSetting()
   {
	   this.setting = new Setting(this);
   }
   
   public Setting getSetting()
   {
	   return setting;
   }
   
   public void togglePause()
   {
	   if(pause == false)
		   pause = true;
	   else
		   pause = false;
   }
   
   public void setPause(boolean pause)
   {
	   this.pause = pause;
   }
   
   public void setInitVelX(double velX)
   {
	   initVelX = velX;
   }
   
   public void setInitVelY(double velY)
   {
	   initVelY = velY;
   }
   
   public static void check(String string) 
   {
	   System.out.println(string);
   }
   
   
   
   // FIELDS
   
   public Graphics g;
   
   Options options;
   
   Setting setting;
   Window window;
   KeyInput keyInput;
   
   public static double timer = 0;
   public static double initVelX;
   public static double initVelY;
   
   public static double UPDATE_CAP = 60.0;				// max updates per second
   
   public boolean pause;
   
   protected boolean running = false;
   
   private Thread thread;
}