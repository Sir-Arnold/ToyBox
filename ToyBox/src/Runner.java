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


public class Runner extends Canvas implements Runnable
{
   public Graphics g;
   
   Options options;
   
   Setting setting;
   Window window;
   
   public static long timer = 0;
   protected boolean running = false;
   
   private Thread thread;
   
   public static void main(String[] args)
   {
      javax.swing.SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				Runner runner = new Runner();
            runner.createGUI();
            //runner.run();
			}
		});

      
      //Runner runner = new Runner();
   }
   
   public void createGUI()
   {
      window = new Window(options.WIDTH, options.HEIGHT, "Toy Box", this);
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
      setting = new Setting();
      options = new Options();
      
      
      //this.addKeyListener(new KeyInput(window));
      //this.addMouseListener(new MyAdapter(window));
      
      
      //System.out.println(Options.getWIDTH());
   }

   public void run()
   {
      boolean running = true;
      int time = 0;
      long lastTime = System.nanoTime();
      double amountOfTicks = 30.0;
      double ns = 1000000000 / amountOfTicks;
      double delta = 0; 
      long timer = System.currentTimeMillis();
      int frames = 0;
      int targetFPS = 30;
      
      while(running)
      {
         long now = System.nanoTime();
         delta += (now - lastTime) / ns;
         lastTime = now;
         
         //System.out.println(delta);
         
         while(delta >= 1)
         {
           frames += 1;
           tick(delta);
           //System.out.println(">");
           render();
           timer += 1/30;
           delta--;
         }

         if(frames >= amountOfTicks)
         {
            frames = 0;
            time += 1;
            System.out.println(time);
         }

      }
   }
   
   public void tick(double delta)
   {
      setting.tick(delta);
   }
   
   public void render()
   {
      BufferStrategy bs = this.getBufferStrategy();
      if(bs == null)
      {
         this.createBufferStrategy(3);
         return;
      }
      
      g = bs.getDrawGraphics();
      
      Graphics2D g2 = (Graphics2D) g;
      RenderingHints rh = new RenderingHints(
             RenderingHints.KEY_ANTIALIASING,
             RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setRenderingHints(rh);
      
      g.setColor(Color.black);
      g.fillRect(0,0, options.WIDTH, options.HEIGHT);
      
      g2.setColor(Color.BLUE);
      setting.render(g2);
      
      g.dispose();
      bs.show();
   }
}